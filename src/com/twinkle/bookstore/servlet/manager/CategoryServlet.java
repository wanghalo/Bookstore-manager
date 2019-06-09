package com.twinkle.bookstore.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twinkle.bookstore.bean.Category;
import com.twinkle.bookstore.factory.BeanFactory;
import com.twinkle.bookstore.service.CategoryService;
import com.twinkle.bookstore.service.impl.CategoryServiceImpl;
import com.twinkle.bookstore.servlet.BaseServlet;
import com.twinkle.bookstore.util.TransacationProxyUtils;
import com.twinkle.bookstore.util.WebUtils;

/**
 * @Name com.twinkle.bookstore.servlet.manager/CategoryServlet.java
 * @Description: 处理图书分类的相关操作请求
 * 	1.添加分类
 * 	2.显示所有分类列表
 * 	3.更新某个分类的名称
 * 	4.删除某个分类
 *
 * @VersionInformation: Twinkle 2019年5月22日 V1.0
 */
@SuppressWarnings("unused")
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private CategoryService categoryService = TransacationProxyUtils.getProxy(BeanFactory.get(CategoryService.class));
	
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Category category = WebUtils.request2Bean(request, Category.class);
		
		boolean success = categoryService.addCategory(category);
		
		if(success) {
			WebUtils.forwordMessageUI(request, response, "添加分类成功!");
		} else {
			WebUtils.forword(request, response, "/manager/category/add.jsp", "message", "此分类已存在!");
		}
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> list = categoryService.getAllCategorys();
		
		WebUtils.forword(request, response, "/manager/category/list.jsp", "list", list);
	}
	
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--delete()--");
		
		String id = request.getParameter("categoryid");
		System.out.println(id);
		categoryService.deleteCategoryById(id);
		System.out.println("已获取id");
		WebUtils.forwordMessageUI(request, response, "删除分类成功!");
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---CategoryServlet update()---");
		
		Category category = WebUtils.request2Bean(request, Category.class);
		categoryService.updateCategory(category);
		WebUtils.forwordMessageUI(request, response, "更新分类成功!");
	}
}
