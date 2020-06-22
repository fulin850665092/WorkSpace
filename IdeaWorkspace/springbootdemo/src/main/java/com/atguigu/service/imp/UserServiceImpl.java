package com.atguigu.service.imp;

import com.atguigu.bean.User;
import com.atguigu.mapper.UserDao;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(int age) {
        return userDao.getUser(age);
    }
}

