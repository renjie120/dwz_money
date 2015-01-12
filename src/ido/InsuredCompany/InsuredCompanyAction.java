
package ido.InsuredCompany;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.util.*; 
import dwz.framework.constants.Constants;
import com.alibaba.fastjson.JSON;
import common.util.CommonUtil;
import common.util.DateTool;
import com.opensymphony.xwork2.ActionContext; 
import dwz.framework.user.User;
import dwz.framework.user.impl.UserImpl;
import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;
import org.apache.struts2.ServletActionContext;

import ido.loginfo.LogInfoManager;
/**
 * 关于保险公司的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredCompanyAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	InsuredCompanyManager pMgr = bf.getManager(BeanManagerKey.insuredcompanyManager);
	//业务实体对象
	private InsuredCompany vo;
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
		return "detail";
	}
  
 	/**
 	 * 添加保险公司.
 	 */
	public String doAdd() {
		try {
			User currentUser = (UserImpl) request.getSession().getAttribute(Constants.AUTHENTICATION_KEY);
			createUser = Integer.parseInt(currentUser.getId());
			createTime = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss");
			updateUser = Integer.parseInt(currentUser.getId());
			updateTime = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss");
			InsuredCompanyImpl insuredcompanyImpl = new InsuredCompanyImpl(comName ,comNo ,comShortName ,comPhone ,comContactName ,comContactPhone ,ownerCompany ,comEmail ,comAddress ,comRemark ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createInsuredCompany(insuredcompanyImpl);
			insertLog(logMgr,"添加保险公司","/doAdd", "", "" ,JSON.toJSONString(insuredcompanyImpl));  
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
		insertLog(logMgr,"导入保险公司","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		User currentUser = (UserImpl) request.getSession().getAttribute(Constants.AUTHENTICATION_KEY);
		createUser = Integer.parseInt(currentUser.getId());
		createTime = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss");
		updateUser = Integer.parseInt(currentUser.getId());
		updateTime = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss");
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<InsuredCompany> allDeleteIds = new ArrayList<InsuredCompany>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getInsuredCompany(Integer.parseInt(_id)));
		}
		pMgr.removeInsuredCompanys(ids);
		insertLog(logMgr,"删除保险公司","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getInsuredCompany(sno);
		return "editdetail";
	} 
	
	public String doUpdate() {
		try {
			// 当前用户
			User currentUser = (UserImpl) request.getSession().getAttribute(Constants.AUTHENTICATION_KEY);
			createUser = Integer.parseInt(currentUser.getId());
			createTime = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss");
			updateUser = Integer.parseInt(currentUser.getId());
			updateTime = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss");
			InsuredCompany old = pMgr.getInsuredCompany( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getComName(),comName)){
				oldObj += "comName="+old.getComName()+";";
				newObj+= "comName="+comName+";";
			} 
			if(!compare(old.getComNo(),comNo)){
				oldObj += "comNo="+old.getComNo()+";";
				newObj+= "comNo="+comNo+";";
			} 
			if(!compare(old.getComShortName(),comShortName)){
				oldObj += "comShortName="+old.getComShortName()+";";
				newObj+= "comShortName="+comShortName+";";
			} 
			if(!compare(old.getComPhone(),comPhone)){
				oldObj += "comPhone="+old.getComPhone()+";";
				newObj+= "comPhone="+comPhone+";";
			} 
			if(!compare(old.getComContactName(),comContactName)){
				oldObj += "comContactName="+old.getComContactName()+";";
				newObj+= "comContactName="+comContactName+";";
			} 
			if(!compare(old.getComContactPhone(),comContactPhone)){
				oldObj += "comContactPhone="+old.getComContactPhone()+";";
				newObj+= "comContactPhone="+comContactPhone+";";
			} 
			if(!compare(old.getOwnerCompany(),ownerCompany)){
				oldObj += "ownerCompany="+old.getOwnerCompany()+";";
				newObj+= "ownerCompany="+ownerCompany+";";
			} 
			if(!compare(old.getComEmail(),comEmail)){
				oldObj += "comEmail="+old.getComEmail()+";";
				newObj+= "comEmail="+comEmail+";";
			} 
			if(!compare(old.getComAddress(),comAddress)){
				oldObj += "comAddress="+old.getComAddress()+";";
				newObj+= "comAddress="+comAddress+";";
			} 
			if(!compare(old.getComRemark(),comRemark)){
				oldObj += "comRemark="+old.getComRemark()+";";
				newObj+= "comRemark="+comRemark+";";
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
			
			InsuredCompanyImpl insuredcompanyImpl = new InsuredCompanyImpl( sno , comName , comNo , comShortName , comPhone , comContactName , comContactPhone , ownerCompany , comEmail , comAddress , comRemark , createUser , createTime , updateUser , updateTime );
			pMgr.updateInsuredCompany(insuredcompanyImpl);
			insertLog(logMgr,"修改保险公司","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(insuredcompanyImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,COMNAME("保险公司名称")  ,COMNO("保险公司编号 ")  ,COMSHORTNAME("简称")  ,COMPHONE("电话")  ,COMCONTACTNAME("联系人名称")  ,COMCONTACTPHONE("联系人手机")  ,OWNERCOMPANY("所属保险公司")  ,COMEMAIL("邮箱");
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
		response.addHeader("Content-Disposition","attachment;filename=InsuredCompanyList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<InsuredCompanySearchFields, Object> criterias = getCriterias();

		Collection<InsuredCompany> insuredcompanyList = pMgr.searchInsuredCompany(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (InsuredCompany insuredcompany : insuredcompanyList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), insuredcompany.getSno()); 
					break;
					case COMNAME:
						 e.setCell(filed.ordinal(), insuredcompany.getComName()); 
					break;
					case COMNO:
						 e.setCell(filed.ordinal(), insuredcompany.getComNo()); 
					break;
					case COMSHORTNAME:
						 e.setCell(filed.ordinal(), insuredcompany.getComShortName()); 
					break;
					case COMPHONE:
						 e.setCell(filed.ordinal(), insuredcompany.getComPhone()); 
					break;
					case COMCONTACTNAME:
						 e.setCell(filed.ordinal(), insuredcompany.getComContactName()); 
					break;
					case COMCONTACTPHONE:
						 e.setCell(filed.ordinal(), insuredcompany.getComContactPhone()); 
					break;
					case OWNERCOMPANY:
						 e.setCell(filed.ordinal(), insuredcompany.getOwnerCompany()); 
					break;
					case COMEMAIL:
						 e.setCell(filed.ordinal(), insuredcompany.getComEmail()); 
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
		Map<InsuredCompanySearchFields, Object> criterias = getCriterias();

		Collection<InsuredCompany> moneyList = pMgr.searchInsuredCompany(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchInsuredCompanyNum(criterias);
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

	private Map<InsuredCompanySearchFields, Object> getCriterias() {
		Map<InsuredCompanySearchFields, Object> criterias = new HashMap<InsuredCompanySearchFields, Object>();
		if (getComName()!=null&&!"".equals(getComName())&&!"-1".equals(getComName())&&!"-2".equals(getComName()))
			criterias.put(InsuredCompanySearchFields.COMNAME, "%"+getComName()+"%"); 
		if (getComNo()!=null&&!"".equals(getComNo())&&!"-1".equals(getComNo())&&!"-2".equals(getComNo()))
			criterias.put(InsuredCompanySearchFields.COMNO, "%"+getComNo()+"%"); 
		return criterias;
	}

	public InsuredCompany getVo() {
		return vo;
	}

	public void setVo(InsuredCompany vo) {
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
	private String comName; 
 	/**
 	 * 获取保险公司名称的属性值.
 	 */
 	public String getComName(){
 		return comName;
 	}
 	
 	/**
 	 * 设置保险公司名称的属性值.
 	 */
 	public void setComName(String comname){
 		this.comName = comname;
 	}
	private String comNo; 
 	/**
 	 * 获取保险公司编号 的属性值.
 	 */
 	public String getComNo(){
 		return comNo;
 	}
 	
 	/**
 	 * 设置保险公司编号 的属性值.
 	 */
 	public void setComNo(String comno){
 		this.comNo = comno;
 	}
	private String comShortName; 
 	/**
 	 * 获取简称的属性值.
 	 */
 	public String getComShortName(){
 		return comShortName;
 	}
 	
 	/**
 	 * 设置简称的属性值.
 	 */
 	public void setComShortName(String comshortname){
 		this.comShortName = comshortname;
 	}
	private String comPhone; 
 	/**
 	 * 获取电话的属性值.
 	 */
 	public String getComPhone(){
 		return comPhone;
 	}
 	
 	/**
 	 * 设置电话的属性值.
 	 */
 	public void setComPhone(String comphone){
 		this.comPhone = comphone;
 	}
	private String comContactName; 
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public String getComContactName(){
 		return comContactName;
 	}
 	
 	/**
 	 * 设置联系人名称的属性值.
 	 */
 	public void setComContactName(String comcontactname){
 		this.comContactName = comcontactname;
 	}
	private String comContactPhone; 
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public String getComContactPhone(){
 		return comContactPhone;
 	}
 	
 	/**
 	 * 设置联系人手机的属性值.
 	 */
 	public void setComContactPhone(String comcontactphone){
 		this.comContactPhone = comcontactphone;
 	}
	private String ownerCompany; 
 	/**
 	 * 获取所属保险公司的属性值.
 	 */
 	public String getOwnerCompany(){
 		return ownerCompany;
 	}
 	
 	/**
 	 * 设置所属保险公司的属性值.
 	 */
 	public void setOwnerCompany(String ownercompany){
 		this.ownerCompany = ownercompany;
 	}
	private String comEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getComEmail(){
 		return comEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setComEmail(String comemail){
 		this.comEmail = comemail;
 	}
	private String comAddress; 
 	/**
 	 * 获取地址的属性值.
 	 */
 	public String getComAddress(){
 		return comAddress;
 	}
 	
 	/**
 	 * 设置地址的属性值.
 	 */
 	public void setComAddress(String comaddress){
 		this.comAddress = comaddress;
 	}
	private String comRemark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getComRemark(){
 		return comRemark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setComRemark(String comremark){
 		this.comRemark = comremark;
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
