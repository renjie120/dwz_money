package dwz.framework.taglib;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerPrepagesTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -291857911178556838L;
	private int pageNumShown;
	private int prePages;	 
	private int currentPageNum;
	
	
	public int doStartTag() throws JspException{
		PagerInfoTag tag = (PagerInfoTag)findAncestorWithClass(this,PagerInfoTag.class);
		if(tag==null){
			throw new JspTagException("The root tag not found!");
		}
		try{
		currentPageNum = Integer.parseInt(tag.getCurrentPageNum());
		pageNumShown = Integer.parseInt(tag.getPageNumShown());
		}catch(Exception e){
			throw new JspException(e);
		}
		if(currentPageNum <= pageNumShown)
			return SKIP_BODY;
		if(currentPageNum%pageNumShown==0){
			prePages=(currentPageNum/pageNumShown-2)*pageNumShown+1;
		}else{
			prePages=(currentPageNum/pageNumShown-1)*pageNumShown+1;
		}
		pageContext.setAttribute("prePages", prePages);
		pageContext.setAttribute("pageNumShown", pageNumShown);
		
		return EVAL_BODY_INCLUDE;	
	}
	
	public int doEndTag() throws JspException{
		pageContext.removeAttribute("prePages");
		pageContext.removeAttribute("pageNumShown");
		return EVAL_PAGE;
	}

}