package com.twinkle.bookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Name com.twinkle.bookstore.filter/CharacterEncodingFilter.java
 * @Description: 解决乱码的过滤器
 *
 * @VersionInformation: Twinkle 2019年5月22日 V1.0
 */
public class CharacterEncodingFilter implements Filter {
	
	/**
	 * Default constructor.
	 */
	public CharacterEncodingFilter() {
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

		HttpServletRequest req = (HttpServletRequest) request;

		req.setCharacterEncoding("utf-8");

		response.setContentType("text/html;charset=utf-8");

		HttpSession session = req.getSession();
		if (session.isNew()) {
			String ip = req.getRemoteAddr();
			session.setAttribute("ip", ip);
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
