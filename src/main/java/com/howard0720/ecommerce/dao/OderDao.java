package com.howard0720.ecommerce.dao;

import com.howard0720.ecommerce.model.OrderItem;

import java.util.List;

public interface OderDao {

    Integer creteOder(Integer userId, Integer totalAmount);
    void createOderItems(Integer oderId, List<OrderItem> orderItemdList);
}
