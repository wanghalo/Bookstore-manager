package com.twinkle.bookstore.service;

import java.util.List;

import com.twinkle.bookstore.bean.Address;
import com.twinkle.bookstore.bean.Cart;
import com.twinkle.bookstore.bean.Order;
import com.twinkle.bookstore.bean.OrderItem;
import com.twinkle.bookstore.bean.Transacation;

/**
 * @Name com.twinkle.bookstore.service/OrderService.java
 * @Description: 订单相关操作的业务接口
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public interface OrderService {

	/**
	 * 添加地址
	 * 
	 * @param address
	 */
	void addAddress(Address address);

	/**
	 * 生成一个订单
	 * 
	 * @param userId
	 * @param addressId
	 * @param cart
	 * @return
	 */
	@Transacation
	public String makeOrder(String userId, String addressId, Cart cart);

	/**
	 * 得到某个用户的所有地址信息
	 * 
	 * @param userid
	 * @return
	 */
	List<Address> getAdressesByUserId(String userid);

	/**
	 * 得到某个用户的所有订单信息
	 * 
	 * @param id
	 * @return
	 */
	List<Order> getOrdersByUserId(String id);

	/**
	 * 得到某个订单的所有订单项信息
	 * 
	 * @param orderid
	 * @return
	 */
	List<OrderItem> getOrderItemsByOrderId(String orderid);

	/**
	 * 得到地址对象
	 * 
	 * @param addressid
	 * @return
	 */
	Address getAdressByAddresId(String addressid);

	/**
	 * 得到某个订单状态的所有订单
	 * 
	 * @param status
	 * @return
	 */
	List<Order> getOrdersByStatus(boolean status);

	/**
	 * 给某个订单发货
	 * 
	 * @param orderid
	 */
	void send(String orderid);

}
