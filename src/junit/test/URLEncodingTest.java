package junit.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author Twinkle 字符处理
 */
public class URLEncodingTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String encode = URLEncoder.encode("中", "utf-8");

		System.out.println(encode); // %E4%B8%AD

		String decode = URLDecoder.decode(encode, "utf-8");
		System.out.println(decode);

	}
}
