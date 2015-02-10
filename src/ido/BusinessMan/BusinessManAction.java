
package ido.BusinessMan;
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
import ido.InsuredCompany.InsuredCompany;
import ido.loginfo.LogInfoManager;
import common.base.AllSelect;
import common.base.AllSelectContants;
/**
 * 关于商家的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessManAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	BusinessManManager pMgr = bf.getManager(BeanManagerKey.businessmanManager);
	//业务实体对象
	private BusinessMan vo;
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
	 * 添加商家关联到对应的商家集团
	 * @return
	 */
	public String addShopmToGroup() {
		String ids = request.getParameter("mids");
		pMgr.addToGroupSno(ids, groupSno);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}
	
	/**
	 * 打开简短的商家页面供选择.
	 * @return
	 */
	public String tinyShopmList() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<BusinessManSearchFields, Object> criterias = getCriterias();

		Collection<BusinessMan> moneyList = pMgr.searchBusinessMan(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("groupSno", groupSno);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBusinessManNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count); 
		return "tinyShopm";
	}
  
 	/**
 	 * 添加商家.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			BusinessManImpl businessmanImpl = new BusinessManImpl(shopmSno ,shopmName ,shopmShortName ,shopmType ,shopmEmail ,shopmConPhone ,shopmContactName ,shopmAddress ,openBank ,openBankName ,openBankNo ,openTicketUnit ,OpenBankProvince ,OpenBankCity ,compensationDay ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createBusinessMan(businessmanImpl);
			pMgr.addCache();
			insertLog(logMgr,"添加商家","/doAdd", "", "" ,JSON.toJSONString(businessmanImpl));  
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
		insertLog(logMgr,"导入商家","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<BusinessMan> allDeleteIds = new ArrayList<BusinessMan>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getBusinessMan(Integer.parseInt(_id)));
		}
		pMgr.removeBusinessMans(ids);
		pMgr.addCache();
		insertLog(logMgr,"删除商家","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getBusinessMan(sno);
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
			BusinessMan old = pMgr.getBusinessMan( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getShopmSno(),shopmSno)){
				oldObj += "shopmSno="+old.getShopmSno()+";";
				newObj+= "shopmSno="+shopmSno+";";
			} 
			if(!compare(old.getShopmName(),shopmName)){
				oldObj += "shopmName="+old.getShopmName()+";";
				newObj+= "shopmName="+shopmName+";";
			} 
			if(!compare(old.getShopmShortName(),shopmShortName)){
				oldObj += "shopmShortName="+old.getShopmShortName()+";";
				newObj+= "shopmShortName="+shopmShortName+";";
			} 
			if(!compare(old.getShopmType(),shopmType)){
				oldObj += "shopmType="+old.getShopmType()+";";
				newObj+= "shopmType="+shopmType+";";
			} 
			if(!compare(old.getShopmEmail(),shopmEmail)){
				oldObj += "shopmEmail="+old.getShopmEmail()+";";
				newObj+= "shopmEmail="+shopmEmail+";";
			} 
			if(!compare(old.getShopmConPhone(),shopmConPhone)){
				oldObj += "shopmConPhone="+old.getShopmConPhone()+";";
				newObj+= "shopmConPhone="+shopmConPhone+";";
			} 
			if(!compare(old.getShopmContactName(),shopmContactName)){
				oldObj += "shopmContactName="+old.getShopmContactName()+";";
				newObj+= "shopmContactName="+shopmContactName+";";
			} 
			if(!compare(old.getShopmAddress(),shopmAddress)){
				oldObj += "shopmAddress="+old.getShopmAddress()+";";
				newObj+= "shopmAddress="+shopmAddress+";";
			} 
			if(!compare(old.getOpenBank(),openBank)){
				oldObj += "openBank="+old.getOpenBank()+";";
				newObj+= "openBank="+openBank+";";
			} 
			if(!compare(old.getOpenBankName(),openBankName)){
				oldObj += "openBankName="+old.getOpenBankName()+";";
				newObj+= "openBankName="+openBankName+";";
			} 
			if(!compare(old.getOpenBankNo(),openBankNo)){
				oldObj += "openBankNo="+old.getOpenBankNo()+";";
				newObj+= "openBankNo="+openBankNo+";";
			} 
			if(!compare(old.getOpenTicketUnit(),openTicketUnit)){
				oldObj += "openTicketUnit="+old.getOpenTicketUnit()+";";
				newObj+= "openTicketUnit="+openTicketUnit+";";
			} 
			if(!compare(old.getOpenBankProvince(),OpenBankProvince)){
				oldObj += "OpenBankProvince="+old.getOpenBankProvince()+";";
				newObj+= "OpenBankProvince="+OpenBankProvince+";";
			} 
			if(!compare(old.getOpenBankCity(),OpenBankCity)){
				oldObj += "OpenBankCity="+old.getOpenBankCity()+";";
				newObj+= "OpenBankCity="+OpenBankCity+";";
			} 
			if(!compare(old.getCompensationDay(),compensationDay)){
				oldObj += "compensationDay="+old.getCompensationDay()+";";
				newObj+= "compensationDay="+compensationDay+";";
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
			
			BusinessManImpl businessmanImpl = new BusinessManImpl( sno , shopmSno , shopmName , shopmShortName , shopmType , shopmEmail , shopmConPhone , shopmContactName , shopmAddress , openBank , openBankName , openBankNo , openTicketUnit , OpenBankProvince , OpenBankCity , compensationDay , createUser , createTime , updateUser , updateTime );
			pMgr.updateBusinessMan(businessmanImpl);
			pMgr.addCache();
			insertLog(logMgr,"修改商家","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(businessmanImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,SHOPMSNO("商家编号")  ,SHOPMNAME("商家名称 ")  ,SHOPMSHORTNAME("商家简称 ")  ,SHOPMEMAIL("邮箱")  ,SHOPMCONPHONE("联系人手机")  ,SHOPMCONTACTNAME("联系人名称")  ,SHOPMADDRESS("地址");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		  SHOPMSNO("商家编号")  ,SHOPMNAME("商家名称 ")  ,SHOPMSHORTNAME("商家简称 ")  ,SHOPMEMAIL("邮箱")  ,SHOPMCONPHONE("联系人手机")  ,SHOPMCONTACTNAME("联系人名称")  ,SHOPMADDRESS("地址");
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
		request.setAttribute("shopmtype _list", select_shopman_status.getSelectAbles()); 
		Cache cache_OpenBankProvince = CacheManager.getCacheInfoNotNull(AllSelectContants.PROVINCE_DICT.getName());
		ParamSelect select_OpenBankProvince = (ParamSelect)cache_OpenBankProvince.getValue();
		request.setAttribute("openbankprovince _list", select_OpenBankProvince.getSelectAbles()); 
		Cache cache_OpenBankCity = CacheManager.getCacheInfoNotNull(AllSelectContants.CITY_DICT.getName());
		ParamSelect select_OpenBankCity = (ParamSelect)cache_OpenBankCity.getValue();
		request.setAttribute("openbankcity _list", select_OpenBankCity.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=BusinessManList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<BusinessManSearchFields, Object> criterias = getCriterias();

		Collection<BusinessMan> businessmanList = pMgr.searchBusinessMan(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (BusinessMan businessman : businessmanList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), businessman.getSno()); 
					break;
					case SHOPMSNO:
						 e.setCell(filed.ordinal(), businessman.getShopmSno()); 
					break;
					case SHOPMNAME:
						 e.setCell(filed.ordinal(), businessman.getShopmName()); 
					break;
					case SHOPMSHORTNAME:
						 e.setCell(filed.ordinal(), businessman.getShopmShortName()); 
					break;
					case SHOPMEMAIL:
						 e.setCell(filed.ordinal(), businessman.getShopmEmail()); 
					break;
					case SHOPMCONPHONE:
						 e.setCell(filed.ordinal(), businessman.getShopmConPhone()); 
					break;
					case SHOPMCONTACTNAME:
						 e.setCell(filed.ordinal(), businessman.getShopmContactName()); 
					break;
					case SHOPMADDRESS:
						 e.setCell(filed.ordinal(), businessman.getShopmAddress()); 
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
		Map<BusinessManSearchFields, Object> criterias = getCriterias();

		Collection<BusinessMan> moneyList = pMgr.searchBusinessMan(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBusinessManNum(criterias);
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
		Map<BusinessManSearchFields, Object> criterias = getCriterias();

		Collection<BusinessMan> moneyList = pMgr.searchBusinessMan(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBusinessManNum(criterias);
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

	private Map<BusinessManSearchFields, Object> getCriterias() {
		Map<BusinessManSearchFields, Object> criterias = new HashMap<BusinessManSearchFields, Object>();
		if (getShopmSno()!=null&&!"".equals(getShopmSno())&&!"-1".equals(getShopmSno())&&!"-2".equals(getShopmSno()))
			criterias.put(BusinessManSearchFields.SHOPMSNO,  getShopmSno());
		if (getShopmName()!=null&&!"".equals(getShopmName())&&!"-1".equals(getShopmName())&&!"-2".equals(getShopmName()))
			criterias.put(BusinessManSearchFields.SHOPMNAME, "%"+getShopmName()+"%"); 
		if (getShopmShortName()!=null&&!"".equals(getShopmShortName())&&!"-1".equals(getShopmShortName())&&!"-2".equals(getShopmShortName()))
			criterias.put(BusinessManSearchFields.SHOPMSHORTNAME, "%"+getShopmShortName()+"%"); 
		if (getShopmType()!=null&&!"".equals(getShopmType())&&!"-1".equals(getShopmType())&&!"-2".equals(getShopmType()))
			criterias.put(BusinessManSearchFields.SHOPMTYPE,  getShopmType());
		
		//下面是高级查询的时候添加的条件
		//添加商家编号的查询条件
		addShopmSnoCondition(criterias,getCondition_shopmSno(),getQuery_shopmSno());
		//添加商家名称 的查询条件
		addShopmNameCondition(criterias,getCondition_shopmName(),getQuery_shopmName());
		//添加商家简称 的查询条件
		addShopmShortNameCondition(criterias,getCondition_shopmShortName(),getQuery_shopmShortName());
		//添加商家类型 的查询条件
		addShopmTypeCondition(criterias,getCondition_shopmType(),getQuery_shopmType());
		//添加邮箱的查询条件
		addShopmEmailCondition(criterias,getCondition_shopmEmail(),getQuery_shopmEmail());
		//添加联系人手机的查询条件
		addShopmConPhoneCondition(criterias,getCondition_shopmConPhone(),getQuery_shopmConPhone());
		//添加联系人名称的查询条件
		addShopmContactNameCondition(criterias,getCondition_shopmContactName(),getQuery_shopmContactName());
		//添加地址的查询条件
		addShopmAddressCondition(criterias,getCondition_shopmAddress(),getQuery_shopmAddress());
		//添加开户行的查询条件
		addOpenBankCondition(criterias,getCondition_openBank(),getQuery_openBank());
		//添加户名的查询条件
		addOpenBankNameCondition(criterias,getCondition_openBankName(),getQuery_openBankName());
		//添加银行帐号的查询条件
		addOpenBankNoCondition(criterias,getCondition_openBankNo(),getQuery_openBankNo());
		//添加开票单位的查询条件
		addOpenTicketUnitCondition(criterias,getCondition_openTicketUnit(),getQuery_openTicketUnit());
		//添加开户所在省的查询条件
		addOpenBankProvinceCondition(criterias,getCondition_OpenBankProvince(),getQuery_OpenBankProvince());
		//添加开户所在市的查询条件
		addOpenBankCityCondition(criterias,getCondition_OpenBankCity(),getQuery_OpenBankCity());
		//添加理赔截止日期的查询条件
		addCompensationDayCondition(criterias,getCondition_compensationDay(),getQuery_compensationDay());
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
	 * 添加查询商家编号的查询条件.
	 */
	public void addShopmSnoCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMSNO_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMSNO_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询商家名称 的查询条件.
	 */
	public void addShopmNameCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMNAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMNAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询商家简称 的查询条件.
	 */
	public void addShopmShortNameCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMSHORTNAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMSHORTNAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询商家类型 的查询条件.
	 */
	public void addShopmTypeCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMTYPE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMTYPE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询邮箱的查询条件.
	 */
	public void addShopmEmailCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMEMAIL_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMEMAIL_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询联系人手机的查询条件.
	 */
	public void addShopmConPhoneCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMCONPHONE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMCONPHONE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询联系人名称的查询条件.
	 */
	public void addShopmContactNameCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMCONTACTNAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMCONTACTNAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询地址的查询条件.
	 */
	public void addShopmAddressCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMADDRESS_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.SHOPMADDRESS_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询开户行的查询条件.
	 */
	public void addOpenBankCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENBANK_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENBANK_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询户名的查询条件.
	 */
	public void addOpenBankNameCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENBANKNAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENBANKNAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询银行帐号的查询条件.
	 */
	public void addOpenBankNoCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENBANKNO_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENBANKNO_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询开票单位的查询条件.
	 */
	public void addOpenTicketUnitCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENTICKETUNIT_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENTICKETUNIT_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询开户所在省的查询条件.
	 */
	public void addOpenBankProvinceCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENBANKPROVINCE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENBANKPROVINCE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询开户所在市的查询条件.
	 */
	public void addOpenBankCityCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENBANKCITY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.OPENBANKCITY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询理赔截止日期的查询条件.
	 */
	public void addCompensationDayCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.COMPENSATIONDAY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.COMPENSATIONDAY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建用户的查询条件.
	 */
	public void addCreateUserCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新用户的查询条件.
	 */
	public void addUpdateUserCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.UPDATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.UPDATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新时间的查询条件.
	 */
	public void addUpdateTimeCondition(Map<BusinessManSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.UPDATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessManSearchFields.UPDATETIME_COM_NOT_EQUALS, value);
		} 
	} 

	public BusinessMan getVo() {
		return vo;
	}

	public void setVo(BusinessMan vo) {
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
	private String shopmSno; 
 	/**
 	 * 获取商家编号的属性值.
 	 */
 	public String getShopmSno(){
 		return shopmSno;
 	}
 	
 	/**
 	 * 设置商家编号的属性值.
 	 */
 	public void setShopmSno(String shopmsno){
 		this.shopmSno = shopmsno;
 	}
	private String shopmName; 
 	/**
 	 * 获取商家名称 的属性值.
 	 */
 	public String getShopmName(){
 		return shopmName;
 	}
 	
 	/**
 	 * 设置商家名称 的属性值.
 	 */
 	public void setShopmName(String shopmname){
 		this.shopmName = shopmname;
 	}
	private String shopmShortName; 
 	/**
 	 * 获取商家简称 的属性值.
 	 */
 	public String getShopmShortName(){
 		return shopmShortName;
 	}
 	
 	/**
 	 * 设置商家简称 的属性值.
 	 */
 	public void setShopmShortName(String shopmshortname){
 		this.shopmShortName = shopmshortname;
 	}
	private String shopmType; 
 	/**
 	 * 获取商家类型 的属性值.
 	 */
 	public String getShopmType(){
 		return shopmType;
 	}
 	
 	/**
 	 * 设置商家类型 的属性值.
 	 */
 	public void setShopmType(String shopmtype){
 		this.shopmType = shopmtype;
 	}
	private String shopmEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getShopmEmail(){
 		return shopmEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setShopmEmail(String shopmemail){
 		this.shopmEmail = shopmemail;
 	}
	private String shopmConPhone; 
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public String getShopmConPhone(){
 		return shopmConPhone;
 	}
 	
 	/**
 	 * 设置联系人手机的属性值.
 	 */
 	public void setShopmConPhone(String shopmconphone){
 		this.shopmConPhone = shopmconphone;
 	}
	private String shopmContactName; 
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public String getShopmContactName(){
 		return shopmContactName;
 	}
 	
 	/**
 	 * 设置联系人名称的属性值.
 	 */
 	public void setShopmContactName(String shopmcontactname){
 		this.shopmContactName = shopmcontactname;
 	}
	private String shopmAddress; 
 	/**
 	 * 获取地址的属性值.
 	 */
 	public String getShopmAddress(){
 		return shopmAddress;
 	}
 	
 	/**
 	 * 设置地址的属性值.
 	 */
 	public void setShopmAddress(String shopmaddress){
 		this.shopmAddress = shopmaddress;
 	}
	private String openBank; 
 	/**
 	 * 获取开户行的属性值.
 	 */
 	public String getOpenBank(){
 		return openBank;
 	}
 	
 	/**
 	 * 设置开户行的属性值.
 	 */
 	public void setOpenBank(String openbank){
 		this.openBank = openbank;
 	}
	private String openBankName; 
 	/**
 	 * 获取户名的属性值.
 	 */
 	public String getOpenBankName(){
 		return openBankName;
 	}
 	
 	/**
 	 * 设置户名的属性值.
 	 */
 	public void setOpenBankName(String openbankname){
 		this.openBankName = openbankname;
 	}
	private String openBankNo; 
 	/**
 	 * 获取银行帐号的属性值.
 	 */
 	public String getOpenBankNo(){
 		return openBankNo;
 	}
 	
 	/**
 	 * 设置银行帐号的属性值.
 	 */
 	public void setOpenBankNo(String openbankno){
 		this.openBankNo = openbankno;
 	}
	private String openTicketUnit; 
 	/**
 	 * 获取开票单位的属性值.
 	 */
 	public String getOpenTicketUnit(){
 		return openTicketUnit;
 	}
 	
 	/**
 	 * 设置开票单位的属性值.
 	 */
 	public void setOpenTicketUnit(String openticketunit){
 		this.openTicketUnit = openticketunit;
 	}
	private String OpenBankProvince; 
 	/**
 	 * 获取开户所在省的属性值.
 	 */
 	public String getOpenBankProvince(){
 		return OpenBankProvince;
 	}
 	
 	/**
 	 * 设置开户所在省的属性值.
 	 */
 	public void setOpenBankProvince(String openbankprovince){
 		this.OpenBankProvince = openbankprovince;
 	}
	private String OpenBankCity; 
 	/**
 	 * 获取开户所在市的属性值.
 	 */
 	public String getOpenBankCity(){
 		return OpenBankCity;
 	}
 	
 	/**
 	 * 设置开户所在市的属性值.
 	 */
 	public void setOpenBankCity(String openbankcity){
 		this.OpenBankCity = openbankcity;
 	}
	private String compensationDay; 
 	/**
 	 * 获取理赔截止日期的属性值.
 	 */
 	public String getCompensationDay(){
 		return compensationDay;
 	}
 	
 	/**
 	 * 设置理赔截止日期的属性值.
 	 */
 	public void setCompensationDay(String compensationday){
 		this.compensationDay = compensationday;
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
	
	private String condition_shopmSno;
	
	public String getCondition_shopmSno(){
		return this.condition_shopmSno;
	}
	
	public void setCondition_shopmSno(String s){
		this.condition_shopmSno = s;
	}
	
	private String query_shopmSno;
		
	public String getQuery_shopmSno(){
		return this.query_shopmSno;
	}
	
	public void setQuery_shopmSno(String s){
		this.query_shopmSno = s;
	}
	
	private String condition_shopmName;
	
	public String getCondition_shopmName(){
		return this.condition_shopmName;
	}
	
	public void setCondition_shopmName(String s){
		this.condition_shopmName = s;
	}
	
	private String query_shopmName;
		
	public String getQuery_shopmName(){
		return this.query_shopmName;
	}
	
	public void setQuery_shopmName(String s){
		this.query_shopmName = s;
	}
	
	private String condition_shopmShortName;
	
	public String getCondition_shopmShortName(){
		return this.condition_shopmShortName;
	}
	
	public void setCondition_shopmShortName(String s){
		this.condition_shopmShortName = s;
	}
	
	private String query_shopmShortName;
		
	public String getQuery_shopmShortName(){
		return this.query_shopmShortName;
	}
	
	public void setQuery_shopmShortName(String s){
		this.query_shopmShortName = s;
	}
	
	private String condition_shopmType;
	
	public String getCondition_shopmType(){
		return this.condition_shopmType;
	}
	
	public void setCondition_shopmType(String s){
		this.condition_shopmType = s;
	}
	
	private String query_shopmType;
		
	public String getQuery_shopmType(){
		return this.query_shopmType;
	}
	
	public void setQuery_shopmType(String s){
		this.query_shopmType = s;
	}
	
	private String condition_shopmEmail;
	
	public String getCondition_shopmEmail(){
		return this.condition_shopmEmail;
	}
	
	public void setCondition_shopmEmail(String s){
		this.condition_shopmEmail = s;
	}
	
	private String query_shopmEmail;
		
	public String getQuery_shopmEmail(){
		return this.query_shopmEmail;
	}
	
	public void setQuery_shopmEmail(String s){
		this.query_shopmEmail = s;
	}
	
	private String condition_shopmConPhone;
	
	public String getCondition_shopmConPhone(){
		return this.condition_shopmConPhone;
	}
	
	public void setCondition_shopmConPhone(String s){
		this.condition_shopmConPhone = s;
	}
	
	private String query_shopmConPhone;
		
	public String getQuery_shopmConPhone(){
		return this.query_shopmConPhone;
	}
	
	public void setQuery_shopmConPhone(String s){
		this.query_shopmConPhone = s;
	}
	
	private String condition_shopmContactName;
	
	public String getCondition_shopmContactName(){
		return this.condition_shopmContactName;
	}
	
	public void setCondition_shopmContactName(String s){
		this.condition_shopmContactName = s;
	}
	
	private String query_shopmContactName;
		
	public String getQuery_shopmContactName(){
		return this.query_shopmContactName;
	}
	
	public void setQuery_shopmContactName(String s){
		this.query_shopmContactName = s;
	}
	
	private String condition_shopmAddress;
	
	public String getCondition_shopmAddress(){
		return this.condition_shopmAddress;
	}
	
	public void setCondition_shopmAddress(String s){
		this.condition_shopmAddress = s;
	}
	
	private String query_shopmAddress;
		
	public String getQuery_shopmAddress(){
		return this.query_shopmAddress;
	}
	
	public void setQuery_shopmAddress(String s){
		this.query_shopmAddress = s;
	}
	
	private String condition_openBank;
	
	public String getCondition_openBank(){
		return this.condition_openBank;
	}
	
	public void setCondition_openBank(String s){
		this.condition_openBank = s;
	}
	
	private String query_openBank;
		
	public String getQuery_openBank(){
		return this.query_openBank;
	}
	
	public void setQuery_openBank(String s){
		this.query_openBank = s;
	}
	
	private String condition_openBankName;
	
	public String getCondition_openBankName(){
		return this.condition_openBankName;
	}
	
	public void setCondition_openBankName(String s){
		this.condition_openBankName = s;
	}
	
	private String query_openBankName;
		
	public String getQuery_openBankName(){
		return this.query_openBankName;
	}
	
	public void setQuery_openBankName(String s){
		this.query_openBankName = s;
	}
	
	private String condition_openBankNo;
	
	public String getCondition_openBankNo(){
		return this.condition_openBankNo;
	}
	
	public void setCondition_openBankNo(String s){
		this.condition_openBankNo = s;
	}
	
	private String query_openBankNo;
		
	public String getQuery_openBankNo(){
		return this.query_openBankNo;
	}
	
	public void setQuery_openBankNo(String s){
		this.query_openBankNo = s;
	}
	
	private String condition_openTicketUnit;
	
	public String getCondition_openTicketUnit(){
		return this.condition_openTicketUnit;
	}
	
	public void setCondition_openTicketUnit(String s){
		this.condition_openTicketUnit = s;
	}
	
	private String query_openTicketUnit;
		
	public String getQuery_openTicketUnit(){
		return this.query_openTicketUnit;
	}
	
	public void setQuery_openTicketUnit(String s){
		this.query_openTicketUnit = s;
	}
	
	private String condition_OpenBankProvince;
	
	public String getCondition_OpenBankProvince(){
		return this.condition_OpenBankProvince;
	}
	
	public void setCondition_OpenBankProvince(String s){
		this.condition_OpenBankProvince = s;
	}
	
	private String query_OpenBankProvince;
		
	public String getQuery_OpenBankProvince(){
		return this.query_OpenBankProvince;
	}
	
	public void setQuery_OpenBankProvince(String s){
		this.query_OpenBankProvince = s;
	}
	
	private String condition_OpenBankCity;
	
	public String getCondition_OpenBankCity(){
		return this.condition_OpenBankCity;
	}
	
	public void setCondition_OpenBankCity(String s){
		this.condition_OpenBankCity = s;
	}
	
	private String query_OpenBankCity;
		
	public String getQuery_OpenBankCity(){
		return this.query_OpenBankCity;
	}
	
	public void setQuery_OpenBankCity(String s){
		this.query_OpenBankCity = s;
	}
	
	private String condition_compensationDay;
	
	public String getCondition_compensationDay(){
		return this.condition_compensationDay;
	}
	
	public void setCondition_compensationDay(String s){
		this.condition_compensationDay = s;
	}
	
	private String query_compensationDay;
		
	public String getQuery_compensationDay(){
		return this.query_compensationDay;
	}
	
	public void setQuery_compensationDay(String s){
		this.query_compensationDay = s;
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
	
	private String groupSno;

	public String getGroupSno() {
		return groupSno;
	}

	public void setGroupSno(String groupSno) {
		this.groupSno = groupSno;
	}
}
