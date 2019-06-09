package junit.test;

import com.twinkle.bookstore.util.JDBCUtils;

/**
 * @author Twinkle 数据库连接测试
 */
public class JDBCUtilsTest {

	public static void main(String[] args) {
		System.out.println(JDBCUtils.getConnection());
	}
}
