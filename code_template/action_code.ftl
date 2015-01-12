<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.util.*; 
import dwz.framework.constants.Constants;
import com.alibaba.fastjson.JSON;
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

import ido.loginfo.LogInfoManager;
/**
 * 关于${model.classDesc}的Action操作类.
 * @author ${author}
 * ${auth}
 * ${website}
 */ 
public class ${bignm}Action extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	${bignm}Manager pMgr = bf.getManager(BeanManagerKey.${classarg}Manager);
	//业务实体对象
	private ${bignm} vo;
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
 	 * 添加${model.classDesc}.
 	 */
	public String doAdd() {
		try {
			User currentUser = (UserImpl) request.getSession().getAttribute(Constants.AUTHENTICATION_KEY);
			<#list model.attributes as attr>
				<#if attr.currentTime='true'>
			${attr.name} = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss");
				</#if>
				<#if attr.currentUser='true'>
			${attr.name} = Integer.parseInt(currentUser.getId());
				</#if>
			</#list>
			${bignm}Impl ${classarg}Impl = new ${bignm}Impl(<@allfield2notkey nm=model.attributes />);
			pMgr.create${bignm}(${classarg}Impl);
			<#if model.addToCache='true'>
			pMgr.addCache();
			</#if>
			insertLog(logMgr,"添加${model.classDesc}","/doAdd", "", "" ,JSON.toJSONString(${classarg}Impl));  
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
		insertLog(logMgr,"导入${model.classDesc}","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		User currentUser = (UserImpl) request.getSession().getAttribute(Constants.AUTHENTICATION_KEY);
		<#list model.attributes as attr>
				<#if attr.currentTime='true'>
		${attr.name} = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss");
				</#if>
				<#if attr.currentUser='true'>
		${attr.name} = Integer.parseInt(currentUser.getId());
				</#if>
			</#list>
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<${bignm}> allDeleteIds = new ArrayList<${bignm}>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.get${bignm}(Integer.parseInt(_id)));
		}
		pMgr.remove${bignm}s(ids);
		<#if model.addToCache='true'>
		pMgr.addCache();
		</#if>
		insertLog(logMgr,"删除${model.classDesc}","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.get${bignm}(${model.keyName});
		return "editdetail";
	} 
	
	public String doUpdate() {
		try {
			// 当前用户
			User currentUser = (UserImpl) request.getSession().getAttribute(Constants.AUTHENTICATION_KEY);
			<#list model.attributes as attr>
				<#if attr.currentTime='true'>
			${attr.name} = DateTool.toString(DateTool.now(),"yyyy-MM-dd HH:mm:ss");
				</#if>
				<#if attr.currentUser='true'>
			${attr.name} = Integer.parseInt(currentUser.getId());
				</#if>
			</#list>
			${bignm} old = pMgr.get${bignm}( sno );
			String oldObj= "";
			String newObj= ""; 
			<#list model.attributes as attr>
			if(!compare(old.get${attr.name?cap_first}(),${attr.name})){
				oldObj += "${attr.name}="+old.get${attr.name?cap_first}()+";";
				newObj+= "${attr.name}="+${attr.name}+";";
			} 
			</#list>
			
			${bignm}Impl ${classarg}Impl = new ${bignm}Impl(<@allfield2 nm=model.attributes />);
			pMgr.update${bignm}(${classarg}Impl);
			<#if model.addToCache='true'>
			pMgr.addCache();
			</#if>
			insertLog(logMgr,"修改${model.classDesc}","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(${classarg}Impl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		<#assign index=0> 
		<#list model.attributes as attr><#if attr.canExport='true'>  <#if index!=0>,</#if>${attr.name?upper_case}("${attr.desc}")<#assign index=index+1></#if></#list>;
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
		response.addHeader("Content-Disposition","attachment;filename=${bignm}List.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<${bignm}SearchFields, Object> criterias = getCriterias();

		Collection<${bignm}> ${classarg}List = pMgr.search${bignm}(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (${bignm} ${classarg} : ${classarg}List) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				<#list model.attributes as attr>
				<#if attr.canExport='true'>
					case ${attr.name?upper_case}:
						 e.setCell(filed.ordinal(), ${classarg}.get${attr.name?cap_first}()); 
					break;
				</#if>
				</#list>  
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
		Map<${bignm}SearchFields, Object> criterias = getCriterias();

		Collection<${bignm}> moneyList = pMgr.search${bignm}(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.search${bignm}Num(criterias);
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

	private Map<${bignm}SearchFields, Object> getCriterias() {
		Map<${bignm}SearchFields, Object> criterias = new HashMap<${bignm}SearchFields, Object>();
		<#list model.attributes as attr>  
		<#if "${attr.query}"='true'> 
			 	<#if '${attr.type}'='int'||'${attr.type}'='double'||'${attr.type}'='float'>
		if (get${attr.name?cap_first}()!=null&&get${attr.name?cap_first}() !=0)
			criterias.put(${bignm}SearchFields.${attr.name?upper_case}, get${attr.name?cap_first}()); 
				<#else><#if '${attr.type}'='select'>
		if (get${attr.name?cap_first}()!=null&&!"".equals(get${attr.name?cap_first}())) 		
				 	<#else> 
		if (get${attr.name?cap_first}()!=null&&!"".equals(get${attr.name?cap_first}())&&!"-1".equals(get${attr.name?cap_first}())&&!"-2".equals(get${attr.name?cap_first}()))
					</#if><#if '${attr.querylike}'='true'>
			criterias.put(${bignm}SearchFields.${attr.name?upper_case}, "%"+get${attr.name?cap_first}()+"%"); 
					<#else>
			criterias.put(${bignm}SearchFields.${attr.name?upper_case},  get${attr.name?cap_first}());
					</#if> 
				</#if>  
		</#if>
		</#list>  
		return criterias;
	}

	public ${bignm} getVo() {
		return vo;
	}

	public void setVo(${bignm} vo) {
		this.vo = vo;
	} 
  
  	<@allGetAndSet nm=model.attributes />
  	
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
