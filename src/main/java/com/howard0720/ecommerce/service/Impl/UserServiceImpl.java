package com.howard0720.ecommerce.service.Impl;

import com.howard0720.ecommerce.dao.UserDao;
import com.howard0720.ecommerce.dto.UserRegisterRequest;
import com.howard0720.ecommerce.model.User;
import com.howard0720.ecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user!=null){
            log.warn("該email {} 已經被 {} 註冊了！",userRegisterRequest.getEmail(),"Howard");
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return userDao.register(userRegisterRequest);

    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
