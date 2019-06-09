package com.twinkle.bookstore.dao.impl;

import java.util.List;

import com.twinkle.bookstore.bean.Order;
import com.twinkle.bookstore.dao.OrderDao;

/**
 * @Name com.twinkle.bookstore.dao.impl/OrderDaoImpl.java
 * @Description: 操作orders表的dao实现类
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

	@Override
	public long getTodayCount() {
		String sql = "select count(*) from orders where ordertime>CURRENT_DATE()";
		return QueryForSingleValue(sql);
	}

	@Override
	public void insert(Order order) {
		String sql = "insert into orders(id,number,price,usersid,addressid) values(?,?,?,?,?)";
		update(sql, order.getId(), order.getNumber(), order.getPrice(), order.getUsersid(),order.getAddressid());
	}

	@Override
	public List<Order> getListByUserId(String userId) {
		String sql = "select * from orders where usersid=?";
		return queryForList(sql, userId);
	}

	@Override
	public List<Order> getListByStatus(boolean status) {
		String sql = "select * from orders where status=?";
		return queryForList(sql, status);
	}

	@Override
	public void updateStatus(String orderid) {
		String sql = "update orders set status=? where id=?";
		update(sql, true, orderid);
	}
}
