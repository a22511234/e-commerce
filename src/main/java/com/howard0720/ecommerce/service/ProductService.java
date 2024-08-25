package com.howard0720.ecommerce.service;

import com.howard0720.ecommerce.dto.ProductRequest;
import com.howard0720.ecommerce.model.Product;

public interface ProductService {
    Product getProductById (Integer productId);
    void deleteProductById(Integer productId);
    Integer createProduct(ProductRequest product);
}
