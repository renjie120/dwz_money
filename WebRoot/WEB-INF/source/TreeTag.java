package common.taglib;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import common.util.CommonUtil;

/**
 * DWZ的树形控件.
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class TreeTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String height;
	private String width;
	private String treeName;
	private String title;
	private String style;
	private boolean checkbox;
	private String checkFun;
	private String rootId;
	private List treeList;

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	} 

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}    

	public List getTreeList() {
		return treeList;
	}

	public void setTreeList(List treeList) {
		this.treeList = treeList;
	}

	public int doStartTag() throws JspException {
		if(CommonUtil.isBlank(height)){
			height = "300";
		}
		if(CommonUtil.isBlank(width)){
			width = "200";
		}
		if(CommonUtil.isBlank(style)){
			style = "float: left; display: block; margin: 10px; overflow: auto; width: "+width+"px; height: "+height+"px; border: solid 1px #CCC; line-height: 21px; background: #FFF;";
		}
		return super.doStartTag();
	}

	private synchronized String getXML() {
		 StringBuffer buf = new StringBuffer();
		 buf.append("<div style=\""+style+"\">");
		 buf.append("<p>").append(CommonUtil.notBlank(title, "")).append("</p>");
		 buf.append("<ul ");
		 //多选树.
		 if(checkbox){
			 buf.append("class=\"tree treeFolder treeCheck expand \" ");
		 }else{
			 buf.append("class=\"tree treeFolder expand \" ");
		 }
		 if(!CommonUtil.isEmpty(checkFun)){
			 buf.append(" oncheck=\""+checkFun+"\"");
		 }
		 buf.append(">");
		 buf.append(getTreeString(treeList));
		 buf.append("</div>");
		 return buf.toString();
	}
	
	private String getTreeString(List list) {
		if (list == null) {
			return "";
		}
		DwzTreeUtil tree = new DwzTreeUtil();
		StringBuffer buffer = new StringBuffer();
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			TreeNode node = (TreeNode) iterator.next();
			tree.addNode(node);
		}
		TreeNode rooTreeNode = tree.getNodeById(rootId);
		return tree.getDwzTreeString(rooTreeNode);
	}

	public int doEndTag() throws JspException { 
		try {
			pageContext.getOut().write(getXML());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	} 

	public String getCheckFun() {
		return checkFun;
	}

	public void setCheckFun(String checkFun) {
		this.checkFun = checkFun;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

}
