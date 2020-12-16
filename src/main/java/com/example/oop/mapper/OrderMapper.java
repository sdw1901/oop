package com.example.oop.mapper;

import com.example.oop.model.OrderDetail;
import com.example.oop.model.OrderMaster;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<OrderMaster> findAll();

    List<OrderDetail> findDetailByOrderId(String orderId);

    OrderMaster findOne(String orderId);

    void finish(String orderId);
}
