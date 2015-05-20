package blog.controller.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.model.weather.WeatherData;

/**
 * 气象查询
 * @author skywalker
 *
 */
@Controller
@ResponseBody
public class WeatherController {
	
	/**
	 * IP地址在此被包含在内，即为本机
	 */
	private static final String[] LOCALHOST = new String[] {"127.0.0.1", "0:0:0:0:0:0:0:1"};
	/**
	 * 本机所在城市
	 */
	private static final String LOCALCITY = "青岛";
	/**
	 * 百度天气地址
	 */
	private static final String WEATHER_URL = "http://api.map.baidu.com/telematics/v3/weather?ak=75fb46847fa50e87a07000d1e573a168&output=json&location=";
	/**
	 * IP地址查询地址
	 */
	private static final String IP_URL = "http://ipapi.sinaapp.com/api.php?f=text&ip=";
	/**
	 * 天气预报结果缓存
	 */
	private static final ConcurrentHashMap<String, WeatherData> WEATHERCACHE = new ConcurrentHashMap<String, WeatherData>();

	@RequestMapping("/weather")
	public void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ip = request.getRemoteAddr();
		String city = isLocalhost(ip) ? LOCALCITY : getCity(ip);
		WeatherData data = checkCache(city);
		if(data == null) {
			data = getWeather(city);
			WEATHERCACHE.put(city, data);
		}
		writeToClientWithJSON(response, data.getData());
	}
	
	/**
	 * 检查缓存
	 * 条件是缓存中存在此城市并且没有过时
	 */
	private WeatherData checkCache(String city) {
		WeatherData data = WEATHERCACHE.get(city);
		return (data == null || data.isExpired()) ? null : data;
	}
	
	/**
	 * 以json格式把数据发送到客户端
	 */
	private void writeToClientWithJSON(HttpServletResponse response, String data) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(data);
	}
	
	/**
	 * 查询相应城市的天气
	 */
	private WeatherData getWeather(String city) throws IOException {
		String address = WEATHER_URL + URLEncoder.encode(LOCALCITY, "UTF-8");
		return new WeatherData(city, netQuery(address));
	}
	
	/**
	 * 查询IP地址所在的城市
	 */
	private String getCity(String ip) throws IOException {
		String address = IP_URL + ip;
		String result = null;
		//IP地址：112.67.148.9，起始地址：112.67.45.0，结束地址：112.67.255.255，归属地信息：海南省海口市 电信
		result = extractCity(netQuery(address));
		return result;
	}
	
	/**
	 * 联网查询给定的地址
	 */
	private String netQuery(String address) throws IOException {
		String result = "";
		BufferedReader br = null;
		try {
			URL url = new URL(address);
			URLConnection connection = url.openConnection();
			br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line = null;
			while((line = br.readLine()) != null) {
				result += line;
			}
		}finally {
			if(br != null) {
				br.close();
			}
		}
		return result;
	}
	
	/**
	 * 从搜狐返回的数据中获取城市名称
	 */
	private String extractCity(String city) {
		//省
		int provincePosition = city.indexOf("省");
		if(provincePosition == -1) {
			//直辖市都是两个字
			int cityPosition = city.indexOf("市");
			return city.substring(cityPosition - 2, cityPosition);
		}
		return city.substring(city.indexOf("省") + 1, city.indexOf("市"));
	}
	
	
	/**
	 * 判断是否是本机
	 */
	private boolean isLocalhost(String ip) {
		for(int i = 0;i < LOCALHOST.length;i ++) {
			if(LOCALHOST[i].equals(ip)) {
				return true;
			}
		}
		return false;
	}
	
}