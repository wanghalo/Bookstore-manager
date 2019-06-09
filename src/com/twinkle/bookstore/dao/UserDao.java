package com.twinkle.bookstore.dao;

import com.twinkle.bookstore.bean.User;

/**
 * @Name com.twinkle.bookstore.dao/UserDao.java
 * @Description: users表操作的dao接口
 *
 * @VersionInformation: Twinkle 2019年5月20日 V1.0
 */
public interface UserDao {
	/* 根据用户名得到对应的记录对象 */
	User getByName(String username);

	/* 插入一条记录 */
	void insert(User user);

	/* 根据用户名和密码得到对应的记录对象 */
	User getByNameAndPwd(String username, String password);

	/* 根据id得到对应的记录对象 */
	User getById(String usersid);
}
