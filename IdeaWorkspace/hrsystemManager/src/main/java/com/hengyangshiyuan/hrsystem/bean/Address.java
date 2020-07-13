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
public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String addressStr;
	private String phone;
	private int addressId;
	private String recname;
	// 0未发货1，表示已发货。2表示已签收
	private String email;
	private String type;
	private String zipcode;
	private int id;
}
