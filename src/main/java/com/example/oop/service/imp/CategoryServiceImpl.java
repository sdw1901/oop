package com.example.oop.service.imp;

import com.example.oop.form.CategoryForm;
import com.example.oop.mapper.CategoryMapper;
import com.example.oop.model.ProductCategory;
import com.example.oop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<ProductCategory> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryMapper.findByCategoryId(categoryId);
    }

    @Override
    public void save(CategoryForm form) {
        categoryMapper.save(form);
    }

    @Override
    public void update(CategoryForm form) {
        categoryMapper.update(form);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {

        return categoryMapper.findByCategoryTypeIn(categoryTypeList);
    }
}
