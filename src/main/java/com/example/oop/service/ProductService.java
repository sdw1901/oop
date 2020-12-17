package com.example.oop.service;

import com.example.oop.form.ProductForm;
import com.example.oop.model.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * 商品
 */
public interface ProductService {


    /**
     * 分页查找所有商品
     * @param page
     * @param size
     * @param categoryType
     * @return
     */
    PageInfo<ProductInfo> findAllWithPage(Integer page, Integer size, Integer categoryType);

    /**
     * 查询指定商品
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 更新
     * @param productForm
     */
    void update(ProductForm productForm);

    /**
     * 新增
     * @param productForm
     * @return
     */
    void save(ProductForm productForm);

    void onSale(String productId);

    void offSale(String productId);

    List<ProductInfo> findUpAll();

    void decreaseStock(String productId, Integer productQuantity);

    void increaseStock(String productId, Integer productQuantity);
}
