package com.twinkle.bookstore.bean;

import com.twinkle.bookstore.bean.Book;

/**
 * @Name com.twinkle.bookstore.bean/CartItem.java
 * @Description: 购物项信息封装
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
public class CartItem {
	private Book book;//书
	private int count;//数

	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}

	public CartItem() {
		super();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getItemPrice() {

		return book.getPrice() * count;
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
}
