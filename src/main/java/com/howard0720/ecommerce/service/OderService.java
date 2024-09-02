package com.howard0720.ecommerce.service;

import com.howard0720.ecommerce.dto.CreateOderRequest;
import com.howard0720.ecommerce.dto.OrdertQueryParrams;
import com.howard0720.ecommerce.model.Order;

import java.util.List;

public interface OderService {
    Integer createOder(Integer userId, CreateOderRequest createOderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrdertQueryParrams ordertQueryParrams);

    Integer countOrder(OrdertQueryParrams ordertQueryParrams);
}
