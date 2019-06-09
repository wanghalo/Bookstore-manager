package com.twinkle.bookstore.dao.impl;

import java.util.List;

import com.twinkle.bookstore.bean.Book;
import com.twinkle.bookstore.dao.BookDao;

/**
 * @Name com.twinkle.bookstore.dao.impl/BookDaoImpl.java
 * @Description: books表操作的dao实现类
 *
 * @VersionInformation: Twinkle 2019年5月20日 V1.0
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public void insert(Book book) {
		String sql = "insert into books values(?,?,?,?,?,?,?,?)";
		this.update(sql, book.getId(), book.getName(), book.getAuthor(), book.getPrice(),
				book.getSalesamount(), book.getStorenumber(), book.getImagepath(), book.getCategorysid());
	}

	@Override
	public List<Book> getAll() {
		String sql = "select * from books";
		return queryForList(sql);
	}

	@Override
	public Book getById(String id) {
		String sql = "select * from books where id=?";
		return query(sql, id);
	}

	@Override
	public void update(Book book) {
		String sql = "update books set name=?, author=?,price=?,categorysid=? where id=?";
		update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getCategorysid(), book.getId());
	}

	@Override
	public void deleteById(String id) {
		String sql = "SET FOREIGN_KEY_CHECKS=0;";
		update(sql);
		sql = "delete from books where id=?";
		update(sql, id);
		sql = "SET FOREIGN_KEY_CHECKS=1;";
		update(sql);
	}
	
	@Override
	public Book searchBookByName(String name) {
		String sql = "select * from books where name like '%?%'";
		return query(sql, name);
	}

	@Override
	public long getTotalRecord(String categorysid, int minprice, int maxPrice) {
		String sql = "select count(*) from books where price>=? and price<=? and categorysid like ?";

		return QueryForSingleValue(sql, minprice, maxPrice, categorysid);
	}

	@Override
	public List<Book> getList(String categorysid, int minprice, int maxPrice, int start, int count) {
		String sql = "select * from books where price>=? and price<=? and categorysid like ? limit ?,?";
		return queryForList(sql, minprice, maxPrice, categorysid, start, count);
	}

	@Override
	public boolean hasBookByCategorysId(String categorysid) {
		String sql = "select count(*) from books where categorysid=?";
		return (Long)QueryForSingleValue(sql, categorysid)>0;
	}

	@Override
	public void batchUpdateStoreAndSale(Object[][] data) {
		String sql = "update books set storenumber=storenumber-?, salesamount=salesamount+? where id=?";
		batch(sql, data);
	}
}
