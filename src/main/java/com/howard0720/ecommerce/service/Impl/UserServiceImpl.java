package com.howard0720.ecommerce.service.Impl;

import com.howard0720.ecommerce.dao.UserDao;
import com.howard0720.ecommerce.dto.UserLoginRequest;
import com.howard0720.ecommerce.dto.UserRegisterRequest;
import com.howard0720.ecommerce.model.User;
import com.howard0720.ecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
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

        //MD5
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        return userDao.register(userRegisterRequest);

    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //MD5
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
        userLoginRequest.setPassword(hashedPassword);

        if(user==null){
            log.warn("該email {} 尚未被註冊了！",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (user.getPassword().equals(userLoginRequest.getPassword())) {
            return user;
        }
        else {
            log.warn("該email {} 密碼不正確！",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
