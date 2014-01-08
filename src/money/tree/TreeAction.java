package money.tree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import dwz.constants.BeanManagerKey;
import dwz.present.BaseAction;

/**
 * 树的查询控制.
 * @author lsq
 * 
 */
public class TreeAction extends BaseAction {
	TreeManager tMgr = bf.getManager(BeanManagerKey.treeManager);
	public String moneyTypeTree(){ 
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.setAttribute("treeType", "moneyType");
		return "tree";
	}
	
	public String menuTree(){ 
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.setAttribute("treeType", "menu");
		return "tree";
	}
	
	public String orgTree(){ 
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.setAttribute("treeType", "org");
		return "tree";
	}
	
	public String getMoneyTypeTree(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		writeToPage(response,tMgr.getMoneyTypeTree());
		return null;
	}
	
	public String getOrgTree(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		writeToPage(response,tMgr.getOrgTree());
		return null;
	}
	
	public String getOrgWithPeopleTree(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		writeToPage(response,tMgr.getOrgWithPeopleTree(id));
		return null;
	}
	
	private String id; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuTree(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		writeToPage(response,tMgr.getMenuTree());
		return null;
	}
	
	public String demo(){
		return "demo";
	}
}
