
package ido.InsuredUnit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext; 
import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于投保单位的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredUnitAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	//业务接口对象.
	InsuredUnitManager pMgr = bf.getManager(BeanManagerKey.insuredunitManager);
	//业务实体对象
	private InsuredUnit vo;
	//当前页数
	private int page = 1;
	//每页显示数量
	private int pageSize = 50;
	//总页数
	private long count;
	
	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			InsuredUnitImpl insuredunitImpl = new InsuredUnitImpl(unitCode ,unitName ,contactName ,contactMobile ,contactEmail ,unitParentId ,unitState ,unitAddress ,unitRemark ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createInsuredUnit(insuredunitImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeInsuredUnits(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getInsuredUnit(sno);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			InsuredUnitImpl insuredunitImpl = new InsuredUnitImpl( sno , unitCode , unitName , contactName , contactMobile , contactEmail , unitParentId , unitState , unitAddress , unitRemark , createUser , createTime , updateUser , updateTime );
			pMgr.updateInsuredUnit(insuredunitImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号"),  UNITCODE("编号"),  UNITNAME("投保单位 "),  CONTACTNAME("联系人"),  CONTACTMOBILE("手机"),  CONTACTEMAIL("邮箱"),  UNITPARENTID("上级单位"),  UNITSTATE("状态"),  UNITADDRESS("地址"),  UNITREMARK("备注"),  CREATEUSER("创建用户"),  CREATETIME("创建时间"),  UPDATEUSER("更新用户"),  UPDATETIME("更新时间");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}

	public String beforeQuery() {
		return "query";
	}

	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=InsuredUnitList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<InsuredUnitSearchFields, Object> criterias = getCriterias();

		Collection<InsuredUnit> insuredunitList = pMgr.searchInsuredUnit(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (InsuredUnit insuredunit : insuredunitList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), insuredunit.getSno()); 
					break;
					case UNITCODE:
						 e.setCell(filed.ordinal(), insuredunit.getUnitCode()); 
					break;
					case UNITNAME:
						 e.setCell(filed.ordinal(), insuredunit.getUnitName()); 
					break;
					case CONTACTNAME:
						 e.setCell(filed.ordinal(), insuredunit.getContactName()); 
					break;
					case CONTACTMOBILE:
						 e.setCell(filed.ordinal(), insuredunit.getContactMobile()); 
					break;
					case CONTACTEMAIL:
						 e.setCell(filed.ordinal(), insuredunit.getContactEmail()); 
					break;
					case UNITPARENTID:
						 e.setCell(filed.ordinal(), insuredunit.getUnitParentId()); 
					break;
					case UNITSTATE:
						 e.setCell(filed.ordinal(), insuredunit.getUnitState()); 
					break;
					case UNITADDRESS:
						 e.setCell(filed.ordinal(), insuredunit.getUnitAddress()); 
					break;
					case UNITREMARK:
						 e.setCell(filed.ordinal(), insuredunit.getUnitRemark()); 
					break;
					case CREATEUSER:
						 e.setCell(filed.ordinal(), insuredunit.getCreateUser()); 
					break;
					case CREATETIME:
						 e.setCell(filed.ordinal(), insuredunit.getCreateTime()); 
					break;
					case UPDATEUSER:
						 e.setCell(filed.ordinal(), insuredunit.getUpdateUser()); 
					break;
					case UPDATETIME:
						 e.setCell(filed.ordinal(), insuredunit.getUpdateTime()); 
					break;
				default:
					break;
				}

			}
		}

		e.exportXls(response);
		return null;
	}

	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<InsuredUnitSearchFields, Object> criterias = getCriterias();

		Collection<InsuredUnit> moneyList = pMgr.searchInsuredUnit(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchInsuredUnitNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "list";
	}

	public String reQuery() {
		return "list";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	private Map<InsuredUnitSearchFields, Object> getCriterias() {
		Map<InsuredUnitSearchFields, Object> criterias = new HashMap<InsuredUnitSearchFields, Object>();
			if (getUnitCode()!=null&&!"".equals(getUnitCode()))
				criterias.put(InsuredUnitSearchFields.UNITCODE, "%"+getUnitCode()+"%"); 
			if (getUnitName()!=null&&!"".equals(getUnitName()))
				criterias.put(InsuredUnitSearchFields.UNITNAME, "%"+getUnitName()+"%"); 
		return criterias;
	}

	public InsuredUnit getVo() {
		return vo;
	}

	public void setVo(InsuredUnit vo) {
		this.vo = vo;
	} 
  
	private Integer sno; 
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public Integer getSno(){
 		return sno;
 	}
 	
 	/**
 	 * 设置流水号的属性值.
 	 */
 	public void setSno(Integer sno){
 		this.sno = sno;
 	}
	private String unitCode; 
 	/**
 	 * 获取编号的属性值.
 	 */
 	public String getUnitCode(){
 		return unitCode;
 	}
 	
 	/**
 	 * 设置编号的属性值.
 	 */
 	public void setUnitCode(String unitcode){
 		this.unitCode = unitcode;
 	}
	private String unitName; 
 	/**
 	 * 获取投保单位 的属性值.
 	 */
 	public String getUnitName(){
 		return unitName;
 	}
 	
 	/**
 	 * 设置投保单位 的属性值.
 	 */
 	public void setUnitName(String unitname){
 		this.unitName = unitname;
 	}
	private String contactName; 
 	/**
 	 * 获取联系人的属性值.
 	 */
 	public String getContactName(){
 		return contactName;
 	}
 	
 	/**
 	 * 设置联系人的属性值.
 	 */
 	public void setContactName(String contactname){
 		this.contactName = contactname;
 	}
	private String contactMobile; 
 	/**
 	 * 获取手机的属性值.
 	 */
 	public String getContactMobile(){
 		return contactMobile;
 	}
 	
 	/**
 	 * 设置手机的属性值.
 	 */
 	public void setContactMobile(String contactmobile){
 		this.contactMobile = contactmobile;
 	}
	private String contactEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getContactEmail(){
 		return contactEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setContactEmail(String contactemail){
 		this.contactEmail = contactemail;
 	}
	private int unitParentId; 
 	/**
 	 * 获取上级单位的属性值.
 	 */
 	public int getUnitParentId(){
 		return unitParentId;
 	}
 	
 	/**
 	 * 设置上级单位的属性值.
 	 */
 	public void setUnitParentId(int unitparentid){
 		this.unitParentId = unitparentid;
 	}
	private String unitState; 
 	/**
 	 * 获取状态的属性值.
 	 */
 	public String getUnitState(){
 		return unitState;
 	}
 	
 	/**
 	 * 设置状态的属性值.
 	 */
 	public void setUnitState(String unitstate){
 		this.unitState = unitstate;
 	}
	private String unitAddress; 
 	/**
 	 * 获取地址的属性值.
 	 */
 	public String getUnitAddress(){
 		return unitAddress;
 	}
 	
 	/**
 	 * 设置地址的属性值.
 	 */
 	public void setUnitAddress(String unitaddress){
 		this.unitAddress = unitaddress;
 	}
	private String unitRemark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getUnitRemark(){
 		return unitRemark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setUnitRemark(String unitremark){
 		this.unitRemark = unitremark;
 	}
	private int createUser; 
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public int getCreateUser(){
 		return createUser;
 	}
 	
 	/**
 	 * 设置创建用户的属性值.
 	 */
 	public void setCreateUser(int createuser){
 		this.createUser = createuser;
 	}
	private String createTime; 
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public String getCreateTime(){
 		return createTime;
 	}
 	
 	/**
 	 * 设置创建时间的属性值.
 	 */
 	public void setCreateTime(String createtime){
 		this.createTime = createtime;
 	}
	private int updateUser; 
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public int getUpdateUser(){
 		return updateUser;
 	}
 	
 	/**
 	 * 设置更新用户的属性值.
 	 */
 	public void setUpdateUser(int updateuser){
 		this.updateUser = updateuser;
 	}
	private String updateTime; 
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public String getUpdateTime(){
 		return updateTime;
 	}
 	
 	/**
 	 * 设置更新时间的属性值.
 	 */
 	public void setUpdateTime(String updatetime){
 		this.updateTime = updatetime;
 	}
}
