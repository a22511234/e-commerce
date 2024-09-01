package com.howard0720.ecommerce.dao;

import com.howard0720.ecommerce.constant.ProductCategory;
import com.howard0720.ecommerce.dto.ProductQueryParrams;
import com.howard0720.ecommerce.dto.ProductRequest;
import com.howard0720.ecommerce.model.Product;

import java.util.List;

public interface ProductDao {
    Product getProductById (Integer productId);
   void deleteProductById(Integer studentId);
   Integer createProduct(ProductRequest productRequest);
   void updateProduct(Integer productId, ProductRequest product);
    List<Product> getProducts(ProductQueryParrams productQueryParrams);
    Integer countProducts(ProductQueryParrams productQueryParrams);
    void updateStock(Integer productId, int i);
}
