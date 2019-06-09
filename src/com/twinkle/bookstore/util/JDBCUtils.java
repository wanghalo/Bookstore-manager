package com.twinkle.bookstore.util;

import java.sql.Connection;
import java.sql.SQLException;
//
//import org.apache.commons.dbutils.QueryRunner;
//
//import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.twinkle.bookstore.exception.DBException;

/**
 * @Name com.twinkle.bookstore.util/JDBCUtils.java
 * @Description: 数据库操作工具类，得到对象并释放连接，使用连接池
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.1
 */
public final class JDBCUtils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource("webDataSource");
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();

	/* 得到连接对象 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = local.get();
			if (conn == null) {
				conn = dataSource.getConnection();
				local.set(conn);
			}
		} catch (SQLException e) {
			throw new DBException(e);
		}
		return conn;
	}

	/* 释放连接 conn */
	public static void releaseConn(Connection conn) {
		if (conn != null) {
			try {
				local.remove();
				conn.close();
			} catch (SQLException e) {
				throw new DBException(e);
			}
		}
	}
}
