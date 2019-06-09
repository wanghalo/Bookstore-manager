package com.twinkle.bookstore.dao.impl;

import java.util.List;

import com.twinkle.bookstore.bean.Address;
import com.twinkle.bookstore.dao.AddressDao;

/**
 * @Name com.twinkle.bookstore.dao.impl/AddressDaoImpl.java
 * @Description: Address表操作的dao实现类
 *
 * @VersionInformation: Twinkle 2019年5月20日 V1.0
 */
public class AddressDaoImpl extends BaseDao<Address> implements AddressDao {

	@Override
	public void insert(Address address) {
		String sql = "insert into address values(?,?,?,?,?)";
		update(sql, address.getId(), address.getName(), address.getLocation(), address.getCellphone(),
				address.getUsersid());
	}

	@Override
	public List<Address> getListByUserId(String userid) {
		String sql = "select * from address where usersid=?";
		return queryForList(sql, userid);
	}

	@Override
	public Address getById(String addressid) {
		String sql = "select * from address where id=?";
		return query(sql, addressid);
	}

}

