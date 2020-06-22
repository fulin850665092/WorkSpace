package com.hengyangshiyuan.hrsystem.service.impl;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hengyangshiyuan.hrsystem.bean.Order;
import com.hengyangshiyuan.hrsystem.dao.OrderDao;
import com.hengyangshiyuan.hrsystem.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

//	@Override
//	public void creatOrder(Order order) {
//		// TODO Auto-generated method stub
//		payDao.creatOrder(order);
//	}

	@Override
	public void creatOrder(String email, String mobile, String address,
			BigDecimal price, int userId, String orderId, String recName) {
		// TODO Auto-generated method stub
		orderDao.creatOrder(email,mobile,address,price,userId,orderId,recName);
	}

	@Override
	public int queryCountOrderByUserId(int userId) {
		// TODO Auto-generated method stub
		return orderDao.queryCountOrderByUserId(userId);
	}

	@Override
	public List<Order> queryOrderListByUserId(int userId) {
		// TODO Auto-generated method stub
		return orderDao.queryOrderListByUserId(userId);
	}

	@Override
	public List<Order> queryOrderList() {
		// TODO Auto-generated method stub
		return orderDao.queryOrderList();
	}

	@Override
	public int queryForPageTotalCount() {
		// TODO Auto-generated method stub
		return orderDao.queryForPageTotalCount();
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao.updateOrder(order);
	}

}
