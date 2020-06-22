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
public class Cart implements Serializable{

	private String cartId;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String status;
	private int userId;
	// 0未发货1，表示已发货。2表示已签收
	private String commodityId;
	private int count;
}
