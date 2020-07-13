package com.hengyangshiyuan.hrsystem.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true) //开启链式get/set
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private BigDecimal price;
	private int count;
	private BigDecimal totalPrice;
	private String specification;

	public CartItem(int id, String name, double price, int count,
			double totalPrice) {
		super();
		this.id = id;
		this.name = name;
		this.price = new BigDecimal(price + "");
		this.count = count;
		this.totalPrice = new BigDecimal(totalPrice + "");
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", name=" + name + ", price=" + price
				+ ", count=" + count + ", totalPrice=" + totalPrice + "]";
	}

}
