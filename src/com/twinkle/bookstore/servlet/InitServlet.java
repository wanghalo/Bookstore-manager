package com.twinkle.bookstore.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.twinkle.bookstore.factory.BeanFactory;

/**
 * @author Twinkle 用来读取数据源的初始化Servlet
 *
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		BeanFactory.init();
	}
}
