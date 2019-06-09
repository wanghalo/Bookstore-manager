package com.twinkle.bookstore.bean;

import java.util.Date;

/**
 * @Name com.twinkle.bookstore.bean/Order.java
 * @Description: 订单表
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
public class Order {

	private String id;
	private String number;
	private boolean status;
	private float price;
	private Date ordertime;
	private String usersid;
	private String addressid;

	public Order(String id, String number, boolean status, float price,
			Date ordertime, String usersid, String addressid) {
		super();
		this.id = id;
		this.number = number;
		this.status = status;
		this.price = price;
		this.ordertime = ordertime;
		this.usersid = usersid;
		this.addressid = addressid;
	}

	public Order() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public String getUsersid() {
		return usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", number=" + number + ", status=" + status
				+ ", price=" + price + ", ordertime=" + ordertime
				+ ", usersid=" + usersid + ", addressid=" + addressid + "]";
	}

}
