package com.twinkle.bookstore.service.impl;

import java.util.List;

import com.twinkle.bookstore.bean.Category;
import com.twinkle.bookstore.dao.BookDao;
import com.twinkle.bookstore.dao.CategoryDao;
import com.twinkle.bookstore.dao.impl.BookDaoImpl;
import com.twinkle.bookstore.factory.BeanFactory;
import com.twinkle.bookstore.service.CategoryService;

/**
 * @Name com.twinkle.bookstore.service.impl/CategoryServiceImpl.java
 * @Description: 图书分类的相关功能操作业务接口的实现类
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
@SuppressWarnings("unused")
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = BeanFactory.get(CategoryDao.class);

	@Override
	public boolean addCategory(Category category) {
		boolean success = false;
		Category dbCategory = categoryDao.getByName(category.getName());
		if (dbCategory == null) {
			categoryDao.insert(category);
			success = true;
		}
		return success;
	}

	@Override
	public List<Category> getAllCategorys() {

		return categoryDao.getAll();
	}
	
	@Override
	public void deleteCategoryById(String id) {
		categoryDao.deleteById(id);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.update(category);
	}

	@Override
	public Category getCategory(String id) {
		return categoryDao.getById(id);
	}
}
