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
	/**
	 * 得到金额类型树.
	 * @return
	 */
	public String moneyTypeTree(){ 
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.setAttribute("treeType", "moneyType");
		return "tree";
	}
	
	/**
	 * 得到城市树.
	 * @return
	 */
	public String cityTree(){ 
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.setAttribute("treeType", "city");
		return "tree";
	}
	
	/**
	 * 得到菜单树.
	 * @return
	 */
	public String menuTree(){ 
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.setAttribute("treeType", "menu");
		return "tree";
	}
	
	/**
	 * 组织机构树 .
	 * @return
	 */
	public String orgTree(){ 
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.setAttribute("treeType", "org");
		return "tree";
	}
	
//	/**
//	 * 金额类型树的ZTREE树形字符串.
//	 * @return
//	 */
//	public String getMoneyTypeTree(){
//		HttpServletResponse response = ServletActionContext.getResponse(); 
//		writeToPage(response,tMgr.getMoneyTypeTree());
//		return null;
//	}
//	
	/**
	 * 组织机构json串
	 * @return
	 */
	public String getOrgTree(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		writeToPage(response,tMgr.getOrgTree());
		return null;
	}
	
	/**
	 * 得到组织机构人员树.
	 * @return
	 */
	public String getOrgWithPeopleTree(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		writeToPage(response,tMgr.getOrgWithPeopleTree(id));
		return null;
	}
	
	/**
	 * 返回参保单位的架构树.
	 * @return
	 */
	public String getInsuredLazyTree(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		writeToPage(response,tMgr.getInsuredTree(id));
		return null;
	}
	
	private String id; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 菜单树.
	 * @return
	 */
	public String getMenuTree(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		writeToPage(response,tMgr.getMenuTree());
		return null;
	} 
	
	/**
	 * 投保单位树
	 * @return
	 */
	public String getInsuredTree(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		writeToPage(response,tMgr.getInsuredTree());
		return null;
	} 
	
	/**
	 * 返回城市树.
	 * @return
	 */
	public String getCityTree(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		writeToPage(response,tMgr.getCityTree());
		return null;
	} 
	
	public String demo(){
		return "demo";
	}
}
