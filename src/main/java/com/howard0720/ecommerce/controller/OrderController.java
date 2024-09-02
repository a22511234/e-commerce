package com.howard0720.ecommerce.controller;

import com.howard0720.ecommerce.dto.CreateOderRequest;
import com.howard0720.ecommerce.dto.OrdertQueryParrams;
import com.howard0720.ecommerce.model.Order;
import com.howard0720.ecommerce.service.OderService;
import com.howard0720.ecommerce.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OderService oderService;

    @PostMapping("users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                                         @RequestBody @Valid CreateOderRequest createOderRequest){
        Integer orderId=oderService.createOder(userId,createOderRequest);
        Order order = oderService.getOrderById(orderId);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    @GetMapping("users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(@PathVariable Integer userId,
                                                 @RequestParam(defaultValue = "10")@Max(1000)@Min(0) Integer limit,
                                                 @RequestParam(defaultValue = "0")@Min(0) Integer offset){
        OrdertQueryParrams ordertQueryParrams = new OrdertQueryParrams();
        ordertQueryParrams.setUserId(userId);
        ordertQueryParrams.setOffset(offset);
        ordertQueryParrams.setLimit(limit);

        List<Order> orderList = oderService.getOrders(ordertQueryParrams);
        Integer count = oderService.countOrder(ordertQueryParrams);

        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}
