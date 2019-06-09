package com.twinkle.bookstore.bean;

import java.util.HashMap;
import java.util.Map;

import com.twinkle.bookstore.bean.CartItem;

/**
 * @Name com.twinkle.bookstore.bean/Cart.java
 * @Description: 购物车信息封装
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
public class Cart {
	private Map<String, CartItem> map = new HashMap<String, CartItem>();//书=数

	public Map<String, CartItem> getMap() {
		return map;
	}

	public int getTotalCount() {
		// 得到书的总数量
		int total = 0;
		for (CartItem cartItem : map.values()) {
			total += cartItem.getCount();
		}
		return total;
	}

	public float getTotalPrice() {
		// 总价格
		float total = 0;

		for (CartItem cartItem : map.values()) {
			total += cartItem.getItemPrice();
		}
		return total;
	}

}
