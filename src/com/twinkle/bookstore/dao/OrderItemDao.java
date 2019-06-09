package com.twinkle.bookstore.dao;

import java.util.List;

import com.twinkle.bookstore.bean.OrderItem;

/**
 * @Name com.twinkle.bookstore.dao/OrderItemDao.java
 * @Description: orderitems表操作的dao接口
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public interface OrderItemDao {

	/**
	 * 批量插入数据
	 * @param data
	 */
	public void batchInsert(Object[][] data);

	/**
	 * 得到某个订单id所对应的所有订单项的集合
	 * @param orderid
	 * @return
	 */
	public List<OrderItem> getListByOrderId(String orderid);
}

