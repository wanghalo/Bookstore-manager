package com.twinkle.bookstore.dao;

import java.util.List;

import com.twinkle.bookstore.bean.Category;

/**
 * @Name com.twinkle.bookstore.dao/CategoryDao.java
 * @Description: categorys表数据操作的dao接口
 *
 * @VersionInformation: Twinkle 2019年5月20日 V1.0
 */
public interface CategoryDao {

	/**
	 * 根据名称得到对应的分类对象
	 * @param name
	 * @return
	 */
	Category getByName(String name);

	/**
	 * 插入一条数据
	 * @param category
	 */
	void insert(Category category);

	/**
	 * 得到所有记录的集合
	 * @return
	 */
	List<Category> getAll();

	/**
	 * 更新某个分类
	 * @param category
	 */
	void update(Category category);
	
	/**
	 * 删除某条记录
	 * @param id
	 */
	void deleteById(String id);

	/**
	 * 根据id得到对应的分类对象
	 * @param id
	 * @return
	 */
	Category getById(String id);
}
