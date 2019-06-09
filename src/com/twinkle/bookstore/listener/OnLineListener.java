package com.twinkle.bookstore.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.twinkle.bookstore.bean.OnLineBean;
import com.twinkle.bookstore.bean.User;

/**
 * @Name com.twinkle.bookstore.listener/OnLineListener.java
 * @Description: 用来统计在线用户列表的监听器类
 *
 * @VersionInformation: Twinkle 2019年5月22日 V1.0
 */
public class OnLineListener implements ServletContextListener, HttpSessionAttributeListener, HttpSessionListener {

	private OnLineBean onLineBean = new OnLineBean();

	public void attributeRemoved(HttpSessionBindingEvent se) {

		String name = se.getName();
		if ("user".equals(name)) {
			HttpSession session = se.getSession();
			String ip = (String) session.getAttribute("ip");
			onLineBean.removeUser(ip, true);
		}
	}

	public void attributeAdded(HttpSessionBindingEvent se) {

		HttpSession session = se.getSession();
		String ip = (String) session.getAttribute("ip");
		String name = se.getName();
		if ("user".equals(name)) {
			User user = (User) session.getAttribute("user");
			onLineBean.addUser(ip, user.getUsername());
		} else if ("ip".equals(name)) {
			onLineBean.addVisitor(ip);
		}

	}

	/**
	 * 应用被初始化了
	 */
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		application.setAttribute("OnLineBean", onLineBean);
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {

	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

		HttpSession session = se.getSession();
		String ip = (String) session.getAttribute("ip");
		onLineBean.removeUser(ip, false);
	}
}