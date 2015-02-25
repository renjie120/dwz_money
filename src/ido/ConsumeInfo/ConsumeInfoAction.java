
package ido.ConsumeInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.util.*; 
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import dwz.framework.constants.Constants;
import com.alibaba.fastjson.JSON;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.util.CommonUtil;
import common.util.DateTool;
import common.util.DateUtil;
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
 * 关于消费记录的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ConsumeInfoAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	ConsumeInfoManager pMgr = bf.getManager(BeanManagerKey.consumeinfoManager);
	//业务实体对象
	private ConsumeInfo vo;
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
 	 * 添加消费记录.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			ConsumeInfoImpl consumeinfoImpl = new ConsumeInfoImpl(iuserId ,comId ,shopmId ,shopId ,ownerCom ,consumeStatus ,consumeMoney ,cardMoney ,cashMoney ,consumeTime ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createConsumeInfo(consumeinfoImpl);
			insertLog(logMgr,"添加消费记录","/doAdd", "", "" ,JSON.toJSONString(consumeinfoImpl));  
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
		String file_name = "消费记录上传模板";
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
		insertLog(logMgr,"导入消费记录","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<ConsumeInfo> allDeleteIds = new ArrayList<ConsumeInfo>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getConsumeInfo(Integer.parseInt(_id)));
		}
		pMgr.removeConsumeInfos(ids);
		insertLog(logMgr,"删除消费记录","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getConsumeInfo(sno);
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
			ConsumeInfo old = pMgr.getConsumeInfo( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getIuserId(),iuserId)){
				oldObj += "iuserId="+old.getIuserId()+";";
				newObj+= "iuserId="+iuserId+";";
			} 
			if(!compare(old.getComId(),comId)){
				oldObj += "comId="+old.getComId()+";";
				newObj+= "comId="+comId+";";
			} 
			if(!compare(old.getShopmId(),shopmId)){
				oldObj += "shopmId="+old.getShopmId()+";";
				newObj+= "shopmId="+shopmId+";";
			} 
			if(!compare(old.getShopId(),shopId)){
				oldObj += "shopId="+old.getShopId()+";";
				newObj+= "shopId="+shopId+";";
			} 
			if(!compare(old.getOwnerCom(),ownerCom)){
				oldObj += "ownerCom="+old.getOwnerCom()+";";
				newObj+= "ownerCom="+ownerCom+";";
			} 
			if(!compare(old.getConsumeStatus(),consumeStatus)){
				oldObj += "consumeStatus="+old.getConsumeStatus()+";";
				newObj+= "consumeStatus="+consumeStatus+";";
			} 
			if(!compare(old.getConsumeMoney(),consumeMoney)){
				oldObj += "consumeMoney="+old.getConsumeMoney()+";";
				newObj+= "consumeMoney="+consumeMoney+";";
			} 
			if(!compare(old.getCardMoney(),cardMoney)){
				oldObj += "cardMoney="+old.getCardMoney()+";";
				newObj+= "cardMoney="+cardMoney+";";
			} 
			if(!compare(old.getCashMoney(),cashMoney)){
				oldObj += "cashMoney="+old.getCashMoney()+";";
				newObj+= "cashMoney="+cashMoney+";";
			} 
			if(!compare(old.getConsumeTime(),consumeTime)){
				oldObj += "consumeTime="+old.getConsumeTime()+";";
				newObj+= "consumeTime="+consumeTime+";";
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
			
			ConsumeInfoImpl consumeinfoImpl = new ConsumeInfoImpl( sno , iuserId , comId , shopmId , shopId , ownerCom , consumeStatus , consumeMoney , cardMoney , cashMoney , consumeTime , createUser , createTime , updateUser , updateTime );
			pMgr.updateConsumeInfo(consumeinfoImpl);
			insertLog(logMgr,"修改消费记录","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(consumeinfoImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,IUSERID("投保用户号")  ,CONSUMESTATUS("支付状态")  ,CONSUMEMONEY("消费金额")  ,CARDMONEY("刷卡消费")  ,CASHMONEY("现金支付")  ,CONSUMETIME("消费时间");
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
		ParamSelect select_paytype = allSelect
				.getParamsByType(AllSelectContants.PAYTYPE.getName()); 
		request.setAttribute("consumestatus_list", select_paytype.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		String file_name = "消费记录_"+DateUtil.toString(DateUtil.now(),"yyyyMMddHHmm");
		try {
			file_name = URLEncoder.encode(file_name, "UTF-8");
		} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace();
		}   
		response.addHeader("Content-Disposition","attachment;filename="+file_name+".xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<ConsumeInfoSearchFields, Object> criterias = getCriterias();

		Collection<ConsumeInfo> consumeinfoList = pMgr.searchConsumeInfo(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (ConsumeInfo consumeinfo : consumeinfoList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), consumeinfo.getSno()); 
					break;
					case IUSERID:
						 e.setCell(filed.ordinal(), consumeinfo.getIuserId()); 
					break;
					case CONSUMESTATUS:
						 e.setCell(filed.ordinal(), consumeinfo.getConsumeStatus()); 
					break;
					case CONSUMEMONEY:
						 e.setCell(filed.ordinal(), consumeinfo.getConsumeMoney()); 
					break;
					case CARDMONEY:
						 e.setCell(filed.ordinal(), consumeinfo.getCardMoney()); 
					break;
					case CASHMONEY:
						 e.setCell(filed.ordinal(), consumeinfo.getCashMoney()); 
					break;
					case CONSUMETIME:
						 e.setCell(filed.ordinal(), consumeinfo.getConsumeTime()); 
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
		Map<ConsumeInfoSearchFields, Object> criterias = getCriterias();

		Collection<ConsumeInfo> moneyList = pMgr.searchConsumeInfo(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchConsumeInfoNum(criterias);
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
		Map<ConsumeInfoSearchFields, Object> criterias = getCriterias();

		Collection<ConsumeInfo> moneyList = pMgr.searchConsumeInfo(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchConsumeInfoNum(criterias);
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

	private Map<ConsumeInfoSearchFields, Object> getCriterias() {
		Map<ConsumeInfoSearchFields, Object> criterias = new HashMap<ConsumeInfoSearchFields, Object>();
		if (getIuserId()!=null&&!"".equals(getIuserId())&&!"-1".equals(getIuserId())&&!"-2".equals(getIuserId()))
			criterias.put(ConsumeInfoSearchFields.IUSERID,  getIuserId());
		if (getShopmId()!=null&&!"".equals(getShopmId())&&!"-1".equals(getShopmId())&&!"-2".equals(getShopmId()))
			criterias.put(ConsumeInfoSearchFields.SHOPMID,  getShopmId());
		if (getShopId()!=null&&!"".equals(getShopId())&&!"-1".equals(getShopId())&&!"-2".equals(getShopId()))
			criterias.put(ConsumeInfoSearchFields.SHOPID,  getShopId());
		if (getOwnerCom()!=null&&!"".equals(getOwnerCom())&&!"-1".equals(getOwnerCom())&&!"-2".equals(getOwnerCom()))
			criterias.put(ConsumeInfoSearchFields.OWNERCOM,  getOwnerCom());
		
		//下面是高级查询的时候添加的条件
		//添加投保用户号的查询条件
		addIuserIdCondition(criterias,getCondition_iuserId(),getQuery_iuserId());
		//添加投保公司的查询条件
		addComIdCondition(criterias,getCondition_comId(),getQuery_comId());
		//添加所属商家 的查询条件
		addShopmIdCondition(criterias,getCondition_shopmId(),getQuery_shopmId());
		//添加所属商铺 的查询条件
		addShopIdCondition(criterias,getCondition_shopId(),getQuery_shopId());
		//添加所属分公司 的查询条件
		addOwnerComCondition(criterias,getCondition_ownerCom(),getQuery_ownerCom());
		//添加支付状态的查询条件
		addConsumeStatusCondition(criterias,getCondition_consumeStatus(),getQuery_consumeStatus());
		//添加消费金额的查询条件
		addConsumeMoneyCondition(criterias,getCondition_consumeMoney(),getQuery_consumeMoney());
		//添加刷卡消费的查询条件
		addCardMoneyCondition(criterias,getCondition_cardMoney(),getQuery_cardMoney());
		//添加现金支付的查询条件
		addCashMoneyCondition(criterias,getCondition_cashMoney(),getQuery_cashMoney());
		//添加消费时间的查询条件
		addConsumeTimeCondition(criterias,getCondition_consumeTime(),getQuery_consumeTime());
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
	 * 添加查询投保用户号的查询条件.
	 */
	public void addIuserIdCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.IUSERID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.IUSERID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询投保公司的查询条件.
	 */
	public void addComIdCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.COMID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.COMID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询所属商家 的查询条件.
	 */
	public void addShopmIdCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.SHOPMID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.SHOPMID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询所属商铺 的查询条件.
	 */
	public void addShopIdCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.SHOPID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.SHOPID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询所属分公司 的查询条件.
	 */
	public void addOwnerComCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.OWNERCOM_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.OWNERCOM_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询支付状态的查询条件.
	 */
	public void addConsumeStatusCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CONSUMESTATUS_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CONSUMESTATUS_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询消费金额的查询条件.
	 */
	public void addConsumeMoneyCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CONSUMEMONEY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CONSUMEMONEY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询刷卡消费的查询条件.
	 */
	public void addCardMoneyCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CARDMONEY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CARDMONEY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询现金支付的查询条件.
	 */
	public void addCashMoneyCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CASHMONEY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CASHMONEY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询消费时间的查询条件.
	 */
	public void addConsumeTimeCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CONSUMETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CONSUMETIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建用户的查询条件.
	 */
	public void addCreateUserCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新用户的查询条件.
	 */
	public void addUpdateUserCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.UPDATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.UPDATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新时间的查询条件.
	 */
	public void addUpdateTimeCondition(Map<ConsumeInfoSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.UPDATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(ConsumeInfoSearchFields.UPDATETIME_COM_NOT_EQUALS, value);
		} 
	} 

	public ConsumeInfo getVo() {
		return vo;
	}

	public void setVo(ConsumeInfo vo) {
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
	private String iuserId; 
 	/**
 	 * 获取投保用户号的属性值.
 	 */
 	public String getIuserId(){
 		return iuserId;
 	}
 	
 	/**
 	 * 设置投保用户号的属性值.
 	 */
 	public void setIuserId(String iuserid){
 		this.iuserId = iuserid;
 	}
	private String comId; 
 	/**
 	 * 获取投保公司的属性值.
 	 */
 	public String getComId(){
 		return comId;
 	}
 	
 	/**
 	 * 设置投保公司的属性值.
 	 */
 	public void setComId(String comid){
 		this.comId = comid;
 	}
	private String shopmId; 
 	/**
 	 * 获取所属商家 的属性值.
 	 */
 	public String getShopmId(){
 		return shopmId;
 	}
 	
 	/**
 	 * 设置所属商家 的属性值.
 	 */
 	public void setShopmId(String shopmid){
 		this.shopmId = shopmid;
 	}
	private String shopId; 
 	/**
 	 * 获取所属商铺 的属性值.
 	 */
 	public String getShopId(){
 		return shopId;
 	}
 	
 	/**
 	 * 设置所属商铺 的属性值.
 	 */
 	public void setShopId(String shopid){
 		this.shopId = shopid;
 	}
	private String ownerCom; 
 	/**
 	 * 获取所属分公司 的属性值.
 	 */
 	public String getOwnerCom(){
 		return ownerCom;
 	}
 	
 	/**
 	 * 设置所属分公司 的属性值.
 	 */
 	public void setOwnerCom(String ownercom){
 		this.ownerCom = ownercom;
 	}
	private String consumeStatus; 
 	/**
 	 * 获取支付状态的属性值.
 	 */
 	public String getConsumeStatus(){
 		return consumeStatus;
 	}
 	
 	/**
 	 * 设置支付状态的属性值.
 	 */
 	public void setConsumeStatus(String consumestatus){
 		this.consumeStatus = consumestatus;
 	}
	private double consumeMoney; 
 	/**
 	 * 获取消费金额的属性值.
 	 */
 	public double getConsumeMoney(){
 		return consumeMoney;
 	}
 	
 	/**
 	 * 设置消费金额的属性值.
 	 */
 	public void setConsumeMoney(double consumemoney){
 		this.consumeMoney = consumemoney;
 	}
	private double cardMoney; 
 	/**
 	 * 获取刷卡消费的属性值.
 	 */
 	public double getCardMoney(){
 		return cardMoney;
 	}
 	
 	/**
 	 * 设置刷卡消费的属性值.
 	 */
 	public void setCardMoney(double cardmoney){
 		this.cardMoney = cardmoney;
 	}
	private double cashMoney; 
 	/**
 	 * 获取现金支付的属性值.
 	 */
 	public double getCashMoney(){
 		return cashMoney;
 	}
 	
 	/**
 	 * 设置现金支付的属性值.
 	 */
 	public void setCashMoney(double cashmoney){
 		this.cashMoney = cashmoney;
 	}
	private String consumeTime; 
 	/**
 	 * 获取消费时间的属性值.
 	 */
 	public String getConsumeTime(){
 		return consumeTime;
 	}
 	
 	/**
 	 * 设置消费时间的属性值.
 	 */
 	public void setConsumeTime(String consumetime){
 		this.consumeTime = consumetime;
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
	
	private String condition_iuserId;
	
	public String getCondition_iuserId(){
		return this.condition_iuserId;
	}
	
	public void setCondition_iuserId(String s){
		this.condition_iuserId = s;
	}
	
	private String query_iuserId;
		
	public String getQuery_iuserId(){
		return this.query_iuserId;
	}
	
	public void setQuery_iuserId(String s){
		this.query_iuserId = s;
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
	
	private String condition_shopId;
	
	public String getCondition_shopId(){
		return this.condition_shopId;
	}
	
	public void setCondition_shopId(String s){
		this.condition_shopId = s;
	}
	
	private String query_shopId;
		
	public String getQuery_shopId(){
		return this.query_shopId;
	}
	
	public void setQuery_shopId(String s){
		this.query_shopId = s;
	}
	
	private String condition_ownerCom;
	
	public String getCondition_ownerCom(){
		return this.condition_ownerCom;
	}
	
	public void setCondition_ownerCom(String s){
		this.condition_ownerCom = s;
	}
	
	private String query_ownerCom;
		
	public String getQuery_ownerCom(){
		return this.query_ownerCom;
	}
	
	public void setQuery_ownerCom(String s){
		this.query_ownerCom = s;
	}
	
	private String condition_consumeStatus;
	
	public String getCondition_consumeStatus(){
		return this.condition_consumeStatus;
	}
	
	public void setCondition_consumeStatus(String s){
		this.condition_consumeStatus = s;
	}
	
	private String query_consumeStatus;
		
	public String getQuery_consumeStatus(){
		return this.query_consumeStatus;
	}
	
	public void setQuery_consumeStatus(String s){
		this.query_consumeStatus = s;
	}
	
	private String condition_consumeMoney;
	
	public String getCondition_consumeMoney(){
		return this.condition_consumeMoney;
	}
	
	public void setCondition_consumeMoney(String s){
		this.condition_consumeMoney = s;
	}
	
	private String query_consumeMoney;
		
	public String getQuery_consumeMoney(){
		return this.query_consumeMoney;
	}
	
	public void setQuery_consumeMoney(String s){
		this.query_consumeMoney = s;
	}
	
	private String condition_cardMoney;
	
	public String getCondition_cardMoney(){
		return this.condition_cardMoney;
	}
	
	public void setCondition_cardMoney(String s){
		this.condition_cardMoney = s;
	}
	
	private String query_cardMoney;
		
	public String getQuery_cardMoney(){
		return this.query_cardMoney;
	}
	
	public void setQuery_cardMoney(String s){
		this.query_cardMoney = s;
	}
	
	private String condition_cashMoney;
	
	public String getCondition_cashMoney(){
		return this.condition_cashMoney;
	}
	
	public void setCondition_cashMoney(String s){
		this.condition_cashMoney = s;
	}
	
	private String query_cashMoney;
		
	public String getQuery_cashMoney(){
		return this.query_cashMoney;
	}
	
	public void setQuery_cashMoney(String s){
		this.query_cashMoney = s;
	}
	
	private String condition_consumeTime;
	
	public String getCondition_consumeTime(){
		return this.condition_consumeTime;
	}
	
	public void setCondition_consumeTime(String s){
		this.condition_consumeTime = s;
	}
	
	private String query_consumeTime;
		
	public String getQuery_consumeTime(){
		return this.query_consumeTime;
	}
	
	public void setQuery_consumeTime(String s){
		this.query_consumeTime = s;
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
