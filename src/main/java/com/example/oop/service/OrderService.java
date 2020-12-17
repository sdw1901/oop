package com.example.oop.service;


import com.example.oop.dto.OrderDTO;
import com.example.oop.model.OrderDetail;
import com.example.oop.model.OrderMaster;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {


    PageInfo<OrderMaster> findList(Integer page, Integer size);

    List<OrderDetail> findDetailByOrderId(String orderId);

    OrderMaster findOne(String orderId);

    void finish(String orderId);

    void cancel(String orderId, String openId);

    OrderDTO create(OrderDTO orderDTO);


    List<OrderDTO> findListByOpenId(String openid, Integer page, Integer size);

    void receive(String orderId);
}
