package com.twinkle.bookstore.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.twinkle.bookstore.bean.Book;
import com.twinkle.bookstore.bean.Cart;
import com.twinkle.bookstore.bean.User;

/**
 * @Name com.twinkle.bookstore.util/WebUtils.java
 * @Description: Web层处理的工具类
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
public class WebUtils {

	/**
	 * 将请求中的请求参数数据封装到指定类型的对象中, 并返回
	 * 
	 * @param request
	 * @param beanClass
	 */
	public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {

		T t = null;
		try {
			t = beanClass.newInstance();
			// 从request中取出所有请求参数(名和值),设置到t对象的对应属性中
			Map<String, String[]> map = request.getParameterMap();
			for (String name : map.keySet()) {
				Object value = map.get(name);
				BeanUtils.copyProperty(t, name, value);
			}
			if (request.getParameter("id") == null) {// 如果请求参数没有携带id
				// 给对象设置一个UUID工具类产生的一个36位随机值
				BeanUtils.copyProperty(t, "id", UUID.randomUUID().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 转发请求到/result.jsp页面, 显示指定的提示信息
	 * 
	 * @param request
	 * @param response
	 * @param message
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void forwordMessageUI(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	/**
	 * 转发请求到指定路径, 并保存指定的请求域属性
	 * 
	 * @param request
	 * @param response
	 * @param path
	 * @param name
	 * @param value
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void forword(HttpServletRequest request, HttpServletResponse response, String path, String name,
			Object value) throws ServletException, IOException {
		request.setAttribute(name, value);
		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * 检查用户是否登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static User checkUser(HttpServletRequest request, HttpServletResponse response) {
		User user = null;
		user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie c : cookies) {
					String name = c.getName();
					if ("user".equals(name)) {
						String value = c.getValue();
						String[] namePassword = value.split(",");
						user = new User(namePassword[0], namePassword[1], namePassword[2], null);
						request.getSession().setAttribute("user", user);
						break;
					}
				}
			}
		}
		return user;
	}

	/**
	 * 登陆
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @param auto
	 */
	public static void login(HttpServletRequest request, HttpServletResponse response, User user, boolean auto) {
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		if (auto) {
			Cookie c = new Cookie("user", user.getId() + "," + user.getUsername() + "," + user.getPassword());
			c.setMaxAge(60 * 60 * 24 * 30);
			c.setPath("/");
			response.addCookie(c);
		}
	}

	/**
	 * 登出
	 * 
	 * @param request
	 * @param response
	 */
	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		Cookie c = new Cookie("user", "");
		c.setMaxAge(0);
		c.setPath("/");
		response.addCookie(c);
	}

	/**
	 * 添加到浏览历史
	 * 
	 * @param request
	 * @param response
	 * @param book
	 */
	public static void addToHistory(HttpServletRequest request, HttpServletResponse response, Book book) {
		// cookie的key是由bookName_currentTime
		// book.jsp?book=JavaWeb
		// 获取cookie
		Cookie[] cs = request.getCookies();
		int count = 0;
		Cookie deleteCookie = null;
		// 删除其中的一个cookie
		if (cs != null && cs.length > 0) {
			for (int i = 0; i < cs.length; i++) {
				String name = cs[i].getName();
				if (name.startsWith("book_")) {
					if (name.startsWith("book_" + book.getId())) {
						cs[i].setMaxAge(0);
						response.addCookie(cs[i]);
						break;
					} else {
						count++;
						if (deleteCookie == null) {
							deleteCookie = cs[i];
						}
					}
				}
			}
			if (count == 4) {
				// 将cookie数组中的第一个删除
				deleteCookie.setMaxAge(0);
				response.addCookie(deleteCookie);
			}
		}
		try {
			Cookie c = new Cookie("book_" + book.getId(), URLEncoder.encode(book.getName(), "utf-8"));
			c.setMaxAge(60 * 60 * 24 * 30);
			response.addCookie(c);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 得到浏览过的所有图书
	 * 
	 * @param request
	 * @return
	 */
	public static List<Map<String, String>> getHistoryBooks(HttpServletRequest request) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Cookie[] cs = request.getCookies();
		if (cs != null && cs.length > 0) {
			for (int i = cs.length - 1; i >= 0; i--) {
				Cookie c = cs[i];
				String cName = c.getName();
				if (cName.startsWith("book_")) {
					Map<String, String> map = new HashMap<>();
					String id = cName.substring(cName.indexOf("_") + 1);
					try {
						String name = URLDecoder.decode(c.getValue(), "UTF-8");
						map.put("name", name);
					} catch (UnsupportedEncodingException e) {
						throw new UnsupportedOperationException();
					}

					map.put("id", id);

					list.add(map);
				}
			}
		}
		return list;
	}

	/**
	 * 清空浏览历史
	 * 
	 * @param request
	 * @param resp
	 */
	public static void clearHistory(HttpServletRequest request, HttpServletResponse resp) {
		Cookie[] cs = request.getCookies();
		if (cs != null && cs.length > 0) {
			for (int i = cs.length - 1; i >= 0; i--) {
				Cookie c = cs[i];
				String cName = c.getName();
				if (cName.startsWith("book_")) {
					c.setMaxAge(0);
					resp.addCookie(c);
				}
			}
		}
	}

	public static Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("CART");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("CART", cart);
		}
		return cart;
	}

	/**
	 * 生成订单号,订单号的组成 两位年+两位月+两位日+两位小时+当天的订单总数 如:11071012100
	 * 
	 * @param count
	 * 
	 * @return
	 */
	public static String buildOrderid(long count) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHH");// 2011-07-10
		StringBuilder out = new StringBuilder(dateFormat.format(new Date()));
		int length = 15;
		int countLength = String.valueOf(out).length();
		int zeroLength = length - countLength;
		for (int i = 0; i < zeroLength; i++) {
			out.append(0);
		}
		out.append(count);
		return out.toString();
	}

	/**
	 * 上传图书图片
	 * 
	 * @param request
	 * @return
	 */
	public static Book uploadBook(HttpServletRequest request) {
		Book book = new Book();

		String id = UUID.randomUUID().toString();
		book.setId(id);

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			@SuppressWarnings("unchecked")
			List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem item : fileItems) {
				// 判断是否为普通表单字段
				if (item.isFormField()) {
					// 封装bean
					BeanUtils.setProperty(book, item.getFieldName(), item.getString("utf-8"));
				} else {
					// 文件上传字段
					// 获得文件名 文件名有可能有中文 和麻烦， 所以我们采取自定义文件名， 保证不重复
					String filename = item.getName();
					String extName = filename.substring(filename.lastIndexOf("."));
					filename = id + extName;

					// 获得相对当前web应用的路径
					String webPath = "/images/" + filename;
					// 获得绝对路径
					ServletContext servletContext = request.getSession().getServletContext();
					String path = servletContext.getRealPath(webPath);
					// 创建文件
					File file = new File(path);
					file.getParentFile().mkdirs();
					file.createNewFile();

					// 创建流对拷
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(file);
					Streams.copy(in, out, true);

					// 删除临时文件
					item.delete();

					// 文件路径 封装到bean 的 imagepath
					BeanUtils.setProperty(book, "imagepath", webPath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
}
