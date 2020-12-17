package com.example.oop.mapper;

import com.example.oop.form.ProductForm;
import com.example.oop.model.ProductInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ProductInfo> findAll(Integer categoryType);

    ProductInfo findOne(String productId);

    void save(ProductForm productForm);

    void update(ProductForm productForm);

    void offSale(String productId);

    void onSale(String productId);

    List<ProductInfo> findUpAll();

    void decreaseStock(String productId, Integer productQuantity);

    ProductInfo findOneForUpdate(String productId);

    void increaseStock(String productId, Integer productQuantity);
}
