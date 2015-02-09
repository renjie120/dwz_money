
package ido.addmoneydetail;
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
import common.cache.CacheUtil;
import ido.loginfo.LogInfoManager;
import common.base.AllSelect;
import common.base.AllSelectContants;
/**
 * 关于充值记录明细的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class AddMoneyDetailAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	AddMoneyDetailManager pMgr = bf.getManager(BeanManagerKey.addmoneydetailManager);
	//业务实体对象
	private AddMoneyDetail vo;
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
 	 * 添加充值记录明细.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			AddMoneyDetailImpl addmoneydetailImpl = new AddMoneyDetailImpl(iuserId ,addType ,addMoney ,insuredFileId ,addTime ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createAddMoneyDetail(addmoneydetailImpl);
			insertLog(logMgr,"添加充值记录明细","/doAdd", "", "" ,JSON.toJSONString(addmoneydetailImpl));  
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
		insertLog(logMgr,"导入充值记录明细","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<AddMoneyDetail> allDeleteIds = new ArrayList<AddMoneyDetail>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getAddMoneyDetail(Integer.parseInt(_id)));
		}
		pMgr.removeAddMoneyDetails(ids);
		insertLog(logMgr,"删除充值记录明细","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getAddMoneyDetail(sno);
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
			AddMoneyDetail old = pMgr.getAddMoneyDetail( sno );
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
			if(!compare(old.getAddType(),addType)){
				oldObj += "addType="+old.getAddType()+";";
				newObj+= "addType="+addType+";";
			} 
			if(!compare(old.getAddMoney(),addMoney)){
				oldObj += "addMoney="+old.getAddMoney()+";";
				newObj+= "addMoney="+addMoney+";";
			} 
			if(!compare(old.getInsuredFileId(),insuredFileId)){
				oldObj += "insuredFileId="+old.getInsuredFileId()+";";
				newObj+= "insuredFileId="+insuredFileId+";";
			} 
			if(!compare(old.getAddTime(),addTime)){
				oldObj += "addTime="+old.getAddTime()+";";
				newObj+= "addTime="+addTime+";";
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
			
			AddMoneyDetailImpl addmoneydetailImpl = new AddMoneyDetailImpl( sno , iuserId , addType , addMoney , insuredFileId , addTime , createUser , createTime , updateUser , updateTime );
			pMgr.updateAddMoneyDetail(addmoneydetailImpl);
			insertLog(logMgr,"修改充值记录明细","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(addmoneydetailImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,IUSERID("投保用户号")  ,ADDTYPE("充值字段")  ,ADDMONEY("充值金额 ")  ,INSUREDFILEID("投保单号 ")  ,ADDTIME("充值时间")  ,CREATEUSER("创建用户")  ,CREATETIME("创建时间");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		  IUSERID("投保用户号")  ,ADDTYPE("充值字段")  ,ADDMONEY("充值金额 ")  ,INSUREDFILEID("投保单号 ");
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
		ParamSelect select_addmoneytype = allSelect
				.getParamsByType(AllSelectContants.ADDMONEYTYPE.getName()); 
		request.setAttribute("addtype_list", select_addmoneytype.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=AddMoneyDetailList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<AddMoneyDetailSearchFields, Object> criterias = getCriterias();

		Collection<AddMoneyDetail> addmoneydetailList = pMgr.searchAddMoneyDetail(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (AddMoneyDetail addmoneydetail : addmoneydetailList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), addmoneydetail.getSno()); 
					break;
					case IUSERID:
						 e.setCell(filed.ordinal(), addmoneydetail.getIuserId()); 
					break;
					case ADDTYPE:
						 e.setCell(filed.ordinal(), addmoneydetail.getAddType()); 
					break;
					case ADDMONEY:
						 e.setCell(filed.ordinal(), addmoneydetail.getAddMoney()); 
					break;
					case INSUREDFILEID:
						 e.setCell(filed.ordinal(), addmoneydetail.getInsuredFileId()); 
					break;
					case ADDTIME:
						 e.setCell(filed.ordinal(), addmoneydetail.getAddTime()); 
					break;
					case CREATEUSER:
						 e.setCell(filed.ordinal(), CacheUtil.getSystemUserName(""+addmoneydetail.getCreateUser())); 
					break;
					case CREATETIME:
						 e.setCell(filed.ordinal(), addmoneydetail.getCreateTime()); 
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
		Map<AddMoneyDetailSearchFields, Object> criterias = getCriterias();

		Collection<AddMoneyDetail> moneyList = pMgr.searchAddMoneyDetail(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchAddMoneyDetailNum(criterias);
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
		Map<AddMoneyDetailSearchFields, Object> criterias = getCriterias();

		Collection<AddMoneyDetail> moneyList = pMgr.searchAddMoneyDetail(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchAddMoneyDetailNum(criterias);
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

	private Map<AddMoneyDetailSearchFields, Object> getCriterias() {
		Map<AddMoneyDetailSearchFields, Object> criterias = new HashMap<AddMoneyDetailSearchFields, Object>();
		if (getIuserId()!=null&&!"".equals(getIuserId())&&!"-1".equals(getIuserId())&&!"-2".equals(getIuserId()))
			criterias.put(AddMoneyDetailSearchFields.IUSERID,  getIuserId());
		if (getInsuredFileId()!=null&&!"".equals(getInsuredFileId())&&!"-1".equals(getInsuredFileId())&&!"-2".equals(getInsuredFileId()))
			criterias.put(AddMoneyDetailSearchFields.INSUREDFILEID,  getInsuredFileId());
		
		//下面是高级查询的时候添加的条件
		//添加投保用户号的查询条件
		addIuserIdCondition(criterias,getCondition_iuserId(),getQuery_iuserId());
		//添加充值字段的查询条件
		addAddTypeCondition(criterias,getCondition_addType(),getQuery_addType());
		//添加充值金额 的查询条件
		addAddMoneyCondition(criterias,getCondition_addMoney(),getQuery_addMoney());
		//添加投保单号 的查询条件
		addInsuredFileIdCondition(criterias,getCondition_insuredFileId(),getQuery_insuredFileId());
		//添加充值时间的查询条件
		addAddTimeCondition(criterias,getCondition_addTime(),getQuery_addTime());
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
	public void addIuserIdCondition(Map<AddMoneyDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.IUSERID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.IUSERID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询充值字段的查询条件.
	 */
	public void addAddTypeCondition(Map<AddMoneyDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.ADDTYPE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.ADDTYPE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询充值金额 的查询条件.
	 */
	public void addAddMoneyCondition(Map<AddMoneyDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.ADDMONEY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.ADDMONEY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询投保单号 的查询条件.
	 */
	public void addInsuredFileIdCondition(Map<AddMoneyDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.INSUREDFILEID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.INSUREDFILEID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询充值时间的查询条件.
	 */
	public void addAddTimeCondition(Map<AddMoneyDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.ADDTIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.ADDTIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建用户的查询条件.
	 */
	public void addCreateUserCondition(Map<AddMoneyDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<AddMoneyDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新用户的查询条件.
	 */
	public void addUpdateUserCondition(Map<AddMoneyDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.UPDATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.UPDATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新时间的查询条件.
	 */
	public void addUpdateTimeCondition(Map<AddMoneyDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.UPDATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddMoneyDetailSearchFields.UPDATETIME_COM_NOT_EQUALS, value);
		} 
	} 

	public AddMoneyDetail getVo() {
		return vo;
	}

	public void setVo(AddMoneyDetail vo) {
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
	private String addType; 
 	/**
 	 * 获取充值字段的属性值.
 	 */
 	public String getAddType(){
 		return addType;
 	}
 	
 	/**
 	 * 设置充值字段的属性值.
 	 */
 	public void setAddType(String addtype){
 		this.addType = addtype;
 	}
	private String addMoney; 
 	/**
 	 * 获取充值金额 的属性值.
 	 */
 	public String getAddMoney(){
 		return addMoney;
 	}
 	
 	/**
 	 * 设置充值金额 的属性值.
 	 */
 	public void setAddMoney(String addmoney){
 		this.addMoney = addmoney;
 	}
	private String insuredFileId; 
 	/**
 	 * 获取投保单号 的属性值.
 	 */
 	public String getInsuredFileId(){
 		return insuredFileId;
 	}
 	
 	/**
 	 * 设置投保单号 的属性值.
 	 */
 	public void setInsuredFileId(String insuredfileid){
 		this.insuredFileId = insuredfileid;
 	}
	private String addTime; 
 	/**
 	 * 获取充值时间的属性值.
 	 */
 	public String getAddTime(){
 		return addTime;
 	}
 	
 	/**
 	 * 设置充值时间的属性值.
 	 */
 	public void setAddTime(String addtime){
 		this.addTime = addtime;
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
	
	private String condition_addType;
	
	public String getCondition_addType(){
		return this.condition_addType;
	}
	
	public void setCondition_addType(String s){
		this.condition_addType = s;
	}
	
	private String query_addType;
		
	public String getQuery_addType(){
		return this.query_addType;
	}
	
	public void setQuery_addType(String s){
		this.query_addType = s;
	}
	
	private String condition_addMoney;
	
	public String getCondition_addMoney(){
		return this.condition_addMoney;
	}
	
	public void setCondition_addMoney(String s){
		this.condition_addMoney = s;
	}
	
	private String query_addMoney;
		
	public String getQuery_addMoney(){
		return this.query_addMoney;
	}
	
	public void setQuery_addMoney(String s){
		this.query_addMoney = s;
	}
	
	private String condition_insuredFileId;
	
	public String getCondition_insuredFileId(){
		return this.condition_insuredFileId;
	}
	
	public void setCondition_insuredFileId(String s){
		this.condition_insuredFileId = s;
	}
	
	private String query_insuredFileId;
		
	public String getQuery_insuredFileId(){
		return this.query_insuredFileId;
	}
	
	public void setQuery_insuredFileId(String s){
		this.query_insuredFileId = s;
	}
	
	private String condition_addTime;
	
	public String getCondition_addTime(){
		return this.condition_addTime;
	}
	
	public void setCondition_addTime(String s){
		this.condition_addTime = s;
	}
	
	private String query_addTime;
		
	public String getQuery_addTime(){
		return this.query_addTime;
	}
	
	public void setQuery_addTime(String s){
		this.query_addTime = s;
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
