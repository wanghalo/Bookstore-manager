package com.twinkle.bookstore.factory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @Name com.twinkle.bookstore.factory/BeanFactory.java
 * @Description: Service和dao对象的工厂
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public final class BeanFactory {

	private BeanFactory(){}
	private static Map<String, Object> map = new HashMap<String, Object>();
	
	/* 触发static块时被调用 */
	public static void init() {
		System.out.println("BeanFactory init()");
	}
	
	/* 第一次请求一个Servlet时调用--->部署应用 */
	static {
		try {
			
			String[] configs = {"dao.properties","service.properties"};
			for(String config : configs) {
				//读取dao.properties,service.properties //classpath(类加载器)
				InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream(config);
				Properties properties = new Properties();
				properties.load(is);
				//取出所有数据
				Set<Object> keySet = properties.keySet();
				//System.out.println(keySet.size());
				for(Object o : keySet) {
					String interfaceName = (String) o;
					String className = properties.getProperty(interfaceName);
					
					map.put(interfaceName, Class.forName(className).newInstance());
				}
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> type) {
		//System.out.println("type "+type);
		String name = type.getSimpleName();
		return (T) map.get(name);
	}
}