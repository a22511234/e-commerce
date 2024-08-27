package com.howard0720.ecommerce.service;

import com.howard0720.ecommerce.constant.ProductCategory;
import com.howard0720.ecommerce.dto.ProductQueryParrams;
import com.howard0720.ecommerce.dto.ProductRequest;
import com.howard0720.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById (Integer productId);
    void deleteProductById(Integer productId);
    Integer createProduct(ProductRequest product);
    void updateProduct(Integer productId,ProductRequest product);
    List<Product> getProducts(ProductQueryParrams productQueryParrams);
    Integer countProducts(ProductQueryParrams productQueryParrams);
}
