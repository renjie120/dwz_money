
package ido.InsuredUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.util.*; 

import com.alibaba.fastjson.JSON;

import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.cache.Cache;
import common.cache.CacheManager;
import common.util.CommonUtil;

import com.opensymphony.xwork2.ActionContext; 

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;
import money.tree.TreeManager;

import org.apache.struts2.ServletActionContext;

import ido.loginfo.LogInfoManager;
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
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
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
	
	// 下面的两个属性是用来接收依赖注入的属性----如果定义了file上传的属性为xxx，还要再定义两个属性用来封装类型和文件名！（如果不写怎么样？）
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	// 设置允许上传的文件类型
	private String allowTypes;
	// 下面的属性可以通过配置文件来配置，允许动态设置---典型的依赖注入---见这个action的配置文件。
	private String savePath;
	
	public String beforeAdd() {
		request.setAttribute("unitParentId", unitParentId);
		if(unitParentId!=0){
			Cache cache_insuredunit = CacheManager.getCacheInfoNotNull(AllSelectContants.INSUREDUNIT_DICT.getName());
			ParamSelect select_insuredunit = (ParamSelect)cache_insuredunit.getValue();
			request.setAttribute("ddd", select_insuredunit.getName(""+unitParentId));
		}
		else
			request.setAttribute("ddd", "投保单位");
		return "detail";
	}
 
 	/**
 	 * 添加投保单位.
 	 */
	public String doAdd() {
		try {
			InsuredUnitImpl insuredunitImpl = new InsuredUnitImpl(unitCode ,unitName ,contactName ,contactMobile ,contactEmail ,unitParentId ,unitState ,unitAddress ,unitRemark ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createInsuredUnit(insuredunitImpl);
			pMgr.addCache();
			//刷新保险单位树
			CacheManager.clearOnly(AllSelectContants.INSUREDTREE.getName());
			TreeManager tMgr = (TreeManager)SpringContextUtil.getBean(BeanManagerKey.treeManager.toString());
			tMgr.initInsuredCache();
			insertLog(logMgr,"添加投保单位","/doAdd", "", "" ,JSON.toJSONString(insuredunitImpl));  
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	/**
	 * 模板下载.
	 * 
	 * @return
	 */
	public String model() {
		response.setContentType("Application/excel");
		String fileNameString = CommonUtil.toUtf8String("result.xls");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ fileNameString);

		XlsExport e = new XlsExport();
		e.createRow(0);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.toString());
		}
		e.exportXls(response);
		return null;
	}
	
	/**
	 * 指向下载界面.
	 * 
	 * @return
	 */
	public String initImport() {
		return "import";
	}
	
	public String importExcel() throws Exception {
		if (CommonUtil.isEmpty(uploadFileName)
				|| !(uploadFileName.endsWith(".xls") || uploadFileName
						.endsWith(".xlsx"))) {
			writeToPage(response, "请上传excel文件!");
			return null;
		}
		// 得到文件后缀名
		String fileType = uploadFileName.substring(uploadFileName.indexOf("."),
				uploadFileName.length());
		// 得到新的文件名..防止重名.
		String newfile = uploadFileName.substring(0,
				uploadFileName.indexOf("."))
				+ getContextUser().getUserName()
				+ System.currentTimeMillis()
				+ "." + fileType;
		// 下面的文件上传路径先要保证在服务器上面已经存在！
		String desFileString = getSavePath() + "\\" + newfile;
		FileOutputStream fos = new FileOutputStream(desFileString);
		FileInputStream fis = new FileInputStream(upload);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		File f = new File(desFileString);
		if (f.exists()) {
			// 导入excel中的数据到数据库.
			pMgr.importFromExcel(f);
		}
		insertLog(logMgr,"导入投保单位","/importExcel", "", "" ,"");  
		pMgr.addCache();
		CacheManager.clearOnly(AllSelectContants.INSUREDTREE.getName());
		//刷新保险单位树
		TreeManager tMgr = (TreeManager)SpringContextUtil.getBean(BeanManagerKey.treeManager.toString());
		tMgr.initInsuredCache();
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<InsuredUnit> allDeleteIds = new ArrayList<InsuredUnit>();
		for(String _id:allId){
			if(!"".equals(_id))
				allDeleteIds.add(pMgr.getInsuredUnit(Integer.parseInt(_id)));
		}
		pMgr.removeInsuredUnits(ids);
		pMgr.addCache();
		CacheManager.clearOnly(AllSelectContants.INSUREDTREE.getName());
		//刷新保险单位树
		TreeManager tMgr = (TreeManager)SpringContextUtil.getBean(BeanManagerKey.treeManager.toString());
		tMgr.initInsuredCache();
		insertLog(logMgr,"删除投保单位","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getInsuredUnit(sno);
		return "editdetail";
	} 
	
	public String doUpdate() {
		try {
			InsuredUnit old = pMgr.getInsuredUnit( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getUnitCode(),unitCode)){
				oldObj += "unitCode="+old.getUnitCode()+";";
				newObj+= "unitCode="+unitCode+";";
			} 
			if(!compare(old.getUnitName(),unitName)){
				oldObj += "unitName="+old.getUnitName()+";";
				newObj+= "unitName="+unitName+";";
			} 
			if(!compare(old.getContactName(),contactName)){
				oldObj += "contactName="+old.getContactName()+";";
				newObj+= "contactName="+contactName+";";
			} 
			if(!compare(old.getContactMobile(),contactMobile)){
				oldObj += "contactMobile="+old.getContactMobile()+";";
				newObj+= "contactMobile="+contactMobile+";";
			} 
			if(!compare(old.getContactEmail(),contactEmail)){
				oldObj += "contactEmail="+old.getContactEmail()+";";
				newObj+= "contactEmail="+contactEmail+";";
			} 
			if(!compare(old.getUnitParentId(),unitParentId)){
				oldObj += "unitParentId="+old.getUnitParentId()+";";
				newObj+= "unitParentId="+unitParentId+";";
			} 
			if(!compare(old.getUnitState(),unitState)){
				oldObj += "unitState="+old.getUnitState()+";";
				newObj+= "unitState="+unitState+";";
			} 
			if(!compare(old.getUnitAddress(),unitAddress)){
				oldObj += "unitAddress="+old.getUnitAddress()+";";
				newObj+= "unitAddress="+unitAddress+";";
			} 
			if(!compare(old.getUnitRemark(),unitRemark)){
				oldObj += "unitRemark="+old.getUnitRemark()+";";
				newObj+= "unitRemark="+unitRemark+";";
			} 
			if(!compare(old.getCreateUser(),createUser)){
				oldObj += "createUser="+old.getCreateUser()+";";
				newObj+= "createUser="+createUser+";";
			} 
			if(!compare(old.getCreateTime(),createTime)){
				oldObj += "createTime="+old.getCreateTime()+";";
				newObj+= "createTime="+createTime+";";
			} 
			if(!compare(old.getUpdateUser(),updateUser)){
				oldObj += "updateUser="+old.getUpdateUser()+";";
				newObj+= "updateUser="+updateUser+";";
			} 
			if(!compare(old.getUpdateTime(),updateTime)){
				oldObj += "updateTime="+old.getUpdateTime()+";";
				newObj+= "updateTime="+updateTime+";";
			} 
			
			InsuredUnitImpl insuredunitImpl = new InsuredUnitImpl( sno , unitCode , unitName , contactName , contactMobile , contactEmail , unitParentId , unitState , unitAddress , unitRemark , createUser , createTime , updateUser , updateTime );
			pMgr.updateInsuredUnit(insuredunitImpl);
			pMgr.addCache();
			CacheManager.clearOnly(AllSelectContants.INSUREDTREE.getName());
			//刷新保险单位树
			TreeManager tMgr = (TreeManager)SpringContextUtil.getBean(BeanManagerKey.treeManager.toString());
			tMgr.initInsuredCache();
			insertLog(logMgr,"修改投保单位","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(insuredunitImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,UNITCODE("编号")  ,UNITNAME("投保单位 ")  ,CONTACTNAME("联系人")  ,CONTACTMOBILE("手机")  ,CONTACTEMAIL("邮箱")  ,UNITPARENTID("上级单位")  ,UNITSTATE("是否显示")  ,UNITADDRESS("地址");
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
	
	/**
	 * 查询子保险单位.
	 * @return
	 */
	public String queryByParent() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<InsuredUnitSearchFields, Object> criterias = getCriterias();

		Collection<InsuredUnit> moneyList = pMgr.searchInsuredUnit(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("unitParentId", unitParentId);
		int count = pMgr.searchInsuredUnitNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "list_small";
	}
	
	/**
	 * 返回新的保险单位的首页，左侧有一个树形.
	 * @return
	 */
	public String newList() { 
		return "newList";
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
		if (getUnitCode()!=null&&!"".equals(getUnitCode())&&!"-1".equals(getUnitCode())&&!"-2".equals(getUnitCode()))
			criterias.put(InsuredUnitSearchFields.UNITCODE, "%"+getUnitCode()+"%"); 
		if (getUnitName()!=null&&!"".equals(getUnitName())&&!"-1".equals(getUnitName())&&!"-2".equals(getUnitName()))
			criterias.put(InsuredUnitSearchFields.UNITNAME, "%"+getUnitName()+"%"); 
		if (getUnitParentId()!=0)
			criterias.put(InsuredUnitSearchFields.UNITPARENTID,  getUnitParentId() ); 
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
 	 * 获取是否显示的属性值.
 	 */
 	public String getUnitState(){
 		return unitState;
 	}
 	
 	/**
 	 * 设置是否显示的属性值.
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
  	
  	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
}
