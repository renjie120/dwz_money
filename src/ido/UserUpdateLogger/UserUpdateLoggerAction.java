
package ido.UserUpdateLogger;
import ido.InsuredUser.InsuredUser;
import ido.LoginUser.LoginUser;
import ido.loginfo.LogInfoManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.util.Coder;
import common.util.CommonUtil;
import common.util.DateTool;

import dwz.constants.BeanManagerKey;
import dwz.framework.constants.Constants;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.user.User;
import dwz.framework.user.impl.UserImpl;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;
/**
 * 关于用户状态修改记录的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class UserUpdateLoggerAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	UserUpdateLoggerManager pMgr = bf.getManager(BeanManagerKey.userupdateloggerManager);
	//业务实体对象
	private UserUpdateLogger vo;
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
	private String userName;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String beforeKaitong() { 
		request.setAttribute("userId", getUserId());
		request.setAttribute("state", InsuredUser.STATE_KAITONG);
		request.setAttribute("stateName", "已开通");
		userName = changeStr(getUserName());
		request.setAttribute("userName", userName);
		return "detail";
	}
	
	public String beforeDongjie() { 
		request.setAttribute("userId", getUserId());
		request.setAttribute("state", InsuredUser.STATE_DONGJIE);
		request.setAttribute("stateName", "已冻结");
		userName = changeStr(getUserName());
		request.setAttribute("userName", userName);
		return "detail";
	}
	
	public String beforeZhuxiao() { 
		request.setAttribute("userId", getUserId());
		request.setAttribute("state", InsuredUser.STATE_ZHUXIAO);
		request.setAttribute("stateName", "已注销");
		userName = changeStr(getUserName());
		request.setAttribute("userName", userName);
		return "detail";
	}
	
	public String beforeAdd() {
		return "detail";
	}
  
 	/**
 	 * 添加用户状态修改记录.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			UserUpdateLoggerImpl userupdateloggerImpl = new UserUpdateLoggerImpl(userId ,state ,logDetail ,arg1 ,createUser ,createTime );
			pMgr.createUserUpdateLogger(userupdateloggerImpl);
			insertLog(logMgr,"添加用户状态修改记录","/doAdd", "", "" ,JSON.toJSONString(userupdateloggerImpl));  
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		return ajaxForwardSuccess("操作成功!"); 
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
		insertLog(logMgr,"导入用户状态修改记录","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<UserUpdateLogger> allDeleteIds = new ArrayList<UserUpdateLogger>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getUserUpdateLogger(Integer.parseInt(_id)));
		}
		pMgr.removeUserUpdateLoggers(ids);
		insertLog(logMgr,"删除用户状态修改记录","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getUserUpdateLogger(sno);
		return "editdetail";
	} 
	
	private void setCurrentUser(boolean isUpdate){
		User currentUser = (UserImpl) request.getSession().getAttribute(Constants.AUTHENTICATION_KEY);
		if(!isUpdate){
			createUser = Integer.parseInt(currentUser.getId());
			createTime = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss"); 
		} 
	}
	
	public String doUpdate() {
		try {
			setCurrentUser(true);
			UserUpdateLogger old = pMgr.getUserUpdateLogger( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getUserId(),userId)){
				oldObj += "userId="+old.getUserId()+";";
				newObj+= "userId="+userId+";";
			} 
			if(!compare(old.getState(),state)){
				oldObj += "state="+old.getState()+";";
				newObj+= "state="+state+";";
			} 
			if(!compare(old.getLogDetail(),logDetail)){
				oldObj += "logDetail="+old.getLogDetail()+";";
				newObj+= "logDetail="+logDetail+";";
			} 
			if(!compare(old.getArg1(),arg1)){
				oldObj += "arg1="+old.getArg1()+";";
				newObj+= "arg1="+arg1+";";
			} 
			if(!compare(old.getCreateUser(),createUser)){
				oldObj += "createUser="+old.getCreateUser()+";";
				newObj+= "createUser="+createUser+";";
			} 
			if(!compare(old.getCreateTime(),createTime)){
				oldObj += "createTime="+old.getCreateTime()+";";
				newObj+= "createTime="+createTime+";";
			} 
			
			UserUpdateLoggerImpl userupdateloggerImpl = new UserUpdateLoggerImpl( sno , userId , state , logDetail , arg1 , createUser , createTime );
			pMgr.updateUserUpdateLogger(userupdateloggerImpl);
			insertLog(logMgr,"修改用户状态修改记录","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(userupdateloggerImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,USERID("用户 ")  ,STATE("用户状态")  ,LOGDETAIL("操作原因");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		  USERID("用户 ")  ,STATE("用户状态")  ,LOGDETAIL("操作原因");
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
		ParamSelect select_toubaouser_status = allSelect
				.getParamsByType(AllSelectContants.TOUBAOUSER_STATUS.getName()); 
		request.setAttribute("state_list", select_toubaouser_status.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=UserUpdateLoggerList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<UserUpdateLoggerSearchFields, Object> criterias = getCriterias();

		Collection<UserUpdateLogger> userupdateloggerList = pMgr.searchUserUpdateLogger(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (UserUpdateLogger userupdatelogger : userupdateloggerList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), userupdatelogger.getSno()); 
					break;
					case USERID:
						 e.setCell(filed.ordinal(), userupdatelogger.getUserId()); 
					break;
					case STATE:
						 e.setCell(filed.ordinal(), userupdatelogger.getState()); 
					break;
					case LOGDETAIL:
						 e.setCell(filed.ordinal(), userupdatelogger.getLogDetail()); 
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
		Map<UserUpdateLoggerSearchFields, Object> criterias = getCriterias();

		Collection<UserUpdateLogger> moneyList = pMgr.searchUserUpdateLogger(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchUserUpdateLoggerNum(criterias);
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
		Map<UserUpdateLoggerSearchFields, Object> criterias = getCriterias();

		Collection<UserUpdateLogger> moneyList = pMgr.searchUserUpdateLogger(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchUserUpdateLoggerNum(criterias);
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

	private Map<UserUpdateLoggerSearchFields, Object> getCriterias() {
		Map<UserUpdateLoggerSearchFields, Object> criterias = new HashMap<UserUpdateLoggerSearchFields, Object>();
		if (getUserId()!=null&&!"".equals(getUserId())&&!"-1".equals(getUserId())&&!"-2".equals(getUserId()))
			criterias.put(UserUpdateLoggerSearchFields.USERID,  getUserId());
		if (getState()!=null&&!"".equals(getState())&&!"-1".equals(getState())&&!"-2".equals(getState()))
			criterias.put(UserUpdateLoggerSearchFields.STATE,  getState());
		if (getLogDetail()!=null&&!"".equals(getLogDetail())&&!"-1".equals(getLogDetail())&&!"-2".equals(getLogDetail()))
			criterias.put(UserUpdateLoggerSearchFields.LOGDETAIL,  getLogDetail());
		
		//下面是高级查询的时候添加的条件
		//添加用户 的查询条件
		addUserIdCondition(criterias,getCondition_userId(),getQuery_userId());
		//添加用户状态的查询条件
		addStateCondition(criterias,getCondition_state(),getQuery_state());
		//添加操作原因的查询条件
		addLogDetailCondition(criterias,getCondition_logDetail(),getQuery_logDetail());
		//添加备用字段1的查询条件
		addArg1Condition(criterias,getCondition_arg1(),getQuery_arg1());
		//添加创建用户的查询条件
		addCreateUserCondition(criterias,getCondition_createUser(),getQuery_createUser());
		//添加创建时间的查询条件
		addCreateTimeCondition(criterias,getCondition_createTime(),getQuery_createTime());
		return criterias;
	}
	
	//下面是添加高级查询的条件
	/**
	 * 添加查询用户 的查询条件.
	 */
	public void addUserIdCondition(Map<UserUpdateLoggerSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.USERID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.USERID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询用户状态的查询条件.
	 */
	public void addStateCondition(Map<UserUpdateLoggerSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.STATE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.STATE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询操作原因的查询条件.
	 */
	public void addLogDetailCondition(Map<UserUpdateLoggerSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.LOGDETAIL_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.LOGDETAIL_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询备用字段1的查询条件.
	 */
	public void addArg1Condition(Map<UserUpdateLoggerSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.ARG1_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.ARG1_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建用户的查询条件.
	 */
	public void addCreateUserCondition(Map<UserUpdateLoggerSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<UserUpdateLoggerSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UserUpdateLoggerSearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 

	public UserUpdateLogger getVo() {
		return vo;
	}

	public void setVo(UserUpdateLogger vo) {
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
	private String userId; 
 	/**
 	 * 获取用户 的属性值.
 	 */
 	public String getUserId(){
 		return userId;
 	}
 	
 	/**
 	 * 设置用户 的属性值.
 	 */
 	public void setUserId(String userid){
 		this.userId = userid;
 	}
	private String state; 
 	/**
 	 * 获取用户状态的属性值.
 	 */
 	public String getState(){
 		return state;
 	}
 	
 	/**
 	 * 设置用户状态的属性值.
 	 */
 	public void setState(String state){
 		this.state = state;
 	}
	private String logDetail; 
 	/**
 	 * 获取操作原因的属性值.
 	 */
 	public String getLogDetail(){
 		return logDetail;
 	}
 	
 	/**
 	 * 设置操作原因的属性值.
 	 */
 	public void setLogDetail(String logdetail){
 		this.logDetail = logdetail;
 	}
	private int arg1; 
 	/**
 	 * 获取备用字段1的属性值.
 	 */
 	public int getArg1(){
 		return arg1;
 	}
 	
 	/**
 	 * 设置备用字段1的属性值.
 	 */
 	public void setArg1(int arg1){
 		this.arg1 = arg1;
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
	
	private String condition_userId;
	
	public String getCondition_userId(){
		return this.condition_userId;
	}
	
	public void setCondition_userId(String s){
		this.condition_userId = s;
	}
	
	private String query_userId;
		
	public String getQuery_userId(){
		return this.query_userId;
	}
	
	public void setQuery_userId(String s){
		this.query_userId = s;
	}
	
	private String condition_state;
	
	public String getCondition_state(){
		return this.condition_state;
	}
	
	public void setCondition_state(String s){
		this.condition_state = s;
	}
	
	private String query_state;
		
	public String getQuery_state(){
		return this.query_state;
	}
	
	public void setQuery_state(String s){
		this.query_state = s;
	}
	
	private String condition_logDetail;
	
	public String getCondition_logDetail(){
		return this.condition_logDetail;
	}
	
	public void setCondition_logDetail(String s){
		this.condition_logDetail = s;
	}
	
	private String query_logDetail;
		
	public String getQuery_logDetail(){
		return this.query_logDetail;
	}
	
	public void setQuery_logDetail(String s){
		this.query_logDetail = s;
	}
	
	private String condition_arg1;
	
	public String getCondition_arg1(){
		return this.condition_arg1;
	}
	
	public void setCondition_arg1(String s){
		this.condition_arg1 = s;
	}
	
	private String query_arg1;
		
	public String getQuery_arg1(){
		return this.query_arg1;
	}
	
	public void setQuery_arg1(String s){
		this.query_arg1 = s;
	}
	
	private String condition_createUser;
	
	public String getCondition_createUser(){
		return this.condition_createUser;
	}
	
	public void setCondition_createUser(String s){
		this.condition_createUser = s;
	}
	
	private String query_createUser;
		
	public String getQuery_createUser(){
		return this.query_createUser;
	}
	
	public void setQuery_createUser(String s){
		this.query_createUser = s;
	}
	
	private String condition_createTime;
	
	public String getCondition_createTime(){
		return this.condition_createTime;
	}
	
	public void setCondition_createTime(String s){
		this.condition_createTime = s;
	}
	
	private String query_createTime;
		
	public String getQuery_createTime(){
		return this.query_createTime;
	}
	
	public void setQuery_createTime(String s){
		this.query_createTime = s;
	}
	
}
