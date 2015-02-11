
package ido.bindFamily;
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
public class BindFamilyAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	BindFamilyManager pMgr = bf.getManager(BeanManagerKey.bindfamilyManager);
	//业务实体对象
	private BindFamily vo;
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
			BindFamilyImpl bindfamilyImpl = new BindFamilyImpl(iuserNo ,bindName ,relation ,cardNo ,phone ,createUser ,createTime );
			pMgr.createBindFamily(bindfamilyImpl);
			insertLog(logMgr,"添加投保用户","/doAdd", "", "" ,JSON.toJSONString(bindfamilyImpl));  
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
		insertLog(logMgr,"导入投保用户","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<BindFamily> allDeleteIds = new ArrayList<BindFamily>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getBindFamily(Integer.parseInt(_id)));
		}
		pMgr.removeBindFamilys(ids);
		insertLog(logMgr,"删除投保用户","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getBindFamily(sno);
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
			BindFamily old = pMgr.getBindFamily( sno );
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
			if(!compare(old.getBindName(),bindName)){
				oldObj += "bindName="+old.getBindName()+";";
				newObj+= "bindName="+bindName+";";
			} 
			if(!compare(old.getRelation(),relation)){
				oldObj += "relation="+old.getRelation()+";";
				newObj+= "relation="+relation+";";
			} 
			if(!compare(old.getCardNo(),cardNo)){
				oldObj += "cardNo="+old.getCardNo()+";";
				newObj+= "cardNo="+cardNo+";";
			} 
			if(!compare(old.getPhone(),phone)){
				oldObj += "phone="+old.getPhone()+";";
				newObj+= "phone="+phone+";";
			} 
			if(!compare(old.getCreateUser(),createUser)){
				oldObj += "createUser="+old.getCreateUser()+";";
				newObj+= "createUser="+createUser+";";
			} 
			if(!compare(old.getCreateTime(),createTime)){
				oldObj += "createTime="+old.getCreateTime()+";";
				newObj+= "createTime="+createTime+";";
			} 
			
			BindFamilyImpl bindfamilyImpl = new BindFamilyImpl( sno , iuserNo , bindName , relation , cardNo , phone , createUser , createTime );
			pMgr.updateBindFamily(bindfamilyImpl);
			insertLog(logMgr,"修改投保用户","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(bindfamilyImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,IUSERNO("主用户号")  ,BINDNAME("绑定人")  ,RELATION("关系")  ,CARDNO("身份证")  ,PHONE("手机号");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		  IUSERNO("主用户号")  ,BINDNAME("绑定人")  ,RELATION("关系")  ,CARDNO("身份证")  ,PHONE("手机号");
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
		ParamSelect select_bindusertype = allSelect
				.getParamsByType(AllSelectContants.BINDUSERTYPE.getName()); 
		request.setAttribute("relation_list", select_bindusertype.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=BindFamilyList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<BindFamilySearchFields, Object> criterias = getCriterias();

		Collection<BindFamily> bindfamilyList = pMgr.searchBindFamily(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (BindFamily bindfamily : bindfamilyList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), bindfamily.getSno()); 
					break;
					case IUSERNO:
						 e.setCell(filed.ordinal(), bindfamily.getIuserNo()); 
					break;
					case BINDNAME:
						 e.setCell(filed.ordinal(), bindfamily.getBindName()); 
					break;
					case RELATION:
						 e.setCell(filed.ordinal(), bindfamily.getRelation()); 
					break;
					case CARDNO:
						 e.setCell(filed.ordinal(), bindfamily.getCardNo()); 
					break;
					case PHONE:
						 e.setCell(filed.ordinal(), bindfamily.getPhone()); 
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
		Map<BindFamilySearchFields, Object> criterias = getCriterias();

		Collection<BindFamily> moneyList = pMgr.searchBindFamily(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBindFamilyNum(criterias);
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
		Map<BindFamilySearchFields, Object> criterias = getCriterias();

		Collection<BindFamily> moneyList = pMgr.searchBindFamily(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBindFamilyNum(criterias);
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

	private Map<BindFamilySearchFields, Object> getCriterias() {
		Map<BindFamilySearchFields, Object> criterias = new HashMap<BindFamilySearchFields, Object>();
		if (getIuserNo()!=null&&!"".equals(getIuserNo())&&!"-1".equals(getIuserNo())&&!"-2".equals(getIuserNo()))
			criterias.put(BindFamilySearchFields.IUSERNO,  getIuserNo());
		
		//下面是高级查询的时候添加的条件
		//添加主用户号的查询条件
		addIuserNoCondition(criterias,getCondition_iuserNo(),getQuery_iuserNo());
		//添加绑定人的查询条件
		addBindNameCondition(criterias,getCondition_bindName(),getQuery_bindName());
		//添加关系的查询条件
		addRelationCondition(criterias,getCondition_relation(),getQuery_relation());
		//添加身份证的查询条件
		addCardNoCondition(criterias,getCondition_cardNo(),getQuery_cardNo());
		//添加手机号的查询条件
		addPhoneCondition(criterias,getCondition_phone(),getQuery_phone());
		//添加创建用户的查询条件
		addCreateUserCondition(criterias,getCondition_createUser(),getQuery_createUser());
		//添加创建时间的查询条件
		addCreateTimeCondition(criterias,getCondition_createTime(),getQuery_createTime());
		return criterias;
	}
	
	//下面是添加高级查询的条件
	/**
	 * 添加查询主用户号的查询条件.
	 */
	public void addIuserNoCondition(Map<BindFamilySearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.IUSERNO_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.IUSERNO_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询绑定人的查询条件.
	 */
	public void addBindNameCondition(Map<BindFamilySearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.BINDNAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.BINDNAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询关系的查询条件.
	 */
	public void addRelationCondition(Map<BindFamilySearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.RELATION_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.RELATION_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询身份证的查询条件.
	 */
	public void addCardNoCondition(Map<BindFamilySearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.CARDNO_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.CARDNO_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询手机号的查询条件.
	 */
	public void addPhoneCondition(Map<BindFamilySearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.PHONE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.PHONE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建用户的查询条件.
	 */
	public void addCreateUserCondition(Map<BindFamilySearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<BindFamilySearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BindFamilySearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 

	public BindFamily getVo() {
		return vo;
	}

	public void setVo(BindFamily vo) {
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
 	 * 获取主用户号的属性值.
 	 */
 	public String getIuserNo(){
 		return iuserNo;
 	}
 	
 	/**
 	 * 设置主用户号的属性值.
 	 */
 	public void setIuserNo(String iuserno){
 		this.iuserNo = iuserno;
 	}
	private String bindName; 
 	/**
 	 * 获取绑定人的属性值.
 	 */
 	public String getBindName(){
 		return bindName;
 	}
 	
 	/**
 	 * 设置绑定人的属性值.
 	 */
 	public void setBindName(String bindname){
 		this.bindName = bindname;
 	}
	private String relation; 
 	/**
 	 * 获取关系的属性值.
 	 */
 	public String getRelation(){
 		return relation;
 	}
 	
 	/**
 	 * 设置关系的属性值.
 	 */
 	public void setRelation(String relation){
 		this.relation = relation;
 	}
	private String cardNo; 
 	/**
 	 * 获取身份证的属性值.
 	 */
 	public String getCardNo(){
 		return cardNo;
 	}
 	
 	/**
 	 * 设置身份证的属性值.
 	 */
 	public void setCardNo(String cardno){
 		this.cardNo = cardno;
 	}
	private String phone; 
 	/**
 	 * 获取手机号的属性值.
 	 */
 	public String getPhone(){
 		return phone;
 	}
 	
 	/**
 	 * 设置手机号的属性值.
 	 */
 	public void setPhone(String phone){
 		this.phone = phone;
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
	
	private String condition_bindName;
	
	public String getCondition_bindName(){
		return this.condition_bindName;
	}
	
	public void setCondition_bindName(String s){
		this.condition_bindName = s;
	}
	
	private String query_bindName;
		
	public String getQuery_bindName(){
		return this.query_bindName;
	}
	
	public void setQuery_bindName(String s){
		this.query_bindName = s;
	}
	
	private String condition_relation;
	
	public String getCondition_relation(){
		return this.condition_relation;
	}
	
	public void setCondition_relation(String s){
		this.condition_relation = s;
	}
	
	private String query_relation;
		
	public String getQuery_relation(){
		return this.query_relation;
	}
	
	public void setQuery_relation(String s){
		this.query_relation = s;
	}
	
	private String condition_cardNo;
	
	public String getCondition_cardNo(){
		return this.condition_cardNo;
	}
	
	public void setCondition_cardNo(String s){
		this.condition_cardNo = s;
	}
	
	private String query_cardNo;
		
	public String getQuery_cardNo(){
		return this.query_cardNo;
	}
	
	public void setQuery_cardNo(String s){
		this.query_cardNo = s;
	}
	
	private String condition_phone;
	
	public String getCondition_phone(){
		return this.condition_phone;
	}
	
	public void setCondition_phone(String s){
		this.condition_phone = s;
	}
	
	private String query_phone;
		
	public String getQuery_phone(){
		return this.query_phone;
	}
	
	public void setQuery_phone(String s){
		this.query_phone = s;
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
