package com.twinkle.bookstore.bean;

/**
 * @Name com.twinkle.bookstore.bean/Category.java
 * @Description: 分类表
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
public class Category {
	private String id;
	private String name;

	public Category(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category() {
		super();
	}

	public Category(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
}
