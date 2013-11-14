package common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 表格分页栏.
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class PageBarTag extends BodyTagSupport {
	private String name;
	private int totalCount;
	private int numPerPage;
	private int pageNumShown;
	private int  currentPage;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getPageNumShown() {
		return pageNumShown;
	}

	public void setPageNumShown(int pageNumShown) {
		this.pageNumShown = pageNumShown;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 构造函数.
	 * @coustructor method
	 */
	public PageBarTag() {
		super();
	}

	/**
	 * 标签结束方法.
	 * @return
	 * @throws JspException
	 */
	public int doEndTag() throws JspException {
		pageContext.setAttribute("pagebar", this);
		return super.doEndTag();
	}

	/**
	 * 标签开始方法.
	 * @return
	 * @throws JspException
	 */
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}
}
