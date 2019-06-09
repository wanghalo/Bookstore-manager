package com.twinkle.bookstore.dao.impl;

import java.util.List;

import com.twinkle.bookstore.bean.Category;
import com.twinkle.bookstore.dao.CategoryDao;

/**
 * @Name com.twinkle.bookstore.dao.impl/CategoryDaoImpl.java
 * @Description: categorys表操作的实现类
 *
 * @VersionInformation: Twinkle 2019年5月20日 V1.0
 */
public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao {

	@Override
	public Category getByName(String name) {
		String sql = "select * from categorys where name=?";
		return query(sql, name);
	}

	@Override
	public void insert(Category category) {
		String sql = "insert into categorys values(?,?)";
		update(sql, category.getId(), category.getName());
	}

	@Override
	public List<Category> getAll() {
		String sql = "select * from categorys";
		return queryForList(sql);
	}

	@Override
	public void update(Category category) {
		String sql = "update categorys set name=? where id=?";
		update(sql, category.getName(), category.getId());
	}

	@Override
	public void deleteById(String id) {
		String sql = "delete from categorys where id=?";
		update(sql, id);
	}

	@Override
	public Category getById(String id) {
		String sql = "select * from categorys where id=?";
		return query(sql, id);
	}

}
