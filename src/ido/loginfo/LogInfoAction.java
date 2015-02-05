
package ido.loginfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.util.*; 
import dwz.framework.constants.Constants;
import com.alibaba.fastjson.JSON;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
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
import common.cache.Cache;
import common.cache.CacheManager;
import ido.loginfo.LogInfoManager;
import common.base.AllSelect;
import common.base.AllSelectContants;
/**
 * 关于操作日志的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class LogInfoAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	LogInfoManager pMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务实体对象
	private LogInfo vo;
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
 	 * 添加操作日志.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			LogInfoImpl loginfoImpl = new LogInfoImpl(operUser ,operUserName ,operTime ,operType ,operIp ,operUrl ,operBefore ,operAfter ,operDesc );
			pMgr.createLogInfo(loginfoImpl);
			insertLog(logMgr,"添加操作日志","/doAdd", "", "" ,JSON.toJSONString(loginfoImpl));  
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
		for (ImportFiled filed : ImportFiled.values()) {
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
		insertLog(logMgr,"导入操作日志","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<LogInfo> allDeleteIds = new ArrayList<LogInfo>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getLogInfo(Integer.parseInt(_id)));
		}
		pMgr.removeLogInfos(ids);
		insertLog(logMgr,"删除操作日志","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getLogInfo(sno);
		return "editdetail";
	} 
	
	private void setCurrentUser(boolean isUpdate){ 
	}
	
	public String doUpdate() {
		try {
			setCurrentUser(true);
			LogInfo old = pMgr.getLogInfo( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getOperUser(),operUser)){
				oldObj += "operUser="+old.getOperUser()+";";
				newObj+= "operUser="+operUser+";";
			} 
			if(!compare(old.getOperUserName(),operUserName)){
				oldObj += "operUserName="+old.getOperUserName()+";";
				newObj+= "operUserName="+operUserName+";";
			} 
			if(!compare(old.getOperTime(),operTime)){
				oldObj += "operTime="+old.getOperTime()+";";
				newObj+= "operTime="+operTime+";";
			} 
			if(!compare(old.getOperType(),operType)){
				oldObj += "operType="+old.getOperType()+";";
				newObj+= "operType="+operType+";";
			} 
			if(!compare(old.getOperIp(),operIp)){
				oldObj += "operIp="+old.getOperIp()+";";
				newObj+= "operIp="+operIp+";";
			} 
			if(!compare(old.getOperUrl(),operUrl)){
				oldObj += "operUrl="+old.getOperUrl()+";";
				newObj+= "operUrl="+operUrl+";";
			} 
			if(!compare(old.getOperBefore(),operBefore)){
				oldObj += "operBefore="+old.getOperBefore()+";";
				newObj+= "operBefore="+operBefore+";";
			} 
			if(!compare(old.getOperAfter(),operAfter)){
				oldObj += "operAfter="+old.getOperAfter()+";";
				newObj+= "operAfter="+operAfter+";";
			} 
			if(!compare(old.getOperDesc(),operDesc)){
				oldObj += "operDesc="+old.getOperDesc()+";";
				newObj+= "operDesc="+operDesc+";";
			} 
			
			LogInfoImpl loginfoImpl = new LogInfoImpl( sno , operUser , operUserName , operTime , operType , operIp , operUrl , operBefore , operAfter , operDesc );
			pMgr.updateLogInfo(loginfoImpl);
			insertLog(logMgr,"修改操作日志","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(loginfoImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		;
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		;
		private String str;

		ImportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
 
	
	/**
	 * 弹出高级查询界面.
	 * @return
	 */
	public String beforeQuery() {
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=LogInfoList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<LogInfoSearchFields, Object> criterias = getCriterias();

		Collection<LogInfo> loginfoList = pMgr.searchLogInfo(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (LogInfo loginfo : loginfoList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
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
		Map<LogInfoSearchFields, Object> criterias = getCriterias();

		Collection<LogInfo> moneyList = pMgr.searchLogInfo(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchLogInfoNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "list";
	}
	
	public String newQuery() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<LogInfoSearchFields, Object> criterias = getCriterias();

		Collection<LogInfo> moneyList = pMgr.searchLogInfo(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchLogInfoNum(criterias);
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

	private Map<LogInfoSearchFields, Object> getCriterias() {
		Map<LogInfoSearchFields, Object> criterias = new HashMap<LogInfoSearchFields, Object>();
		if (getOperTime()!=null&&!"".equals(getOperTime())&&!"-1".equals(getOperTime())&&!"-2".equals(getOperTime()))
			criterias.put(LogInfoSearchFields.OPERTIME,  getOperTime());
		
		//下面是高级查询的时候添加的条件
		//添加用户id的查询条件
		addOperUserCondition(criterias,getCondition_operUser(),getQuery_operUser());
		//添加用户的查询条件
		addOperUserNameCondition(criterias,getCondition_operUserName(),getQuery_operUserName());
		//添加时间 的查询条件
		addOperTimeCondition(criterias,getCondition_operTime(),getQuery_operTime());
		//添加操作类型的查询条件
		addOperTypeCondition(criterias,getCondition_operType(),getQuery_operType());
		//添加ip地址的查询条件
		addOperIpCondition(criterias,getCondition_operIp(),getQuery_operIp());
		//添加操作地址的查询条件
		addOperUrlCondition(criterias,getCondition_operUrl(),getQuery_operUrl());
		//添加修改前的查询条件
		addOperBeforeCondition(criterias,getCondition_operBefore(),getQuery_operBefore());
		//添加修改后的查询条件
		addOperAfterCondition(criterias,getCondition_operAfter(),getQuery_operAfter());
		//添加备注的查询条件
		addOperDescCondition(criterias,getCondition_operDesc(),getQuery_operDesc());
		return criterias;
	}
	
	//下面是添加高级查询的条件
	/**
	 * 添加查询用户id的查询条件.
	 */
	public void addOperUserCondition(Map<LogInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询用户的查询条件.
	 */
	public void addOperUserNameCondition(Map<LogInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERUSERNAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERUSERNAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询时间 的查询条件.
	 */
	public void addOperTimeCondition(Map<LogInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERTIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERTIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询操作类型的查询条件.
	 */
	public void addOperTypeCondition(Map<LogInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERTYPE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERTYPE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询ip地址的查询条件.
	 */
	public void addOperIpCondition(Map<LogInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERIP_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERIP_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询操作地址的查询条件.
	 */
	public void addOperUrlCondition(Map<LogInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERURL_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERURL_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询修改前的查询条件.
	 */
	public void addOperBeforeCondition(Map<LogInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERBEFORE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERBEFORE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询修改后的查询条件.
	 */
	public void addOperAfterCondition(Map<LogInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERAFTER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERAFTER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询备注的查询条件.
	 */
	public void addOperDescCondition(Map<LogInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERDESC_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LogInfoSearchFields.OPERDESC_COM_NOT_EQUALS, value);
		} 
	} 

	public LogInfo getVo() {
		return vo;
	}

	public void setVo(LogInfo vo) {
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
	private int operUser; 
 	/**
 	 * 获取用户id的属性值.
 	 */
 	public int getOperUser(){
 		return operUser;
 	}
 	
 	/**
 	 * 设置用户id的属性值.
 	 */
 	public void setOperUser(int operuser){
 		this.operUser = operuser;
 	}
	private String operUserName; 
 	/**
 	 * 获取用户的属性值.
 	 */
 	public String getOperUserName(){
 		return operUserName;
 	}
 	
 	/**
 	 * 设置用户的属性值.
 	 */
 	public void setOperUserName(String operusername){
 		this.operUserName = operusername;
 	}
	private String operTime; 
 	/**
 	 * 获取时间 的属性值.
 	 */
 	public String getOperTime(){
 		return operTime;
 	}
 	
 	/**
 	 * 设置时间 的属性值.
 	 */
 	public void setOperTime(String opertime){
 		this.operTime = opertime;
 	}
	private String operType; 
 	/**
 	 * 获取操作类型的属性值.
 	 */
 	public String getOperType(){
 		return operType;
 	}
 	
 	/**
 	 * 设置操作类型的属性值.
 	 */
 	public void setOperType(String opertype){
 		this.operType = opertype;
 	}
	private String operIp; 
 	/**
 	 * 获取ip地址的属性值.
 	 */
 	public String getOperIp(){
 		return operIp;
 	}
 	
 	/**
 	 * 设置ip地址的属性值.
 	 */
 	public void setOperIp(String operip){
 		this.operIp = operip;
 	}
	private String operUrl; 
 	/**
 	 * 获取操作地址的属性值.
 	 */
 	public String getOperUrl(){
 		return operUrl;
 	}
 	
 	/**
 	 * 设置操作地址的属性值.
 	 */
 	public void setOperUrl(String operurl){
 		this.operUrl = operurl;
 	}
	private String operBefore; 
 	/**
 	 * 获取修改前的属性值.
 	 */
 	public String getOperBefore(){
 		return operBefore;
 	}
 	
 	/**
 	 * 设置修改前的属性值.
 	 */
 	public void setOperBefore(String operbefore){
 		this.operBefore = operbefore;
 	}
	private String operAfter; 
 	/**
 	 * 获取修改后的属性值.
 	 */
 	public String getOperAfter(){
 		return operAfter;
 	}
 	
 	/**
 	 * 设置修改后的属性值.
 	 */
 	public void setOperAfter(String operafter){
 		this.operAfter = operafter;
 	}
	private String operDesc; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getOperDesc(){
 		return operDesc;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setOperDesc(String operdesc){
 		this.operDesc = operdesc;
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
	
	/*************  下面自动生成高级查询相关代码           ********************/
	private String condition_sno;
	
	public String getCondition_sno(){
		return this.condition_sno;
	}
	
	public void setCondition_sno(String s){
		this.condition_sno = s;
	}
	
	private String query_sno;
		
	public String getQuery_sno(){
		return this.query_sno;
	}
	
	public void setQuery_sno(String s){
		this.query_sno = s;
	}
	
	private String condition_operUser;
	
	public String getCondition_operUser(){
		return this.condition_operUser;
	}
	
	public void setCondition_operUser(String s){
		this.condition_operUser = s;
	}
	
	private String query_operUser;
		
	public String getQuery_operUser(){
		return this.query_operUser;
	}
	
	public void setQuery_operUser(String s){
		this.query_operUser = s;
	}
	
	private String condition_operUserName;
	
	public String getCondition_operUserName(){
		return this.condition_operUserName;
	}
	
	public void setCondition_operUserName(String s){
		this.condition_operUserName = s;
	}
	
	private String query_operUserName;
		
	public String getQuery_operUserName(){
		return this.query_operUserName;
	}
	
	public void setQuery_operUserName(String s){
		this.query_operUserName = s;
	}
	
	private String condition_operTime;
	
	public String getCondition_operTime(){
		return this.condition_operTime;
	}
	
	public void setCondition_operTime(String s){
		this.condition_operTime = s;
	}
	
	private String query_operTime;
		
	public String getQuery_operTime(){
		return this.query_operTime;
	}
	
	public void setQuery_operTime(String s){
		this.query_operTime = s;
	}
	
	private String condition_operType;
	
	public String getCondition_operType(){
		return this.condition_operType;
	}
	
	public void setCondition_operType(String s){
		this.condition_operType = s;
	}
	
	private String query_operType;
		
	public String getQuery_operType(){
		return this.query_operType;
	}
	
	public void setQuery_operType(String s){
		this.query_operType = s;
	}
	
	private String condition_operIp;
	
	public String getCondition_operIp(){
		return this.condition_operIp;
	}
	
	public void setCondition_operIp(String s){
		this.condition_operIp = s;
	}
	
	private String query_operIp;
		
	public String getQuery_operIp(){
		return this.query_operIp;
	}
	
	public void setQuery_operIp(String s){
		this.query_operIp = s;
	}
	
	private String condition_operUrl;
	
	public String getCondition_operUrl(){
		return this.condition_operUrl;
	}
	
	public void setCondition_operUrl(String s){
		this.condition_operUrl = s;
	}
	
	private String query_operUrl;
		
	public String getQuery_operUrl(){
		return this.query_operUrl;
	}
	
	public void setQuery_operUrl(String s){
		this.query_operUrl = s;
	}
	
	private String condition_operBefore;
	
	public String getCondition_operBefore(){
		return this.condition_operBefore;
	}
	
	public void setCondition_operBefore(String s){
		this.condition_operBefore = s;
	}
	
	private String query_operBefore;
		
	public String getQuery_operBefore(){
		return this.query_operBefore;
	}
	
	public void setQuery_operBefore(String s){
		this.query_operBefore = s;
	}
	
	private String condition_operAfter;
	
	public String getCondition_operAfter(){
		return this.condition_operAfter;
	}
	
	public void setCondition_operAfter(String s){
		this.condition_operAfter = s;
	}
	
	private String query_operAfter;
		
	public String getQuery_operAfter(){
		return this.query_operAfter;
	}
	
	public void setQuery_operAfter(String s){
		this.query_operAfter = s;
	}
	
	private String condition_operDesc;
	
	public String getCondition_operDesc(){
		return this.condition_operDesc;
	}
	
	public void setCondition_operDesc(String s){
		this.condition_operDesc = s;
	}
	
	private String query_operDesc;
		
	public String getQuery_operDesc(){
		return this.query_operDesc;
	}
	
	public void setQuery_operDesc(String s){
		this.query_operDesc = s;
	}
	
}
