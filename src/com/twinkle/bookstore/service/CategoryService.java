package com.twinkle.bookstore.service;

import java.util.List;

import com.twinkle.bookstore.bean.Category;

/**
 * @Name com.twinkle.bookstore.service/CategoryService.java
 * @Description: 图书分类的相关功能操作业务接口
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public interface CategoryService {

	/**
	 * 添加一个分类
	 * 
	 * @param category
	 * @return
	 */
	boolean addCategory(Category category);

	/**
	 * 得到所有的分类
	 * 
	 * @return
	 */
	List<Category> getAllCategorys();
	
	/**
	 * 删除一个分类
	 * 
	 * @param id
	 */
	void deleteCategoryById(String id);

	/**
	 * 更新某个分类
	 * 
	 * @param category
	 */
	void updateCategory(Category category);

	/**
	 * 得到某个分类
	 * 
	 * @param id
	 * @return
	 */
	Category getCategory(String id);
}
