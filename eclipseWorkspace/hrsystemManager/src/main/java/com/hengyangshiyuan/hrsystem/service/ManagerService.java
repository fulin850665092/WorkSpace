package com.hengyangshiyuan.hrsystem.service;

import com.hengyangshiyuan.hrsystem.bean.Order;
import com.hengyangshiyuan.hrsystem.bean.Page;
import com.hengyangshiyuan.hrsystem.bean.User;

public interface ManagerService {

	String getType(int userId);

	Page<Order> page(int pageNo, int pageSize);
	public Page<User> userpage(int pageNo, int pageSize);

	String queryGongGao(String usertype);
}
