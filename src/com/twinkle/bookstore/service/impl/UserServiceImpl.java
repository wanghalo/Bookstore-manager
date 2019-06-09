package com.twinkle.bookstore.service.impl;

import com.twinkle.bookstore.bean.User;
import com.twinkle.bookstore.dao.UserDao;
import com.twinkle.bookstore.factory.BeanFactory;
import com.twinkle.bookstore.service.UserService;

/**
 * @Name com.twinkle.bookstore.service.impl/UserServiceImpl.java
 * @Description: 注册用户相关操作的业务接口的实现类
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao = BeanFactory.get(UserDao.class);

	@Override
	public boolean regist(User user) {
		User dbUser = userDao.getByName(user.getUsername());
		if (dbUser != null) {
			return false;
		}
		userDao.insert(user);
		return true;
	}

	public User login(String username, String password) {

		return userDao.getByNameAndPwd(username, password);
	}

	@Override
	public User getUserById(String usersid) {
		return userDao.getById(usersid);
	}

	@Override
	public boolean checkNameExist(String username) {
		User user = userDao.getByName(username);

		return user != null;
	}
}
