package com.howard0720.ecommerce.service.Impl;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.howard0720.ecommerce.dao.ProductDao;
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
}
