package com.example.oop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 类目
 */
@Data
public class ProductCategory {

    /**
     * 类目id.
     */
    private Integer categoryId;

    /**
     * 类目名字.
     */
    private String categoryName;

    /**
     * 类目编号.
     */
    private Integer categoryType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
