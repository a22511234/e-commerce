package com.howard0720.ecommerce.controller;

import com.howard0720.ecommerce.dto.UserLoginRequest;
import com.howard0720.ecommerce.dto.UserRegisterRequest;
import com.howard0720.ecommerce.model.Product;
import com.howard0720.ecommerce.model.User;
import com.howard0720.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){

        Integer userId = userService.register(userRegisterRequest);
        User user = userService.getUserById(userId);

        return  ResponseEntity.status(HttpStatus.CREATED).body(user);

    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Integer userId){
        User user = userService.getUserById(userId);

        if (user != null){
            return  ResponseEntity.status(HttpStatus.OK).body(user);
        }
        else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        User user = userService.login(userLoginRequest);

        return  ResponseEntity.status(HttpStatus.CREATED).body(user);

    }
}
