package dwz.framework.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerNextpagesTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -235962647410910499L;

	private int pageNumShown;

	private int nextPages;

	private int currentPageNum;

	private int totaItems;

	private int numPerPage;

	private int lastPage;

	public int doStartTag() throws JspException {
		PagerInfoTag tag = (PagerInfoTag) findAncestorWithClass(this,
				PagerInfoTag.class);
		if (tag == null) {
			throw new JspTagException("The root tag not found!");
		}
		try{
		totaItems = Integer.parseInt(tag.getTotalItems());
		numPerPage = Integer.parseInt(tag.getNumPerPage());
		currentPageNum = Integer.parseInt(tag.getCurrentPageNum());
		pageNumShown = Integer.parseInt(tag.getPageNumShown());
		}catch(Exception e){
			throw new JspException(e);
		}
		lastPage = totaItems / numPerPage;
		if (totaItems % numPerPage != 0) {
			lastPage++;
		}

		nextPages = currentPageNum + 1;
		if (currentPageNum >= (lastPage / pageNumShown) * pageNumShown || lastPage>=(lastPage / pageNumShown) * pageNumShown )
			return SKIP_BODY;
		
		if (currentPageNum % pageNumShown == 0) {
			nextPages = (currentPageNum / pageNumShown) * pageNumShown + 1;
		} else {
			nextPages = (currentPageNum / pageNumShown + 1) * pageNumShown + 1;
		}
		pageContext.setAttribute("nextPages", nextPages);
		pageContext.setAttribute("pageNumShown", pageNumShown);
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		pageContext.removeAttribute("nextPages");
		pageContext.removeAttribute("pageNumShown");
		return EVAL_PAGE;
	}

}