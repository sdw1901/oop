package com.example.oop.service;

import com.example.oop.form.ProductForm;
import com.example.oop.model.ProductInfo;
import com.github.pagehelper.PageInfo;



/**
 * 商品
 */
public interface ProductService {

//
//    /**
//     * 查询所有在架商品列表
//     * @return
//     */
//    List<ProductInfo> findUpAllWithPage();
//

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
//
//    //加库存
//    void increaseStock(List<CartDTO> cartDTOList);
//
//    //减库存
//    void decreaseStock(List<CartDTO> cartDTOList);
//
//    //上架
//    ProductInfo onSale(String productId);
//
//    //下架
//    ProductInfo offSale(String productId);
}
