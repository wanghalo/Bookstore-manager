package com.twinkle.bookstore.util;

import java.security.MessageDigest;

/**
 * @Name com.twinkle.bookstore.util/PasswordEncodeUtils.java
 * @Description: 对密码进行加密和验证
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
public class PasswordEncodeUtils {
	/* 十六进制下数字到字符的映射数组 */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/* 把inputString加密 */
	public static String generatePassword(String inputString) {
		return encodeByMD5(inputString);
	}

	/**
	 * 验证输入的密码是否正确 password 加密后的密码 inputString 输入的字符串 验证结果，TRUE:正确 FALSE:错误
	 */
	public static boolean validatePassword(String password, String inputString) {
		if (password.equals(encodeByMD5(inputString))) {
			return true;
		} else {
			return false;
		}
	}

	/* 对字符串进行 MD5 加密 */
	private static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换字节数组为十六进制字符串 参数传入字节数组 返回十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/* 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}