
package money.sequence;
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
 * 关于系统序列号的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class SystemSequenceAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	SystemSequenceManager pMgr = bf.getManager(BeanManagerKey.systemsequenceManager);
	//业务实体对象
	private SystemSequence vo;
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
 	 * 添加系统序列号.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			SystemSequenceImpl systemsequenceImpl = new SystemSequenceImpl(sequenceCode ,sequenceName ,sequenceContent );
			pMgr.createSystemSequence(systemsequenceImpl);
			insertLog(logMgr,"添加系统序列号","/doAdd", "", "" ,JSON.toJSONString(systemsequenceImpl));  
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
		insertLog(logMgr,"导入系统序列号","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<SystemSequence> allDeleteIds = new ArrayList<SystemSequence>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getSystemSequence(Integer.parseInt(_id)));
		}
		pMgr.removeSystemSequences(ids);
		insertLog(logMgr,"删除系统序列号","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getSystemSequence(sno);
		return "editdetail";
	} 
	
	private void setCurrentUser(boolean isUpdate){
		 
	}
	
	public String doUpdate() {
		try {
			setCurrentUser(true);
			SystemSequence old = pMgr.getSystemSequence( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getSequenceCode(),sequenceCode)){
				oldObj += "sequenceCode="+old.getSequenceCode()+";";
				newObj+= "sequenceCode="+sequenceCode+";";
			} 
			if(!compare(old.getSequenceName(),sequenceName)){
				oldObj += "sequenceName="+old.getSequenceName()+";";
				newObj+= "sequenceName="+sequenceName+";";
			} 
			if(!compare(old.getSequenceContent(),sequenceContent)){
				oldObj += "sequenceContent="+old.getSequenceContent()+";";
				newObj+= "sequenceContent="+sequenceContent+";";
			} 
			
			SystemSequenceImpl systemsequenceImpl = new SystemSequenceImpl( sno , sequenceCode , sequenceName , sequenceContent );
			pMgr.updateSystemSequence(systemsequenceImpl);
			insertLog(logMgr,"修改系统序列号","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(systemsequenceImpl));  
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
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=SystemSequenceList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<SystemSequenceSearchFields, Object> criterias = getCriterias();

		Collection<SystemSequence> systemsequenceList = pMgr.searchSystemSequence(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (SystemSequence systemsequence : systemsequenceList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), systemsequence.getSno()); 
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
		Map<SystemSequenceSearchFields, Object> criterias = getCriterias();

		Collection<SystemSequence> moneyList = pMgr.searchSystemSequence(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchSystemSequenceNum(criterias);
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
		Map<SystemSequenceSearchFields, Object> criterias = getCriterias();

		Collection<SystemSequence> moneyList = pMgr.searchSystemSequence(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchSystemSequenceNum(criterias);
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

	private Map<SystemSequenceSearchFields, Object> getCriterias() {
		Map<SystemSequenceSearchFields, Object> criterias = new HashMap<SystemSequenceSearchFields, Object>();
		if (getSequenceCode()!=null&&!"".equals(getSequenceCode())&&!"-1".equals(getSequenceCode())&&!"-2".equals(getSequenceCode()))
			criterias.put(SystemSequenceSearchFields.SEQUENCECODE,  getSequenceCode());
		if (getSequenceName()!=null&&!"".equals(getSequenceName())&&!"-1".equals(getSequenceName())&&!"-2".equals(getSequenceName()))
			criterias.put(SystemSequenceSearchFields.SEQUENCENAME,  getSequenceName());
		if (getSequenceContent()!=null&&!"".equals(getSequenceContent())&&!"-1".equals(getSequenceContent())&&!"-2".equals(getSequenceContent()))
			criterias.put(SystemSequenceSearchFields.SEQUENCECONTENT,  getSequenceContent());
		
		//下面是高级查询的时候添加的条件
		//添加序列代码 的查询条件
		addSequenceCodeCondition(criterias,getCondition_sequenceCode(),getQuery_sequenceCode());
		//添加序列名称的查询条件
		addSequenceNameCondition(criterias,getCondition_sequenceName(),getQuery_sequenceName());
		//添加序列值的查询条件
		addSequenceContentCondition(criterias,getCondition_sequenceContent(),getQuery_sequenceContent());
		return criterias;
	}
	
	//下面是添加高级查询的条件
	/**
	 * 添加查询序列代码 的查询条件.
	 */
	public void addSequenceCodeCondition(Map<SystemSequenceSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(SystemSequenceSearchFields.SEQUENCECODE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(SystemSequenceSearchFields.SEQUENCECODE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询序列名称的查询条件.
	 */
	public void addSequenceNameCondition(Map<SystemSequenceSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(SystemSequenceSearchFields.SEQUENCENAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(SystemSequenceSearchFields.SEQUENCENAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询序列值的查询条件.
	 */
	public void addSequenceContentCondition(Map<SystemSequenceSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(SystemSequenceSearchFields.SEQUENCECONTENT_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(SystemSequenceSearchFields.SEQUENCECONTENT_COM_NOT_EQUALS, value);
		} 
	} 

	public SystemSequence getVo() {
		return vo;
	}

	public void setVo(SystemSequence vo) {
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
	private String sequenceCode; 
 	/**
 	 * 获取序列代码 的属性值.
 	 */
 	public String getSequenceCode(){
 		return sequenceCode;
 	}
 	
 	/**
 	 * 设置序列代码 的属性值.
 	 */
 	public void setSequenceCode(String sequencecode){
 		this.sequenceCode = sequencecode;
 	}
	private String sequenceName; 
 	/**
 	 * 获取序列名称的属性值.
 	 */
 	public String getSequenceName(){
 		return sequenceName;
 	}
 	
 	/**
 	 * 设置序列名称的属性值.
 	 */
 	public void setSequenceName(String sequencename){
 		this.sequenceName = sequencename;
 	}
	private Integer sequenceContent; 
 	/**
 	 * 获取序列值的属性值.
 	 */
 	public Integer getSequenceContent(){
 		return sequenceContent;
 	}
 	
 	/**
 	 * 设置序列值的属性值.
 	 */
 	public void setSequenceContent(Integer sequencecontent){
 		this.sequenceContent = sequencecontent;
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
	
	private String condition_sequenceCode;
	
	public String getCondition_sequenceCode(){
		return this.condition_sequenceCode;
	}
	
	public void setCondition_sequenceCode(String s){
		this.condition_sequenceCode = s;
	}
	
	private String query_sequenceCode;
		
	public String getQuery_sequenceCode(){
		return this.query_sequenceCode;
	}
	
	public void setQuery_sequenceCode(String s){
		this.query_sequenceCode = s;
	}
	
	private String condition_sequenceName;
	
	public String getCondition_sequenceName(){
		return this.condition_sequenceName;
	}
	
	public void setCondition_sequenceName(String s){
		this.condition_sequenceName = s;
	}
	
	private String query_sequenceName;
		
	public String getQuery_sequenceName(){
		return this.query_sequenceName;
	}
	
	public void setQuery_sequenceName(String s){
		this.query_sequenceName = s;
	}
	
	private String condition_sequenceContent;
	
	public String getCondition_sequenceContent(){
		return this.condition_sequenceContent;
	}
	
	public void setCondition_sequenceContent(String s){
		this.condition_sequenceContent = s;
	}
	
	private String query_sequenceContent;
		
	public String getQuery_sequenceContent(){
		return this.query_sequenceContent;
	}
	
	public void setQuery_sequenceContent(String s){
		this.query_sequenceContent = s;
	}
	
}
