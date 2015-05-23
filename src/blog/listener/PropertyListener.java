package blog.listener;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import blog.util.PropertyUtil;

/**
 * 应用启动时加载配置文件
 * @author skywalker
 *
 */
public class PropertyListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Properties properties = new Properties();
		Map<String, String> propertisMap = new HashMap<String, String>();
		try {
			properties.load(this.getClass().getResourceAsStream("/config.properties"));
			//所有属性添加到application范围
			@SuppressWarnings("unchecked")
			Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
			String name = null;
			while(enumeration.hasMoreElements()) {
				name = enumeration.nextElement();
				propertisMap.put(name, properties.getProperty(name));
			}
			PropertyUtil.PROPERTIES = propertisMap;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
