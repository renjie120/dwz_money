package common.dao;

import java.util.List;

public class PaginationSupport {

	public final static int PAGESIZE = 30;

	private int pageSize = PAGESIZE;

	private List items;

	private long totalCount; 

	private int startIndex = 0; 
	
	public PaginationSupport(List items, int totalCount) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);  
	}

	public PaginationSupport(List items, int totalCount, int startIndex) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setItems(items); 
		setStartIndex(startIndex);
	}

	public PaginationSupport(List items, int totalCount, int pageSize,
			int startIndex) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setItems(items); 
		setStartIndex(startIndex);
	}
	
	public PaginationSupport(List items, long totalCount, int pageSize,
			int startIndex) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setItems(items); 
		setStartIndex(startIndex);
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
 

	public long getTotalCount() {
		return totalCount;
	}
 
	public void setTotalCount(long totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount; 
		} else {
			this.totalCount = 0;
		}
	} 

	public int getStartIndex() {
		return startIndex;
	}
	
	public int getCurrentPage() { 
		int currentPage =  getStartIndex()/ getPageSize()+1;
		return currentPage;
	}  

	public int getNextIndex() {
		int nextIndex = getStartIndex() + pageSize;
		if (nextIndex >= totalCount)
			return getStartIndex();
		else
			return nextIndex;
	}

	public int getPreviousIndex() {
		int previousIndex = getStartIndex() - pageSize;
		if (previousIndex < 0)
			return 0;
		else
			return previousIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

}
