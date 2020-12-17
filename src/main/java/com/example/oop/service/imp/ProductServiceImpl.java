package com.example.oop.service.imp;

import com.example.oop.enums.ResultEnum;
import com.example.oop.exception.SellException;
import com.example.oop.form.ProductForm;
import com.example.oop.mapper.ProductMapper;
import com.example.oop.model.ProductInfo;
import com.example.oop.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo<ProductInfo> findAllWithPage(Integer page, Integer size, Integer categoryType) {
        PageHelper.startPage(page,size);
        List<ProductInfo> list = productMapper.findAll(categoryType);
        PageInfo<ProductInfo> productInfoPageInfo = new PageInfo<>(list);
        return productInfoPageInfo;
    }

    @Override
    public ProductInfo findOne(String productId) {
        return productMapper.findOne(productId);
    }

    @Override
    @Transactional
    public void update(ProductForm productForm) {
        productMapper.update(productForm);
    }

    @Override
    @Transactional
    public void save(ProductForm productForm) {
        productMapper.save(productForm);
    }

    @Override
    @Transactional
    public void onSale(String productId) {
        productMapper.onSale(productId);
    }

    @Override
    @Transactional
    public void offSale(String productId) {
        productMapper.offSale(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productMapper.findUpAll();
    }

    /**
     * @param productId
     * @param productQuantity
     */
    @Override
    @Transactional
    public void decreaseStock(String productId, Integer productQuantity) {
        //加入forupdate悲观锁防止并发减库存问题
        ProductInfo productInfo = productMapper.findOneForUpdate(productId);
        //判断商品是否存在
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //判断库存是否够
        int result = productInfo.getProductStock() - productQuantity;
        if (result < 0) {
            throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
        }
        productMapper.decreaseStock(productId,productQuantity);
    }

    @Override
    @Transactional
    public void increaseStock(String orderId, Integer productQuantity) {
        productMapper.increaseStock(orderId,productQuantity);
    }


}
