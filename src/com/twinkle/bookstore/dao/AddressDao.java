package com.twinkle.bookstore.dao;

import java.util.List;

import com.twinkle.bookstore.bean.Address;

/**
 * @Name com.twinkle.bookstore.dao/AddressDao.java
 * @Description: address表操作的Dao接口
 *
 * @VersionInformation: Twinkle 2019年5月20日 V1.0
 */
public interface AddressDao {
	/* 插入一条数据 */
	public void insert(Address address);

	/* 根据用户id得到对应的所有地址的列表 */
	public List<Address> getListByUserId(String userid);

	/* 根据id得到对应的记录对象 */
	public Address getById(String addressid);
}
