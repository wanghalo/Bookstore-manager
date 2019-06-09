package com.twinkle.bookstore.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twinkle.bookstore.bean.Address;
import com.twinkle.bookstore.bean.Order;
import com.twinkle.bookstore.bean.OrderItem;
import com.twinkle.bookstore.bean.User;
import com.twinkle.bookstore.factory.BeanFactory;
import com.twinkle.bookstore.service.OrderService;
import com.twinkle.bookstore.service.UserService;
import com.twinkle.bookstore.servlet.BaseServlet;
import com.twinkle.bookstore.util.TransacationProxyUtils;
import com.twinkle.bookstore.util.WebUtils;

/**
 * @Name com.twinkle.bookstore.servlet.manager/OrderServlet.java
 * @Description: 后台订单处理相关的Servlet 
 * 1.显示某个状态的所有订单 
 * 2.显示某个订单详情 
 * 3.发货
 *
 * @VersionInformation: Twinkle 2019年5月22日 V1.0
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private OrderService orderService = TransacationProxyUtils.getProxy(BeanFactory.get(OrderService.class));
	private UserService userService = TransacationProxyUtils.getProxy(BeanFactory.get(UserService.class));

	/* 1.显示某个状态的所有订单 */
	public void showOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("--OrderServlet showOrders()--");

		String statusString = request.getParameter("status");//获取状态
		boolean status = false;
		try {
			status = Boolean.parseBoolean(statusString);//转换类型
		} catch (Exception e) {
		}
		List<Order> list = orderService.getOrdersByStatus(status);
		WebUtils.forword(request, response, "/manager/order/orders.jsp", "orders", list);
	}

	/* 2.显示某个订单详情 */
	public void showOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---OrderServlet showOrdder()---");
		//获取订单状态
		String orderid = request.getParameter("orderid");
		String addressid = request.getParameter("addressid");

		List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderid);
		Address address = orderService.getAdressByAddresId(addressid);
		User user = userService.getUserById(address.getUsersid());

		request.setAttribute("orderItems", orderItems);
		request.setAttribute("address", address);
		request.setAttribute("user", user);

		request.getRequestDispatcher("/manager/order/order.jsp").forward(request, response);
	}

	/* 3.发货 */
	public void send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---OrderServlet send()---");

		String orderid = request.getParameter("orderid");

		orderService.send(orderid);

		WebUtils.forwordMessageUI(request, response, "发货成功");
	}
}
