package com.twinkle.bookstore.service;

import com.twinkle.bookstore.bean.User;

/**
 * @Name com.twinkle.bookstore.service/UserService.java
 * @Description: 注册用户相关操作的业务接口
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public interface UserService {
	/* 注册 */
	boolean regist(User user);

	/* 登录 */
	User login(String username, String password);

	/* 根据id得到用户对象 */
	User getUserById(String usersid);

	/* 检查用户名是否已存在 */
	boolean checkNameExist(String username);
}
