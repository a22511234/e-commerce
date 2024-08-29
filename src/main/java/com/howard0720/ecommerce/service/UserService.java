package com.howard0720.ecommerce.service;

import com.howard0720.ecommerce.dto.UserRegisterRequest;
import com.howard0720.ecommerce.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
}
