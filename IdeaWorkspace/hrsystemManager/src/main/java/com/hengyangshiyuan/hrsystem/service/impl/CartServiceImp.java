package com.hengyangshiyuan.hrsystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hengyangshiyuan.hrsystem.bean.Cart;
import com.hengyangshiyuan.hrsystem.dao.CartDao;
import com.hengyangshiyuan.hrsystem.service.CartService;

@Service
public class CartServiceImp implements CartService{
	
	@Autowired
	private CartDao cartDao;
	@Override
	public void addCart(String cartId, String labelId, int userId) {
		// TODO Auto-generated method stub
		 cartDao.addCart(cartId,labelId,userId);
	}
	@Override
	public int queryCountByUserId(int userId) {
		// TODO Auto-generated method stub
		return cartDao.queryCountByUserId(userId);
	}
	@Override
	public Cart queryCartByUserId(int userId ,String labelId) {
		// TODO Auto-generated method stub
		return cartDao.queryCartByUserId(userId,labelId);
	}
	@Override
	public void updateCount(String cartId,int count) {
		cartDao.updateCount(cartId,count);
	}
	@Override
	public List<Map<String, Object>> geMyOrders(int userId) {
		// TODO Auto-generated method stub
		return cartDao.geMyOrders(userId);
	}
	@Override
	public void deleteCartByCartId(String cartId) {
		// TODO Auto-generated method stub
		cartDao.deleteCartByCartId(cartId);
	}
	@Override
	public void clearCart(int  userId) {
		// TODO Auto-generated method stub
		cartDao.clearCart(userId);
	}
	@Override
	public void updateCountByCartId(String cartId,int count) {
		// TODO Auto-generated method stub
		cartDao.updateCountByCartId(cartId,count);
	}
	@Override
	public void updataOrderIdAndStatusBycartId(String cartId, String orderId,String status) {
		// TODO Auto-generated method stub
		cartDao.updataOrderIdAndStatusBycartId(cartId,orderId,status);
	}
	@Override
	public int queryCountByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return cartDao.queryCountByOrderId(orderId);
	}

}
