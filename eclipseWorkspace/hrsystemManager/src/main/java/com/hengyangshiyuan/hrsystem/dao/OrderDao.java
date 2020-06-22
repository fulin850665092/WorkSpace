package com.hengyangshiyuan.hrsystem.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hengyangshiyuan.hrsystem.bean.Order;

@Mapper
public interface OrderDao {

//	void creatOrder(Order order);

	void creatOrder(@Param("email")String email,@Param("mobile") String mobile,@Param("address") String address,
			@Param("price")BigDecimal price, @Param("userId")int userId, @Param("orderId")String orderId, @Param("recName")String recName);

	int queryCountOrderByUserId(int userId);

	List<Order> queryOrderListByUserId(int userId);

	List<Order> queryOrderList();

	int queryForPageTotalCount();

	void updateOrder(Order order);


}
