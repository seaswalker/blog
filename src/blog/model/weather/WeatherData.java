package blog.model.weather;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 天气预报查询结果
 * @author skywalker
 *
 */
public class WeatherData implements Serializable {

	private static final long serialVersionUID = 7773257920808439590L;
	
	private String city;
	//过期时间，2小时更新
	private Date expire;
	private String data;
	
	public WeatherData(String city, String data) {
		this.city = city;
		this.data = data;
		//计算过期时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 2);
		this.expire = calendar.getTime();
	}
	
	/**
	 * 判断此时是否过期
	 * @return 过期返回true
	 */
	public boolean isExpired() {
		return new Date().after(expire);
	}

	public String getCity() {
		return city;
	}

	public String getData() {
		return data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherData other = (WeatherData) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		return true;
	}
	
}
