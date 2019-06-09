package com.twinkle.bookstore.dao;

import java.util.List;

import com.twinkle.bookstore.bean.Order;

/**
 * @Name com.twinkle.bookstore.dao/OrderDao.java
 * @Description: 操作orders表的dao接口
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public interface OrderDao {

	/**
	 * 得到今天添加的记录数
	 * @return
	 */
	long getTodayCount();

	/**
	 * 插入一条记录
	 * @param order
	 */
	void insert(Order order);

	/**
	 * 根据用户id得到对应的所有记录的集合
	 * @param userId
	 * @return
	 */
	List<Order> getListByUserId(String userId);

	/**
	 * 根据订单状态得到所有对应的订单的集合
	 * @param status
	 * @return
	 */
	List<Order> getListByStatus(boolean status);

	/**
	 * 更新某个订单的状态
	 * @param orderid
	 */
	void updateStatus(String orderid);
}
