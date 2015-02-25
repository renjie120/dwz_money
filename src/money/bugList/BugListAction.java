
package money.bugList;
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
 * 关于问题清单的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BugListAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	BugListManager pMgr = bf.getManager(BeanManagerKey.buglistManager);
	//业务实体对象
	private BugList vo;
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
 	 * 添加问题清单.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			BugListImpl buglistImpl = new BugListImpl(bugType ,bugDesc ,createUser ,createTime ,needTime ,consolePeople ,consoleTile ,remark );
			pMgr.createBugList(buglistImpl);
			insertLog(logMgr,"添加问题清单","/doAdd", "", "" ,JSON.toJSONString(buglistImpl));  
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
		insertLog(logMgr,"导入问题清单","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<BugList> allDeleteIds = new ArrayList<BugList>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getBugList(Integer.parseInt(_id)));
		}
		pMgr.removeBugLists(ids);
		insertLog(logMgr,"删除问题清单","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getBugList(sno);
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
			BugList old = pMgr.getBugList( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getBugType(),bugType)){
				oldObj += "bugType="+old.getBugType()+";";
				newObj+= "bugType="+bugType+";";
			} 
			if(!compare(old.getBugDesc(),bugDesc)){
				oldObj += "bugDesc="+old.getBugDesc()+";";
				newObj+= "bugDesc="+bugDesc+";";
			} 
			if(!compare(old.getCreateUser(),createUser)){
				oldObj += "createUser="+old.getCreateUser()+";";
				newObj+= "createUser="+createUser+";";
			} 
			if(!compare(old.getCreateTime(),createTime)){
				oldObj += "createTime="+old.getCreateTime()+";";
				newObj+= "createTime="+createTime+";";
			} 
			if(!compare(old.getNeedTime(),needTime)){
				oldObj += "needTime="+old.getNeedTime()+";";
				newObj+= "needTime="+needTime+";";
			} 
			if(!compare(old.getConsolePeople(),consolePeople)){
				oldObj += "consolePeople="+old.getConsolePeople()+";";
				newObj+= "consolePeople="+consolePeople+";";
			} 
			if(!compare(old.getConsoleTile(),consoleTile)){
				oldObj += "consoleTile="+old.getConsoleTile()+";";
				newObj+= "consoleTile="+consoleTile+";";
			} 
			if(!compare(old.getRemark(),remark)){
				oldObj += "remark="+old.getRemark()+";";
				newObj+= "remark="+remark+";";
			} 
			
			BugListImpl buglistImpl = new BugListImpl( sno , bugType , bugDesc , createUser , createTime , needTime , consolePeople , consoleTile , remark );
			pMgr.updateBugList(buglistImpl);
			insertLog(logMgr,"修改问题清单","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(buglistImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,BUGTYPE("问题类型")  ,BUGDESC("问题描述")  ,NEEDTIME("待解决时间")  ,CONSOLEPEOPLE("解决人")  ,CONSOLETILE("解决时间")  ,REMARK("备注");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		  BUGTYPE("问题类型")  ,NEEDTIME("待解决时间")  ,CONSOLETILE("解决时间")  ,REMARK("备注");
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
		ParamSelect select_bugtype = allSelect
				.getParamsByType(AllSelectContants.BUGTYPE.getName()); 
		request.setAttribute("bugtype_list", select_bugtype.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=BugListList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<BugListSearchFields, Object> criterias = getCriterias();

		Collection<BugList> buglistList = pMgr.searchBugList(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (BugList buglist : buglistList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), buglist.getSno()); 
					break;
					case BUGTYPE:
						 e.setCell(filed.ordinal(), buglist.getBugType()); 
					break;
					case BUGDESC:
						 e.setCell(filed.ordinal(), buglist.getBugDesc()); 
					break;
					case NEEDTIME:
						 e.setCell(filed.ordinal(), buglist.getNeedTime()); 
					break;
					case CONSOLEPEOPLE:
						 e.setCell(filed.ordinal(), buglist.getConsolePeople()); 
					break;
					case CONSOLETILE:
						 e.setCell(filed.ordinal(), buglist.getConsoleTile()); 
					break;
					case REMARK:
						 e.setCell(filed.ordinal(), buglist.getRemark()); 
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
		Map<BugListSearchFields, Object> criterias = getCriterias();

		Collection<BugList> moneyList = pMgr.searchBugList(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBugListNum(criterias);
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
		Map<BugListSearchFields, Object> criterias = getCriterias();

		Collection<BugList> moneyList = pMgr.searchBugList(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchBugListNum(criterias);
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

	private Map<BugListSearchFields, Object> getCriterias() {
		Map<BugListSearchFields, Object> criterias = new HashMap<BugListSearchFields, Object>();
		if (getBugDesc()!=null&&!"".equals(getBugDesc())&&!"-1".equals(getBugDesc())&&!"-2".equals(getBugDesc()))
			criterias.put(BugListSearchFields.BUGDESC,  getBugDesc());
		if (getRemark()!=null&&!"".equals(getRemark())&&!"-1".equals(getRemark())&&!"-2".equals(getRemark()))
			criterias.put(BugListSearchFields.REMARK,  getRemark());
		
		//下面是高级查询的时候添加的条件
		//添加问题类型的查询条件
		addBugTypeCondition(criterias,getCondition_bugType(),getQuery_bugType());
		//添加问题描述的查询条件
		addBugDescCondition(criterias,getCondition_bugDesc(),getQuery_bugDesc());
		//添加问题发现人的查询条件
		addCreateUserCondition(criterias,getCondition_createUser(),getQuery_createUser());
		//添加发现时间的查询条件
		addCreateTimeCondition(criterias,getCondition_createTime(),getQuery_createTime());
		//添加待解决时间的查询条件
		addNeedTimeCondition(criterias,getCondition_needTime(),getQuery_needTime());
		//添加解决人的查询条件
		addConsolePeopleCondition(criterias,getCondition_consolePeople(),getQuery_consolePeople());
		//添加解决时间的查询条件
		addConsoleTileCondition(criterias,getCondition_consoleTile(),getQuery_consoleTile());
		//添加备注的查询条件
		addRemarkCondition(criterias,getCondition_remark(),getQuery_remark());
		return criterias;
	}
	
	//下面是添加高级查询的条件
	/**
	 * 添加查询问题类型的查询条件.
	 */
	public void addBugTypeCondition(Map<BugListSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.BUGTYPE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.BUGTYPE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询问题描述的查询条件.
	 */
	public void addBugDescCondition(Map<BugListSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.BUGDESC_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.BUGDESC_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询问题发现人的查询条件.
	 */
	public void addCreateUserCondition(Map<BugListSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询发现时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<BugListSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询待解决时间的查询条件.
	 */
	public void addNeedTimeCondition(Map<BugListSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.NEEDTIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.NEEDTIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询解决人的查询条件.
	 */
	public void addConsolePeopleCondition(Map<BugListSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.CONSOLEPEOPLE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.CONSOLEPEOPLE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询解决时间的查询条件.
	 */
	public void addConsoleTileCondition(Map<BugListSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.CONSOLETILE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.CONSOLETILE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询备注的查询条件.
	 */
	public void addRemarkCondition(Map<BugListSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.REMARK_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(BugListSearchFields.REMARK_COM_NOT_EQUALS, value);
		} 
	} 

	public BugList getVo() {
		return vo;
	}

	public void setVo(BugList vo) {
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
	private String bugType; 
 	/**
 	 * 获取问题类型的属性值.
 	 */
 	public String getBugType(){
 		return bugType;
 	}
 	
 	/**
 	 * 设置问题类型的属性值.
 	 */
 	public void setBugType(String bugtype){
 		this.bugType = bugtype;
 	}
	private String bugDesc; 
 	/**
 	 * 获取问题描述的属性值.
 	 */
 	public String getBugDesc(){
 		return bugDesc;
 	}
 	
 	/**
 	 * 设置问题描述的属性值.
 	 */
 	public void setBugDesc(String bugdesc){
 		this.bugDesc = bugdesc;
 	}
	private int createUser; 
 	/**
 	 * 获取问题发现人的属性值.
 	 */
 	public int getCreateUser(){
 		return createUser;
 	}
 	
 	/**
 	 * 设置问题发现人的属性值.
 	 */
 	public void setCreateUser(int createuser){
 		this.createUser = createuser;
 	}
	private String createTime; 
 	/**
 	 * 获取发现时间的属性值.
 	 */
 	public String getCreateTime(){
 		return createTime;
 	}
 	
 	/**
 	 * 设置发现时间的属性值.
 	 */
 	public void setCreateTime(String createtime){
 		this.createTime = createtime;
 	}
	private String needTime; 
 	/**
 	 * 获取待解决时间的属性值.
 	 */
 	public String getNeedTime(){
 		return needTime;
 	}
 	
 	/**
 	 * 设置待解决时间的属性值.
 	 */
 	public void setNeedTime(String needtime){
 		this.needTime = needtime;
 	}
	private String consolePeople; 
 	/**
 	 * 获取解决人的属性值.
 	 */
 	public String getConsolePeople(){
 		return consolePeople;
 	}
 	
 	/**
 	 * 设置解决人的属性值.
 	 */
 	public void setConsolePeople(String consolepeople){
 		this.consolePeople = consolepeople;
 	}
	private String consoleTile; 
 	/**
 	 * 获取解决时间的属性值.
 	 */
 	public String getConsoleTile(){
 		return consoleTile;
 	}
 	
 	/**
 	 * 设置解决时间的属性值.
 	 */
 	public void setConsoleTile(String consoletile){
 		this.consoleTile = consoletile;
 	}
	private String remark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getRemark(){
 		return remark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setRemark(String remark){
 		this.remark = remark;
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
	
	private String condition_bugType;
	
	public String getCondition_bugType(){
		return this.condition_bugType;
	}
	
	public void setCondition_bugType(String s){
		this.condition_bugType = s;
	}
	
	private String query_bugType;
		
	public String getQuery_bugType(){
		return this.query_bugType;
	}
	
	public void setQuery_bugType(String s){
		this.query_bugType = s;
	}
	
	private String condition_bugDesc;
	
	public String getCondition_bugDesc(){
		return this.condition_bugDesc;
	}
	
	public void setCondition_bugDesc(String s){
		this.condition_bugDesc = s;
	}
	
	private String query_bugDesc;
		
	public String getQuery_bugDesc(){
		return this.query_bugDesc;
	}
	
	public void setQuery_bugDesc(String s){
		this.query_bugDesc = s;
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
	
	private String condition_needTime;
	
	public String getCondition_needTime(){
		return this.condition_needTime;
	}
	
	public void setCondition_needTime(String s){
		this.condition_needTime = s;
	}
	
	private String query_needTime;
		
	public String getQuery_needTime(){
		return this.query_needTime;
	}
	
	public void setQuery_needTime(String s){
		this.query_needTime = s;
	}
	
	private String condition_consolePeople;
	
	public String getCondition_consolePeople(){
		return this.condition_consolePeople;
	}
	
	public void setCondition_consolePeople(String s){
		this.condition_consolePeople = s;
	}
	
	private String query_consolePeople;
		
	public String getQuery_consolePeople(){
		return this.query_consolePeople;
	}
	
	public void setQuery_consolePeople(String s){
		this.query_consolePeople = s;
	}
	
	private String condition_consoleTile;
	
	public String getCondition_consoleTile(){
		return this.condition_consoleTile;
	}
	
	public void setCondition_consoleTile(String s){
		this.condition_consoleTile = s;
	}
	
	private String query_consoleTile;
		
	public String getQuery_consoleTile(){
		return this.query_consoleTile;
	}
	
	public void setQuery_consoleTile(String s){
		this.query_consoleTile = s;
	}
	
	private String condition_remark;
	
	public String getCondition_remark(){
		return this.condition_remark;
	}
	
	public void setCondition_remark(String s){
		this.condition_remark = s;
	}
	
	private String query_remark;
		
	public String getQuery_remark(){
		return this.query_remark;
	}
	
	public void setQuery_remark(String s){
		this.query_remark = s;
	}
	
}
