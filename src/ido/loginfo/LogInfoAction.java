
package ido.loginfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import common.util.CommonUtil;
import com.opensymphony.xwork2.ActionContext; 
import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;
import org.apache.struts2.ServletActionContext;

/**
 * 关于操作日志的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class LogInfoAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	//业务接口对象.
	LogInfoManager pMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务实体对象
	private LogInfo vo;
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

	public String doAdd() {
		try {
			LogInfoImpl loginfoImpl = new LogInfoImpl(operUser ,operUserName ,operTime ,operType ,operIp ,operUrl ,operBefore ,operAfter ,operDesc );
			pMgr.createLogInfo(loginfoImpl);
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
		for (ExportFiled filed : ExportFiled.values()) {
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
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeLogInfos(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getLogInfo(sno);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			LogInfoImpl loginfoImpl = new LogInfoImpl( sno , operUser , operUserName , operTime , operType , operIp , operUrl , operBefore , operAfter , operDesc );
			pMgr.updateLogInfo(loginfoImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号"),  OPERUSER("用户id"),  OPERUSERNAME("用户"),  OPERTIME("时间 "),  OPERTYPE("操作类型"),  OPERIP("ip地址"),  OPERURL("操作地址"),  OPERBEFORE("修改前"),  OPERAFTER("修改后"),  OPERDESC("备注");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}

	public String beforeQuery() {
		return "query";
	}

	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=LogInfoList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<LogInfoSearchFields, Object> criterias = getCriterias();

		Collection<LogInfo> loginfoList = pMgr.searchLogInfo(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (LogInfo loginfo : loginfoList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), loginfo.getSno()); 
					break;
					case OPERUSER:
						 e.setCell(filed.ordinal(), loginfo.getOperUser()); 
					break;
					case OPERUSERNAME:
						 e.setCell(filed.ordinal(), loginfo.getOperUserName()); 
					break;
					case OPERTIME:
						 e.setCell(filed.ordinal(), loginfo.getOperTime()); 
					break;
					case OPERTYPE:
						 e.setCell(filed.ordinal(), loginfo.getOperType()); 
					break;
					case OPERIP:
						 e.setCell(filed.ordinal(), loginfo.getOperIp()); 
					break;
					case OPERURL:
						 e.setCell(filed.ordinal(), loginfo.getOperUrl()); 
					break;
					case OPERBEFORE:
						 e.setCell(filed.ordinal(), loginfo.getOperBefore()); 
					break;
					case OPERAFTER:
						 e.setCell(filed.ordinal(), loginfo.getOperAfter()); 
					break;
					case OPERDESC:
						 e.setCell(filed.ordinal(), loginfo.getOperDesc()); 
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
		Map<LogInfoSearchFields, Object> criterias = getCriterias();

		Collection<LogInfo> moneyList = pMgr.searchLogInfo(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchLogInfoNum(criterias);
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

	private Map<LogInfoSearchFields, Object> getCriterias() {
		Map<LogInfoSearchFields, Object> criterias = new HashMap<LogInfoSearchFields, Object>();
			if (getOperTime()!=null&&!"".equals(getOperTime()))
			 	criterias.put(LogInfoSearchFields.OPERTIME,  getOperTime());
		return criterias;
	}

	public LogInfo getVo() {
		return vo;
	}

	public void setVo(LogInfo vo) {
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
	private int operUser; 
 	/**
 	 * 获取用户id的属性值.
 	 */
 	public int getOperUser(){
 		return operUser;
 	}
 	
 	/**
 	 * 设置用户id的属性值.
 	 */
 	public void setOperUser(int operuser){
 		this.operUser = operuser;
 	}
	private String operUserName; 
 	/**
 	 * 获取用户的属性值.
 	 */
 	public String getOperUserName(){
 		return operUserName;
 	}
 	
 	/**
 	 * 设置用户的属性值.
 	 */
 	public void setOperUserName(String operusername){
 		this.operUserName = operusername;
 	}
	private String operTime; 
 	/**
 	 * 获取时间 的属性值.
 	 */
 	public String getOperTime(){
 		return operTime;
 	}
 	
 	/**
 	 * 设置时间 的属性值.
 	 */
 	public void setOperTime(String opertime){
 		this.operTime = opertime;
 	}
	private String operType; 
 	/**
 	 * 获取操作类型的属性值.
 	 */
 	public String getOperType(){
 		return operType;
 	}
 	
 	/**
 	 * 设置操作类型的属性值.
 	 */
 	public void setOperType(String opertype){
 		this.operType = opertype;
 	}
	private String operIp; 
 	/**
 	 * 获取ip地址的属性值.
 	 */
 	public String getOperIp(){
 		return operIp;
 	}
 	
 	/**
 	 * 设置ip地址的属性值.
 	 */
 	public void setOperIp(String operip){
 		this.operIp = operip;
 	}
	private String operUrl; 
 	/**
 	 * 获取操作地址的属性值.
 	 */
 	public String getOperUrl(){
 		return operUrl;
 	}
 	
 	/**
 	 * 设置操作地址的属性值.
 	 */
 	public void setOperUrl(String operurl){
 		this.operUrl = operurl;
 	}
	private String operBefore; 
 	/**
 	 * 获取修改前的属性值.
 	 */
 	public String getOperBefore(){
 		return operBefore;
 	}
 	
 	/**
 	 * 设置修改前的属性值.
 	 */
 	public void setOperBefore(String operbefore){
 		this.operBefore = operbefore;
 	}
	private String operAfter; 
 	/**
 	 * 获取修改后的属性值.
 	 */
 	public String getOperAfter(){
 		return operAfter;
 	}
 	
 	/**
 	 * 设置修改后的属性值.
 	 */
 	public void setOperAfter(String operafter){
 		this.operAfter = operafter;
 	}
	private String operDesc; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getOperDesc(){
 		return operDesc;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setOperDesc(String operdesc){
 		this.operDesc = operdesc;
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
}
