package dwz.framework.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerCountTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5059981992769245053L;

	private int pageCount;

	private int totaItems;
	
	private int numPerPage;

	private int lastPage;

	@Override
	public int doStartTag() throws JspException {
		PagerInfoTag tag = (PagerInfoTag) findAncestorWithClass(this,
				PagerInfoTag.class);
		if (tag == null) {
			throw new JspTagException("The root tag not found!");
		}
		try{
		totaItems = Integer.parseInt(tag.getTotalItems());
		numPerPage = Integer.parseInt(tag.getNumPerPage());
		}catch(Exception e){
			throw new JspException(e);
		}
		pageCount = 1;
		lastPage = totaItems / numPerPage;
		if (totaItems % numPerPage != 0) {
			lastPage++;
		}
		
		if (pageCount <= lastPage) {
			pageContext.setAttribute("pageCount", pageCount);
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}

	}

	public int doAfterBody() throws JspException {
		pageCount++;
		if (pageCount <= lastPage) {
			pageContext.setAttribute("pageCount", pageCount);
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}

	public int doEndTag() throws JspException {
		pageContext.removeAttribute("pageCount");
		return SKIP_BODY;
	}

}
