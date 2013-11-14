package querygridtree.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import querygridtree.bo.QueryGridTreeBO;
import dwz.present.BaseAction;

public class InitGridTreeAction extends BaseAction{ 
	private static final long serialVersionUID = 1L;
	private String backToUrl;
	private final Log logger = LogFactory.getLog(getClass());
	private QueryGridTreeBO queryGridTreeBO;

	public String getBackToUrl() {
		return backToUrl;
	}

	public void setBackToUrl(String backToUrl) {
		this.backToUrl = backToUrl;
	}
	
	public String demo(){   
		 return INPUT;
	} 
	
	/**
	 * 由部门联动带出项目+人员.
	 * @return
	 */
	public String getProjectByDepart(){ 
		 return null;
	}
	
	/**
	 * 由项目+部门 带出人员.
	 * @return
	 */
	public String getPeopleByProject(){ 
		 return null;
	} 
	
	public String gridTreeDemo() {  
		return INPUT;
	}
	
	public String newGridTreeDemo() {  
		return INPUT;
	}

	public QueryGridTreeBO getQueryGridTreeBO() {
		return queryGridTreeBO;
	}

	public void setQueryGridTreeBO(QueryGridTreeBO queryGridTreeBO) {
		this.queryGridTreeBO = queryGridTreeBO;
	}
}
