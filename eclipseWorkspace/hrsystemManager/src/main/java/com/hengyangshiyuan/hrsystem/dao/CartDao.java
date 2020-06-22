package com.hengyangshiyuan.hrsystem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hengyangshiyuan.hrsystem.bean.Cart;

@Mapper
public interface CartDao {

	public void addCart(@Param(value="cartId")String cartId, @Param(value="labelId")String labelId, @Param(value="userId")int userId);

	public int queryCountByUserId(int userId);

	public Cart queryCartByUserId(  @Param(value="userId")int userId,@Param(value="labelId")String labelId);

	public void updateCount(@Param(value="cartId")String cartId, @Param(value="count")int count);

	public List<Map<String, Object>> geMyOrders(int userId);

	public void deleteCartByCartId(String cartId);

	public void clearCart(int userId);

	public void updateCountByCartId(@Param(value="cartId")String cartId, @Param(value="count")int count);

	public void updataOrderIdAndStatusBycartId(@Param(value="cartId")String cartId, @Param(value="orderId")String orderId,
			@Param(value="status")String status);

	public int queryCountByOrderId(String orderId);
		

}
