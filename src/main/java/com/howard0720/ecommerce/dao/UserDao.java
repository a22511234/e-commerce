package com.howard0720.ecommerce.dao;

import com.howard0720.ecommerce.dto.UserRegisterRequest;
import com.howard0720.ecommerce.model.User;

public interface UserDao {
    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User getUserByEmail(String email);
}
