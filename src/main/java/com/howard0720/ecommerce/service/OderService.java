package com.howard0720.ecommerce.service;

import com.howard0720.ecommerce.dto.CreateOderRequest;
import com.howard0720.ecommerce.model.Order;

public interface OderService {
    Integer createOder(Integer userId, CreateOderRequest createOderRequest);

    Order getOrderById(Integer orderId);
}
