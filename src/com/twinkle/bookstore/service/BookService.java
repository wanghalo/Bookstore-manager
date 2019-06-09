package com.twinkle.bookstore.service;

import java.util.List;

import com.twinkle.bookstore.bean.Book;
import com.twinkle.bookstore.bean.ConditionBook;
import com.twinkle.bookstore.bean.Page;

/**
 * @Name com.twinkle.bookstore.service/BookService.java
 * @Description: 图书相关操作的业务接口
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public interface BookService {

	/* 添加一本书 */
	public void addBook(Book book);

	/* 得到所有的书 */
	public List<Book> getAllBooks();

	/* 得到对应的一本书 */
	public Book getBookById(String id);

	/* 删除对应的一本书 */
	public void deleteBookById(String id);

	/* 更新对应的一本书 */
	public void updateBook(Book book);

	/* 得到分页对象 */
	public Page<Book> getPage(ConditionBook conditionBook);
}
