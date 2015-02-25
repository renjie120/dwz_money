
package ido.BusinessShop;
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
import common.cache.Cache;
import common.cache.CacheManager;
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
 * 关于商铺的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessShopAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	BusinessShopManager pMgr = bf.getManager(BeanManagerKey.businessshopManager);
	//业务实体对象
	private BusinessShop vo;
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
 	 * 添加商铺.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			BusinessShopImpl businessshopImpl = new BusinessShopImpl(shopmId ,shopName ,shopSno ,shopStatus ,shopContactName ,shopConPhone ,shopEmail ,shopAddress ,shopDate ,shopJingdu ,shopWeidu ,shopProvince ,shopCity ,shopxian ,shopRemark ,shopMain ,shopIntroduce ,shopSpecial ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createBusinessShop(businessshopImpl);
			insertLog(logMgr,"添加商铺","/doAdd", "", "" ,JSON.toJSONString(businessshopImpl));  
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
		String file_name = "商铺上传模板";
		try {
			file_name = URLEncoder.encode(file_name, "UTF-8");
		} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace();
		}   
		String fileNameString =file_name+".xls"; 
		response.addHeader("Content-Disposition", "attachment;filename="
				+ fileNameString);

		XlsExport e = new XlsExport();
		e.createRow(0);
		for (ImportFiled filed : ImportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
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
				+ getContextUser().getId()
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
		insertLog(logMgr,"导入商铺","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<BusinessShop> allDeleteIds = new ArrayList<BusinessShop>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getBusinessShop(Integer.parseInt(_id)));
		}
		pMgr.removeBusinessShops(ids);
		insertLog(logMgr,"删除商铺","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getBusinessShop(sno);
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
			BusinessShop old = pMgr.getBusinessShop( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getShopmId(),shopmId)){
				oldObj += "shopmId="+old.getShopmId()+";";
				newObj+= "shopmId="+shopmId+";";
			} 
			if(!compare(old.getShopName(),shopName)){
				oldObj += "shopName="+old.getShopName()+";";
				newObj+= "shopName="+shopName+";";
			} 
			if(!compare(old.getShopSno(),shopSno)){
				oldObj += "shopSno="+old.getShopSno()+";";
				newObj+= "shopSno="+shopSno+";";
			} 
			if(!compare(old.getShopStatus(),shopStatus)){
				oldObj += "shopStatus="+old.getShopStatus()+";";
				newObj+= "shopStatus="+shopStatus+";";
			} 
			if(!compare(old.getShopContactName(),shopContactName)){
				oldObj += "shopContactName="+old.getShopContactName()+";";
				newObj+= "shopContactName="+shopContactName+";";
			} 
			if(!compare(old.getShopConPhone(),shopConPhone)){
				oldObj += "shopConPhone="+old.getShopConPhone()+";";
				newObj+= "shopConPhone="+shopConPhone+";";
			} 
			if(!compare(old.getShopEmail(),shopEmail)){
				oldObj += "shopEmail="+old.getShopEmail()+";";
				newObj+= "shopEmail="+shopEmail+";";
			} 
			if(!compare(old.getShopAddress(),shopAddress)){
				oldObj += "shopAddress="+old.getShopAddress()+";";
				newObj+= "shopAddress="+shopAddress+";";
			} 
			if(!compare(old.getShopDate(),shopDate)){
				oldObj += "shopDate="+old.getShopDate()+";";
				newObj+= "shopDate="+shopDate+";";
			} 
			if(!compare(old.getShopJingdu(),shopJingdu)){
				oldObj += "shopJingdu="+old.getShopJingdu()+";";
				newObj+= "shopJingdu="+shopJingdu+";";
			} 
			if(!compare(old.getShopWeidu(),shopWeidu)){
				oldObj += "shopWeidu="+old.getShopWeidu()+";";
				newObj+= "shopWeidu="+shopWeidu+";";
			} 
			if(!compare(old.getShopProvince(),shopProvince)){
				oldObj += "shopProvince="+old.getShopProvince()+";";
				newObj+= "shopProvince="+shopProvince+";";
			} 
			if(!compare(old.getShopCity(),shopCity)){
				oldObj += "shopCity="+old.getShopCity()+";";
				newObj+= "shopCity="+shopCity+";";
			} 
			if(!compare(old.getShopxian(),shopxian)){
				oldObj += "shopxian="+old.getShopxian()+";";
				newObj+= "shopxian="+shopxian+";";
			} 
			if(!compare(old.getShopRemark(),shopRemark)){
				oldObj += "shopRemark="+old.getShopRemark()+";";
				newObj+= "shopRemark="+shopRemark+";";
			} 
			if(!compare(old.getShopMain(),shopMain)){
				oldObj += "shopMain="+old.getShopMain()+";";
				newObj+= "shopMain="+shopMain+";";
			} 
			if(!compare(old.getShopIntroduce(),shopIntroduce)){
				oldObj += "shopIntroduce="+old.getShopIntroduce()+";";
				newObj+= "shopIntroduce="+shopIntroduce+";";
			} 
			if(!compare(old.getShopSpecial(),shopSpecial)){
				oldObj += "shopSpecial="+old.getShopSpecial()+";";
				newObj+= "shopSpecial="+shopSpecial+";";
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
			
			BusinessShopImpl businessshopImpl = new BusinessShopImpl( sno , shopmId , shopName , shopSno , shopStatus , shopContactName , shopConPhone , shopEmail , shopAddress , shopDate , shopJingdu , shopWeidu , shopProvince , shopCity , shopxian , shopRemark , shopMain , shopIntroduce , shopSpecial , createUser , createTime , updateUser , updateTime );
			pMgr.updateBusinessShop(businessshopImpl);
			insertLog(logMgr,"修改商铺","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(businessshopImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,SHOPMID("商家编号")  ,SHOPNAME("商铺名称 ")  ,SHOPSNO("商铺编号 ")  ,SHOPCONTACTNAME("联系人名称")  ,SHOPCONPHONE("联系人手机")  ,SHOPEMAIL("邮箱")  ,SHOPADDRESS("地址");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		  SHOPMID("商家编号")  ,SHOPNAME("商铺名称 ")  ,SHOPSNO("商铺编号 ")  ,SHOPCONTACTNAME("联系人名称")  ,SHOPCONPHONE("联系人手机")  ,SHOPEMAIL("邮箱")  ,SHOPADDRESS("地址"), SHOPSTATUS("商铺状态");
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
		ParamSelect select_shopstatus = allSelect
				.getParamsByType(AllSelectContants.SHOPSTATUS.getName()); 
		request.setAttribute("shopstatus _list", select_shopstatus.getSelectAbles()); 
		Cache cache_shopProvince = CacheManager.getCacheInfoNotNull(AllSelectContants.PROVINCE_DICT.getName());
		ParamSelect select_shopProvince = (ParamSelect)cache_shopProvince.getValue();
		request.setAttribute("shopprovince _list", select_shopProvince.getSelectAbles()); 
		Cache cache_shopCity = CacheManager.getCacheInfoNotNull(AllSelectContants.CITY_DICT.getName());
		ParamSelect select_shopCity = (ParamSelect)cache_shopCity.getValue();
		request.setAttribute("shopcity _list", select_shopCity.getSelectAbles()); 
		ParamSelect select_yesorno = allSelect
				.getParamsByType(AllSelectContants.YESORNO.getName()); 
		request.setAttribute("shopxian _list", select_yesorno.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		String file_name = "商铺信息_"+DateUtil.toString(DateUtil.now(),"yyyyMMddHHmm");
		try {
			file_name = URLEncoder.encode(file_name, "UTF-8");
		} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace();
		}   
		response.addHeader("Content-Disposition","attachment;filename="+file_name+".xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<BusinessShopSearchFields, Object> criterias = getCriterias();

		Collection<BusinessShop> businessshopList = pMgr.searchBusinessShop(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (BusinessShop businessshop : businessshopList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), businessshop.getSno()); 
					break;
					case SHOPMID:
						 e.setCell(filed.ordinal(), businessshop.getShopmId()); 
					break;
					case SHOPNAME:
						 e.setCell(filed.ordinal(), businessshop.getShopName()); 
					break;
					case SHOPSNO:
						 e.setCell(filed.ordinal(), businessshop.getShopSno()); 
					break;
					case SHOPCONTACTNAME:
						 e.setCell(filed.ordinal(), businessshop.getShopContactName()); 
					break;
					case SHOPCONPHONE:
						 e.setCell(filed.ordinal(), businessshop.getShopConPhone()); 
					break;
					case SHOPEMAIL:
						 e.setCell(filed.ordinal(), businessshop.getShopEmail()); 
					break;
					case SHOPADDRESS:
						 e.setCell(filed.ordinal(), businessshop.getShopAddress()); 
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
		Map<BusinessShopSearchFields, Object> criterias = getCriterias();

		Collection<BusinessShop> moneyList = pMgr.searchBusinessShop(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBusinessShopNum(criterias);
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
		Map<BusinessShopSearchFields, Object> criterias = getCriterias();

		Collection<BusinessShop> moneyList = pMgr.searchBusinessShop(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBusinessShopNum(criterias);
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

	private Map<BusinessShopSearchFields, Object> getCriterias() {
		Map<BusinessShopSearchFields, Object> criterias = new HashMap<BusinessShopSearchFields, Object>();
		if (getShopmId()!=null&&!"".equals(getShopmId())&&!"-1".equals(getShopmId())&&!"-2".equals(getShopmId()))
			criterias.put(BusinessShopSearchFields.SHOPMID,  getShopmId());
		if (getShopName()!=null&&!"".equals(getShopName())&&!"-1".equals(getShopName())&&!"-2".equals(getShopName()))
			criterias.put(BusinessShopSearchFields.SHOPNAME, "%"+getShopName()+"%"); 
		if (getShopSno()!=null&&!"".equals(getShopSno())&&!"-1".equals(getShopSno())&&!"-2".equals(getShopSno()))
			criterias.put(BusinessShopSearchFields.SHOPSNO, "%"+getShopSno()+"%"); 
		if (getShopStatus()!=null&&!"".equals(getShopStatus())&&!"-1".equals(getShopStatus())&&!"-2".equals(getShopStatus()))
			criterias.put(BusinessShopSearchFields.SHOPSTATUS,  getShopStatus());
		
		//下面是高级查询的时候添加的条件
		//添加商家编号的查询条件
		addShopmIdCondition(criterias,getCondition_shopmId(),getQuery_shopmId());
		//添加商铺名称 的查询条件
		addShopNameCondition(criterias,getCondition_shopName(),getQuery_shopName());
		//添加商铺编号 的查询条件
		addShopSnoCondition(criterias,getCondition_shopSno(),getQuery_shopSno());
		//添加商铺状态 的查询条件
		addShopStatusCondition(criterias,getCondition_shopStatus(),getQuery_shopStatus());
		//添加联系人名称的查询条件
		addShopContactNameCondition(criterias,getCondition_shopContactName(),getQuery_shopContactName());
		//添加联系人手机的查询条件
		addShopConPhoneCondition(criterias,getCondition_shopConPhone(),getQuery_shopConPhone());
		//添加邮箱的查询条件
		addShopEmailCondition(criterias,getCondition_shopEmail(),getQuery_shopEmail());
		//添加地址的查询条件
		addShopAddressCondition(criterias,getCondition_shopAddress(),getQuery_shopAddress());
		//添加签约日期的查询条件
		addShopDateCondition(criterias,getCondition_shopDate(),getQuery_shopDate());
		//添加经度的查询条件
		addShopJingduCondition(criterias,getCondition_shopJingdu(),getQuery_shopJingdu());
		//添加纬度的查询条件
		addShopWeiduCondition(criterias,getCondition_shopWeidu(),getQuery_shopWeidu());
		//添加省份的查询条件
		addShopProvinceCondition(criterias,getCondition_shopProvince(),getQuery_shopProvince());
		//添加市的查询条件
		addShopCityCondition(criterias,getCondition_shopCity(),getQuery_shopCity());
		//添加区县的查询条件
		addShopxianCondition(criterias,getCondition_shopxian(),getQuery_shopxian());
		//添加备注的查询条件
		addShopRemarkCondition(criterias,getCondition_shopRemark(),getQuery_shopRemark());
		//添加主营的查询条件
		addShopMainCondition(criterias,getCondition_shopMain(),getQuery_shopMain());
		//添加简介的查询条件
		addShopIntroduceCondition(criterias,getCondition_shopIntroduce(),getQuery_shopIntroduce());
		//添加特色的查询条件
		addShopSpecialCondition(criterias,getCondition_shopSpecial(),getQuery_shopSpecial());
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
	public void addShopmIdCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPMID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPMID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询商铺名称 的查询条件.
	 */
	public void addShopNameCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPNAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPNAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询商铺编号 的查询条件.
	 */
	public void addShopSnoCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPSNO_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPSNO_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询商铺状态 的查询条件.
	 */
	public void addShopStatusCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPSTATUS_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPSTATUS_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询联系人名称的查询条件.
	 */
	public void addShopContactNameCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPCONTACTNAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPCONTACTNAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询联系人手机的查询条件.
	 */
	public void addShopConPhoneCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPCONPHONE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPCONPHONE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询邮箱的查询条件.
	 */
	public void addShopEmailCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPEMAIL_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPEMAIL_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询地址的查询条件.
	 */
	public void addShopAddressCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPADDRESS_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPADDRESS_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询签约日期的查询条件.
	 */
	public void addShopDateCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPDATE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPDATE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询经度的查询条件.
	 */
	public void addShopJingduCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPJINGDU_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPJINGDU_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询纬度的查询条件.
	 */
	public void addShopWeiduCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPWEIDU_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPWEIDU_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询省份的查询条件.
	 */
	public void addShopProvinceCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPPROVINCE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPPROVINCE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询市的查询条件.
	 */
	public void addShopCityCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPCITY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPCITY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询区县的查询条件.
	 */
	public void addShopxianCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPXIAN_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPXIAN_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询备注的查询条件.
	 */
	public void addShopRemarkCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPREMARK_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPREMARK_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询主营的查询条件.
	 */
	public void addShopMainCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPMAIN_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPMAIN_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询简介的查询条件.
	 */
	public void addShopIntroduceCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPINTRODUCE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPINTRODUCE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询特色的查询条件.
	 */
	public void addShopSpecialCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPSPECIAL_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.SHOPSPECIAL_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建用户的查询条件.
	 */
	public void addCreateUserCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新用户的查询条件.
	 */
	public void addUpdateUserCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.UPDATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.UPDATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新时间的查询条件.
	 */
	public void addUpdateTimeCondition(Map<BusinessShopSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.UPDATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BusinessShopSearchFields.UPDATETIME_COM_NOT_EQUALS, value);
		} 
	} 

	public BusinessShop getVo() {
		return vo;
	}

	public void setVo(BusinessShop vo) {
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
	private String shopmId; 
 	/**
 	 * 获取商家编号的属性值.
 	 */
 	public String getShopmId(){
 		return shopmId;
 	}
 	
 	/**
 	 * 设置商家编号的属性值.
 	 */
 	public void setShopmId(String shopmid){
 		this.shopmId = shopmid;
 	}
	private String shopName; 
 	/**
 	 * 获取商铺名称 的属性值.
 	 */
 	public String getShopName(){
 		return shopName;
 	}
 	
 	/**
 	 * 设置商铺名称 的属性值.
 	 */
 	public void setShopName(String shopname){
 		this.shopName = shopname;
 	}
	private String shopSno; 
 	/**
 	 * 获取商铺编号 的属性值.
 	 */
 	public String getShopSno(){
 		return shopSno;
 	}
 	
 	/**
 	 * 设置商铺编号 的属性值.
 	 */
 	public void setShopSno(String shopsno){
 		this.shopSno = shopsno;
 	}
	private String shopStatus; 
 	/**
 	 * 获取商铺状态 的属性值.
 	 */
 	public String getShopStatus(){
 		return shopStatus;
 	}
 	
 	/**
 	 * 设置商铺状态 的属性值.
 	 */
 	public void setShopStatus(String shopstatus){
 		this.shopStatus = shopstatus;
 	}
	private String shopContactName; 
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public String getShopContactName(){
 		return shopContactName;
 	}
 	
 	/**
 	 * 设置联系人名称的属性值.
 	 */
 	public void setShopContactName(String shopcontactname){
 		this.shopContactName = shopcontactname;
 	}
	private String shopConPhone; 
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public String getShopConPhone(){
 		return shopConPhone;
 	}
 	
 	/**
 	 * 设置联系人手机的属性值.
 	 */
 	public void setShopConPhone(String shopconphone){
 		this.shopConPhone = shopconphone;
 	}
	private String shopEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getShopEmail(){
 		return shopEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setShopEmail(String shopemail){
 		this.shopEmail = shopemail;
 	}
	private String shopAddress; 
 	/**
 	 * 获取地址的属性值.
 	 */
 	public String getShopAddress(){
 		return shopAddress;
 	}
 	
 	/**
 	 * 设置地址的属性值.
 	 */
 	public void setShopAddress(String shopaddress){
 		this.shopAddress = shopaddress;
 	}
	private String shopDate; 
 	/**
 	 * 获取签约日期的属性值.
 	 */
 	public String getShopDate(){
 		return shopDate;
 	}
 	
 	/**
 	 * 设置签约日期的属性值.
 	 */
 	public void setShopDate(String shopdate){
 		this.shopDate = shopdate;
 	}
	private String shopJingdu; 
 	/**
 	 * 获取经度的属性值.
 	 */
 	public String getShopJingdu(){
 		return shopJingdu;
 	}
 	
 	/**
 	 * 设置经度的属性值.
 	 */
 	public void setShopJingdu(String shopjingdu){
 		this.shopJingdu = shopjingdu;
 	}
	private String shopWeidu; 
 	/**
 	 * 获取纬度的属性值.
 	 */
 	public String getShopWeidu(){
 		return shopWeidu;
 	}
 	
 	/**
 	 * 设置纬度的属性值.
 	 */
 	public void setShopWeidu(String shopweidu){
 		this.shopWeidu = shopweidu;
 	}
	private String shopProvince; 
 	/**
 	 * 获取省份的属性值.
 	 */
 	public String getShopProvince(){
 		return shopProvince;
 	}
 	
 	/**
 	 * 设置省份的属性值.
 	 */
 	public void setShopProvince(String shopprovince){
 		this.shopProvince = shopprovince;
 	}
	private String shopCity; 
 	/**
 	 * 获取市的属性值.
 	 */
 	public String getShopCity(){
 		return shopCity;
 	}
 	
 	/**
 	 * 设置市的属性值.
 	 */
 	public void setShopCity(String shopcity){
 		this.shopCity = shopcity;
 	}
	private String shopxian; 
 	/**
 	 * 获取区县的属性值.
 	 */
 	public String getShopxian(){
 		return shopxian;
 	}
 	
 	/**
 	 * 设置区县的属性值.
 	 */
 	public void setShopxian(String shopxian){
 		this.shopxian = shopxian;
 	}
	private String shopRemark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getShopRemark(){
 		return shopRemark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setShopRemark(String shopremark){
 		this.shopRemark = shopremark;
 	}
	private String shopMain; 
 	/**
 	 * 获取主营的属性值.
 	 */
 	public String getShopMain(){
 		return shopMain;
 	}
 	
 	/**
 	 * 设置主营的属性值.
 	 */
 	public void setShopMain(String shopmain){
 		this.shopMain = shopmain;
 	}
	private String shopIntroduce; 
 	/**
 	 * 获取简介的属性值.
 	 */
 	public String getShopIntroduce(){
 		return shopIntroduce;
 	}
 	
 	/**
 	 * 设置简介的属性值.
 	 */
 	public void setShopIntroduce(String shopintroduce){
 		this.shopIntroduce = shopintroduce;
 	}
	private String shopSpecial; 
 	/**
 	 * 获取特色的属性值.
 	 */
 	public String getShopSpecial(){
 		return shopSpecial;
 	}
 	
 	/**
 	 * 设置特色的属性值.
 	 */
 	public void setShopSpecial(String shopspecial){
 		this.shopSpecial = shopspecial;
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
	
	private String condition_shopmId;
	
	public String getCondition_shopmId(){
		return this.condition_shopmId;
	}
	
	public void setCondition_shopmId(String s){
		this.condition_shopmId = s;
	}
	
	private String query_shopmId;
		
	public String getQuery_shopmId(){
		return this.query_shopmId;
	}
	
	public void setQuery_shopmId(String s){
		this.query_shopmId = s;
	}
	
	private String condition_shopName;
	
	public String getCondition_shopName(){
		return this.condition_shopName;
	}
	
	public void setCondition_shopName(String s){
		this.condition_shopName = s;
	}
	
	private String query_shopName;
		
	public String getQuery_shopName(){
		return this.query_shopName;
	}
	
	public void setQuery_shopName(String s){
		this.query_shopName = s;
	}
	
	private String condition_shopSno;
	
	public String getCondition_shopSno(){
		return this.condition_shopSno;
	}
	
	public void setCondition_shopSno(String s){
		this.condition_shopSno = s;
	}
	
	private String query_shopSno;
		
	public String getQuery_shopSno(){
		return this.query_shopSno;
	}
	
	public void setQuery_shopSno(String s){
		this.query_shopSno = s;
	}
	
	private String condition_shopStatus;
	
	public String getCondition_shopStatus(){
		return this.condition_shopStatus;
	}
	
	public void setCondition_shopStatus(String s){
		this.condition_shopStatus = s;
	}
	
	private String query_shopStatus;
		
	public String getQuery_shopStatus(){
		return this.query_shopStatus;
	}
	
	public void setQuery_shopStatus(String s){
		this.query_shopStatus = s;
	}
	
	private String condition_shopContactName;
	
	public String getCondition_shopContactName(){
		return this.condition_shopContactName;
	}
	
	public void setCondition_shopContactName(String s){
		this.condition_shopContactName = s;
	}
	
	private String query_shopContactName;
		
	public String getQuery_shopContactName(){
		return this.query_shopContactName;
	}
	
	public void setQuery_shopContactName(String s){
		this.query_shopContactName = s;
	}
	
	private String condition_shopConPhone;
	
	public String getCondition_shopConPhone(){
		return this.condition_shopConPhone;
	}
	
	public void setCondition_shopConPhone(String s){
		this.condition_shopConPhone = s;
	}
	
	private String query_shopConPhone;
		
	public String getQuery_shopConPhone(){
		return this.query_shopConPhone;
	}
	
	public void setQuery_shopConPhone(String s){
		this.query_shopConPhone = s;
	}
	
	private String condition_shopEmail;
	
	public String getCondition_shopEmail(){
		return this.condition_shopEmail;
	}
	
	public void setCondition_shopEmail(String s){
		this.condition_shopEmail = s;
	}
	
	private String query_shopEmail;
		
	public String getQuery_shopEmail(){
		return this.query_shopEmail;
	}
	
	public void setQuery_shopEmail(String s){
		this.query_shopEmail = s;
	}
	
	private String condition_shopAddress;
	
	public String getCondition_shopAddress(){
		return this.condition_shopAddress;
	}
	
	public void setCondition_shopAddress(String s){
		this.condition_shopAddress = s;
	}
	
	private String query_shopAddress;
		
	public String getQuery_shopAddress(){
		return this.query_shopAddress;
	}
	
	public void setQuery_shopAddress(String s){
		this.query_shopAddress = s;
	}
	
	private String condition_shopDate;
	
	public String getCondition_shopDate(){
		return this.condition_shopDate;
	}
	
	public void setCondition_shopDate(String s){
		this.condition_shopDate = s;
	}
	
	private String query_shopDate;
		
	public String getQuery_shopDate(){
		return this.query_shopDate;
	}
	
	public void setQuery_shopDate(String s){
		this.query_shopDate = s;
	}
	
	private String condition_shopJingdu;
	
	public String getCondition_shopJingdu(){
		return this.condition_shopJingdu;
	}
	
	public void setCondition_shopJingdu(String s){
		this.condition_shopJingdu = s;
	}
	
	private String query_shopJingdu;
		
	public String getQuery_shopJingdu(){
		return this.query_shopJingdu;
	}
	
	public void setQuery_shopJingdu(String s){
		this.query_shopJingdu = s;
	}
	
	private String condition_shopWeidu;
	
	public String getCondition_shopWeidu(){
		return this.condition_shopWeidu;
	}
	
	public void setCondition_shopWeidu(String s){
		this.condition_shopWeidu = s;
	}
	
	private String query_shopWeidu;
		
	public String getQuery_shopWeidu(){
		return this.query_shopWeidu;
	}
	
	public void setQuery_shopWeidu(String s){
		this.query_shopWeidu = s;
	}
	
	private String condition_shopProvince;
	
	public String getCondition_shopProvince(){
		return this.condition_shopProvince;
	}
	
	public void setCondition_shopProvince(String s){
		this.condition_shopProvince = s;
	}
	
	private String query_shopProvince;
		
	public String getQuery_shopProvince(){
		return this.query_shopProvince;
	}
	
	public void setQuery_shopProvince(String s){
		this.query_shopProvince = s;
	}
	
	private String condition_shopCity;
	
	public String getCondition_shopCity(){
		return this.condition_shopCity;
	}
	
	public void setCondition_shopCity(String s){
		this.condition_shopCity = s;
	}
	
	private String query_shopCity;
		
	public String getQuery_shopCity(){
		return this.query_shopCity;
	}
	
	public void setQuery_shopCity(String s){
		this.query_shopCity = s;
	}
	
	private String condition_shopxian;
	
	public String getCondition_shopxian(){
		return this.condition_shopxian;
	}
	
	public void setCondition_shopxian(String s){
		this.condition_shopxian = s;
	}
	
	private String query_shopxian;
		
	public String getQuery_shopxian(){
		return this.query_shopxian;
	}
	
	public void setQuery_shopxian(String s){
		this.query_shopxian = s;
	}
	
	private String condition_shopRemark;
	
	public String getCondition_shopRemark(){
		return this.condition_shopRemark;
	}
	
	public void setCondition_shopRemark(String s){
		this.condition_shopRemark = s;
	}
	
	private String query_shopRemark;
		
	public String getQuery_shopRemark(){
		return this.query_shopRemark;
	}
	
	public void setQuery_shopRemark(String s){
		this.query_shopRemark = s;
	}
	
	private String condition_shopMain;
	
	public String getCondition_shopMain(){
		return this.condition_shopMain;
	}
	
	public void setCondition_shopMain(String s){
		this.condition_shopMain = s;
	}
	
	private String query_shopMain;
		
	public String getQuery_shopMain(){
		return this.query_shopMain;
	}
	
	public void setQuery_shopMain(String s){
		this.query_shopMain = s;
	}
	
	private String condition_shopIntroduce;
	
	public String getCondition_shopIntroduce(){
		return this.condition_shopIntroduce;
	}
	
	public void setCondition_shopIntroduce(String s){
		this.condition_shopIntroduce = s;
	}
	
	private String query_shopIntroduce;
		
	public String getQuery_shopIntroduce(){
		return this.query_shopIntroduce;
	}
	
	public void setQuery_shopIntroduce(String s){
		this.query_shopIntroduce = s;
	}
	
	private String condition_shopSpecial;
	
	public String getCondition_shopSpecial(){
		return this.condition_shopSpecial;
	}
	
	public void setCondition_shopSpecial(String s){
		this.condition_shopSpecial = s;
	}
	
	private String query_shopSpecial;
		
	public String getQuery_shopSpecial(){
		return this.query_shopSpecial;
	}
	
	public void setQuery_shopSpecial(String s){
		this.query_shopSpecial = s;
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
