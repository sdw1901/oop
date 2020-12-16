package com.example.oop.service.imp;

import com.example.oop.enums.OrderStatusEnum;
import com.example.oop.enums.ResultEnum;
import com.example.oop.exception.SellException;
import com.example.oop.mapper.OrderMapper;
import com.example.oop.model.OrderDetail;
import com.example.oop.model.OrderMaster;
import com.example.oop.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public PageInfo<OrderMaster> findList(Integer page, Integer size) {
        PageHelper.startPage(page,size);
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
    public void cancel(String orderId) {

    }
}
