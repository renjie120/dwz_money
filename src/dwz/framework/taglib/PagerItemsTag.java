package dwz.framework.taglib;


import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerItemsTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3317096645296630097L;
	
	private Collection collection;
	private Iterator iterator;
	private int rowIndex=1;
	
	public int doStartTag() throws JspException{
		PagerInfoTag tag = (PagerInfoTag)findAncestorWithClass(this,PagerInfoTag.class);
		if(tag==null){
			throw new JspTagException("The root tag not found!");
		}
		collection = tag.getCollection();
		iterator = collection.iterator();
		
		if(iterator.hasNext()){
			pageContext.setAttribute("rowIndex", rowIndex);
			pageContext.setAttribute(getId(), iterator.next());
			return EVAL_BODY_AGAIN;
		}else{
			return SKIP_BODY;
		}
	}


	public int doAfterBody() throws JspException{
		rowIndex++;
		if(iterator.hasNext()){
			pageContext.setAttribute("rowIndex", rowIndex);
			pageContext.setAttribute(getId(), iterator.next());
			return EVAL_BODY_AGAIN;
		}else{
			return SKIP_BODY;
		}
	}
	
	public int doEndTag() throws JspException{
		pageContext.removeAttribute(getId());
		pageContext.removeAttribute("rowIndex");
		return SKIP_BODY;
	}
	
}
