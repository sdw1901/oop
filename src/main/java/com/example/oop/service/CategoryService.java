package com.example.oop.service;
import com.example.oop.form.CategoryForm;
import com.example.oop.model.ProductCategory;
import java.util.List;

public interface CategoryService {

    List<ProductCategory> findAll();

    ProductCategory findOne(Integer categoryId);

    void save(CategoryForm form);

    void update(CategoryForm form);
}
