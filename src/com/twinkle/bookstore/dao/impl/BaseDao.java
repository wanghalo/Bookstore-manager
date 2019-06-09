package com.twinkle.bookstore.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.twinkle.bookstore.exception.DBException;
import com.twinkle.bookstore.util.JDBCUtils;

/**
 * @Name com.twinkle.bookstore.dao.impl/BaseDao.java
 * @Description: 被所有Dao实现类继承
 *
 * @VersionInformation: Twinkle 2019年5月20日 V1.1
 */
public abstract class BaseDao<T> { // Map<K,V>

	private Class<T> beanClass;
	private QueryRunner queryRunner = new QueryRunner();

	@SuppressWarnings("unchecked")
	public BaseDao() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		beanClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];

	}

	/* 执行DML,进行增删改的操作 */
	public void update(String sql, Object... params) {
		Connection conn = null;

		try {
			conn = JDBCUtils.getConnection();
			queryRunner.update(conn, sql, params);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/* 执行DQL,得到对象 */
	public T query(String sql, Object... params) {
		Connection conn = null;
		T t = null;

		try {
			conn = JDBCUtils.getConnection();
			t = queryRunner.query(conn, sql, new BeanHandler<T>(beanClass),
					params);
		} catch (SQLException e) {
			throw new DBException(e);
		}

		return t;
	}

	/* 执行DQL,得到对象的集合 */
	public List<T> queryForList(String sql, Object... params) {

		Connection conn = null;
		List<T> list = new ArrayList<>();

		try {
			conn = JDBCUtils.getConnection();
			list = queryRunner.query(conn, sql, new BeanListHandler<T>(
					beanClass), params);
		} catch (SQLException e) {
			throw new DBException(e);
		}

		return list;
	}
	
	/**
	 *  执行DQL, 得到第一条数据中的第一个字段的值
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K> K QueryForSingleValue(String sql, Object... params) {
		K k = null;
		Connection conn = null;

		try {
			conn = JDBCUtils.getConnection();
			k = (K) queryRunner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			throw new DBException(e);
		}
		return k;
	}
	
	/**
	 * 批量操作
	 * @param sql
	 * @param params
	 */
	public void batch(String sql, Object[]... params) {
		Connection conn = null;

		try {
			conn = JDBCUtils.getConnection();
			queryRunner.batch(conn, sql, params);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}
}
