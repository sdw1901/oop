package com.example.oop.service.imp;

import com.example.oop.form.ProductForm;
import com.example.oop.mapper.ProductMapper;
import com.example.oop.model.ProductInfo;
import com.example.oop.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void update(ProductForm productForm) {
        productMapper.update(productForm);
    }

    @Override
    public void save(ProductForm productForm) {
        productMapper.save(productForm);
    }

    @Override
    public void onSale(String productId) {
        productMapper.onSale(productId);
    }

    @Override
    public void offSale(String productId) {
        productMapper.offSale(productId);
    }




//
//    @Override
//    public ProductInfo save(ProductInfo productInfo) {
//        return repository.save(productInfo);
//    }
//
//    @Override
//    @Transactional
//    public void increaseStock(List<CartDTO> cartDTOList) {
//        for (CartDTO cartDTO: cartDTOList) {
//            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
//            if (productInfo == null) {
//                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//            }
//            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
//            productInfo.setProductStock(result);
//
//            repository.save(productInfo);
//        }
//
//    }
//
//    @Override
//    @Transactional
//    public void decreaseStock(List<CartDTO> cartDTOList) {
//        for (CartDTO cartDTO: cartDTOList) {
//            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
//            if (productInfo == null) {
//                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//            }
//
//            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
//            if (result < 0) {
//                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
//            }
//
//            productInfo.setProductStock(result);
//
//            repository.save(productInfo);
//        }
//    }
//
//    @Override
//    public ProductInfo onSale(String productId) {
//        ProductInfo productInfo = repository.findOne(productId);
//        if (productInfo == null) {
//            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//        }
//        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
//            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
//        }
//
//        //更新
//        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
//        return repository.save(productInfo);
//    }
//
//    @Override
//    public ProductInfo offSale(String productId) {
//        ProductInfo productInfo = repository.findOne(productId);
//        if (productInfo == null) {
//            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//        }
//        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
//            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
//        }
//
//        //更新
//        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
//        return repository.save(productInfo);
//    }
}
