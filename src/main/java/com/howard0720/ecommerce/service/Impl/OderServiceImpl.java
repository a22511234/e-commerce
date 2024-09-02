package com.howard0720.ecommerce.service.Impl;

import com.howard0720.ecommerce.dao.OrderDao;
import com.howard0720.ecommerce.dao.ProductDao;
import com.howard0720.ecommerce.dao.UserDao;
import com.howard0720.ecommerce.dto.BuyItem;
import com.howard0720.ecommerce.dto.CreateOderRequest;
import com.howard0720.ecommerce.dto.OrdertQueryParrams;
import com.howard0720.ecommerce.model.Order;
import com.howard0720.ecommerce.model.OrderItem;
import com.howard0720.ecommerce.model.Product;
import com.howard0720.ecommerce.model.User;
import com.howard0720.ecommerce.service.OderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OderServiceImpl implements OderService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public Integer createOder(Integer userId, CreateOderRequest createOderRequest) {
        User user = userDao.getUserById(userId);
        if(user == null){
            log.warn("該 userId {} 不存在",userId);
            throw  new ResponseStatusException((HttpStatus.BAD_REQUEST));
        }

        int totalAmount = 0;
        List<OrderItem> orderItemdList =new ArrayList<>();
        for (BuyItem buyItem : createOderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            if(product == null){
                log.warn("商品 {} 不存在",buyItem.getProductId());
                throw  new ResponseStatusException((HttpStatus.BAD_REQUEST));
            } else if (product.getStock()<buyItem.getQuentity()) {
                log.warn("商品 {} 庫存數量不足，無法購買。剩餘庫存 {} ，欲購買數量為 {}",buyItem.getProductId(),product.getStock(),buyItem.getQuentity());
                throw  new ResponseStatusException((HttpStatus.BAD_REQUEST));
            }
            productDao.updateStock(product.getProductId(),product.getStock()- buyItem.getQuentity());

            int amount = buyItem.getQuentity() * product.getPrice();
            totalAmount += amount;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuentity());
            orderItem.setAmount(amount);
            orderItemdList.add(orderItem);
        }

        Integer orderId = orderDao.creteOder(userId, (Integer)totalAmount);
        orderDao.createOderItems(orderId, orderItemdList);
        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);
        order.setOrderItemList(orderItemList);
        return order;
    }

    @Override
    public List<Order> getOrders(OrdertQueryParrams ordertQueryParrams) {
        List<Order> orderList =orderDao.getOrders(ordertQueryParrams);
        for (Order order:orderList){
            List<OrderItem> orderItemList=orderDao.getOrderItemsByOrderId(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }
        return orderList;

    }

    @Override
    public Integer countOrder(OrdertQueryParrams ordertQueryParrams) {
        return orderDao.countOrder(ordertQueryParrams);
    }
}
