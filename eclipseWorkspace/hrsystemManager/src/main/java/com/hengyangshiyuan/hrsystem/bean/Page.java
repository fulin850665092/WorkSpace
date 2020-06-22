package com.hengyangshiyuan.hrsystem.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Page对象是分页的模型
 * 
 * @author Administrator
 * 
 * @param <T>
 *            T 是不同的模块的分页要显示的不同的对象
 */
@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true) //开启链式get/set
public class Page<T> implements Serializable{

	public static final int PAGE_SIZE = 12;

	// 当前页
	private int pageNo;
	// 总的记录数
	private int pageTotalCount;
	// 总的页码
	private int pageTotal;
	// 每页显示的数量
	private int pageSize = PAGE_SIZE;
	// 当前页要显示的数据集合
	private List<T> items;
	// 不同模块的分页的请求地址。
	private String url;

	public Page(int pageNo, int pageTotalCount, int pageTotal, int pageSize,
			List<T> items) {
		super();
		this.pageNo = pageNo;
		this.pageTotalCount = pageTotalCount;
		this.pageTotal = pageTotal;
		this.pageSize = pageSize;
		this.items = items;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		// 检查数据的有效边界
		if (pageNo < 1) {
			pageNo = 1;
		}
		if (pageNo > pageTotal) {
			pageNo = pageTotal;
		}

		this.pageNo = pageNo;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageTotalCount=" + pageTotalCount
				+ ", pageTotal=" + pageTotal + ", pageSize=" + pageSize
				+ ", items=" + items + ", url=" + url + "]";
	}

}
