package com.howard0720.ecommerce.service.Impl;

import com.howard0720.ecommerce.dao.OderDao;
import com.howard0720.ecommerce.dao.ProductDao;
import com.howard0720.ecommerce.dto.BuyItem;
import com.howard0720.ecommerce.dto.CreateOderRequest;
import com.howard0720.ecommerce.model.OrderItem;
import com.howard0720.ecommerce.model.Product;
import com.howard0720.ecommerce.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OderServiceImpl implements OderService {
    @Autowired
    private OderDao oderDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer creteOder(Integer userId, CreateOderRequest createOderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemdList =new ArrayList<>();
        for (BuyItem buyItem : createOderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            int amount = buyItem.getQuentity() * product.getPrice();
            totalAmount += amount;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuentity());
            orderItem.setAmount(amount);
            orderItemdList.add(orderItem);
        }

        Integer orderId = oderDao.creteOder(userId, (Integer)totalAmount);
        oderDao.createOderItems(orderId, orderItemdList);
        return orderId;
    }
}
