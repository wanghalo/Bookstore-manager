package com.twinkle.bookstore.servlet.client;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twinkle.bookstore.bean.Book;
import com.twinkle.bookstore.bean.Category;
import com.twinkle.bookstore.bean.ConditionBook;
import com.twinkle.bookstore.bean.Page;
import com.twinkle.bookstore.factory.BeanFactory;
import com.twinkle.bookstore.service.BookService;
import com.twinkle.bookstore.service.CategoryService;
import com.twinkle.bookstore.servlet.BaseServlet;
import com.twinkle.bookstore.util.TransacationProxyUtils;
import com.twinkle.bookstore.util.WebUtils;

/**
 * 处理应用前台图书相关操作请求 
 * 1. 显示图书分页列表 
 * 2. 显示图书详情 
 * 3. 显示图书浏览记录 
 * 4. 清空浏览记录clearHistory
 */
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = TransacationProxyUtils.getProxy(BeanFactory.get(BookService.class));
	private CategoryService categoryService = TransacationProxyUtils.getProxy(BeanFactory.get(CategoryService.class));

	/* 1. 显示图书分页列表 */
	public void getBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ConditionBook conditionBook = WebUtils.request2Bean(req, ConditionBook.class);

		Page<Book> page = bookService.getPage(conditionBook);

		String categorysid = conditionBook.getCategorysid();
		if (categorysid == null || "".equals(categorysid.trim())) {
			req.setAttribute("category", new Category("全部分类"));
		} else {
			Category category = categoryService.getCategory(categorysid);
			req.setAttribute("category", category);
		}

		WebUtils.forword(req, resp, "/client/book/books.jsp", "page", page);
	}

	/* 2. 显示图书详情 */
	public void showBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("bookid");
		Book book = bookService.getBookById(id);
		Category category = categoryService.getCategory(book.getCategorysid());

		WebUtils.addToHistory(req, resp, book);

		req.setAttribute("book", book);
		req.setAttribute("category", category);

		req.getRequestDispatcher("/client/book/book.jsp").forward(req, resp);
	}

	/* 3. 显示图书浏览记录 */
	public void showHistoryBooks(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		List<Map<String, String>> list = WebUtils.getHistoryBooks(request);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/client/book/historyBooks.jsp").forward(request, resp);
	}

	/* 4. 清空浏览记录clearHistory */
	public void clearHistory(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		WebUtils.clearHistory(request, resp);

		WebUtils.forwordMessageUI(request, resp, "清空浏览记录成功!");
	}
}
