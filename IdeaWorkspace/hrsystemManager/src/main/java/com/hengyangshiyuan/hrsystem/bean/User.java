package com.hengyangshiyuan.hrsystem.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * User实体类
 * 
 * @author Administrator
 * 
 */
@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true) //开启链式get/set
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private String email;
	private String usertype;
	private String lastDate;
	private Date brithday;
	private String phone;
	private String sex;
	private String remark;
	private String gonggao;
}
