package com.microblog.filter;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("rawtypes")
public class PageBean {
   private int totalRows;//总行数
   private int pageSize;//页大小，每页数量
   private int currentPage;//当前(第几)页
   @SuppressWarnings("unused")
   private int totalPages;//总页数
    private List data=new ArrayList();//每页内容(集合)
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalRows%pageSize==0?totalRows/pageSize:totalRows/pageSize+1;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
}
