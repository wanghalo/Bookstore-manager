package com.twinkle.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twinkle.bookstore.exception.DBException;

/*
 * 所有Servlet的基类
 * 1. 应用中的所有对Servlet的请求都需要添加method=xxx的请求参数
 * 2. 在应用的Servlet类中, 只需要定义处理请求的方法: 
 * public void xxx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { } 
 * 注意: 方法必须是public的,且与method请求参数值同名, 它不需要在web.xml文件中配置
 */
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String methodName = req.getParameter("method");
		try {
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			// e.printStackTrace();
			Throwable cause = e.getCause();
			if (cause instanceof DBException) {
				throw new DBException(cause);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
