package com.hengyangshiyuan.hrsystem.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 订单对象
 * 
 * @author Administrator
 * 
 */
@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true) //开启链式get/set
public class Order implements Serializable{

	private String orderId;
	private Timestamp createTime;
	private BigDecimal price;
	private int userId;
	private int status;
	// 0未发货1，表示已发货。2表示已签收
	private String address;
	private String phone;
	private String recName;
	private String email;
	private String remark;
}
