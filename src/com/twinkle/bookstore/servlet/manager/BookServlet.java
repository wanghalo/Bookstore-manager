package com.twinkle.bookstore.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twinkle.bookstore.bean.Book;
import com.twinkle.bookstore.bean.Category;
import com.twinkle.bookstore.factory.BeanFactory;
import com.twinkle.bookstore.service.BookService;
import com.twinkle.bookstore.service.CategoryService;
import com.twinkle.bookstore.servlet.BaseServlet;
import com.twinkle.bookstore.util.TransacationProxyUtils;
import com.twinkle.bookstore.util.WebUtils;

/**
 * @Name com.twinkle.bookstore.servlet.manager/BookServlet.java
 * @Description: 后台图书相关操作
 * 	0.进入图书添加页面
 * 	1.添加图书
 * 	2.查看所有图书列表
 * 	3.查看某本图书详情
 * 	4.删除某本图书
 * 	5.进入更新界面
 * 	6.更新某本图书
 *
 * @VersionInformation: Twinkle 2019年5月22日 V1.0
 */
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService = TransacationProxyUtils.getProxy(BeanFactory.get(BookService.class));
	private CategoryService categoryService = TransacationProxyUtils.getProxy(BeanFactory.get(CategoryService.class));
	
	public void toAddUI(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		System.out.println("---BookServlet toAddUI()---");
		
		List<Category> allCategorys = categoryService.getAllCategorys();
		WebUtils.forword(request, response, "/manager/book/add.jsp", "allCategorys", allCategorys);
	}
	public void add(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		System.out.println("---BookServlet add()---");
		
		Book book = WebUtils.uploadBook(request);
		
		bookService.addBook(book);
		
		WebUtils.forwordMessageUI(request, response, "添加图书成功！");
	}
	public void listAll(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		System.out.println("---BookServlet listAll()---");
		
		List<Book> list = bookService.getAllBooks();
		WebUtils.forword(request, response, "/manager/book/list.jsp", "list", list);
	}
	public void showBook(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		System.out.println("---BookServlet showBook()---");
		
		String id = request.getParameter("bookid");
		Book book = bookService.getBookById(id);
		WebUtils.forword(request, response, "/manager/book/book.jsp", "book", book);
	}
	public void delete(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		System.out.println("---BookServlet delete()---");
		
		String id = request.getParameter("bookid");
		bookService.deleteBookById(id);
		WebUtils.forwordMessageUI(request, response, "删除图书成功！");
	}
	public void toUpdateUI(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		System.out.println("---BookServlet toUpdateUI()---");
		
		String bookid = request.getParameter("bookid");
		
		List<Category> allCategorys = categoryService.getAllCategorys();
		Book book = bookService.getBookById(bookid);
		
		request.setAttribute("allCategorys", allCategorys);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/manager/book/update.jsp").forward(request, response);
	}
	public void update(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		System.out.println("---BookServlet update()---");
		
		Book book = WebUtils.request2Bean(request, Book.class);
		bookService.updateBook(book);
		WebUtils.forwordMessageUI(request, response, "更新图书成功！");
	}
}
