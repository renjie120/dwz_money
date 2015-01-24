
package ido.city;
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
 * 关于城市字典表的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class CityDictAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	CityDictManager pMgr = bf.getManager(BeanManagerKey.citydictManager);
	//业务实体对象
	private CityDict vo;
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
 	 * 添加城市字典表.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			CityDictImpl citydictImpl = new CityDictImpl(cityName ,provId ,cityState );
			pMgr.createCityDict(citydictImpl);
			pMgr.addCache();
			insertLog(logMgr,"添加城市字典表","/doAdd", "", "" ,JSON.toJSONString(citydictImpl));  
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
		insertLog(logMgr,"导入城市字典表","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<CityDict> allDeleteIds = new ArrayList<CityDict>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getCityDict(Integer.parseInt(_id)));
		}
		pMgr.removeCityDicts(ids);
		pMgr.addCache();
		insertLog(logMgr,"删除城市字典表","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getCityDict(sno);
		return "editdetail";
	} 
	
	private void setCurrentUser(boolean isUpdate){
	 
	}
	
	public String doUpdate() {
		try {
			setCurrentUser(true);
			CityDict old = pMgr.getCityDict( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getCityName(),cityName)){
				oldObj += "cityName="+old.getCityName()+";";
				newObj+= "cityName="+cityName+";";
			} 
			if(!compare(old.getProvId(),provId)){
				oldObj += "provId="+old.getProvId()+";";
				newObj+= "provId="+provId+";";
			} 
			if(!compare(old.getCityState(),cityState)){
				oldObj += "cityState="+old.getCityState()+";";
				newObj+= "cityState="+cityState+";";
			} 
			
			CityDictImpl citydictImpl = new CityDictImpl( sno , cityName , provId , cityState );
			pMgr.updateCityDict(citydictImpl);
			pMgr.addCache();
			insertLog(logMgr,"修改城市字典表","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(citydictImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		;
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
		Cache cache_provId = CacheManager.getCacheInfoNotNull(AllSelectContants.PROVINCE_DICT.getName());
		ParamSelect select_provId = (ParamSelect)cache_provId.getValue();
		request.setAttribute("provid _list", select_provId.getSelectAbles()); 
		ParamSelect select_yesorno_status = allSelect
				.getParamsByType(AllSelectContants.YESORNO_STATUS.getName()); 
		request.setAttribute("citystate _list", select_yesorno_status.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=CityDictList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<CityDictSearchFields, Object> criterias = getCriterias();

		Collection<CityDict> citydictList = pMgr.searchCityDict(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (CityDict citydict : citydictList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
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
		Map<CityDictSearchFields, Object> criterias = getCriterias();

		Collection<CityDict> moneyList = pMgr.searchCityDict(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchCityDictNum(criterias);
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
		Map<CityDictSearchFields, Object> criterias = getCriterias();

		Collection<CityDict> moneyList = pMgr.searchCityDict(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchCityDictNum(criterias);
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

	private Map<CityDictSearchFields, Object> getCriterias() {
		Map<CityDictSearchFields, Object> criterias = new HashMap<CityDictSearchFields, Object>();
		
		//下面是高级查询的时候添加的条件
		//添加名称的查询条件
		addCityNameCondition(criterias,getCondition_cityName(),getQuery_cityName());
		//添加省份的查询条件
		addProvIdCondition(criterias,getCondition_provId(),getQuery_provId());
		//添加状态 的查询条件
		addCityStateCondition(criterias,getCondition_cityState(),getQuery_cityState());
		return criterias;
	}
	
	//下面是添加高级查询的条件
	/**
	 * 添加查询名称的查询条件.
	 */
	public void addCityNameCondition(Map<CityDictSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(CityDictSearchFields.CITYNAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(CityDictSearchFields.CITYNAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询省份的查询条件.
	 */
	public void addProvIdCondition(Map<CityDictSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(CityDictSearchFields.PROVID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(CityDictSearchFields.PROVID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询状态 的查询条件.
	 */
	public void addCityStateCondition(Map<CityDictSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(CityDictSearchFields.CITYSTATE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(CityDictSearchFields.CITYSTATE_COM_NOT_EQUALS, value);
		} 
	} 

	public CityDict getVo() {
		return vo;
	}

	public void setVo(CityDict vo) {
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
	private String cityName; 
 	/**
 	 * 获取名称的属性值.
 	 */
 	public String getCityName(){
 		return cityName;
 	}
 	
 	/**
 	 * 设置名称的属性值.
 	 */
 	public void setCityName(String cityname){
 		this.cityName = cityname;
 	}
	private String provId; 
 	/**
 	 * 获取省份的属性值.
 	 */
 	public String getProvId(){
 		return provId;
 	}
 	
 	/**
 	 * 设置省份的属性值.
 	 */
 	public void setProvId(String provid){
 		this.provId = provid;
 	}
	private String cityState; 
 	/**
 	 * 获取状态 的属性值.
 	 */
 	public String getCityState(){
 		return cityState;
 	}
 	
 	/**
 	 * 设置状态 的属性值.
 	 */
 	public void setCityState(String citystate){
 		this.cityState = citystate;
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
	
	private String condition_cityName;
	
	public String getCondition_cityName(){
		return this.condition_cityName;
	}
	
	public void setCondition_cityName(String s){
		this.condition_cityName = s;
	}
	
	private String query_cityName;
		
	public String getQuery_cityName(){
		return this.query_cityName;
	}
	
	public void setQuery_cityName(String s){
		this.query_cityName = s;
	}
	
	private String condition_provId;
	
	public String getCondition_provId(){
		return this.condition_provId;
	}
	
	public void setCondition_provId(String s){
		this.condition_provId = s;
	}
	
	private String query_provId;
		
	public String getQuery_provId(){
		return this.query_provId;
	}
	
	public void setQuery_provId(String s){
		this.query_provId = s;
	}
	
	private String condition_cityState;
	
	public String getCondition_cityState(){
		return this.condition_cityState;
	}
	
	public void setCondition_cityState(String s){
		this.condition_cityState = s;
	}
	
	private String query_cityState;
		
	public String getQuery_cityState(){
		return this.query_cityState;
	}
	
	public void setQuery_cityState(String s){
		this.query_cityState = s;
	}
	
}
