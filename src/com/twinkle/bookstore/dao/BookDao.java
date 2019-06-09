package com.twinkle.bookstore.dao;

import java.util.List;

import com.twinkle.bookstore.bean.Book;

/**
 * @Name com.twinkle.bookstore.dao/BookDao.java
 * @Description: books表操作的dao接口
 *
 * @VersionInformation: Twinkle 2019年5月20日 V1.0
 */
public interface BookDao {
	/* 插入一条数据 */
	public void insert(Book book);

	/* 得到所有记录的集合 */
	public List<Book> getAll();

	/* 根据id得到对应的记录对象 */
	public Book getById(String id);

	/* 更新某条记录 */
	public void update(Book book);

	/* 删除对应的记录 */
	public void deleteById(String id);
	
	/* 实现搜索功能 */
	public Book searchBookByName(String name);

	/* 根据条件得到总记录数 */
	public long getTotalRecord(String categorysid, int minprice, int maxprice);

	/* 根据条件得到满足条件的记录的集合 */
	public List<Book> getList(String categorysid, int minprice, int maxprice, int start, int count);

	/* 根据categorysid判断有没有对应的记录 */
	public boolean hasBookByCategorysId(String categorysid);

	/* 批量更新书的数据 */
	public void batchUpdateStoreAndSale(Object[][] data);
}
