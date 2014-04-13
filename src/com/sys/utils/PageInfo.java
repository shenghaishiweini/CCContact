package com.sys.utils;

import java.io.Serializable;
import java.util.List;



public class PageInfo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int pageIndex; // 当前第几页数据
	private int pageSize; 	//每页要显示的最大记录个数	
	private int recordeCount; // 满足查询条件的总记录数
	private int pageCount; // 满足条件的总页数
	@SuppressWarnings("rawtypes")
	private List pageData; // 当前查询的分页数据
    private boolean hasPreviousPage;  //是否有上一页

    private boolean hasNextPage; //是否有下一页


	
	@SuppressWarnings("rawtypes")
	public PageInfo(List pageData, int recordeCount, int pageIndex, int pageSize) {
		this.pageData = pageData;
		this.recordeCount = recordeCount;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		
	
		if(pageIndex == pageCount) {
			this.pageSize = recordeCount - ((pageCount - 1) * pageSize);
		}
		this.setHasNextPage(this.isNextPageEnable());
		this.setHasPreviousPage(this.isPreviousPageEnable());
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getRecordeCount() {
		return recordeCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	@SuppressWarnings("rawtypes")
	public List getPageData() {
		return pageData;
	}

	public boolean isNextPageEnable() {
		return this.setHasNextPage(pageIndex < pageCount);
	}

	public boolean isPreviousPageEnable() {
		return this.setHasPreviousPage(pageIndex > 1);
	}

	public boolean setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
		return hasPreviousPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public boolean setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
		return hasNextPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	
}
