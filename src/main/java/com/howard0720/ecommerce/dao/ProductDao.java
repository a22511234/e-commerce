package com.howard0720.ecommerce.dao;

import com.howard0720.ecommerce.dto.ProductRequest;
import com.howard0720.ecommerce.model.Product;

public interface ProductDao {
    Product getProductById (Integer productId);
   void deleteProductById(Integer studentId);
   Integer createProduct(ProductRequest productRequest);
   void updateProduct(Product product);
}
