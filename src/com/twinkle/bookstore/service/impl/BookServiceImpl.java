package com.twinkle.bookstore.service.impl;

import java.util.List;

import com.twinkle.bookstore.bean.Book;
import com.twinkle.bookstore.bean.ConditionBook;
import com.twinkle.bookstore.bean.Page;
import com.twinkle.bookstore.dao.BookDao;
import com.twinkle.bookstore.factory.BeanFactory;
import com.twinkle.bookstore.service.BookService;

/**
 * @Name com.twinkle.bookstore.service.impl/BookServiceImpl.java
 * @Description: 图书相关操作的业务接口实现类
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public class BookServiceImpl implements BookService {

	private BookDao bookDao = BeanFactory.get(BookDao.class);

	@Override
	public void addBook(Book book) {
		bookDao.insert(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAll();
	}

	@Override
	public Book getBookById(String id) {
		return bookDao.getById(id);
	}

	@Override
	public void deleteBookById(String id) {
		bookDao.deleteById(id);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.update(book);
	}

	@Override
	public Page<Book> getPage(ConditionBook conditionBook) {

		String pageNumString = conditionBook.getPageNum();
		String categorysid = conditionBook.getCategorysid();
		String minpriceString = conditionBook.getMinprice();
		String maxpriceString = conditionBook.getMaxprice();

		int pageNum = 1;
		int minprice = 0;
		int maxPrice = Integer.MAX_VALUE;

		try {
			pageNum = Integer.parseInt(pageNumString);

		} catch (Exception e) {
		}
		try {
			minprice = Integer.parseInt(minpriceString);
		} catch (Exception e) {
		}
		try {
			maxPrice = Integer.parseInt(maxpriceString);
		} catch (Exception e) {
		}

		if (categorysid == null || "".equals(categorysid.trim())) {
			categorysid = "%%";
		}

		// 查询满足条件的所有记录数
		int totalRecord = (int) bookDao.getTotalRecord(categorysid, minprice, maxPrice);

		if (totalRecord == 0) {
			return null;
		}
		int totalPageCount = (totalRecord + Page.PAGE_RECORD - 1) / Page.PAGE_RECORD;

		if (pageNum > totalPageCount) {
			pageNum = totalPageCount;
		} else if (pageNum < 1) {
			pageNum = 1;
		}

		int start = (pageNum - 1) * Page.PAGE_RECORD;

		List<Book> list = bookDao.getList(categorysid, minprice, maxPrice, start, Page.PAGE_RECORD);

		return new Page<Book>(pageNum, list, totalRecord);
	}
}
