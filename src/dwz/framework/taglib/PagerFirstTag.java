package dwz.framework.taglib;




import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerFirstTag extends TagSupport{

	private static final long serialVersionUID = -6717631041489730918L;
	/**
	 * 
	 */
	
	private int firstPage = 1;
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
		if(currentPageNum<=1){
			return SKIP_BODY;
		}
		pageContext.setAttribute("firstPage", new Integer(firstPage));
		return EVAL_BODY_INCLUDE;	
	}
	
	public int doEndTag() throws JspException{
		pageContext.removeAttribute("firstPage");
		return EVAL_PAGE;
	}

}
