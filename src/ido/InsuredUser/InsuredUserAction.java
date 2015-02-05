
package ido.InsuredUser;
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
 * 关于投保用户的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredUserAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	InsuredUserManager pMgr = bf.getManager(BeanManagerKey.insureduserManager);
	//业务实体对象
	private InsuredUser vo;
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
 	 * 添加投保用户.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			InsuredUserImpl insureduserImpl = new InsuredUserImpl(iuserNo ,comId ,unitId ,iuserStatus ,iuserNumber ,leftMoney ,emergencyMoney ,frozenMoney ,hospitalMoney ,tesMoney ,iuserName ,iuserIsman ,iuserCardno ,iuserPhone ,iuserEmail ,iuserBirthday ,iuserRemark ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createInsuredUser(insureduserImpl);
			insertLog(logMgr,"添加投保用户","/doAdd", "", "" ,JSON.toJSONString(insureduserImpl));  
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
		insertLog(logMgr,"导入投保用户","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<InsuredUser> allDeleteIds = new ArrayList<InsuredUser>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getInsuredUser(Integer.parseInt(_id)));
		}
		pMgr.removeInsuredUsers(ids);
		insertLog(logMgr,"删除投保用户","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getInsuredUser(sno); 
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
			InsuredUser old = pMgr.getInsuredUser( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getIuserNo(),iuserNo)){
				oldObj += "iuserNo="+old.getIuserNo()+";";
				newObj+= "iuserNo="+iuserNo+";";
			} 
			if(!compare(old.getComId(),comId)){
				oldObj += "comId="+old.getComId()+";";
				newObj+= "comId="+comId+";";
			} 
			if(!compare(old.getUnitId(),unitId)){
				oldObj += "unitId="+old.getUnitId()+";";
				newObj+= "unitId="+unitId+";";
			} 
			if(!compare(old.getIuserStatus(),iuserStatus)){
				oldObj += "iuserStatus="+old.getIuserStatus()+";";
				newObj+= "iuserStatus="+iuserStatus+";";
			} 
			if(!compare(old.getIuserNumber(),iuserNumber)){
				oldObj += "iuserNumber="+old.getIuserNumber()+";";
				newObj+= "iuserNumber="+iuserNumber+";";
			} 
			if(!compare(old.getLeftMoney(),leftMoney)){
				oldObj += "leftMoney="+old.getLeftMoney()+";";
				newObj+= "leftMoney="+leftMoney+";";
			} 
			if(!compare(old.getEmergencyMoney(),emergencyMoney)){
				oldObj += "emergencyMoney="+old.getEmergencyMoney()+";";
				newObj+= "emergencyMoney="+emergencyMoney+";";
			} 
			if(!compare(old.getFrozenMoney(),frozenMoney)){
				oldObj += "frozenMoney="+old.getFrozenMoney()+";";
				newObj+= "frozenMoney="+frozenMoney+";";
			} 
			if(!compare(old.getHospitalMoney(),hospitalMoney)){
				oldObj += "hospitalMoney="+old.getHospitalMoney()+";";
				newObj+= "hospitalMoney="+hospitalMoney+";";
			} 
			if(!compare(old.getTesMoney(),tesMoney)){
				oldObj += "tesMoney="+old.getTesMoney()+";";
				newObj+= "tesMoney="+tesMoney+";";
			} 
			if(!compare(old.getIuserName(),iuserName)){
				oldObj += "iuserName="+old.getIuserName()+";";
				newObj+= "iuserName="+iuserName+";";
			} 
			if(!compare(old.getIuserIsman(),iuserIsman)){
				oldObj += "iuserIsman="+old.getIuserIsman()+";";
				newObj+= "iuserIsman="+iuserIsman+";";
			} 
			if(!compare(old.getIuserCardno(),iuserCardno)){
				oldObj += "iuserCardno="+old.getIuserCardno()+";";
				newObj+= "iuserCardno="+iuserCardno+";";
			} 
			if(!compare(old.getIuserPhone(),iuserPhone)){
				oldObj += "iuserPhone="+old.getIuserPhone()+";";
				newObj+= "iuserPhone="+iuserPhone+";";
			} 
			if(!compare(old.getIuserEmail(),iuserEmail)){
				oldObj += "iuserEmail="+old.getIuserEmail()+";";
				newObj+= "iuserEmail="+iuserEmail+";";
			} 
			if(!compare(old.getIuserBirthday(),iuserBirthday)){
				oldObj += "iuserBirthday="+old.getIuserBirthday()+";";
				newObj+= "iuserBirthday="+iuserBirthday+";";
			} 
			if(!compare(old.getIuserRemark(),iuserRemark)){
				oldObj += "iuserRemark="+old.getIuserRemark()+";";
				newObj+= "iuserRemark="+iuserRemark+";";
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
			
			InsuredUserImpl insureduserImpl = new InsuredUserImpl( sno , iuserNo , comId , unitId , iuserStatus , iuserNumber , leftMoney , emergencyMoney , frozenMoney , hospitalMoney , tesMoney , iuserName , iuserIsman , iuserCardno , iuserPhone , iuserEmail , iuserBirthday , iuserRemark , createUser , createTime , updateUser , updateTime );
			pMgr.updateInsuredUser(insureduserImpl);
			insertLog(logMgr,"修改投保用户","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(insureduserImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,IUSERNO("投保用户编号")  ,IUSERNUMBER("员工号");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		  IUSERNO("投保用户编号")  ,IUSERNUMBER("员工号");
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
		Cache cache_comId = CacheManager.getCacheInfoNotNull(AllSelectContants.INSURED_COM_DICT.getName());
		ParamSelect select_comId = (ParamSelect)cache_comId.getValue();
		request.setAttribute("comid_list", select_comId.getSelectAbles()); 
		ParamSelect select_toubaouser_status = allSelect
				.getParamsByType(AllSelectContants.TOUBAOUSER_STATUS.getName()); 
		request.setAttribute("iuserstatus_list", select_toubaouser_status.getSelectAbles()); 
		ParamSelect select_sex = allSelect
				.getParamsByType(AllSelectContants.SEX.getName()); 
		request.setAttribute("iuserisman_list", select_sex.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=InsuredUserList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<InsuredUserSearchFields, Object> criterias = getCriterias();

		Collection<InsuredUser> insureduserList = pMgr.searchInsuredUser(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (InsuredUser insureduser : insureduserList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), insureduser.getSno()); 
					break;
					case IUSERNO:
						 e.setCell(filed.ordinal(), insureduser.getIuserNo()); 
					break;
					case IUSERNUMBER:
						 e.setCell(filed.ordinal(), insureduser.getIuserNumber()); 
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
		Map<InsuredUserSearchFields, Object> criterias = getCriterias();

		Collection<InsuredUser> moneyList = pMgr.searchInsuredUser(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchInsuredUserNum(criterias);
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
		Map<InsuredUserSearchFields, Object> criterias = getCriterias();

		Collection<InsuredUser> moneyList = pMgr.searchInsuredUser(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchInsuredUserNum(criterias);
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

	private Map<InsuredUserSearchFields, Object> getCriterias() {
		Map<InsuredUserSearchFields, Object> criterias = new HashMap<InsuredUserSearchFields, Object>();
		if (getIuserNo()!=null&&!"".equals(getIuserNo())&&!"-1".equals(getIuserNo())&&!"-2".equals(getIuserNo()))
			criterias.put(InsuredUserSearchFields.IUSERNO,  getIuserNo());
		if (getComId()!=null&&!"".equals(getComId())&&!"-1".equals(getComId())&&!"-2".equals(getComId()))
			criterias.put(InsuredUserSearchFields.COMID,  getComId());
		if (getUnitId()!=null&&!"".equals(getUnitId())&&!"-1".equals(getUnitId())&&!"-2".equals(getUnitId()))
			criterias.put(InsuredUserSearchFields.UNITID,  getUnitId());
		if (getIuserStatus()!=null&&!"".equals(getIuserStatus())&&!"-1".equals(getIuserStatus())&&!"-2".equals(getIuserStatus()))
			criterias.put(InsuredUserSearchFields.IUSERSTATUS,  getIuserStatus());
		
		//下面是高级查询的时候添加的条件
		//添加投保用户编号的查询条件
		addIuserNoCondition(criterias,getCondition_iuserNo(),getQuery_iuserNo());
		//添加所投保险公司 的查询条件
		addComIdCondition(criterias,getCondition_comId(),getQuery_comId());
		//添加所属投保单位 的查询条件
		addUnitIdCondition(criterias,getCondition_unitId(),getQuery_unitId());
		//添加状态 的查询条件
		addIuserStatusCondition(criterias,getCondition_iuserStatus(),getQuery_iuserStatus());
		//添加员工号的查询条件
		addIuserNumberCondition(criterias,getCondition_iuserNumber(),getQuery_iuserNumber());
		//添加余额的查询条件
		addLeftMoneyCondition(criterias,getCondition_leftMoney(),getQuery_leftMoney());
		//添加门急诊额度的查询条件
		addEmergencyMoneyCondition(criterias,getCondition_emergencyMoney(),getQuery_emergencyMoney());
		//添加冻结金额的查询条件
		addFrozenMoneyCondition(criterias,getCondition_frozenMoney(),getQuery_frozenMoney());
		//添加住院报销额度的查询条件
		addHospitalMoneyCondition(criterias,getCondition_hospitalMoney(),getQuery_hospitalMoney());
		//添加体检额度的查询条件
		addTesMoneyCondition(criterias,getCondition_tesMoney(),getQuery_tesMoney());
		//添加姓名的查询条件
		addIuserNameCondition(criterias,getCondition_iuserName(),getQuery_iuserName());
		//添加性别的查询条件
		addIuserIsmanCondition(criterias,getCondition_iuserIsman(),getQuery_iuserIsman());
		//添加证件号的查询条件
		addIuserCardnoCondition(criterias,getCondition_iuserCardno(),getQuery_iuserCardno());
		//添加手机号的查询条件
		addIuserPhoneCondition(criterias,getCondition_iuserPhone(),getQuery_iuserPhone());
		//添加邮箱的查询条件
		addIuserEmailCondition(criterias,getCondition_iuserEmail(),getQuery_iuserEmail());
		//添加生日的查询条件
		addIuserBirthdayCondition(criterias,getCondition_iuserBirthday(),getQuery_iuserBirthday());
		//添加备注的查询条件
		addIuserRemarkCondition(criterias,getCondition_iuserRemark(),getQuery_iuserRemark());
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
	 * 添加查询投保用户编号的查询条件.
	 */
	public void addIuserNoCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERNO_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERNO_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询所投保险公司 的查询条件.
	 */
	public void addComIdCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.COMID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.COMID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询所属投保单位 的查询条件.
	 */
	public void addUnitIdCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.UNITID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.UNITID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询状态 的查询条件.
	 */
	public void addIuserStatusCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERSTATUS_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERSTATUS_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询员工号的查询条件.
	 */
	public void addIuserNumberCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERNUMBER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERNUMBER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询余额的查询条件.
	 */
	public void addLeftMoneyCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.LEFTMONEY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.LEFTMONEY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询门急诊额度的查询条件.
	 */
	public void addEmergencyMoneyCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.EMERGENCYMONEY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.EMERGENCYMONEY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询冻结金额的查询条件.
	 */
	public void addFrozenMoneyCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.FROZENMONEY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.FROZENMONEY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询住院报销额度的查询条件.
	 */
	public void addHospitalMoneyCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.HOSPITALMONEY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.HOSPITALMONEY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询体检额度的查询条件.
	 */
	public void addTesMoneyCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.TESMONEY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.TESMONEY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询姓名的查询条件.
	 */
	public void addIuserNameCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERNAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERNAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询性别的查询条件.
	 */
	public void addIuserIsmanCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERISMAN_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERISMAN_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询证件号的查询条件.
	 */
	public void addIuserCardnoCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERCARDNO_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERCARDNO_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询手机号的查询条件.
	 */
	public void addIuserPhoneCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERPHONE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERPHONE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询邮箱的查询条件.
	 */
	public void addIuserEmailCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSEREMAIL_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSEREMAIL_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询生日的查询条件.
	 */
	public void addIuserBirthdayCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERBIRTHDAY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERBIRTHDAY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询备注的查询条件.
	 */
	public void addIuserRemarkCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERREMARK_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.IUSERREMARK_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建用户的查询条件.
	 */
	public void addCreateUserCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新用户的查询条件.
	 */
	public void addUpdateUserCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.UPDATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.UPDATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新时间的查询条件.
	 */
	public void addUpdateTimeCondition(Map<InsuredUserSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.UPDATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredUserSearchFields.UPDATETIME_COM_NOT_EQUALS, value);
		} 
	} 

	public InsuredUser getVo() {
		return vo;
	}

	public void setVo(InsuredUser vo) {
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
	private String iuserNo; 
 	/**
 	 * 获取投保用户编号的属性值.
 	 */
 	public String getIuserNo(){
 		return iuserNo;
 	}
 	
 	/**
 	 * 设置投保用户编号的属性值.
 	 */
 	public void setIuserNo(String iuserno){
 		this.iuserNo = iuserno;
 	}
	private String comId; 
 	/**
 	 * 获取所投保险公司 的属性值.
 	 */
 	public String getComId(){
 		return comId;
 	}
 	
 	/**
 	 * 设置所投保险公司 的属性值.
 	 */
 	public void setComId(String comid){
 		this.comId = comid;
 	}
	private String unitId; 
 	/**
 	 * 获取所属投保单位 的属性值.
 	 */
 	public String getUnitId(){
 		return unitId;
 	}
 	
 	/**
 	 * 设置所属投保单位 的属性值.
 	 */
 	public void setUnitId(String unitid){
 		this.unitId = unitid;
 	}
	private String iuserStatus; 
 	/**
 	 * 获取状态 的属性值.
 	 */
 	public String getIuserStatus(){
 		return iuserStatus;
 	}
 	
 	/**
 	 * 设置状态 的属性值.
 	 */
 	public void setIuserStatus(String iuserstatus){
 		this.iuserStatus = iuserstatus;
 	}
	private String iuserNumber; 
 	/**
 	 * 获取员工号的属性值.
 	 */
 	public String getIuserNumber(){
 		return iuserNumber;
 	}
 	
 	/**
 	 * 设置员工号的属性值.
 	 */
 	public void setIuserNumber(String iusernumber){
 		this.iuserNumber = iusernumber;
 	}
	private double leftMoney; 
 	/**
 	 * 获取余额的属性值.
 	 */
 	public double getLeftMoney(){
 		return leftMoney;
 	}
 	
 	/**
 	 * 设置余额的属性值.
 	 */
 	public void setLeftMoney(double leftmoney){
 		this.leftMoney = leftmoney;
 	}
	private double emergencyMoney; 
 	/**
 	 * 获取门急诊额度的属性值.
 	 */
 	public double getEmergencyMoney(){
 		return emergencyMoney;
 	}
 	
 	/**
 	 * 设置门急诊额度的属性值.
 	 */
 	public void setEmergencyMoney(double emergencymoney){
 		this.emergencyMoney = emergencymoney;
 	}
	private double frozenMoney; 
 	/**
 	 * 获取冻结金额的属性值.
 	 */
 	public double getFrozenMoney(){
 		return frozenMoney;
 	}
 	
 	/**
 	 * 设置冻结金额的属性值.
 	 */
 	public void setFrozenMoney(double frozenmoney){
 		this.frozenMoney = frozenmoney;
 	}
	private double hospitalMoney; 
 	/**
 	 * 获取住院报销额度的属性值.
 	 */
 	public double getHospitalMoney(){
 		return hospitalMoney;
 	}
 	
 	/**
 	 * 设置住院报销额度的属性值.
 	 */
 	public void setHospitalMoney(double hospitalmoney){
 		this.hospitalMoney = hospitalmoney;
 	}
	private double tesMoney; 
 	/**
 	 * 获取体检额度的属性值.
 	 */
 	public double getTesMoney(){
 		return tesMoney;
 	}
 	
 	/**
 	 * 设置体检额度的属性值.
 	 */
 	public void setTesMoney(double tesmoney){
 		this.tesMoney = tesmoney;
 	}
	private String iuserName; 
 	/**
 	 * 获取姓名的属性值.
 	 */
 	public String getIuserName(){
 		return iuserName;
 	}
 	
 	/**
 	 * 设置姓名的属性值.
 	 */
 	public void setIuserName(String iusername){
 		this.iuserName = iusername;
 	}
	private String iuserIsman; 
 	/**
 	 * 获取性别的属性值.
 	 */
 	public String getIuserIsman(){
 		return iuserIsman;
 	}
 	
 	/**
 	 * 设置性别的属性值.
 	 */
 	public void setIuserIsman(String iuserisman){
 		this.iuserIsman = iuserisman;
 	}
	private String iuserCardno; 
 	/**
 	 * 获取证件号的属性值.
 	 */
 	public String getIuserCardno(){
 		return iuserCardno;
 	}
 	
 	/**
 	 * 设置证件号的属性值.
 	 */
 	public void setIuserCardno(String iusercardno){
 		this.iuserCardno = iusercardno;
 	}
	private String iuserPhone; 
 	/**
 	 * 获取手机号的属性值.
 	 */
 	public String getIuserPhone(){
 		return iuserPhone;
 	}
 	
 	/**
 	 * 设置手机号的属性值.
 	 */
 	public void setIuserPhone(String iuserphone){
 		this.iuserPhone = iuserphone;
 	}
	private String iuserEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getIuserEmail(){
 		return iuserEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setIuserEmail(String iuseremail){
 		this.iuserEmail = iuseremail;
 	}
	private String iuserBirthday; 
 	/**
 	 * 获取生日的属性值.
 	 */
 	public String getIuserBirthday(){
 		return iuserBirthday;
 	}
 	
 	/**
 	 * 设置生日的属性值.
 	 */
 	public void setIuserBirthday(String iuserbirthday){
 		this.iuserBirthday = iuserbirthday;
 	}
	private String iuserRemark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getIuserRemark(){
 		return iuserRemark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setIuserRemark(String iuserremark){
 		this.iuserRemark = iuserremark;
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
	
	private String condition_iuserNo;
	
	public String getCondition_iuserNo(){
		return this.condition_iuserNo;
	}
	
	public void setCondition_iuserNo(String s){
		this.condition_iuserNo = s;
	}
	
	private String query_iuserNo;
		
	public String getQuery_iuserNo(){
		return this.query_iuserNo;
	}
	
	public void setQuery_iuserNo(String s){
		this.query_iuserNo = s;
	}
	
	private String condition_comId;
	
	public String getCondition_comId(){
		return this.condition_comId;
	}
	
	public void setCondition_comId(String s){
		this.condition_comId = s;
	}
	
	private String query_comId;
		
	public String getQuery_comId(){
		return this.query_comId;
	}
	
	public void setQuery_comId(String s){
		this.query_comId = s;
	}
	
	private String condition_unitId;
	
	public String getCondition_unitId(){
		return this.condition_unitId;
	}
	
	public void setCondition_unitId(String s){
		this.condition_unitId = s;
	}
	
	private String query_unitId;
		
	public String getQuery_unitId(){
		return this.query_unitId;
	}
	
	public void setQuery_unitId(String s){
		this.query_unitId = s;
	}
	
	private String condition_iuserStatus;
	
	public String getCondition_iuserStatus(){
		return this.condition_iuserStatus;
	}
	
	public void setCondition_iuserStatus(String s){
		this.condition_iuserStatus = s;
	}
	
	private String query_iuserStatus;
		
	public String getQuery_iuserStatus(){
		return this.query_iuserStatus;
	}
	
	public void setQuery_iuserStatus(String s){
		this.query_iuserStatus = s;
	}
	
	private String condition_iuserNumber;
	
	public String getCondition_iuserNumber(){
		return this.condition_iuserNumber;
	}
	
	public void setCondition_iuserNumber(String s){
		this.condition_iuserNumber = s;
	}
	
	private String query_iuserNumber;
		
	public String getQuery_iuserNumber(){
		return this.query_iuserNumber;
	}
	
	public void setQuery_iuserNumber(String s){
		this.query_iuserNumber = s;
	}
	
	private String condition_leftMoney;
	
	public String getCondition_leftMoney(){
		return this.condition_leftMoney;
	}
	
	public void setCondition_leftMoney(String s){
		this.condition_leftMoney = s;
	}
	
	private String query_leftMoney;
		
	public String getQuery_leftMoney(){
		return this.query_leftMoney;
	}
	
	public void setQuery_leftMoney(String s){
		this.query_leftMoney = s;
	}
	
	private String condition_emergencyMoney;
	
	public String getCondition_emergencyMoney(){
		return this.condition_emergencyMoney;
	}
	
	public void setCondition_emergencyMoney(String s){
		this.condition_emergencyMoney = s;
	}
	
	private String query_emergencyMoney;
		
	public String getQuery_emergencyMoney(){
		return this.query_emergencyMoney;
	}
	
	public void setQuery_emergencyMoney(String s){
		this.query_emergencyMoney = s;
	}
	
	private String condition_frozenMoney;
	
	public String getCondition_frozenMoney(){
		return this.condition_frozenMoney;
	}
	
	public void setCondition_frozenMoney(String s){
		this.condition_frozenMoney = s;
	}
	
	private String query_frozenMoney;
		
	public String getQuery_frozenMoney(){
		return this.query_frozenMoney;
	}
	
	public void setQuery_frozenMoney(String s){
		this.query_frozenMoney = s;
	}
	
	private String condition_hospitalMoney;
	
	public String getCondition_hospitalMoney(){
		return this.condition_hospitalMoney;
	}
	
	public void setCondition_hospitalMoney(String s){
		this.condition_hospitalMoney = s;
	}
	
	private String query_hospitalMoney;
		
	public String getQuery_hospitalMoney(){
		return this.query_hospitalMoney;
	}
	
	public void setQuery_hospitalMoney(String s){
		this.query_hospitalMoney = s;
	}
	
	private String condition_tesMoney;
	
	public String getCondition_tesMoney(){
		return this.condition_tesMoney;
	}
	
	public void setCondition_tesMoney(String s){
		this.condition_tesMoney = s;
	}
	
	private String query_tesMoney;
		
	public String getQuery_tesMoney(){
		return this.query_tesMoney;
	}
	
	public void setQuery_tesMoney(String s){
		this.query_tesMoney = s;
	}
	
	private String condition_iuserName;
	
	public String getCondition_iuserName(){
		return this.condition_iuserName;
	}
	
	public void setCondition_iuserName(String s){
		this.condition_iuserName = s;
	}
	
	private String query_iuserName;
		
	public String getQuery_iuserName(){
		return this.query_iuserName;
	}
	
	public void setQuery_iuserName(String s){
		this.query_iuserName = s;
	}
	
	private String condition_iuserIsman;
	
	public String getCondition_iuserIsman(){
		return this.condition_iuserIsman;
	}
	
	public void setCondition_iuserIsman(String s){
		this.condition_iuserIsman = s;
	}
	
	private String query_iuserIsman;
		
	public String getQuery_iuserIsman(){
		return this.query_iuserIsman;
	}
	
	public void setQuery_iuserIsman(String s){
		this.query_iuserIsman = s;
	}
	
	private String condition_iuserCardno;
	
	public String getCondition_iuserCardno(){
		return this.condition_iuserCardno;
	}
	
	public void setCondition_iuserCardno(String s){
		this.condition_iuserCardno = s;
	}
	
	private String query_iuserCardno;
		
	public String getQuery_iuserCardno(){
		return this.query_iuserCardno;
	}
	
	public void setQuery_iuserCardno(String s){
		this.query_iuserCardno = s;
	}
	
	private String condition_iuserPhone;
	
	public String getCondition_iuserPhone(){
		return this.condition_iuserPhone;
	}
	
	public void setCondition_iuserPhone(String s){
		this.condition_iuserPhone = s;
	}
	
	private String query_iuserPhone;
		
	public String getQuery_iuserPhone(){
		return this.query_iuserPhone;
	}
	
	public void setQuery_iuserPhone(String s){
		this.query_iuserPhone = s;
	}
	
	private String condition_iuserEmail;
	
	public String getCondition_iuserEmail(){
		return this.condition_iuserEmail;
	}
	
	public void setCondition_iuserEmail(String s){
		this.condition_iuserEmail = s;
	}
	
	private String query_iuserEmail;
		
	public String getQuery_iuserEmail(){
		return this.query_iuserEmail;
	}
	
	public void setQuery_iuserEmail(String s){
		this.query_iuserEmail = s;
	}
	
	private String condition_iuserBirthday;
	
	public String getCondition_iuserBirthday(){
		return this.condition_iuserBirthday;
	}
	
	public void setCondition_iuserBirthday(String s){
		this.condition_iuserBirthday = s;
	}
	
	private String query_iuserBirthday;
		
	public String getQuery_iuserBirthday(){
		return this.query_iuserBirthday;
	}
	
	public void setQuery_iuserBirthday(String s){
		this.query_iuserBirthday = s;
	}
	
	private String condition_iuserRemark;
	
	public String getCondition_iuserRemark(){
		return this.condition_iuserRemark;
	}
	
	public void setCondition_iuserRemark(String s){
		this.condition_iuserRemark = s;
	}
	
	private String query_iuserRemark;
		
	public String getQuery_iuserRemark(){
		return this.query_iuserRemark;
	}
	
	public void setQuery_iuserRemark(String s){
		this.query_iuserRemark = s;
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
