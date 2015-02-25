
package ido.BusinessGroup;
import ido.loginfo.LogInfoManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import common.util.CommonUtil;
import common.util.DateTool;
import common.util.DateUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.constants.Constants;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.user.User;
import dwz.framework.user.impl.UserImpl;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;
/**
 * 关于商家集团的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessGroupAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	BusinessGroupManager pMgr = bf.getManager(BeanManagerKey.businessgroupManager);
	//业务实体对象
	private BusinessGroup vo;
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
 	 * 添加商家集团.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			BusinessGroupImpl businessgroupImpl = new BusinessGroupImpl(groupSno ,groupName ,groupEmail ,groupContact ,groupContactPhone ,groupContactMobile ,groupStatus ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createBusinessGroup(businessgroupImpl);
			pMgr.addCache();
			insertLog(logMgr,"添加商家集团","/doAdd", "", "" ,JSON.toJSONString(businessgroupImpl));  
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
		insertLog(logMgr,"导入商家集团","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<BusinessGroup> allDeleteIds = new ArrayList<BusinessGroup>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getBusinessGroup(Integer.parseInt(_id)));
		}
		pMgr.removeBusinessGroups(ids);
		pMgr.addCache();
		insertLog(logMgr,"删除商家集团","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getBusinessGroup(sno);
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
			BusinessGroup old = pMgr.getBusinessGroup( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getGroupSno(),groupSno)){
				oldObj += "groupSno="+old.getGroupSno()+";";
				newObj+= "groupSno="+groupSno+";";
			} 
			if(!compare(old.getGroupName(),groupName)){
				oldObj += "groupName="+old.getGroupName()+";";
				newObj+= "groupName="+groupName+";";
			} 
			if(!compare(old.getGroupEmail(),groupEmail)){
				oldObj += "groupEmail="+old.getGroupEmail()+";";
				newObj+= "groupEmail="+groupEmail+";";
			} 
			if(!compare(old.getGroupContact(),groupContact)){
				oldObj += "groupContact="+old.getGroupContact()+";";
				newObj+= "groupContact="+groupContact+";";
			} 
			if(!compare(old.getGroupContactPhone(),groupContactPhone)){
				oldObj += "groupContactPhone="+old.getGroupContactPhone()+";";
				newObj+= "groupContactPhone="+groupContactPhone+";";
			} 
			if(!compare(old.getGroupContactMobile(),groupContactMobile)){
				oldObj += "groupContactMobile="+old.getGroupContactMobile()+";";
				newObj+= "groupContactMobile="+groupContactMobile+";";
			} 
			if(!compare(old.getGroupStatus(),groupStatus)){
				oldObj += "groupStatus="+old.getGroupStatus()+";";
				newObj+= "groupStatus="+groupStatus+";";
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
			
			BusinessGroupImpl businessgroupImpl = new BusinessGroupImpl( sno , groupSno , groupName , groupEmail , groupContact , groupContactPhone , groupContactMobile , groupStatus , createUser , createTime , updateUser , updateTime );
			pMgr.updateBusinessGroup(businessgroupImpl);
			pMgr.addCache();
			insertLog(logMgr,"修改商家集团","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(businessgroupImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,GROUPSNO("集团编号")  ,GROUPNAME("集团名称 ")  ,GROUPEMAIL("邮箱")  ,GROUPCONTACT("联系人名称")  ,GROUPCONTACTPHONE("联系电话")  ,GROUPCONTACTMOBILE("联系人手机");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		  GROUPSNO("集团编号")  ,GROUPNAME("集团名称 ")  ,GROUPEMAIL("邮箱")  ,GROUPCONTACT("联系人名称")  ,GROUPCONTACTPHONE("联系电话")  ,GROUPCONTACTMOBILE("联系人手机");
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
		ParamSelect select_shopman_status = allSelect
				.getParamsByType(AllSelectContants.SHOPMAN_STATUS.getName()); 
		request.setAttribute("groupstatus _list", select_shopman_status.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		String file_name = "商家集团_"+DateUtil.toString(DateUtil.now(),"yyyyMMddHHmm");
		try {
			file_name = URLEncoder.encode(file_name, "UTF-8");
		} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace();
		}   
		response.addHeader("Content-Disposition","attachment;filename="+file_name+".xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<BusinessGroupSearchFields, Object> criterias = getCriterias();

		Collection<BusinessGroup> businessgroupList = pMgr.searchBusinessGroup(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (BusinessGroup businessgroup : businessgroupList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), businessgroup.getSno()); 
					break;
					case GROUPSNO:
						 e.setCell(filed.ordinal(), businessgroup.getGroupSno()); 
					break;
					case GROUPNAME:
						 e.setCell(filed.ordinal(), businessgroup.getGroupName()); 
					break;
					case GROUPEMAIL:
						 e.setCell(filed.ordinal(), businessgroup.getGroupEmail()); 
					break;
					case GROUPCONTACT:
						 e.setCell(filed.ordinal(), businessgroup.getGroupContact()); 
					break;
					case GROUPCONTACTPHONE:
						 e.setCell(filed.ordinal(), businessgroup.getGroupContactPhone()); 
					break;
					case GROUPCONTACTMOBILE:
						 e.setCell(filed.ordinal(), businessgroup.getGroupContactMobile()); 
					break;
				default:
					break;
				}

			}
		}

		e.exportXls(response);
		return null;
	}

	public String groupAddShopm() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<BusinessGroupSearchFields, Object> criterias = getCriterias(); 
		
		Collection<BusinessGroup> moneyList = pMgr.searchBusinessGroup(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBusinessGroupNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "groupAddShopm";
	}
	
	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<BusinessGroupSearchFields, Object> criterias = getCriterias();

		Collection<BusinessGroup> moneyList = pMgr.searchBusinessGroup(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBusinessGroupNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		System.out.println(" getOrderDirection()==="+ getOrderDirection());
		if("desc".equals(getOrderDirection()))
			ActionContext.getContext().put("orderDirection", "asc");
		else
			ActionContext.getContext().put("orderDirection", "asc");
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "list";
	}
	
	public String newQuery() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<BusinessGroupSearchFields, Object> criterias = getCriterias();

		Collection<BusinessGroup> moneyList = pMgr.searchBusinessGroup(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBusinessGroupNum(criterias);
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

	private Map<BusinessGroupSearchFields, Object> getCriterias() {
		Map<BusinessGroupSearchFields, Object> criterias = new HashMap<BusinessGroupSearchFields, Object>();
		if (getGroupSno()!=null&&!"".equals(getGroupSno())&&!"-1".equals(getGroupSno())&&!"-2".equals(getGroupSno()))
			criterias.put(BusinessGroupSearchFields.GROUPSNO, "%"+getGroupSno()+"%"); 
		if (getGroupName()!=null&&!"".equals(getGroupName())&&!"-1".equals(getGroupName())&&!"-2".equals(getGroupName()))
			criterias.put(BusinessGroupSearchFields.GROUPNAME,  getGroupName());
		if (getGroupStatus()!=null&&!"".equals(getGroupStatus())&&!"-1".equals(getGroupStatus())&&!"-2".equals(getGroupStatus()))
			criterias.put(BusinessGroupSearchFields.GROUPSTATUS,  getGroupStatus());
		
		//下面是高级查询的时候添加的条件
		//添加集团编号的查询条件
		addGroupSnoCondition(criterias,getCondition_groupSno(),getQuery_groupSno());
		//添加集团名称 的查询条件
		addGroupNameCondition(criterias,getCondition_groupName(),getQuery_groupName());
		//添加邮箱的查询条件
		addGroupEmailCondition(criterias,getCondition_groupEmail(),getQuery_groupEmail());
		//添加联系人名称的查询条件
		addGroupContactCondition(criterias,getCondition_groupContact(),getQuery_groupContact());
		//添加联系电话的查询条件
		addGroupContactPhoneCondition(criterias,getCondition_groupContactPhone(),getQuery_groupContactPhone());
		//添加联系人手机的查询条件
		addGroupContactMobileCondition(criterias,getCondition_groupContactMobile(),getQuery_groupContactMobile());
		//添加状态的查询条件
		addGroupStatusCondition(criterias,getCondition_groupStatus(),getQuery_groupStatus());
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
	 * 添加查询集团编号的查询条件.
	 */
	public void addGroupSnoCondition(Map<BusinessGroupSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.STR_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPSNO_STR_EQUALS, value);
		} 
		else if(Constants.STR_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPSNO_STR_LIKE, value);
		} 
		else if(Constants.STR_NOT_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPSNO_STR_NOT_LIKE, value);
		} 
		else if(Constants.STR_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPSNO_STR_NOT_EQUALS, value);
		}  
	} 
	/**
	 * 添加查询集团名称 的查询条件.
	 */
	public void addGroupNameCondition(Map<BusinessGroupSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.STR_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPNAME_STR_EQUALS, value);
		} 
		else if(Constants.STR_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPNAME_STR_LIKE, value);
		} 
		else if(Constants.STR_NOT_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPNAME_STR_NOT_LIKE, value);
		} 
		else if(Constants.STR_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPNAME_STR_NOT_EQUALS, value);
		}  
	} 
	/**
	 * 添加查询邮箱的查询条件.
	 */
	public void addGroupEmailCondition(Map<BusinessGroupSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.STR_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPEMAIL_STR_EQUALS, value);
		} 
		else if(Constants.STR_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPEMAIL_STR_LIKE, value);
		} 
		else if(Constants.STR_NOT_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPEMAIL_STR_NOT_LIKE, value);
		} 
		else if(Constants.STR_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPEMAIL_STR_NOT_EQUALS, value);
		}  
	} 
	/**
	 * 添加查询联系人名称的查询条件.
	 */
	public void addGroupContactCondition(Map<BusinessGroupSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.STR_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACT_STR_EQUALS, value);
		} 
		else if(Constants.STR_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACT_STR_LIKE, value);
		} 
		else if(Constants.STR_NOT_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACT_STR_NOT_LIKE, value);
		} 
		else if(Constants.STR_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACT_STR_NOT_EQUALS, value);
		}  
	} 
	/**
	 * 添加查询联系电话的查询条件.
	 */
	public void addGroupContactPhoneCondition(Map<BusinessGroupSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.STR_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACTPHONE_STR_EQUALS, value);
		} 
		else if(Constants.STR_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACTPHONE_STR_LIKE, value);
		} 
		else if(Constants.STR_NOT_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACTPHONE_STR_NOT_LIKE, value);
		} 
		else if(Constants.STR_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACTPHONE_STR_NOT_EQUALS, value);
		}  
	} 
	/**
	 * 添加查询联系人手机的查询条件.
	 */
	public void addGroupContactMobileCondition(Map<BusinessGroupSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.STR_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACTMOBILE_STR_EQUALS, value);
		} 
		else if(Constants.STR_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACTMOBILE_STR_LIKE, value);
		} 
		else if(Constants.STR_NOT_LIKE.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACTMOBILE_STR_NOT_LIKE, value);
		} 
		else if(Constants.STR_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPCONTACTMOBILE_STR_NOT_EQUALS, value);
		}  
	} 
	/**
	 * 添加查询状态的查询条件.
	 */
	public void addGroupStatusCondition(Map<BusinessGroupSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPSTATUS_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.GROUPSTATUS_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建用户的查询条件.
	 */
	public void addCreateUserCondition(Map<BusinessGroupSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<BusinessGroupSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新用户的查询条件.
	 */
	public void addUpdateUserCondition(Map<BusinessGroupSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.UPDATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.UPDATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新时间的查询条件.
	 */
	public void addUpdateTimeCondition(Map<BusinessGroupSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.UPDATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessGroupSearchFields.UPDATETIME_COM_NOT_EQUALS, value);
		} 
	} 

	public BusinessGroup getVo() {
		return vo;
	}

	public void setVo(BusinessGroup vo) {
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
	private String groupSno; 
 	/**
 	 * 获取集团编号的属性值.
 	 */
 	public String getGroupSno(){
 		return groupSno;
 	}
 	
 	/**
 	 * 设置集团编号的属性值.
 	 */
 	public void setGroupSno(String groupsno){
 		this.groupSno = groupsno;
 	}
	private String groupName; 
 	/**
 	 * 获取集团名称 的属性值.
 	 */
 	public String getGroupName(){
 		return groupName;
 	}
 	
 	/**
 	 * 设置集团名称 的属性值.
 	 */
 	public void setGroupName(String groupname){
 		this.groupName = groupname;
 	}
	private String groupEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getGroupEmail(){
 		return groupEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setGroupEmail(String groupemail){
 		this.groupEmail = groupemail;
 	}
	private String groupContact; 
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public String getGroupContact(){
 		return groupContact;
 	}
 	
 	/**
 	 * 设置联系人名称的属性值.
 	 */
 	public void setGroupContact(String groupcontact){
 		this.groupContact = groupcontact;
 	}
	private String groupContactPhone; 
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public String getGroupContactPhone(){
 		return groupContactPhone;
 	}
 	
 	/**
 	 * 设置联系电话的属性值.
 	 */
 	public void setGroupContactPhone(String groupcontactphone){
 		this.groupContactPhone = groupcontactphone;
 	}
	private String groupContactMobile; 
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public String getGroupContactMobile(){
 		return groupContactMobile;
 	}
 	
 	/**
 	 * 设置联系人手机的属性值.
 	 */
 	public void setGroupContactMobile(String groupcontactmobile){
 		this.groupContactMobile = groupcontactmobile;
 	}
	private String groupStatus; 
 	/**
 	 * 获取状态的属性值.
 	 */
 	public String getGroupStatus(){
 		return groupStatus;
 	}
 	
 	/**
 	 * 设置状态的属性值.
 	 */
 	public void setGroupStatus(String groupstatus){
 		this.groupStatus = groupstatus;
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
	
	private String condition_groupSno;
	
	public String getCondition_groupSno(){
		return this.condition_groupSno;
	}
	
	public void setCondition_groupSno(String s){
		this.condition_groupSno = s;
	}
	
	private String query_groupSno;
		
	public String getQuery_groupSno(){
		return this.query_groupSno;
	}
	
	public void setQuery_groupSno(String s){
		this.query_groupSno = s;
	}
	
	private String condition_groupName;
	
	public String getCondition_groupName(){
		return this.condition_groupName;
	}
	
	public void setCondition_groupName(String s){
		this.condition_groupName = s;
	}
	
	private String query_groupName;
		
	public String getQuery_groupName(){
		return this.query_groupName;
	}
	
	public void setQuery_groupName(String s){
		this.query_groupName = s;
	}
	
	private String condition_groupEmail;
	
	public String getCondition_groupEmail(){
		return this.condition_groupEmail;
	}
	
	public void setCondition_groupEmail(String s){
		this.condition_groupEmail = s;
	}
	
	private String query_groupEmail;
		
	public String getQuery_groupEmail(){
		return this.query_groupEmail;
	}
	
	public void setQuery_groupEmail(String s){
		this.query_groupEmail = s;
	}
	
	private String condition_groupContact;
	
	public String getCondition_groupContact(){
		return this.condition_groupContact;
	}
	
	public void setCondition_groupContact(String s){
		this.condition_groupContact = s;
	}
	
	private String query_groupContact;
		
	public String getQuery_groupContact(){
		return this.query_groupContact;
	}
	
	public void setQuery_groupContact(String s){
		this.query_groupContact = s;
	}
	
	private String condition_groupContactPhone;
	
	public String getCondition_groupContactPhone(){
		return this.condition_groupContactPhone;
	}
	
	public void setCondition_groupContactPhone(String s){
		this.condition_groupContactPhone = s;
	}
	
	private String query_groupContactPhone;
		
	public String getQuery_groupContactPhone(){
		return this.query_groupContactPhone;
	}
	
	public void setQuery_groupContactPhone(String s){
		this.query_groupContactPhone = s;
	}
	
	private String condition_groupContactMobile;
	
	public String getCondition_groupContactMobile(){
		return this.condition_groupContactMobile;
	}
	
	public void setCondition_groupContactMobile(String s){
		this.condition_groupContactMobile = s;
	}
	
	private String query_groupContactMobile;
		
	public String getQuery_groupContactMobile(){
		return this.query_groupContactMobile;
	}
	
	public void setQuery_groupContactMobile(String s){
		this.query_groupContactMobile = s;
	}
	
	private String condition_groupStatus;
	
	public String getCondition_groupStatus(){
		return this.condition_groupStatus;
	}
	
	public void setCondition_groupStatus(String s){
		this.condition_groupStatus = s;
	}
	
	private String query_groupStatus;
		
	public String getQuery_groupStatus(){
		return this.query_groupStatus;
	}
	
	public void setQuery_groupStatus(String s){
		this.query_groupStatus = s;
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
