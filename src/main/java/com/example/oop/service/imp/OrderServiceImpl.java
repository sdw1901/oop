package com.example.oop.service.imp;

import com.example.oop.dto.OrderDTO;
import com.example.oop.enums.OrderStatusEnum;
import com.example.oop.enums.PayStatusEnum;
import com.example.oop.enums.ResultEnum;
import com.example.oop.exception.SellException;
import com.example.oop.mapper.OrderMapper;
import com.example.oop.model.OrderDetail;
import com.example.oop.model.OrderMaster;
import com.example.oop.model.ProductInfo;
import com.example.oop.service.OrderService;
import com.example.oop.service.ProductService;
import com.example.oop.service.WebSocket;
import com.example.oop.utils.KeyUtil;
import com.example.oop.utils.OrderMaster2OrderDTOConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private WebSocket webSocket;


    @Override
    public PageInfo<OrderMaster> findList(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<OrderMaster> list = orderMapper.findAll();
        PageInfo<OrderMaster> orderMasterPageInfo = new PageInfo<>(list);
        return orderMasterPageInfo;
    }

    @Override
    public List<OrderDetail> findDetailByOrderId(String orderId) {
        return orderMapper.findDetailByOrderId(orderId);
    }

    @Override
    public OrderMaster findOne(String orderId) {
        return orderMapper.findOne(orderId);
    }

    @Override
    @Transactional
    public void finish(String orderId) {
        OrderMaster order = orderMapper.findOne(orderId);
        if (OrderStatusEnum.NEW.getCode() != order.getOrderStatus()) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", order.getOrderId(), order.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderMapper.finish(orderId);
    }

    @Override
    @Transactional
    public void cancel(String orderId, String openId) {
        OrderMaster orderMaster = orderMapper.findOne(orderId);

        //卖家可在配送中取消订单,买家只能在未接单时候取消订单,买家如果想在配送中取消订单需要联系卖家取消
        if (StringUtils.isEmpty(openId)) {
            if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.WAIT.getCode())&&
                    !orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
                log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderId, orderMaster.getOrderStatus());
                throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
            }
        }else {
            if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.WAIT.getCode())) {
                log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderId, orderMaster.getOrderStatus());
                throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
            }
        }

        //修改订单状态
        orderMapper.cancel(orderId);
        //返回库存
        synchronized (this) {
            List<OrderDetail> orderDetailList = orderMapper.findDetailByOrderId(orderId);
            for (OrderDetail orderDetail : orderDetailList) {
                productService.increaseStock(orderDetail.getOrderId(), orderDetail.getProductQuantity());
            }
        }
    }

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1. 查询商品（数量, 价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2. 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderMapper.saveDetail(orderDetail);
            //减库存
            productService.decreaseStock(orderDetail.getProductId(), orderDetail.getProductQuantity());

        }
        //3. 写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.WAIT.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMapper.saveMaster(orderMaster);
        //发送websocket消息
        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findListByOpenId(String openid, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<OrderMaster> orderMasterList = orderMapper.findByOpenId(openid);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterList);
        String pName = "";
        for (OrderDTO orderDTO : orderDTOList) {
            List<OrderDetail> orderDetailList = orderMapper.findDetailByOrderId(orderDTO.getOrderId());
            for (int i = 0;i<orderDetailList.size();i++) {
                if (i < 1) {
                    pName += "+" + orderDetailList.get(i).getProductName();
                }
            }
            orderDTO.setOrderDetailList(orderDetailList);

        }
        return orderDTOList;
    }

    @Override
    @Transactional
    public void receive(String orderId) {
        orderMapper.receive(orderId);
    }


}
