package blog.model.page;

import java.util.List;

/**
 * 封装分页相关的数据
 * @author skywalker
 * @param <T>
 *
 */
public class PageBean<T> {

	/**
	 * 当前页的数据
	 */
	private List<T> records;
	/**
	 * 每页记录数
	 */
	private int pageSize;
	
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 总记录数
	 */
	private int recordCount;
	
	
	/**
	 * 开始页号，用于下面的页面导航
	 */
	private int pageBeginIndex;
	/**
	 * 结束页号
	 */
	private int pageEndIndex;
	/**
	 * 总页数
	 */
	private int pageCount;
	
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageBeginIndex() {
		return pageBeginIndex;
	}
	public void setPageBeginIndex(int pageBeginIndex) {
		this.pageBeginIndex = pageBeginIndex;
	}
	public int getPageEndIndex() {
		return pageEndIndex;
	}
	public void setPageEndIndex(int pageEndIndex) {
		this.pageEndIndex = pageEndIndex;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	/**
	 * 这四个参数需要从service传来，其他3个参数可以由这些计算而来
	 */
	public PageBean(List<T> records, int pageSize, int currentPage,
			int recordCount) {
		this.records = records;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.recordCount = recordCount;
		//计算总页数
		this.pageCount = (recordCount + pageSize - 1) / pageSize;
		//计算起止页码
			//当总页数不超过10页
		if(pageCount <= 10) {
			pageBeginIndex = 1;
			pageEndIndex = pageCount;
		}
		else  {
			pageBeginIndex = currentPage - 4;
			pageEndIndex = currentPage + 5;
			//当前页之前不足4页,显示10页
			if(pageBeginIndex < 1) {
			pageBeginIndex = 1;
			pageEndIndex = 10;
			}
			//当前页之后不足5页，显示后10页
			else if(pageEndIndex > pageCount) {
				pageEndIndex = pageCount;
				pageBeginIndex = pageEndIndex - 9;
			}
		}
		
	}
	public PageBean() {}
}
