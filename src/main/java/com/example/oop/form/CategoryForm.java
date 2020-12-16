package com.example.oop.form;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
