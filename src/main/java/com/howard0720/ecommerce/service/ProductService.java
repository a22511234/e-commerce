package com.howard0720.ecommerce.service;

import com.howard0720.ecommerce.model.Product;

public interface ProductService {
    Product getProductById (Integer productId);
    void deleteProductById(Integer productId);
    void insertProduct(Product product);
}
