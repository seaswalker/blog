package blog.model.page;

import java.util.ArrayList;
import java.util.List;

/**
 * lucence分页
 * @author skywalker
 *
 */
public class LucencePageBean<T> extends PageBean<T> {
	
	private long cost;
	/**
	 * 空对象
	 */
	@SuppressWarnings("rawtypes")
	private static LucencePageBean empty;
	
	/**
	 * 获取空对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static LucencePageBean empty() {
		if(empty == null) {
			synchronized (LucencePageBean.class) {
				if(empty == null) {
					empty = new LucencePageBean(new ArrayList(), 10, 1, 0, 0L);
				}
			}
		}
		return empty;
	}

	public LucencePageBean(List<T> records, int pageSize, int currentPage,
			int recordCount, long cost) {
		super(records, pageSize, currentPage, recordCount);
		this.cost = cost;
	}

	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}
	
}
