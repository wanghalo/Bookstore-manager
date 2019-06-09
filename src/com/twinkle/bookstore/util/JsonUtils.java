package com.twinkle.bookstore.util;

import com.google.gson.Gson;

/**
 * @Name com.twinkle.bookstore.util/JsonUtils.java
 * @Description: json数据处理的工具类
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
public class JsonUtils {
	/* 将一个对象转换成一个json字符串 */
	public static String toJson(Object data) {
		Gson gson = new Gson();
		return gson.toJson(data);
	}
}
