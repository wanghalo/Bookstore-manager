package com.twinkle.bookstore.service.impl;

import java.util.List;
import java.util.UUID;

import com.twinkle.bookstore.bean.Address;
import com.twinkle.bookstore.bean.Book;
import com.twinkle.bookstore.bean.Cart;
import com.twinkle.bookstore.bean.CartItem;
import com.twinkle.bookstore.bean.Order;
import com.twinkle.bookstore.bean.OrderItem;
import com.twinkle.bookstore.dao.AddressDao;
import com.twinkle.bookstore.dao.BookDao;
import com.twinkle.bookstore.dao.OrderDao;
import com.twinkle.bookstore.dao.OrderItemDao;
import com.twinkle.bookstore.factory.BeanFactory;
import com.twinkle.bookstore.service.OrderService;
import com.twinkle.bookstore.util.WebUtils;

/**
 * @Name com.twinkle.bookstore.service.impl/OrderServiceImpl.java
 * @Description: 订单相关操作的业务接口的实现类
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public class OrderServiceImpl implements OrderService {

	private AddressDao addressDao = BeanFactory.get(AddressDao.class);
	private OrderDao orderDao = BeanFactory.get(OrderDao.class);
	private OrderItemDao orderItemDao = BeanFactory.get(OrderItemDao.class);
	private BookDao bookDao = BeanFactory.get(BookDao.class);

	@Override
	public void addAddress(Address address) {
		addressDao.insert(address);
	}

	@Override
	public String makeOrder(String userId, String addressId, Cart cart) {

		// 更新books表
		Object[][] data = new Object[cart.getMap().size()][3];
		int index = 0;
		for (CartItem cartItem : cart.getMap().values()) {
			data[index][0] = cartItem.getCount();
			data[index][1] = cartItem.getCount();
			data[index][2] = cartItem.getBook().getId();
			index++;
		}
		bookDao.batchUpdateStoreAndSale(data);

		// 更新orders表
		String orderNumber = WebUtils.buildOrderid(orderDao.getTodayCount() + 1);// 生成订单号
		Order order = new Order(UUID.randomUUID().toString(), orderNumber, false, cart.getTotalPrice(), null, userId,
				addressId);
		orderDao.insert(order);

		// 更新orderItem表
		data = new Object[cart.getMap().size()][5];
		index = 0;
		for (CartItem cartItem : cart.getMap().values()) {
			data[index][0] = UUID.randomUUID().toString();
			data[index][1] = cartItem.getCount();
			data[index][2] = cartItem.getItemPrice();
			data[index][3] = order.getId();
			data[index][4] = cartItem.getBook().getId();
			index++;
		}
		orderItemDao.batchInsert(data);

		return orderNumber;
	}

	@Override
	public List<Address> getAdressesByUserId(String userid) {

		return addressDao.getListByUserId(userid);
	}

	@Override
	public List<Order> getOrdersByUserId(String userId) {

		return orderDao.getListByUserId(userId);
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(String orderid) {
		List<OrderItem> list = orderItemDao.getListByOrderId(orderid);
		for (OrderItem item : list) {
			Book book = bookDao.getById(item.getBooksid());
			item.setBook(book);
		}
		return list;
	}

	@Override
	public Address getAdressByAddresId(String addressid) {

		return addressDao.getById(addressid);
	}

	@Override
	public List<Order> getOrdersByStatus(boolean status) {

		return orderDao.getListByStatus(status);
	}

	@Override
	public void send(String orderid) {
		orderDao.updateStatus(orderid);
	}

}
