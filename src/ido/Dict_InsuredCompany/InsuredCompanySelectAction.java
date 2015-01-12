
package ido.Dict_InsuredCompany;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.util.*; 

import com.alibaba.fastjson.JSON;
import common.util.CommonUtil;

import com.opensymphony.xwork2.ActionContext; 

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;
import org.apache.struts2.ServletActionContext;

import ido.loginfo.LogInfoManager;
/**
 * 关于保险公司字典表的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredCompanySelectAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	InsuredCompanySelectManager pMgr = bf.getManager(BeanManagerKey.insuredcompanyselectManager);
	//业务实体对象
	private InsuredCompanySelect vo;
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
 	 * 添加保险公司字典表.
 	 */
	public String doAdd() {
		try {
			InsuredCompanySelectImpl insuredcompanyselectImpl = new InsuredCompanySelectImpl(comName ,comStatus ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createInsuredCompanySelect(insuredcompanyselectImpl);
			pMgr.addCache();
			insertLog(logMgr,"添加保险公司字典表","/doAdd", "", "" ,JSON.toJSONString(insuredcompanyselectImpl));  
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
		insertLog(logMgr,"导入保险公司字典表","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<InsuredCompanySelect> allDeleteIds = new ArrayList<InsuredCompanySelect>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getInsuredCompanySelect(Integer.parseInt(_id)));
		}
		pMgr.removeInsuredCompanySelects(ids);
		pMgr.addCache();
		insertLog(logMgr,"删除保险公司字典表","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getInsuredCompanySelect(sno);
		return "editdetail";
	} 
	
	public String doUpdate() {
		try {
			InsuredCompanySelect old = pMgr.getInsuredCompanySelect( sno );
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
			if(!compare(old.getComStatus(),comStatus)){
				oldObj += "comStatus="+old.getComStatus()+";";
				newObj+= "comStatus="+comStatus+";";
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
			
			InsuredCompanySelectImpl insuredcompanyselectImpl = new InsuredCompanySelectImpl( sno , comName , comStatus , createUser , createTime , updateUser , updateTime );
			pMgr.updateInsuredCompanySelect(insuredcompanyselectImpl);
			pMgr.addCache();
			insertLog(logMgr,"修改保险公司字典表","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(insuredcompanyselectImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,COMNAME("保险公司名称");
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
		response.addHeader("Content-Disposition","attachment;filename=InsuredCompanySelectList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<InsuredCompanySelectSearchFields, Object> criterias = getCriterias();

		Collection<InsuredCompanySelect> insuredcompanyselectList = pMgr.searchInsuredCompanySelect(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (InsuredCompanySelect insuredcompanyselect : insuredcompanyselectList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), insuredcompanyselect.getSno()); 
					break;
					case COMNAME:
						 e.setCell(filed.ordinal(), insuredcompanyselect.getComName()); 
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
		Map<InsuredCompanySelectSearchFields, Object> criterias = getCriterias();

		Collection<InsuredCompanySelect> moneyList = pMgr.searchInsuredCompanySelect(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchInsuredCompanySelectNum(criterias);
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

	private Map<InsuredCompanySelectSearchFields, Object> getCriterias() {
		Map<InsuredCompanySelectSearchFields, Object> criterias = new HashMap<InsuredCompanySelectSearchFields, Object>();
		if (getComName()!=null&&!"".equals(getComName())&&!"-1".equals(getComName())&&!"-2".equals(getComName()))
			criterias.put(InsuredCompanySelectSearchFields.COMNAME, "%"+getComName()+"%"); 
		if (getComStatus()!=null&&!"".equals(getComStatus())&&!"-1".equals(getComStatus())&&!"-2".equals(getComStatus()))
			criterias.put(InsuredCompanySelectSearchFields.COMSTATUS,  getComStatus());
		return criterias;
	}

	public InsuredCompanySelect getVo() {
		return vo;
	}

	public void setVo(InsuredCompanySelect vo) {
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
	private String comStatus; 
 	/**
 	 * 获取是否显示 的属性值.
 	 */
 	public String getComStatus(){
 		return comStatus;
 	}
 	
 	/**
 	 * 设置是否显示 的属性值.
 	 */
 	public void setComStatus(String comstatus){
 		this.comStatus = comstatus;
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
