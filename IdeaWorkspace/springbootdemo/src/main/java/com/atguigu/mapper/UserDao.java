package com.atguigu.mapper;

import com.atguigu.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User getUser(int age);
}
