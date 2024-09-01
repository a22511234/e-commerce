package com.howard0720.ecommerce.controller;

import com.howard0720.ecommerce.dto.CreateOderRequest;
import com.howard0720.ecommerce.service.OderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OderController {

    @Autowired
    private OderService oderService;

    @PostMapping("users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                                         @RequestBody @Valid CreateOderRequest createOderRequest){
        Integer orderId=oderService.creteOder(userId,createOderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }
}
