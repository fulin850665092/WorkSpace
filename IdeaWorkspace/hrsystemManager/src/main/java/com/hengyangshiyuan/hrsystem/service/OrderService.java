package com.hengyangshiyuan.hrsystem.service;

import java.math.BigDecimal;
import java.util.List;

import com.hengyangshiyuan.hrsystem.bean.Order;

public interface OrderService {

//	void creatOrder(Order order);

	void creatOrder(String email, String mobile, String address,
			BigDecimal price, int userId, String orderId, String recName);
	int queryCountOrderByUserId(int userId);
	List<Order> queryOrderListByUserId(int userId);
	List<Order> queryOrderList();
	int queryForPageTotalCount();
	void updateOrder(Order order);


}
