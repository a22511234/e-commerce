package com.howard0720.ecommerce.dao;

import com.howard0720.ecommerce.model.Order;
import com.howard0720.ecommerce.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer creteOder(Integer userId, Integer totalAmount);
    void createOderItems(Integer oderId, List<OrderItem> orderItemdList);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
}
