package junit.test;

import java.util.UUID;

/**
 * @author Twinkle UUID产生一个长度为36的随机唯一值
 */
public class UUIDTest {

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
	}
}
