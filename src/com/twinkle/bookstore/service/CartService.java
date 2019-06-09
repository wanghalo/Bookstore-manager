package com.twinkle.bookstore.service;

import com.twinkle.bookstore.bean.Book;
import com.twinkle.bookstore.bean.Cart;

/**
 * @Name com.twinkle.bookstore.service/CartService.java
 * @Description: 购物车相关功能业务接口
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public interface CartService {
	
	/* 添加一本书到购物车 */
	String addBook(Cart cart, Book book);

	/* 删除一个购物项 */
	void deleteItem(Cart cart, String bookid);

	/* 更新某个购物项 */
	String updateItem(Cart cart, String bookid, int count);

	/* 清空购物车 */
	void clear(Cart cart);
}
