package com.lunarku.shop.common.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable{
	
	private List<SearchItem> itemList;
	private long recordCount;
	private long totalPages;
	
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
}
