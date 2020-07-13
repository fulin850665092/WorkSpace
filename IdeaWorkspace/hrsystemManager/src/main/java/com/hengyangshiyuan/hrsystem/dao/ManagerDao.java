package com.hengyangshiyuan.hrsystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hengyangshiyuan.hrsystem.bean.Order;
import com.hengyangshiyuan.hrsystem.bean.User;

@Mapper
public interface ManagerDao {

	String getType(int userId);
	public List<Order> queryForPageItems(@Param(value="begin")int begin, @Param(value="pageSize")int pageSize);
	public List<User> queryUserForPageItems(@Param(value="begin")int begin, @Param(value="pageSize")int pageSize);
	String queryGongGao(String usertype);

}
