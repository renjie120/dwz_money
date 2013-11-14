package dwz.framework.taglib;

import java.util.Collection;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerInfoTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String totalItems;

	private Collection collection;

	private String currentPageNum = "1";

	private String numPerPage = "20";

	private String pageNumShown = "10";

	private int count;

	public int doStartTag() throws JspException {

		if (collection == null) {
			return SKIP_BODY;
		}
		if (currentPageNum == null || currentPageNum == "") {
			currentPageNum = "1";
		}
		if (numPerPage == null || numPerPage == "") {
			numPerPage = "20";
		}

		if (pageNumShown == null || pageNumShown == "") {
			pageNumShown = "10";
		}
		try {
			count = Integer.parseInt(totalItems) / Integer.parseInt(numPerPage);
			if (Integer.parseInt(totalItems) % Integer.parseInt(numPerPage) != 0) {
				count++;
			}

			if (count < Integer.parseInt(pageNumShown)) {
				pageNumShown = String.valueOf(count);
			}

			if (Integer.parseInt(currentPageNum) < 1) {
				currentPageNum = "1";
			}
			if (Integer.parseInt(currentPageNum) > count) {
				currentPageNum = String.valueOf(count);
			}
		} catch (Exception e) {
			throw new JspException(e);
		}
		pageContext.setAttribute("currentPageNum", currentPageNum);
		pageContext.setAttribute("count", count);
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		pageContext.removeAttribute("currentPageNum");
		pageContext.removeAttribute("count");
		return EVAL_PAGE;
	}

	public String getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection coll) {
		this.collection = coll;
	}

	public String getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(String currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public String getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(String numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getPageNumShown() {

		return pageNumShown;
	}

	public void setPageNumShown(String pageNumShown) {
		this.pageNumShown = pageNumShown;
	}

}
