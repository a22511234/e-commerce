package com.howard0720.ecommerce.service.Impl;

import com.howard0720.ecommerce.dao.ProductDao;
import com.howard0720.ecommerce.dto.ProductRequest;
import com.howard0720.ecommerce.model.Product;
import com.howard0720.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductById(productId);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId,productRequest);
    }
}
