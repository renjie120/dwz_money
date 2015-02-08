
package money.uploadFile;
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
 * 关于上传文件的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class UploadFileAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	UploadFileManager pMgr = bf.getManager(BeanManagerKey.uploadfileManager);
	//业务实体对象
	private UploadFile vo;
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
 	 * 添加上传文件.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			UploadFileImpl uploadfileImpl = new UploadFileImpl(businessId ,fileType ,isExist ,fileName ,realFileName ,fileSize ,createUser ,createTime );
			pMgr.createUploadFile(uploadfileImpl);
			insertLog(logMgr,"添加上传文件","/doAdd", "", "" ,JSON.toJSONString(uploadfileImpl));  
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
		insertLog(logMgr,"导入上传文件","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<UploadFile> allDeleteIds = new ArrayList<UploadFile>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getUploadFile(Integer.parseInt(_id)));
		}
		pMgr.removeUploadFiles(ids);
		insertLog(logMgr,"删除上传文件","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getUploadFile(sno);
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
			UploadFile old = pMgr.getUploadFile( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getBusinessId(),businessId)){
				oldObj += "businessId="+old.getBusinessId()+";";
				newObj+= "businessId="+businessId+";";
			} 
			if(!compare(old.getFileType(),fileType)){
				oldObj += "fileType="+old.getFileType()+";";
				newObj+= "fileType="+fileType+";";
			} 
			if(!compare(old.getIsExist(),isExist)){
				oldObj += "isExist="+old.getIsExist()+";";
				newObj+= "isExist="+isExist+";";
			} 
			if(!compare(old.getFileName(),fileName)){
				oldObj += "fileName="+old.getFileName()+";";
				newObj+= "fileName="+fileName+";";
			} 
			if(!compare(old.getRealFileName(),realFileName)){
				oldObj += "realFileName="+old.getRealFileName()+";";
				newObj+= "realFileName="+realFileName+";";
			} 
			if(!compare(old.getFileSize(),fileSize)){
				oldObj += "fileSize="+old.getFileSize()+";";
				newObj+= "fileSize="+fileSize+";";
			} 
			if(!compare(old.getCreateUser(),createUser)){
				oldObj += "createUser="+old.getCreateUser()+";";
				newObj+= "createUser="+createUser+";";
			} 
			if(!compare(old.getCreateTime(),createTime)){
				oldObj += "createTime="+old.getCreateTime()+";";
				newObj+= "createTime="+createTime+";";
			} 
			
			UploadFileImpl uploadfileImpl = new UploadFileImpl( sno , businessId , fileType , isExist , fileName , realFileName , fileSize , createUser , createTime );
			pMgr.updateUploadFile(uploadfileImpl);
			insertLog(logMgr,"修改上传文件","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(uploadfileImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号");
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
		ParamSelect select_file_type = allSelect
				.getParamsByType(AllSelectContants.FILE_TYPE.getName()); 
		request.setAttribute("filetype_list", select_file_type.getSelectAbles()); 
		ParamSelect select_yesorno = allSelect
				.getParamsByType(AllSelectContants.YESORNO.getName()); 
		request.setAttribute("isexist_list", select_yesorno.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=UploadFileList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<UploadFileSearchFields, Object> criterias = getCriterias();

		Collection<UploadFile> uploadfileList = pMgr.searchUploadFile(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (UploadFile uploadfile : uploadfileList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), uploadfile.getSno()); 
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
		Map<UploadFileSearchFields, Object> criterias = getCriterias();

		Collection<UploadFile> moneyList = pMgr.searchUploadFile(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchUploadFileNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "list";
	}
	 
	public String initCompanyFile() throws Exception {
		request.setAttribute("businessId", businessId);
		return "initCompanyFile";
	} 
	
	public String queryCompanyList() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage; 
		
		Map<UploadFileSearchFields, Object> criterias = getCriterias();
		criterias.put(UploadFileSearchFields.ISEXIST, Constants.YES);
		criterias.put(UploadFileSearchFields.FILETYPE, UploadFile.FILETYPE_COMAPNY);
		criterias.put(UploadFileSearchFields.BUSINESSID, businessId);
		Collection<UploadFile> moneyList = pMgr.searchUploadFile(criterias, realOrderField(),
				startIndex, numPerPage); 
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("businessId", businessId);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchUploadFileNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "companyFileList";
	}
	
	public String newQuery() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<UploadFileSearchFields, Object> criterias = getCriterias();

		Collection<UploadFile> moneyList = pMgr.searchUploadFile(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchUploadFileNum(criterias);
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

	private Map<UploadFileSearchFields, Object> getCriterias() {
		Map<UploadFileSearchFields, Object> criterias = new HashMap<UploadFileSearchFields, Object>();
		if (getFileType()!=null&&!"".equals(getFileType())&&!"-1".equals(getFileType())&&!"-2".equals(getFileType()))
			criterias.put(UploadFileSearchFields.FILETYPE,  getFileType());
		
		//下面是高级查询的时候添加的条件
		//添加业务关联id 的查询条件
		addBusinessIdCondition(criterias,getCondition_businessId(),getQuery_businessId());
		//添加文件类型的查询条件
		addFileTypeCondition(criterias,getCondition_fileType(),getQuery_fileType());
		//添加是否有效的查询条件
		addIsExistCondition(criterias,getCondition_isExist(),getQuery_isExist());
		//添加文件名的查询条件
		addFileNameCondition(criterias,getCondition_fileName(),getQuery_fileName());
		//添加实际文件名的查询条件
		addRealFileNameCondition(criterias,getCondition_realFileName(),getQuery_realFileName());
		//添加文件大小的查询条件
		addFileSizeCondition(criterias,getCondition_fileSize(),getQuery_fileSize());
		//添加上传用户的查询条件
		addCreateUserCondition(criterias,getCondition_createUser(),getQuery_createUser());
		//添加上传时间的查询条件
		addCreateTimeCondition(criterias,getCondition_createTime(),getQuery_createTime());
		return criterias;
	}
	
	//下面是添加高级查询的条件
	/**
	 * 添加查询业务关联id 的查询条件.
	 */
	public void addBusinessIdCondition(Map<UploadFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.BUSINESSID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.BUSINESSID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询文件类型的查询条件.
	 */
	public void addFileTypeCondition(Map<UploadFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.FILETYPE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.FILETYPE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询是否有效的查询条件.
	 */
	public void addIsExistCondition(Map<UploadFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.ISEXIST_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.ISEXIST_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询文件名的查询条件.
	 */
	public void addFileNameCondition(Map<UploadFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.FILENAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.FILENAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询实际文件名的查询条件.
	 */
	public void addRealFileNameCondition(Map<UploadFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.REALFILENAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.REALFILENAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询文件大小的查询条件.
	 */
	public void addFileSizeCondition(Map<UploadFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.FILESIZE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.FILESIZE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询上传用户的查询条件.
	 */
	public void addCreateUserCondition(Map<UploadFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询上传时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<UploadFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(UploadFileSearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 

	public UploadFile getVo() {
		return vo;
	}

	public void setVo(UploadFile vo) {
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
	private String businessId; 
 	/**
 	 * 获取业务关联id 的属性值.
 	 */
 	public String getBusinessId(){
 		return businessId;
 	}
 	
 	/**
 	 * 设置业务关联id 的属性值.
 	 */
 	public void setBusinessId(String businessid){
 		this.businessId = businessid;
 	}
	private String fileType; 
 	/**
 	 * 获取文件类型的属性值.
 	 */
 	public String getFileType(){
 		return fileType;
 	}
 	
 	/**
 	 * 设置文件类型的属性值.
 	 */
 	public void setFileType(String filetype){
 		this.fileType = filetype;
 	}
	private String isExist; 
 	/**
 	 * 获取是否有效的属性值.
 	 */
 	public String getIsExist(){
 		return isExist;
 	}
 	
 	/**
 	 * 设置是否有效的属性值.
 	 */
 	public void setIsExist(String isexist){
 		this.isExist = isexist;
 	}
	private String fileName; 
 	/**
 	 * 获取文件名的属性值.
 	 */
 	public String getFileName(){
 		return fileName;
 	}
 	
 	/**
 	 * 设置文件名的属性值.
 	 */
 	public void setFileName(String filename){
 		this.fileName = filename;
 	}
	private String realFileName; 
 	/**
 	 * 获取实际文件名的属性值.
 	 */
 	public String getRealFileName(){
 		return realFileName;
 	}
 	
 	/**
 	 * 设置实际文件名的属性值.
 	 */
 	public void setRealFileName(String realfilename){
 		this.realFileName = realfilename;
 	}
	private int fileSize; 
 	/**
 	 * 获取文件大小的属性值.
 	 */
 	public int getFileSize(){
 		return fileSize;
 	}
 	
 	/**
 	 * 设置文件大小的属性值.
 	 */
 	public void setFileSize(int filesize){
 		this.fileSize = filesize;
 	}
	private int createUser; 
 	/**
 	 * 获取上传用户的属性值.
 	 */
 	public int getCreateUser(){
 		return createUser;
 	}
 	
 	/**
 	 * 设置上传用户的属性值.
 	 */
 	public void setCreateUser(int createuser){
 		this.createUser = createuser;
 	}
	private String createTime; 
 	/**
 	 * 获取上传时间的属性值.
 	 */
 	public String getCreateTime(){
 		return createTime;
 	}
 	
 	/**
 	 * 设置上传时间的属性值.
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
	
	private String condition_businessId;
	
	public String getCondition_businessId(){
		return this.condition_businessId;
	}
	
	public void setCondition_businessId(String s){
		this.condition_businessId = s;
	}
	
	private String query_businessId;
		
	public String getQuery_businessId(){
		return this.query_businessId;
	}
	
	public void setQuery_businessId(String s){
		this.query_businessId = s;
	}
	
	private String condition_fileType;
	
	public String getCondition_fileType(){
		return this.condition_fileType;
	}
	
	public void setCondition_fileType(String s){
		this.condition_fileType = s;
	}
	
	private String query_fileType;
		
	public String getQuery_fileType(){
		return this.query_fileType;
	}
	
	public void setQuery_fileType(String s){
		this.query_fileType = s;
	}
	
	private String condition_isExist;
	
	public String getCondition_isExist(){
		return this.condition_isExist;
	}
	
	public void setCondition_isExist(String s){
		this.condition_isExist = s;
	}
	
	private String query_isExist;
		
	public String getQuery_isExist(){
		return this.query_isExist;
	}
	
	public void setQuery_isExist(String s){
		this.query_isExist = s;
	}
	
	private String condition_fileName;
	
	public String getCondition_fileName(){
		return this.condition_fileName;
	}
	
	public void setCondition_fileName(String s){
		this.condition_fileName = s;
	}
	
	private String query_fileName;
		
	public String getQuery_fileName(){
		return this.query_fileName;
	}
	
	public void setQuery_fileName(String s){
		this.query_fileName = s;
	}
	
	private String condition_realFileName;
	
	public String getCondition_realFileName(){
		return this.condition_realFileName;
	}
	
	public void setCondition_realFileName(String s){
		this.condition_realFileName = s;
	}
	
	private String query_realFileName;
		
	public String getQuery_realFileName(){
		return this.query_realFileName;
	}
	
	public void setQuery_realFileName(String s){
		this.query_realFileName = s;
	}
	
	private String condition_fileSize;
	
	public String getCondition_fileSize(){
		return this.condition_fileSize;
	}
	
	public void setCondition_fileSize(String s){
		this.condition_fileSize = s;
	}
	
	private String query_fileSize;
		
	public String getQuery_fileSize(){
		return this.query_fileSize;
	}
	
	public void setQuery_fileSize(String s){
		this.query_fileSize = s;
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
