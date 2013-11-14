package querygridtree.data;

import java.math.BigDecimal;

import common.util.CommonUtil;

public class TaskVO {
	Object[] obj;
	private String isParent;
	private String taskId;
	private String projectName;
	private String nodeName;
	private String encharge;
	private String approved;
	private String wremark;
	private String palnstart;
	private String planend;
	private String taskkeyword;
	private String department;
	/**
	 * 工时
	 */
	private String attention; 
	private String parenttid; 
	private String description;
	private String taskParentPath; 
	/**
	 * 计算出来的子任务的成本之和.
	 */
	private String subCost;

	public String getDescription() {
		return (String)obj[1];
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectName() {
		return (String)obj[2];
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getNodeName() {
		return (String)obj[3];
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getEncharge() {
		return (String)obj[4];
	}

	public void setEncharge(String encharge) {
		this.encharge = encharge;
	}

	public String getApproved() {
		return ((BigDecimal)obj[5]).toString(); 
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getWremark() {
		return (String)obj[6];
	}

	public void setWremark(String wremark) {
		this.wremark = wremark;
	}

	public String getPalnstart() {
		return (String)obj[7];
	}

	public void setPalnstart(String palnstart) {
		this.palnstart = palnstart;
	}

	public String getPlanend() {
		return (String)obj[8];
	}

	public void setPlanend(String planend) {
		this.planend = planend;
	}

	public String getTaskkeyword() {
		return (String)obj[11];
	}

	public void setTaskkeyword(String taskkeyword) {
		this.taskkeyword = taskkeyword;
	}

	public String getAttention() {
		//return ((BigDecimal)obj[9]).toString();
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getParenttid() {
		return CommonUtil.notBlank((BigDecimal)obj[12]); 
	}

	public void setParenttid(String parenttid) {
		this.parenttid = parenttid;
	} 
 
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public TaskVO() {

	}

	public TaskVO(Object[] obj) {
		this.obj = obj;
	}
	
	public String getTaskId(){
		return ((BigDecimal)obj[0]).toString();
	}
	 

	public String getTaskParentPath(){
		return (obj[14]).toString();
	}

	public void setTaskParentPath(String taskParentPath) {
		this.taskParentPath = taskParentPath;
	} 

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

//	public String getSubHour() {
//		return subHour;
//	}
//
//	public void setSubHour(String subHour) {
//		this.subHour = subHour;
//	}

	public String getSubCost() {
		return subCost;
	}

	public void setSubCost(String subCost) {
		this.subCost = subCost;
	}

	public String getDepartment() {
		return (obj[15]).toString();
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
