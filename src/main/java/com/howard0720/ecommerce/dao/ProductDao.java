package com.howard0720.ecommerce.dao;

import com.howard0720.ecommerce.model.Product;

public interface ProductDao {
    Product getProductById (Integer productId);
}
