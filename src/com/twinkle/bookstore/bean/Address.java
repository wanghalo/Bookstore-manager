package com.twinkle.bookstore.bean;

/**
 * @Name com.twinkle.bookstore.bean/Address.java
 * @Description: 地址表
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
public class Address {

	private String id;
	private String name;
	private String location;
	private String cellphone;
	private String usersid;

	public Address(String id, String name, String location, String cellphone, String usersid) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.cellphone = cellphone;
		this.usersid = usersid;
	}

	public Address() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getUsersid() {
		return usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", location=" + location + ", cellphone=" + cellphone
				+ ", usersid=" + usersid + "]";
	}
}
