package com.hengyangshiyuan.hrsystem.service;

import java.util.List;
import java.util.Map;

import com.hengyangshiyuan.hrsystem.bean.Cart;

public interface CartService {

	void addCart(String cartId, String labelId, int userId);

	int queryCountByUserId(int userId);

	Cart queryCartByUserId(int userId,String labelId);

	void updateCount(String cartId,int count);

	List<Map<String, Object>> geMyOrders(int userId);

	void deleteCartByCartId(String cartId);

	void clearCart(int userId);

	void updateCountByCartId(String cartId,int count);

	void updataOrderIdAndStatusBycartId(String cartId, String orderId,
			String status);

	int queryCountByOrderId(String orderId);

}
