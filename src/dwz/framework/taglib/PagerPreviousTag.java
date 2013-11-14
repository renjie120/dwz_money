package dwz.framework.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerPreviousTag extends TagSupport{
/**
	 * 
	 */
	private static final long serialVersionUID = -8087949809819566869L;

	private int prePage;

	private int currentPageNum;
	
	public int doStartTag() throws JspException{
		PagerInfoTag tag = (PagerInfoTag)findAncestorWithClass(this,PagerInfoTag.class);

		if(tag==null){
			throw new JspTagException("The root tag not found!");
		}
		try{
		currentPageNum = Integer.parseInt(tag.getCurrentPageNum());
		}catch(Exception e){
			throw new JspException(e);
		}
		if(currentPageNum>1)
			prePage = currentPageNum - 1;
		else 
			prePage = 1;
		if(currentPageNum<=1){
			return SKIP_BODY;
		}
		pageContext.setAttribute("prePage", prePage);
		return EVAL_BODY_INCLUDE;	
	}
	
	public int doEndTag() throws JspException{
		pageContext.removeAttribute("prePage");
		return EVAL_PAGE;
	}

}