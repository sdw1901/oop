package com.example.oop.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum {
    WAIT(0,"待接单"),
    NEW(1, "配送中"),
    FINISHED(2, "已完结"),
    CANCEL(3, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
