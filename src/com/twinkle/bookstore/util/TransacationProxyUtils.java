package com.twinkle.bookstore.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import com.twinkle.bookstore.bean.Transacation;

/**
 * @author Twinkle 生成代理对象的工具类（用于事务处理）
 */
public class TransacationProxyUtils implements InvocationHandler {

	private Object src;

	private TransacationProxyUtils(Object src) {
		super();
		this.src = src;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getProxy(Object src) {
		Object proxyObject = Proxy.newProxyInstance(src.getClass().getClassLoader(), src.getClass().getInterfaces(),
				new TransacationProxyUtils(src));
		System.out.println("proxy " + proxyObject.getClass().getInterfaces()[0]);
		return (T) proxyObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Connection connection = null;
		Object result = null;
		boolean hasTrans = method.isAnnotationPresent(Transacation.class);
		try {
			connection = JDBCUtils.getConnection();

			if (hasTrans) {
				connection.setAutoCommit(false);
			}

			result = method.invoke(src, args);
			if (hasTrans) {
				connection.commit();
			}

		} catch (Exception e) {
			if (connection != null && hasTrans) {
				connection.rollback();
			}
			throw e;
		} finally {
			JDBCUtils.releaseConn(connection);
		}
		return result;
	}
}
