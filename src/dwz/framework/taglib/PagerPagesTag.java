package dwz.framework.taglib;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;


public class PagerPagesTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1757519602179918415L;
	private int totaItems;
	private int currentPageNum;
	private int numPerPage;
	private int pageNumShown;
	private int from;
	private int to;
	private int pageNum;
	private int lastPage;
	
	public int doStartTag() throws JspException{
		PagerInfoTag tag = (PagerInfoTag)findAncestorWithClass(this,PagerInfoTag.class);
		if(tag==null){
			throw new JspTagException("The root tag not found!");
		}
		try{
		totaItems = Integer.parseInt(tag.getTotalItems());
		currentPageNum = Integer.parseInt(tag.getCurrentPageNum());
		numPerPage = Integer.parseInt(tag.getNumPerPage());
		pageNumShown = Integer.parseInt(tag.getPageNumShown());
		}catch(Exception e){
			throw new JspException(e);
		}
		lastPage = totaItems/numPerPage;
		if (lastPage < 1) return SKIP_BODY;
		if(totaItems%numPerPage!=0){
			lastPage++;
		}
		if(currentPageNum%pageNumShown==0){
			from=(currentPageNum/pageNumShown-1)*pageNumShown+1;
		}else{
			from=(currentPageNum/pageNumShown)*pageNumShown+1;
		}
		
		to = from + pageNumShown - 1;
		
		if(to > lastPage)
			to = lastPage;
		
		pageNum = from;
		if(pageNum <= to){
			pageContext.setAttribute("pageNum", pageNum);
			return EVAL_BODY_AGAIN;
		}else{
			return SKIP_BODY;
		}		
	}

	public int doAfterBody() throws JspException{
		pageNum++;
		if (lastPage < 1) return SKIP_BODY;
		if(pageNum <= to){
			pageContext.setAttribute("pageNum", pageNum);
			return EVAL_BODY_AGAIN;
		}else{
			return SKIP_BODY;
		}		
	}
	
	public int doEndTag() throws JspException{
		pageContext.removeAttribute("pageNum");
		return SKIP_BODY;
	}
}
