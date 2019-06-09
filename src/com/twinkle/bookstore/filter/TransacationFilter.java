package com.twinkle.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.twinkle.bookstore.util.JDBCUtils;

/**
 * @Name com.twinkle.bookstore.filter/TransacationFilter.java
 * @Description: 事务处理过滤器
 *
 * @VersionInformation: Twinkle 2019年5月22日 V1.0
 */
public class TransacationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public TransacationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Connection connection = null;
		try {

			connection = JDBCUtils.getConnection();

			connection.setAutoCommit(false);

			chain.doFilter(request, response);

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			JDBCUtils.releaseConn(connection);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
