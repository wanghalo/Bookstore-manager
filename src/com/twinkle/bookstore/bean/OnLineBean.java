package com.twinkle.bookstore.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Name com.twinkle.bookstore.bean/OnLineBean.java
 * @Description: 在线用户列表信息实体类
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
public class OnLineBean {
	// 所有已登陆用户的集合(key为ip, value为用户名)
	private Map<String, String> userMap = new HashMap<String, String>();

	// 所有未登陆用户ip的集合
	private Set<String> visitorSet = new HashSet<String>();

	public Map<String, String> getUserMap() {
		return userMap;
	}

	public Set<String> getVisitorSet() {
		return visitorSet;
	}

	public void addUser(String ip, String username) {
		userMap.put(ip, username);
		visitorSet.remove(ip);
	}

	public void removeUser(String ip, boolean online) {
		userMap.remove(ip);
		if (online) {
			visitorSet.add(ip);
		} else {
			visitorSet.remove(ip);
		}
	}

	public void addVisitor(String ip) {
		visitorSet.add(ip);
	}

	public int getUserCount() {
		return userMap.size();
	}
	//用

	public int gettotalCount() {
		return visitorSet.size() + userMap.size();
	}
	//总
}
