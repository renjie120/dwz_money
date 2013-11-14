package dwz.framework.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerNextTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2585520376304429512L;
	private int lastPage;
	private int nextPage;
	private int totaItems;
	private int numPerPage;
	private int currentPageNum;
	
	public int doStartTag() throws JspException{
		PagerInfoTag tag = (PagerInfoTag)findAncestorWithClass(this,PagerInfoTag.class);
		if(tag==null){
			throw new JspTagException("The root tag not found!");
		}
		try{
		totaItems = Integer.parseInt(tag.getTotalItems());
		numPerPage = Integer.parseInt(tag.getNumPerPage());
		currentPageNum = Integer.parseInt(tag.getCurrentPageNum());
		}catch(Exception e){
			throw new JspException(e);
		}
		lastPage = totaItems/numPerPage;
		if(totaItems%numPerPage!=0){
			lastPage++;
		}
		
		if(currentPageNum < lastPage)
			nextPage = currentPageNum + 1;
		else 
			nextPage = lastPage;
		
		if(currentPageNum==lastPage){
			return SKIP_BODY;
		}
		
		pageContext.setAttribute("nextPage", nextPage);
		return EVAL_BODY_INCLUDE;	
	}
	
	public int doEndTag() throws JspException{
		pageContext.removeAttribute("nextPage");
		return EVAL_PAGE;
	}

}