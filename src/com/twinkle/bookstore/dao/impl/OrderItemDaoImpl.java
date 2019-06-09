package com.twinkle.bookstore.dao.impl;

import java.util.List;

import com.twinkle.bookstore.bean.OrderItem;
import com.twinkle.bookstore.dao.OrderItemDao;

/**
 * @Name com.twinkle.bookstore.dao.impl/OrderItemDaoImpl.java
 * @Description: orderitems表操作的dao实现类
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

	@Override
	public void batchInsert(Object[][] data) {
		String sql = "insert into orderitems values(?,?,?,?,?)";
		batch(sql, data);
	}

	@Override
	public List<OrderItem> getListByOrderId(String orderid) {
		String sql = "select * from orderitems where ordersid=?";
		return queryForList(sql, orderid);
	}

}
