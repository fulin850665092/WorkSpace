package com.hengyangshiyuan.hrsystem.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true) //开启链式get/set
public class  Commodity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String specification;
	private String name;
	private double price;
	private double marketPrice;
	private String depiction;
	private String water;
	private String imgPath;
	private	String typeId;
	private String display;
	private String labelId;
	private String accessory;
	private int count;
	private String remark;
	

}
