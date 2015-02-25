
package money.addRoleDetail;
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
 * 关于新角色授权明细的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class AddRoleDetailAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	AddRoleDetailManager pMgr = bf.getManager(BeanManagerKey.addroledetailManager);
	//业务实体对象
	private AddRoleDetail vo;
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
 	 * 添加新角色授权明细.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			AddRoleDetailImpl addroledetailImpl = new AddRoleDetailImpl(roleType ,roleKey ,roleId );
			pMgr.createAddRoleDetail(addroledetailImpl);
			insertLog(logMgr,"添加新角色授权明细","/doAdd", "", "" ,JSON.toJSONString(addroledetailImpl));  
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
		insertLog(logMgr,"导入新角色授权明细","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<AddRoleDetail> allDeleteIds = new ArrayList<AddRoleDetail>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getAddRoleDetail(Integer.parseInt(_id)));
		}
		pMgr.removeAddRoleDetails(ids);
		insertLog(logMgr,"删除新角色授权明细","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getAddRoleDetail(sno);
		return "editdetail";
	} 
	
	private void setCurrentUser(boolean isUpdate){
//		User currentUser = (UserImpl) request.getSession().getAttribute(Constants.AUTHENTICATION_KEY);
//		if(!isUpdate){
//			createUser = Integer.parseInt(currentUser.getId());
//			createTime = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss"); 
//		}else{
//			updateUser = Integer.parseInt(currentUser.getId());
//			updateTime = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss"); 
//		}
	}
	
	public String doUpdate() {
		try {
			setCurrentUser(true);
			AddRoleDetail old = pMgr.getAddRoleDetail( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getRoleType(),roleType)){
				oldObj += "roleType="+old.getRoleType()+";";
				newObj+= "roleType="+roleType+";";
			} 
			if(!compare(old.getRoleKey(),roleKey)){
				oldObj += "roleKey="+old.getRoleKey()+";";
				newObj+= "roleKey="+roleKey+";";
			} 
			if(!compare(old.getRoleId(),roleId)){
				oldObj += "roleId="+old.getRoleId()+";";
				newObj+= "roleId="+roleId+";";
			} 
			
			AddRoleDetailImpl addroledetailImpl = new AddRoleDetailImpl( sno , roleType , roleKey , roleId );
			pMgr.updateAddRoleDetail(addroledetailImpl);
			insertLog(logMgr,"修改新角色授权明细","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(addroledetailImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,ROLEKEY("授权对象 ");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		  ROLEKEY("授权对象 ");
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
		ParamSelect select_roletype = allSelect
				.getParamsByType(AllSelectContants.ROLETYPE.getName()); 
		request.setAttribute("roletype_list", select_roletype.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=AddRoleDetailList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<AddRoleDetailSearchFields, Object> criterias = getCriterias();

		Collection<AddRoleDetail> addroledetailList = pMgr.searchAddRoleDetail(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (AddRoleDetail addroledetail : addroledetailList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), addroledetail.getSno()); 
					break;
					case ROLEKEY:
						 e.setCell(filed.ordinal(), addroledetail.getRoleKey()); 
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
		Map<AddRoleDetailSearchFields, Object> criterias = getCriterias();

		Collection<AddRoleDetail> moneyList = pMgr.searchAddRoleDetail(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchAddRoleDetailNum(criterias);
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
		Map<AddRoleDetailSearchFields, Object> criterias = getCriterias();

		Collection<AddRoleDetail> moneyList = pMgr.searchAddRoleDetail(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchAddRoleDetailNum(criterias);
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

	private Map<AddRoleDetailSearchFields, Object> getCriterias() {
		Map<AddRoleDetailSearchFields, Object> criterias = new HashMap<AddRoleDetailSearchFields, Object>();
		if (getRoleType()!=null&&!"".equals(getRoleType())&&!"-1".equals(getRoleType())&&!"-2".equals(getRoleType()))
			criterias.put(AddRoleDetailSearchFields.ROLETYPE,  getRoleType());
		
		//下面是高级查询的时候添加的条件
		//添加角色类型的查询条件
		addRoleTypeCondition(criterias,getCondition_roleType(),getQuery_roleType());
		//添加授权对象 的查询条件
		addRoleKeyCondition(criterias,getCondition_roleKey(),getQuery_roleKey());
		//添加角色 的查询条件
		addRoleIdCondition(criterias,getCondition_roleId(),getQuery_roleId());
		return criterias;
	}
	
	//下面是添加高级查询的条件
	/**
	 * 添加查询角色类型的查询条件.
	 */
	public void addRoleTypeCondition(Map<AddRoleDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddRoleDetailSearchFields.ROLETYPE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddRoleDetailSearchFields.ROLETYPE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询授权对象 的查询条件.
	 */
	public void addRoleKeyCondition(Map<AddRoleDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddRoleDetailSearchFields.ROLEKEY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddRoleDetailSearchFields.ROLEKEY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询角色 的查询条件.
	 */
	public void addRoleIdCondition(Map<AddRoleDetailSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(AddRoleDetailSearchFields.ROLEID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(AddRoleDetailSearchFields.ROLEID_COM_NOT_EQUALS, value);
		} 
	} 

	public AddRoleDetail getVo() {
		return vo;
	}

	public void setVo(AddRoleDetail vo) {
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
	private String roleType; 
 	/**
 	 * 获取角色类型的属性值.
 	 */
 	public String getRoleType(){
 		return roleType;
 	}
 	
 	/**
 	 * 设置角色类型的属性值.
 	 */
 	public void setRoleType(String roletype){
 		this.roleType = roletype;
 	}
	private String roleKey; 
 	/**
 	 * 获取授权对象 的属性值.
 	 */
 	public String getRoleKey(){
 		return roleKey;
 	}
 	
 	/**
 	 * 设置授权对象 的属性值.
 	 */
 	public void setRoleKey(String rolekey){
 		this.roleKey = rolekey;
 	}
	private String roleId; 
 	/**
 	 * 获取角色 的属性值.
 	 */
 	public String getRoleId(){
 		return roleId;
 	}
 	
 	/**
 	 * 设置角色 的属性值.
 	 */
 	public void setRoleId(String roleid){
 		this.roleId = roleid;
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
	
	private String condition_roleType;
	
	public String getCondition_roleType(){
		return this.condition_roleType;
	}
	
	public void setCondition_roleType(String s){
		this.condition_roleType = s;
	}
	
	private String query_roleType;
		
	public String getQuery_roleType(){
		return this.query_roleType;
	}
	
	public void setQuery_roleType(String s){
		this.query_roleType = s;
	}
	
	private String condition_roleKey;
	
	public String getCondition_roleKey(){
		return this.condition_roleKey;
	}
	
	public void setCondition_roleKey(String s){
		this.condition_roleKey = s;
	}
	
	private String query_roleKey;
		
	public String getQuery_roleKey(){
		return this.query_roleKey;
	}
	
	public void setQuery_roleKey(String s){
		this.query_roleKey = s;
	}
	
	private String condition_roleId;
	
	public String getCondition_roleId(){
		return this.condition_roleId;
	}
	
	public void setCondition_roleId(String s){
		this.condition_roleId = s;
	}
	
	private String query_roleId;
		
	public String getQuery_roleId(){
		return this.query_roleId;
	}
	
	public void setQuery_roleId(String s){
		this.query_roleId = s;
	}
	
}
