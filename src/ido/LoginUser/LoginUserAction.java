
package ido.LoginUser;
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
 * 关于系统用户的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class LoginUserAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	LoginUserManager pMgr = bf.getManager(BeanManagerKey.loginuserManager);
	//业务实体对象
	private LoginUser vo;
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
	 * 初始化密码
	 * @return
	 */
	public String initPass() {
		String ids = request.getParameter("ids");
		pMgr.initPassword(ids);
		writeToPage(response,getText("msg.operation.success"));
//		System.out.println("-----"+getText("msg.operation.success"));
		//return ajaxForwardSuccess("密码已经初始化为\""+Coder.INIT_CODER+"\"");
		return ajaxForwardSuccess("密码修改成功初始化为"+Coder.INIT_CODER);
	}
	
	/**
	 * 添加保险公司下面的用户
	 * @return
	 */
	public String beforeAddCompanyUser() {
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_aiduyonghu = allSelect
				.getParamsByType(AllSelectContants.AIDUYONGHU.getName()); 
		request.setAttribute("userType", LoginUser.USER_TYPE_COMPANY);
		request.setAttribute("userTypeName", select_aiduyonghu.getName(LoginUser.USER_TYPE_COMPANY));
		request.setAttribute("userUnit", changeStr(getUserUnit()));
		return "companyUserDetail";
	}
	
	/**
	 * 添加商家集团用户
	 * @return
	 */
	public String beforeAddGroupUser() {
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_aiduyonghu = allSelect
				.getParamsByType(AllSelectContants.AIDUYONGHU.getName()); 
		request.setAttribute("userType", LoginUser.USER_TYPE_BUSINESS_GROUP);
		request.setAttribute("userTypeName", select_aiduyonghu.getName(LoginUser.USER_TYPE_BUSINESS_GROUP));
		request.setAttribute("userUnit", changeStr(getUserUnit()));
		return "groupUserDetail";
	}
	
	/**
	 * 添加保险公司下面的用户
	 * @return
	 */
	public String beforeAddUnitUser() {
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_aiduyonghu = allSelect
				.getParamsByType(AllSelectContants.AIDUYONGHU.getName()); 
		request.setAttribute("userTypeName", select_aiduyonghu.getName(LoginUser.USER_TYPE_INSURED_UNIT));
		request.setAttribute("userType", LoginUser.USER_TYPE_INSURED_UNIT);
		request.setAttribute("userUnit", changeStr(getUserUnit()));
		return "unitUserDetail";
	}
	
	/**
 	 * 添加系统用户.
 	 */
	public String doAddCompanyUser() {
		try {
			setCurrentUser(false);
			userPass = Coder.getMyCoder(userPass);
			LoginUserImpl loginuserImpl = new LoginUserImpl(userName ,userId ,userType ,userUnit ,userPass ,userStatus ,userPhone ,userEmail ,userAddress ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createLoginUser(loginuserImpl);
			pMgr.addCache();
			insertLog(logMgr,"添加保险公司用户","/doAdd", "", "" ,JSON.toJSONString(loginuserImpl));  
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}
	
	/**
 	 * 添加系统用户.
 	 */
	public String doAddGroupUser() {
		try {
			setCurrentUser(false);
			userPass = Coder.getMyCoder(userPass);
			LoginUserImpl loginuserImpl = new LoginUserImpl(userName ,userId ,userType ,userUnit ,userPass ,userStatus ,userPhone ,userEmail ,userAddress ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createLoginUser(loginuserImpl);
			pMgr.addCache();
			insertLog(logMgr,"添加商家集团用户","/doAdd", "", "" ,JSON.toJSONString(loginuserImpl));  
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}
	
 	/**
 	 * 添加系统用户.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			userPass = Coder.getMyCoder(userPass);
			LoginUserImpl loginuserImpl = new LoginUserImpl(userName ,userId ,userType ,userUnit ,userPass ,userStatus ,userPhone ,userEmail ,userAddress ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createLoginUser(loginuserImpl);
			pMgr.addCache();
			insertLog(logMgr,"添加系统用户","/doAdd", "", "" ,JSON.toJSONString(loginuserImpl));  
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}
	
	protected String changeStr(String oldStr){
        try{   
        return new String(oldStr.getBytes("ISO-8859-1"),"UTF-8");
        }catch(Exception e){
                return oldStr;
	    }
	}
	
	/**
	 * 返回保险公司用户
	 * @return
	 */
	public String getGroupUser() {
		int pageNum = 1;
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<LoginUserSearchFields, Object> criterias = new HashMap<LoginUserSearchFields, Object>(); 
		//用户公司类型
		criterias.put(LoginUserSearchFields.USERTYPE,  LoginUser.USER_TYPE_BUSINESS_GROUP);
		//用户单位名称
		userUnit = changeStr(getUserUnit());
		criterias.put(LoginUserSearchFields.USERUNIT,  userUnit);
		
		Collection<LoginUser> moneyList = pMgr.searchLoginUser(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("userType", LoginUser.USER_TYPE_BUSINESS_GROUP);
		request.setAttribute("userUnit", userUnit);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchLoginUserNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "groupUsers";
	} 
	
	/**
	 * 返回保险公司用户
	 * @return
	 */
	public String getCompanyUser() {
		int pageNum = 1;
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<LoginUserSearchFields, Object> criterias = new HashMap<LoginUserSearchFields, Object>(); 
		//用户公司类型
		criterias.put(LoginUserSearchFields.USERTYPE,  LoginUser.USER_TYPE_COMPANY);
		//用户单位名称
		userUnit = changeStr(getUserUnit());
		criterias.put(LoginUserSearchFields.USERUNIT,  userUnit);
		
		Collection<LoginUser> moneyList = pMgr.searchLoginUser(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("userType", LoginUser.USER_TYPE_COMPANY);
		request.setAttribute("userUnit", userUnit);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchLoginUserNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "companyUsers";
	} 
	
	/**
	 * 返回保险公司用户
	 * @return
	 */
	public String getUnitUser() {
		int pageNum = 1;
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<LoginUserSearchFields, Object> criterias = new HashMap<LoginUserSearchFields, Object>(); 
		//用户公司类型
		criterias.put(LoginUserSearchFields.USERTYPE,  LoginUser.USER_TYPE_INSURED_UNIT);
		//用户单位名称
		userUnit = changeStr(getUserUnit());
		criterias.put(LoginUserSearchFields.USERUNIT,  userUnit);
		
		Collection<LoginUser> moneyList = pMgr.searchLoginUser(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("userType", LoginUser.USER_TYPE_INSURED_UNIT);
		request.setAttribute("userUnit", userUnit);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchLoginUserNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "unitUsers";
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
		insertLog(logMgr,"导入系统用户","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<LoginUser> allDeleteIds = new ArrayList<LoginUser>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getLoginUser(Integer.parseInt(_id)));
		}
		pMgr.removeLoginUsers(ids);
		pMgr.addCache();
		insertLog(logMgr,"删除系统用户","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getLoginUser(sno);
		return "editdetail";
	} 
	
	private void setCurrentUser(boolean isUpdate){
		User currentUser = (UserImpl) request.getSession().getAttribute(Constants.AUTHENTICATION_KEY);
		if(!isUpdate){
			createUser = Integer.parseInt(currentUser.getId());
			createTime = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss"); 
		}else{
			updateUser = Integer.parseInt(currentUser.getId());
			updateTime = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss"); 
		}
	}
	
	public String doUpdate() {
		try {
			setCurrentUser(true);
			LoginUser old = pMgr.getLoginUser( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getUserName(),userName)){
				oldObj += "userName="+old.getUserName()+";";
				newObj+= "userName="+userName+";";
			} 
			if(!compare(old.getUserId(),userId)){
				oldObj += "userId="+old.getUserId()+";";
				newObj+= "userId="+userId+";";
			} 
			if(!compare(old.getUserType(),userType)){
				oldObj += "userType="+old.getUserType()+";";
				newObj+= "userType="+userType+";";
			} 
			if(!compare(old.getUserUnit(),userUnit)){
				oldObj += "userUnit="+old.getUserUnit()+";";
				newObj+= "userUnit="+userUnit+";";
			}  
			if(!compare(old.getUserStatus(),userStatus)){
				oldObj += "userStatus="+old.getUserStatus()+";";
				newObj+= "userStatus="+userStatus+";";
			} 
			if(!compare(old.getUserPhone(),userPhone)){
				oldObj += "userPhone="+old.getUserPhone()+";";
				newObj+= "userPhone="+userPhone+";";
			} 
			if(!compare(old.getUserEmail(),userEmail)){
				oldObj += "userEmail="+old.getUserEmail()+";";
				newObj+= "userEmail="+userEmail+";";
			} 
			if(!compare(old.getUserAddress(),userAddress)){
				oldObj += "userAddress="+old.getUserAddress()+";";
				newObj+= "userAddress="+userAddress+";";
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
			
			//密码
			if(CommonUtil.isEmpty(userPass))
				userPass = old.getUserPass();
			else
				userPass = Coder.getMyCoder(userPass);
			LoginUserImpl loginuserImpl = new LoginUserImpl( sno , userName , userId , userType , userUnit , userPass , userStatus , userPhone , userEmail , userAddress , createUser , createTime , updateUser , updateTime );
			pMgr.updateLoginUser(loginuserImpl);
			pMgr.addCache();
			insertLog(logMgr,"修改系统用户","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(loginuserImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,USERNAME("用户姓名")  ,USERPHONE("联系电话");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		  USERNAME("用户姓名")  ,USERPHONE("联系电话");
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
		ParamSelect select_aiduyonghu = allSelect
				.getParamsByType(AllSelectContants.AIDUYONGHU.getName()); 
		request.setAttribute("usertype _list", select_aiduyonghu.getSelectAbles()); 
		ParamSelect select_yesorno_status = allSelect
				.getParamsByType(AllSelectContants.YESORNO_STATUS.getName()); 
		request.setAttribute("userstatus _list", select_yesorno_status.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=LoginUserList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<LoginUserSearchFields, Object> criterias = getCriterias();

		Collection<LoginUser> loginuserList = pMgr.searchLoginUser(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (LoginUser loginuser : loginuserList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), loginuser.getSno()); 
					break;
					case USERNAME:
						 e.setCell(filed.ordinal(), loginuser.getUserName()); 
					break;
					case USERPHONE:
						 e.setCell(filed.ordinal(), loginuser.getUserPhone()); 
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
		Map<LoginUserSearchFields, Object> criterias = getCriterias();

		Collection<LoginUser> moneyList = pMgr.searchLoginUser(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchLoginUserNum(criterias);
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
		Map<LoginUserSearchFields, Object> criterias = getCriterias();

		Collection<LoginUser> moneyList = pMgr.searchLoginUser(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchLoginUserNum(criterias);
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

	private Map<LoginUserSearchFields, Object> getCriterias() {
		Map<LoginUserSearchFields, Object> criterias = new HashMap<LoginUserSearchFields, Object>();
		if (getUserName()!=null&&!"".equals(getUserName())&&!"-1".equals(getUserName())&&!"-2".equals(getUserName()))
			criterias.put(LoginUserSearchFields.USERNAME,  getUserName());
		if (getUserId()!=null&&!"".equals(getUserId())&&!"-1".equals(getUserId())&&!"-2".equals(getUserId()))
			criterias.put(LoginUserSearchFields.USERID,  getUserId());
		if (getUserType()!=null&&!"".equals(getUserType())&&!"-1".equals(getUserType())&&!"-2".equals(getUserType()))
			criterias.put(LoginUserSearchFields.USERTYPE,  getUserType());
		if (getUserUnit()!=null&&!"".equals(getUserUnit())&&!"-1".equals(getUserUnit())&&!"-2".equals(getUserUnit()))
			criterias.put(LoginUserSearchFields.USERUNIT,  getUserUnit());
		if (getUserStatus()!=null&&!"".equals(getUserStatus())&&!"-1".equals(getUserStatus())&&!"-2".equals(getUserStatus()))
			criterias.put(LoginUserSearchFields.USERSTATUS,  getUserStatus());
		
		//下面是高级查询的时候添加的条件
		//添加用户姓名的查询条件
		addUserNameCondition(criterias,getCondition_userName(),getQuery_userName());
		//添加登录名称 的查询条件
		addUserIdCondition(criterias,getCondition_userId(),getQuery_userId());
		//添加所属类别 的查询条件
		addUserTypeCondition(criterias,getCondition_userType(),getQuery_userType());
		//添加所属单位 的查询条件
		addUserUnitCondition(criterias,getCondition_userUnit(),getQuery_userUnit());
		//添加用户密码的查询条件
		addUserPassCondition(criterias,getCondition_userPass(),getQuery_userPass());
		//添加用户状态 的查询条件
		addUserStatusCondition(criterias,getCondition_userStatus(),getQuery_userStatus());
		//添加联系电话的查询条件
		addUserPhoneCondition(criterias,getCondition_userPhone(),getQuery_userPhone());
		//添加邮箱的查询条件
		addUserEmailCondition(criterias,getCondition_userEmail(),getQuery_userEmail());
		//添加地址的查询条件
		addUserAddressCondition(criterias,getCondition_userAddress(),getQuery_userAddress());
		//添加创建用户的查询条件
		addCreateUserCondition(criterias,getCondition_createUser(),getQuery_createUser());
		//添加创建时间的查询条件
		addCreateTimeCondition(criterias,getCondition_createTime(),getQuery_createTime());
		//添加更新用户的查询条件
		addUpdateUserCondition(criterias,getCondition_updateUser(),getQuery_updateUser());
		//添加更新时间的查询条件
		addUpdateTimeCondition(criterias,getCondition_updateTime(),getQuery_updateTime());
		return criterias;
	}
	
	//下面是添加高级查询的条件
	/**
	 * 添加查询用户姓名的查询条件.
	 */
	public void addUserNameCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERNAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERNAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询登录名称 的查询条件.
	 */
	public void addUserIdCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询所属类别 的查询条件.
	 */
	public void addUserTypeCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERTYPE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERTYPE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询所属单位 的查询条件.
	 */
	public void addUserUnitCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERUNIT_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERUNIT_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询用户密码的查询条件.
	 */
	public void addUserPassCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERPASS_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERPASS_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询用户状态 的查询条件.
	 */
	public void addUserStatusCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERSTATUS_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERSTATUS_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询联系电话的查询条件.
	 */
	public void addUserPhoneCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERPHONE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERPHONE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询邮箱的查询条件.
	 */
	public void addUserEmailCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USEREMAIL_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USEREMAIL_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询地址的查询条件.
	 */
	public void addUserAddressCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERADDRESS_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.USERADDRESS_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建用户的查询条件.
	 */
	public void addCreateUserCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新用户的查询条件.
	 */
	public void addUpdateUserCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.UPDATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.UPDATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新时间的查询条件.
	 */
	public void addUpdateTimeCondition(Map<LoginUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.UPDATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(LoginUserSearchFields.UPDATETIME_COM_NOT_EQUALS, value);
		} 
	} 

	public LoginUser getVo() {
		return vo;
	}

	public void setVo(LoginUser vo) {
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
	private String userName; 
 	/**
 	 * 获取用户姓名的属性值.
 	 */
 	public String getUserName(){
 		return userName;
 	}
 	
 	/**
 	 * 设置用户姓名的属性值.
 	 */
 	public void setUserName(String username){
 		this.userName = username;
 	}
	private String userId; 
 	/**
 	 * 获取登录名称 的属性值.
 	 */
 	public String getUserId(){
 		return userId;
 	}
 	
 	/**
 	 * 设置登录名称 的属性值.
 	 */
 	public void setUserId(String userid){
 		this.userId = userid;
 	}
	private String userType; 
 	/**
 	 * 获取所属类别 的属性值.
 	 */
 	public String getUserType(){
 		return userType;
 	}
 	
 	/**
 	 * 设置所属类别 的属性值.
 	 */
 	public void setUserType(String usertype){
 		this.userType = usertype;
 	}
	private String userUnit; 
 	/**
 	 * 获取所属单位 的属性值.
 	 */
 	public String getUserUnit(){
 		return userUnit;
 	}
 	
 	/**
 	 * 设置所属单位 的属性值.
 	 */
 	public void setUserUnit(String userunit){
 		this.userUnit = userunit;
 	}
	private String userPass; 
	/**
	 * 原来密码
	 */
	private String oldPass; 
 	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	/**
 	 * 获取用户密码的属性值.
 	 */
 	public String getUserPass(){
 		return userPass;
 	}
 	
 	/**
 	 * 设置用户密码的属性值.
 	 */
 	public void setUserPass(String userpass){
 		this.userPass = userpass;
 	}
	private String userStatus; 
 	/**
 	 * 获取用户状态 的属性值.
 	 */
 	public String getUserStatus(){
 		return userStatus;
 	}
 	
 	/**
 	 * 设置用户状态 的属性值.
 	 */
 	public void setUserStatus(String userstatus){
 		this.userStatus = userstatus;
 	}
	private String userPhone; 
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public String getUserPhone(){
 		return userPhone;
 	}
 	
 	/**
 	 * 设置联系电话的属性值.
 	 */
 	public void setUserPhone(String userphone){
 		this.userPhone = userphone;
 	}
	private String userEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getUserEmail(){
 		return userEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setUserEmail(String useremail){
 		this.userEmail = useremail;
 	}
	private String userAddress; 
 	/**
 	 * 获取地址的属性值.
 	 */
 	public String getUserAddress(){
 		return userAddress;
 	}
 	
 	/**
 	 * 设置地址的属性值.
 	 */
 	public void setUserAddress(String useraddress){
 		this.userAddress = useraddress;
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
	
	private String condition_userName;
	
	public String getCondition_userName(){
		return this.condition_userName;
	}
	
	public void setCondition_userName(String s){
		this.condition_userName = s;
	}
	
	private String query_userName;
		
	public String getQuery_userName(){
		return this.query_userName;
	}
	
	public void setQuery_userName(String s){
		this.query_userName = s;
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
	
	private String condition_userType;
	
	public String getCondition_userType(){
		return this.condition_userType;
	}
	
	public void setCondition_userType(String s){
		this.condition_userType = s;
	}
	
	private String query_userType;
		
	public String getQuery_userType(){
		return this.query_userType;
	}
	
	public void setQuery_userType(String s){
		this.query_userType = s;
	}
	
	private String condition_userUnit;
	
	public String getCondition_userUnit(){
		return this.condition_userUnit;
	}
	
	public void setCondition_userUnit(String s){
		this.condition_userUnit = s;
	}
	
	private String query_userUnit;
		
	public String getQuery_userUnit(){
		return this.query_userUnit;
	}
	
	public void setQuery_userUnit(String s){
		this.query_userUnit = s;
	}
	
	private String condition_userPass;
	
	public String getCondition_userPass(){
		return this.condition_userPass;
	}
	
	public void setCondition_userPass(String s){
		this.condition_userPass = s;
	}
	
	private String query_userPass;
		
	public String getQuery_userPass(){
		return this.query_userPass;
	}
	
	public void setQuery_userPass(String s){
		this.query_userPass = s;
	}
	
	private String condition_userStatus;
	
	public String getCondition_userStatus(){
		return this.condition_userStatus;
	}
	
	public void setCondition_userStatus(String s){
		this.condition_userStatus = s;
	}
	
	private String query_userStatus;
		
	public String getQuery_userStatus(){
		return this.query_userStatus;
	}
	
	public void setQuery_userStatus(String s){
		this.query_userStatus = s;
	}
	
	private String condition_userPhone;
	
	public String getCondition_userPhone(){
		return this.condition_userPhone;
	}
	
	public void setCondition_userPhone(String s){
		this.condition_userPhone = s;
	}
	
	private String query_userPhone;
		
	public String getQuery_userPhone(){
		return this.query_userPhone;
	}
	
	public void setQuery_userPhone(String s){
		this.query_userPhone = s;
	}
	
	private String condition_userEmail;
	
	public String getCondition_userEmail(){
		return this.condition_userEmail;
	}
	
	public void setCondition_userEmail(String s){
		this.condition_userEmail = s;
	}
	
	private String query_userEmail;
		
	public String getQuery_userEmail(){
		return this.query_userEmail;
	}
	
	public void setQuery_userEmail(String s){
		this.query_userEmail = s;
	}
	
	private String condition_userAddress;
	
	public String getCondition_userAddress(){
		return this.condition_userAddress;
	}
	
	public void setCondition_userAddress(String s){
		this.condition_userAddress = s;
	}
	
	private String query_userAddress;
		
	public String getQuery_userAddress(){
		return this.query_userAddress;
	}
	
	public void setQuery_userAddress(String s){
		this.query_userAddress = s;
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
	
	private String condition_updateUser;
	
	public String getCondition_updateUser(){
		return this.condition_updateUser;
	}
	
	public void setCondition_updateUser(String s){
		this.condition_updateUser = s;
	}
	
	private String query_updateUser;
		
	public String getQuery_updateUser(){
		return this.query_updateUser;
	}
	
	public void setQuery_updateUser(String s){
		this.query_updateUser = s;
	}
	
	private String condition_updateTime;
	
	public String getCondition_updateTime(){
		return this.condition_updateTime;
	}
	
	public void setCondition_updateTime(String s){
		this.condition_updateTime = s;
	}
	
	private String query_updateTime;
		
	public String getQuery_updateTime(){
		return this.query_updateTime;
	}
	
	public void setQuery_updateTime(String s){
		this.query_updateTime = s;
	}
	
}
