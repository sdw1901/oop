package com.example.oop.mapper;

import com.example.oop.form.CategoryForm;
import com.example.oop.model.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<ProductCategory> findAll();

    ProductCategory findByCategoryId(Integer categoryId);

    void save(CategoryForm form);

    void update(CategoryForm form);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
