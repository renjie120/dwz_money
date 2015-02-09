
package ido.InsuredFile;
import ido.loginfo.LogInfoManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.cache.Cache;
import common.cache.CacheManager;
import common.util.CommonUtil;
import common.util.DateTool;

import dwz.constants.BeanManagerKey;
import dwz.framework.constants.Constants;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.user.User;
import dwz.framework.user.impl.UserImpl;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;
/**
 * 关于投保单的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredFileAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	//业务接口对象.
	InsuredFileManager pMgr = bf.getManager(BeanManagerKey.insuredfileManager);
	//业务实体对象
	private InsuredFile vo;
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
	 * 判断保险单名称是否重复
	 * @return
	 */
	public String isExistedFileId() { 
		boolean ans  = pMgr.existed("insured_file", "f_id", changeStr(insuredFileId)); 
		response.setContentType("text/html;charset=GBK"); 
		try {
			response.getWriter().write(ans+"");
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 判断保险公司名称是否重复
	 * @return
	 */
	public String isExistedCompanyCode() { 
		boolean ans  = pMgr.existed("insured_company", "com_name", changeStr(insuredFileId)); 
		response.setContentType("text/html;charset=GBK"); 
		try {
			response.getWriter().write(ans+"");
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 判断投保用户号唯一
	 * @return
	 */
	public String isExistedInsuredUserNo() { 
		boolean ans  = pMgr.existed("insured_user", "iuser_no", changeStr(insuredFileId)); 
		response.setContentType("text/html;charset=GBK"); 
		try {
			response.getWriter().write(ans+"");
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 判断商家编号是否重复.
	 * @return
	 */
	public String isExistedShopmCode() { 
		boolean ans  = pMgr.existed("business_man", "shopm_sno", changeStr(insuredFileId)); 
		response.setContentType("text/html;charset=GBK"); 
		try {
			response.getWriter().write(ans+"");
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 判断商铺编号是否重复.
	 * @return
	 */
	public String isExistedShopCode() { 
		boolean ans  = pMgr.existed("business_shop", "shop_sno", changeStr(insuredFileId)); 
		response.setContentType("text/html;charset=GBK"); 
		try {
			response.getWriter().write(ans+"");
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 判断是否存在用户登录名.
	 * @return
	 */
	public String isExistedUserCode() { 
		boolean ans  = pMgr.existed("ido_user", "user_id", changeStr(insuredFileId)); 
		response.setContentType("text/html;charset=GBK"); 
		try {
			response.getWriter().write(ans+"");
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		return null;
	} 
	
	/**
	 * 判断商家集团名称是否重复.
	 * @return
	 */
	public String isExistedGroupCode() { 
		boolean ans  = pMgr.existed("business_group", "g_sno", changeStr(insuredFileId)); 
		response.setContentType("text/html;charset=GBK"); 
		try {
			response.getWriter().write(ans+"");
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		return null;
	}
	
	
 	/**
 	 * 添加投保单.
 	 */
	public String doAdd() {
		try {
			setCurrentUser(false);
			InsuredFileImpl insuredfileImpl = new InsuredFileImpl(insuredFileId ,insuredFileName ,insuredFileUnit ,insuredFileCompany ,insuredFileEmail ,insuredFileContact ,insuredFileConTel ,insuredFileConMobile ,insuredFileBegin ,insuredFileEnd ,insuredFileStatus ,insuredFileDuijie ,insuredFileDuijieFlag ,insuredFileRemark ,insuredFileTotal ,insuredFileEmerg ,insuredFileHospital ,insuredFileExam ,insuredFileConsumer ,insuredFileConsRule ,createUser ,createTime ,updateUser ,updateTime );
			pMgr.createInsuredFile(insuredfileImpl);
			insertLog(logMgr,"添加投保单","/doAdd", "", "" ,JSON.toJSONString(insuredfileImpl));  
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
		insertLog(logMgr,"导入投保单","/importExcel", "", "" ,"");  
		writeToPage(response, "导入成功!");
		return null;
	}


	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("ids");
		String[] allId = ids.split(",");
		List<InsuredFile> allDeleteIds = new ArrayList<InsuredFile>();
		for(String _id:allId){
			allDeleteIds.add(pMgr.getInsuredFile(Integer.parseInt(_id)));
		}
		pMgr.removeInsuredFiles(ids);
		insertLog(logMgr,"删除投保单","/doDelete", "", "" ,JSON.toJSONString(allDeleteIds));   
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getInsuredFile(sno);
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
			InsuredFile old = pMgr.getInsuredFile( sno );
			String oldObj= "";
			String newObj= ""; 
			if(!compare(old.getSno(),sno)){
				oldObj += "sno="+old.getSno()+";";
				newObj+= "sno="+sno+";";
			} 
			if(!compare(old.getInsuredFileId(),insuredFileId)){
				oldObj += "insuredFileId="+old.getInsuredFileId()+";";
				newObj+= "insuredFileId="+insuredFileId+";";
			} 
			if(!compare(old.getInsuredFileName(),insuredFileName)){
				oldObj += "insuredFileName="+old.getInsuredFileName()+";";
				newObj+= "insuredFileName="+insuredFileName+";";
			} 
			if(!compare(old.getInsuredFileUnit(),insuredFileUnit)){
				oldObj += "insuredFileUnit="+old.getInsuredFileUnit()+";";
				newObj+= "insuredFileUnit="+insuredFileUnit+";";
			} 
			if(!compare(old.getInsuredFileCompany(),insuredFileCompany)){
				oldObj += "insuredFileCompany="+old.getInsuredFileCompany()+";";
				newObj+= "insuredFileCompany="+insuredFileCompany+";";
			} 
			if(!compare(old.getInsuredFileEmail(),insuredFileEmail)){
				oldObj += "insuredFileEmail="+old.getInsuredFileEmail()+";";
				newObj+= "insuredFileEmail="+insuredFileEmail+";";
			} 
			if(!compare(old.getInsuredFileContact(),insuredFileContact)){
				oldObj += "insuredFileContact="+old.getInsuredFileContact()+";";
				newObj+= "insuredFileContact="+insuredFileContact+";";
			} 
			if(!compare(old.getInsuredFileConTel(),insuredFileConTel)){
				oldObj += "insuredFileConTel="+old.getInsuredFileConTel()+";";
				newObj+= "insuredFileConTel="+insuredFileConTel+";";
			} 
			if(!compare(old.getInsuredFileConMobile(),insuredFileConMobile)){
				oldObj += "insuredFileConMobile="+old.getInsuredFileConMobile()+";";
				newObj+= "insuredFileConMobile="+insuredFileConMobile+";";
			} 
			if(!compare(old.getInsuredFileBegin(),insuredFileBegin)){
				oldObj += "insuredFileBegin="+old.getInsuredFileBegin()+";";
				newObj+= "insuredFileBegin="+insuredFileBegin+";";
			} 
			if(!compare(old.getInsuredFileEnd(),insuredFileEnd)){
				oldObj += "insuredFileEnd="+old.getInsuredFileEnd()+";";
				newObj+= "insuredFileEnd="+insuredFileEnd+";";
			} 
			if(!compare(old.getInsuredFileStatus(),insuredFileStatus)){
				oldObj += "insuredFileStatus="+old.getInsuredFileStatus()+";";
				newObj+= "insuredFileStatus="+insuredFileStatus+";";
			} 
			if(!compare(old.getInsuredFileDuijie(),insuredFileDuijie)){
				oldObj += "insuredFileDuijie="+old.getInsuredFileDuijie()+";";
				newObj+= "insuredFileDuijie="+insuredFileDuijie+";";
			} 
			if(!compare(old.getInsuredFileDuijieFlag(),insuredFileDuijieFlag)){
				oldObj += "insuredFileDuijieFlag="+old.getInsuredFileDuijieFlag()+";";
				newObj+= "insuredFileDuijieFlag="+insuredFileDuijieFlag+";";
			} 
			if(!compare(old.getInsuredFileRemark(),insuredFileRemark)){
				oldObj += "insuredFileRemark="+old.getInsuredFileRemark()+";";
				newObj+= "insuredFileRemark="+insuredFileRemark+";";
			} 
			if(!compare(old.getInsuredFileTotal(),insuredFileTotal)){
				oldObj += "insuredFileTotal="+old.getInsuredFileTotal()+";";
				newObj+= "insuredFileTotal="+insuredFileTotal+";";
			} 
			if(!compare(old.getInsuredFileEmerg(),insuredFileEmerg)){
				oldObj += "insuredFileEmerg="+old.getInsuredFileEmerg()+";";
				newObj+= "insuredFileEmerg="+insuredFileEmerg+";";
			} 
			if(!compare(old.getInsuredFileHospital(),insuredFileHospital)){
				oldObj += "insuredFileHospital="+old.getInsuredFileHospital()+";";
				newObj+= "insuredFileHospital="+insuredFileHospital+";";
			} 
			if(!compare(old.getInsuredFileExam(),insuredFileExam)){
				oldObj += "insuredFileExam="+old.getInsuredFileExam()+";";
				newObj+= "insuredFileExam="+insuredFileExam+";";
			} 
			if(!compare(old.getInsuredFileConsumer(),insuredFileConsumer)){
				oldObj += "insuredFileConsumer="+old.getInsuredFileConsumer()+";";
				newObj+= "insuredFileConsumer="+insuredFileConsumer+";";
			} 
			if(!compare(old.getInsuredFileConsRule(),insuredFileConsRule)){
				oldObj += "insuredFileConsRule="+old.getInsuredFileConsRule()+";";
				newObj+= "insuredFileConsRule="+insuredFileConsRule+";";
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
			
			InsuredFileImpl insuredfileImpl = new InsuredFileImpl( sno , insuredFileId , insuredFileName , insuredFileUnit , insuredFileCompany , insuredFileEmail , insuredFileContact , insuredFileConTel , insuredFileConMobile , insuredFileBegin , insuredFileEnd , insuredFileStatus , insuredFileDuijie , insuredFileDuijieFlag , insuredFileRemark , insuredFileTotal , insuredFileEmerg , insuredFileHospital , insuredFileExam , insuredFileConsumer , insuredFileConsRule , createUser , createTime , updateUser , updateTime );
			pMgr.updateInsuredFile(insuredfileImpl);
			insertLog(logMgr,"修改投保单","/doUpdate", oldObj, 
						newObj,
						"原始记录："+JSON.toJSONString(old)+"\n新的记录："+JSON.toJSONString(insuredfileImpl));  
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号")  ,INSUREDFILEID("投保单号 ")  ,INSUREDFILENAME("保单名称")  ,INSUREDFILEUNIT("投保单位")  ,INSUREDFILECOMPANY("保险公司")  ,INSUREDFILEEMAIL("邮箱")  ,INSUREDFILECONTACT("联系人")  ,INSUREDFILECONTEL("联系电话")  ,INSUREDFILECONMOBILE("联系人手机");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}
	
	public enum ImportFiled {
		  INSUREDFILEID("投保单号 ")  ,INSUREDFILENAME("保单名称")  ,INSUREDFILEUNIT("投保单位")  ,INSUREDFILECOMPANY("保险公司")  ,INSUREDFILEEMAIL("邮箱")  ,INSUREDFILECONTACT("联系人")  ,INSUREDFILECONTEL("联系电话")  ,INSUREDFILECONMOBILE("联系人手机");
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
		Cache cache_insuredFileUnit = CacheManager.getCacheInfoNotNull(AllSelectContants.INSUREDUNIT_DICT.getName());
		ParamSelect select_insuredFileUnit = (ParamSelect)cache_insuredFileUnit.getValue();
		request.setAttribute("insuredfileunit_list", select_insuredFileUnit.getSelectAbles()); 
		Cache cache_insuredFileCompany = CacheManager.getCacheInfoNotNull(AllSelectContants.INSURED_COM_DICT.getName());
		ParamSelect select_insuredFileCompany = (ParamSelect)cache_insuredFileCompany.getValue();
		request.setAttribute("insuredfilecompany_list", select_insuredFileCompany.getSelectAbles()); 
		ParamSelect select_insureFile_state = allSelect
				.getParamsByType(AllSelectContants.INSUREFILE_STATE.getName()); 
		request.setAttribute("insuredfilestatus_list", select_insureFile_state.getSelectAbles()); 
		ParamSelect select_sys_duijie = allSelect
				.getParamsByType(AllSelectContants.SYS_DUIJIE.getName()); 
		request.setAttribute("insuredfileduijie_list", select_sys_duijie.getSelectAbles()); 
		ParamSelect select_yesorno = allSelect
				.getParamsByType(AllSelectContants.YESORNO.getName()); 
		request.setAttribute("insuredfileduijieflag_list", select_yesorno.getSelectAbles()); 
		return "query";
	}

	/**
	 * 导出界面.
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=InsuredFileList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<InsuredFileSearchFields, Object> criterias = getCriterias();

		Collection<InsuredFile> insuredfileList = pMgr.searchInsuredFile(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (InsuredFile insuredfile : insuredfileList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), insuredfile.getSno()); 
					break;
					case INSUREDFILEID:
						 e.setCell(filed.ordinal(), insuredfile.getInsuredFileId()); 
					break;
					case INSUREDFILENAME:
						 e.setCell(filed.ordinal(), insuredfile.getInsuredFileName()); 
					break;
					case INSUREDFILEUNIT:
						 e.setCell(filed.ordinal(), insuredfile.getInsuredFileUnit()); 
					break;
					case INSUREDFILECOMPANY:
						 e.setCell(filed.ordinal(), insuredfile.getInsuredFileCompany()); 
					break;
					case INSUREDFILEEMAIL:
						 e.setCell(filed.ordinal(), insuredfile.getInsuredFileEmail()); 
					break;
					case INSUREDFILECONTACT:
						 e.setCell(filed.ordinal(), insuredfile.getInsuredFileContact()); 
					break;
					case INSUREDFILECONTEL:
						 e.setCell(filed.ordinal(), insuredfile.getInsuredFileConTel()); 
					break;
					case INSUREDFILECONMOBILE:
						 e.setCell(filed.ordinal(), insuredfile.getInsuredFileConMobile()); 
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
		Map<InsuredFileSearchFields, Object> criterias = getCriterias();

		Collection<InsuredFile> moneyList = pMgr.searchInsuredFile(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchInsuredFileNum(criterias);
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
		Map<InsuredFileSearchFields, Object> criterias = getCriterias();

		Collection<InsuredFile> moneyList = pMgr.searchInsuredFile(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchInsuredFileNum(criterias);
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

	private Map<InsuredFileSearchFields, Object> getCriterias() {
		Map<InsuredFileSearchFields, Object> criterias = new HashMap<InsuredFileSearchFields, Object>();
		if (getInsuredFileId()!=null&&!"".equals(getInsuredFileId())&&!"-1".equals(getInsuredFileId())&&!"-2".equals(getInsuredFileId()))
			criterias.put(InsuredFileSearchFields.INSUREDFILEID,  getInsuredFileId());
		if (getInsuredFileName()!=null&&!"".equals(getInsuredFileName())&&!"-1".equals(getInsuredFileName())&&!"-2".equals(getInsuredFileName()))
			criterias.put(InsuredFileSearchFields.INSUREDFILENAME, "%"+getInsuredFileName()+"%"); 
		if (getInsuredFileUnit()!=null&&!"".equals(getInsuredFileUnit())&&!"-1".equals(getInsuredFileUnit())&&!"-2".equals(getInsuredFileUnit()))
			criterias.put(InsuredFileSearchFields.INSUREDFILEUNIT,  getInsuredFileUnit());
		if (getInsuredFileCompany()!=null&&!"".equals(getInsuredFileCompany())&&!"-1".equals(getInsuredFileCompany())&&!"-2".equals(getInsuredFileCompany()))
			criterias.put(InsuredFileSearchFields.INSUREDFILECOMPANY,  getInsuredFileCompany());
		if (getInsuredFileStatus()!=null&&!"".equals(getInsuredFileStatus())&&!"-1".equals(getInsuredFileStatus())&&!"-2".equals(getInsuredFileStatus()))
			criterias.put(InsuredFileSearchFields.INSUREDFILESTATUS,  getInsuredFileStatus());
		
		//下面是高级查询的时候添加的条件
		//添加投保单号 的查询条件
		addInsuredFileIdCondition(criterias,getCondition_insuredFileId(),getQuery_insuredFileId());
		//添加保单名称的查询条件
		addInsuredFileNameCondition(criterias,getCondition_insuredFileName(),getQuery_insuredFileName());
		//添加投保单位的查询条件
		addInsuredFileUnitCondition(criterias,getCondition_insuredFileUnit(),getQuery_insuredFileUnit());
		//添加保险公司的查询条件
		addInsuredFileCompanyCondition(criterias,getCondition_insuredFileCompany(),getQuery_insuredFileCompany());
		//添加邮箱的查询条件
		addInsuredFileEmailCondition(criterias,getCondition_insuredFileEmail(),getQuery_insuredFileEmail());
		//添加联系人的查询条件
		addInsuredFileContactCondition(criterias,getCondition_insuredFileContact(),getQuery_insuredFileContact());
		//添加联系电话的查询条件
		addInsuredFileConTelCondition(criterias,getCondition_insuredFileConTel(),getQuery_insuredFileConTel());
		//添加联系人手机的查询条件
		addInsuredFileConMobileCondition(criterias,getCondition_insuredFileConMobile(),getQuery_insuredFileConMobile());
		//添加投保日期的查询条件
		addInsuredFileBeginCondition(criterias,getCondition_insuredFileBegin(),getQuery_insuredFileBegin());
		//添加到期日期的查询条件
		addInsuredFileEndCondition(criterias,getCondition_insuredFileEnd(),getQuery_insuredFileEnd());
		//添加状态的查询条件
		addInsuredFileStatusCondition(criterias,getCondition_insuredFileStatus(),getQuery_insuredFileStatus());
		//添加系统对接方式的查询条件
		addInsuredFileDuijieCondition(criterias,getCondition_insuredFileDuijie(),getQuery_insuredFileDuijie());
		//添加系统对接开启的查询条件
		addInsuredFileDuijieFlagCondition(criterias,getCondition_insuredFileDuijieFlag(),getQuery_insuredFileDuijieFlag());
		//添加备注的查询条件
		addInsuredFileRemarkCondition(criterias,getCondition_insuredFileRemark(),getQuery_insuredFileRemark());
		//添加投保总金额的查询条件
		addInsuredFileTotalCondition(criterias,getCondition_insuredFileTotal(),getQuery_insuredFileTotal());
		//添加门急诊额度的查询条件
		addInsuredFileEmergCondition(criterias,getCondition_insuredFileEmerg(),getQuery_insuredFileEmerg());
		//添加住院额度的查询条件
		addInsuredFileHospitalCondition(criterias,getCondition_insuredFileHospital(),getQuery_insuredFileHospital());
		//添加体检额度的查询条件
		addInsuredFileExamCondition(criterias,getCondition_insuredFileExam(),getQuery_insuredFileExam());
		//添加消费商家控制的查询条件
		addInsuredFileConsumerCondition(criterias,getCondition_insuredFileConsumer(),getQuery_insuredFileConsumer());
		//添加消费规则的查询条件
		addInsuredFileConsRuleCondition(criterias,getCondition_insuredFileConsRule(),getQuery_insuredFileConsRule());
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
	 * 添加查询投保单号 的查询条件.
	 */
	public void addInsuredFileIdCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEID_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEID_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询保单名称的查询条件.
	 */
	public void addInsuredFileNameCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILENAME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILENAME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询投保单位的查询条件.
	 */
	public void addInsuredFileUnitCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEUNIT_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEUNIT_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询保险公司的查询条件.
	 */
	public void addInsuredFileCompanyCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECOMPANY_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECOMPANY_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询邮箱的查询条件.
	 */
	public void addInsuredFileEmailCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEEMAIL_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEEMAIL_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询联系人的查询条件.
	 */
	public void addInsuredFileContactCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECONTACT_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECONTACT_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询联系电话的查询条件.
	 */
	public void addInsuredFileConTelCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECONTEL_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECONTEL_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询联系人手机的查询条件.
	 */
	public void addInsuredFileConMobileCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECONMOBILE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECONMOBILE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询投保日期的查询条件.
	 */
	public void addInsuredFileBeginCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEBEGIN_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEBEGIN_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询到期日期的查询条件.
	 */
	public void addInsuredFileEndCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEEND_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEEND_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询状态的查询条件.
	 */
	public void addInsuredFileStatusCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILESTATUS_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILESTATUS_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询系统对接方式的查询条件.
	 */
	public void addInsuredFileDuijieCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEDUIJIE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEDUIJIE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询系统对接开启的查询条件.
	 */
	public void addInsuredFileDuijieFlagCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEDUIJIEFLAG_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEDUIJIEFLAG_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询备注的查询条件.
	 */
	public void addInsuredFileRemarkCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEREMARK_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEREMARK_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询投保总金额的查询条件.
	 */
	public void addInsuredFileTotalCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILETOTAL_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILETOTAL_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询门急诊额度的查询条件.
	 */
	public void addInsuredFileEmergCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEEMERG_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEEMERG_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询住院额度的查询条件.
	 */
	public void addInsuredFileHospitalCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEHOSPITAL_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEHOSPITAL_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询体检额度的查询条件.
	 */
	public void addInsuredFileExamCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEEXAM_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILEEXAM_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询消费商家控制的查询条件.
	 */
	public void addInsuredFileConsumerCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECONSUMER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECONSUMER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询消费规则的查询条件.
	 */
	public void addInsuredFileConsRuleCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECONSRULE_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.INSUREDFILECONSRULE_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建用户的查询条件.
	 */
	public void addCreateUserCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.CREATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.CREATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询创建时间的查询条件.
	 */
	public void addCreateTimeCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.CREATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.CREATETIME_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新用户的查询条件.
	 */
	public void addUpdateUserCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.UPDATEUSER_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.UPDATEUSER_COM_NOT_EQUALS, value);
		} 
	} 
	/**
	 * 添加查询更新时间的查询条件.
	 */
	public void addUpdateTimeCondition(Map<InsuredFileSearchFields, Object> criterias,String condition,String value){
		if("-1".equals(condition))
			return ;
		if(Constants.COM_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.UPDATETIME_COM_EQUALS, value);
		} 
		else if(Constants.COM_NOT_EQUALS.equals(condition)){
			criterias.put(InsuredFileSearchFields.UPDATETIME_COM_NOT_EQUALS, value);
		} 
	} 

	public InsuredFile getVo() {
		return vo;
	}

	public void setVo(InsuredFile vo) {
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
	private String insuredFileName; 
 	/**
 	 * 获取保单名称的属性值.
 	 */
 	public String getInsuredFileName(){
 		return insuredFileName;
 	}
 	
 	/**
 	 * 设置保单名称的属性值.
 	 */
 	public void setInsuredFileName(String insuredfilename){
 		this.insuredFileName = insuredfilename;
 	}
	private String insuredFileUnit; 
 	/**
 	 * 获取投保单位的属性值.
 	 */
 	public String getInsuredFileUnit(){
 		return insuredFileUnit;
 	}
 	
 	/**
 	 * 设置投保单位的属性值.
 	 */
 	public void setInsuredFileUnit(String insuredfileunit){
 		this.insuredFileUnit = insuredfileunit;
 	}
	private String insuredFileCompany; 
 	/**
 	 * 获取保险公司的属性值.
 	 */
 	public String getInsuredFileCompany(){
 		return insuredFileCompany;
 	}
 	
 	/**
 	 * 设置保险公司的属性值.
 	 */
 	public void setInsuredFileCompany(String insuredfilecompany){
 		this.insuredFileCompany = insuredfilecompany;
 	}
	private String insuredFileEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getInsuredFileEmail(){
 		return insuredFileEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setInsuredFileEmail(String insuredfileemail){
 		this.insuredFileEmail = insuredfileemail;
 	}
	private String insuredFileContact; 
 	/**
 	 * 获取联系人的属性值.
 	 */
 	public String getInsuredFileContact(){
 		return insuredFileContact;
 	}
 	
 	/**
 	 * 设置联系人的属性值.
 	 */
 	public void setInsuredFileContact(String insuredfilecontact){
 		this.insuredFileContact = insuredfilecontact;
 	}
	private String insuredFileConTel; 
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public String getInsuredFileConTel(){
 		return insuredFileConTel;
 	}
 	
 	/**
 	 * 设置联系电话的属性值.
 	 */
 	public void setInsuredFileConTel(String insuredfilecontel){
 		this.insuredFileConTel = insuredfilecontel;
 	}
	private String insuredFileConMobile; 
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public String getInsuredFileConMobile(){
 		return insuredFileConMobile;
 	}
 	
 	/**
 	 * 设置联系人手机的属性值.
 	 */
 	public void setInsuredFileConMobile(String insuredfileconmobile){
 		this.insuredFileConMobile = insuredfileconmobile;
 	}
	private String insuredFileBegin; 
 	/**
 	 * 获取投保日期的属性值.
 	 */
 	public String getInsuredFileBegin(){
 		return insuredFileBegin;
 	}
 	
 	/**
 	 * 设置投保日期的属性值.
 	 */
 	public void setInsuredFileBegin(String insuredfilebegin){
 		this.insuredFileBegin = insuredfilebegin;
 	}
	private String insuredFileEnd; 
 	/**
 	 * 获取到期日期的属性值.
 	 */
 	public String getInsuredFileEnd(){
 		return insuredFileEnd;
 	}
 	
 	/**
 	 * 设置到期日期的属性值.
 	 */
 	public void setInsuredFileEnd(String insuredfileend){
 		this.insuredFileEnd = insuredfileend;
 	}
	private String insuredFileStatus; 
 	/**
 	 * 获取状态的属性值.
 	 */
 	public String getInsuredFileStatus(){
 		return insuredFileStatus;
 	}
 	
 	/**
 	 * 设置状态的属性值.
 	 */
 	public void setInsuredFileStatus(String insuredfilestatus){
 		this.insuredFileStatus = insuredfilestatus;
 	}
	private String insuredFileDuijie; 
 	/**
 	 * 获取系统对接方式的属性值.
 	 */
 	public String getInsuredFileDuijie(){
 		return insuredFileDuijie;
 	}
 	
 	/**
 	 * 设置系统对接方式的属性值.
 	 */
 	public void setInsuredFileDuijie(String insuredfileduijie){
 		this.insuredFileDuijie = insuredfileduijie;
 	}
	private String insuredFileDuijieFlag; 
 	/**
 	 * 获取系统对接开启的属性值.
 	 */
 	public String getInsuredFileDuijieFlag(){
 		return insuredFileDuijieFlag;
 	}
 	
 	/**
 	 * 设置系统对接开启的属性值.
 	 */
 	public void setInsuredFileDuijieFlag(String insuredfileduijieflag){
 		this.insuredFileDuijieFlag = insuredfileduijieflag;
 	}
	private String insuredFileRemark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getInsuredFileRemark(){
 		return insuredFileRemark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setInsuredFileRemark(String insuredfileremark){
 		this.insuredFileRemark = insuredfileremark;
 	}
	private double insuredFileTotal; 
 	/**
 	 * 获取投保总金额的属性值.
 	 */
 	public double getInsuredFileTotal(){
 		return insuredFileTotal;
 	}
 	
 	/**
 	 * 设置投保总金额的属性值.
 	 */
 	public void setInsuredFileTotal(double insuredfiletotal){
 		this.insuredFileTotal = insuredfiletotal;
 	}
	private double insuredFileEmerg; 
 	/**
 	 * 获取门急诊额度的属性值.
 	 */
 	public double getInsuredFileEmerg(){
 		return insuredFileEmerg;
 	}
 	
 	/**
 	 * 设置门急诊额度的属性值.
 	 */
 	public void setInsuredFileEmerg(double insuredfileemerg){
 		this.insuredFileEmerg = insuredfileemerg;
 	}
	private double insuredFileHospital; 
 	/**
 	 * 获取住院额度的属性值.
 	 */
 	public double getInsuredFileHospital(){
 		return insuredFileHospital;
 	}
 	
 	/**
 	 * 设置住院额度的属性值.
 	 */
 	public void setInsuredFileHospital(double insuredfilehospital){
 		this.insuredFileHospital = insuredfilehospital;
 	}
	private double insuredFileExam; 
 	/**
 	 * 获取体检额度的属性值.
 	 */
 	public double getInsuredFileExam(){
 		return insuredFileExam;
 	}
 	
 	/**
 	 * 设置体检额度的属性值.
 	 */
 	public void setInsuredFileExam(double insuredfileexam){
 		this.insuredFileExam = insuredfileexam;
 	}
	private String insuredFileConsumer; 
 	/**
 	 * 获取消费商家控制的属性值.
 	 */
 	public String getInsuredFileConsumer(){
 		return insuredFileConsumer;
 	}
 	
 	/**
 	 * 设置消费商家控制的属性值.
 	 */
 	public void setInsuredFileConsumer(String insuredfileconsumer){
 		this.insuredFileConsumer = insuredfileconsumer;
 	}
	private String insuredFileConsRule; 
 	/**
 	 * 获取消费规则的属性值.
 	 */
 	public String getInsuredFileConsRule(){
 		return insuredFileConsRule;
 	}
 	
 	/**
 	 * 设置消费规则的属性值.
 	 */
 	public void setInsuredFileConsRule(String insuredfileconsrule){
 		this.insuredFileConsRule = insuredfileconsrule;
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
	
	private String condition_insuredFileName;
	
	public String getCondition_insuredFileName(){
		return this.condition_insuredFileName;
	}
	
	public void setCondition_insuredFileName(String s){
		this.condition_insuredFileName = s;
	}
	
	private String query_insuredFileName;
		
	public String getQuery_insuredFileName(){
		return this.query_insuredFileName;
	}
	
	public void setQuery_insuredFileName(String s){
		this.query_insuredFileName = s;
	}
	
	private String condition_insuredFileUnit;
	
	public String getCondition_insuredFileUnit(){
		return this.condition_insuredFileUnit;
	}
	
	public void setCondition_insuredFileUnit(String s){
		this.condition_insuredFileUnit = s;
	}
	
	private String query_insuredFileUnit;
		
	public String getQuery_insuredFileUnit(){
		return this.query_insuredFileUnit;
	}
	
	public void setQuery_insuredFileUnit(String s){
		this.query_insuredFileUnit = s;
	}
	
	private String condition_insuredFileCompany;
	
	public String getCondition_insuredFileCompany(){
		return this.condition_insuredFileCompany;
	}
	
	public void setCondition_insuredFileCompany(String s){
		this.condition_insuredFileCompany = s;
	}
	
	private String query_insuredFileCompany;
		
	public String getQuery_insuredFileCompany(){
		return this.query_insuredFileCompany;
	}
	
	public void setQuery_insuredFileCompany(String s){
		this.query_insuredFileCompany = s;
	}
	
	private String condition_insuredFileEmail;
	
	public String getCondition_insuredFileEmail(){
		return this.condition_insuredFileEmail;
	}
	
	public void setCondition_insuredFileEmail(String s){
		this.condition_insuredFileEmail = s;
	}
	
	private String query_insuredFileEmail;
		
	public String getQuery_insuredFileEmail(){
		return this.query_insuredFileEmail;
	}
	
	public void setQuery_insuredFileEmail(String s){
		this.query_insuredFileEmail = s;
	}
	
	private String condition_insuredFileContact;
	
	public String getCondition_insuredFileContact(){
		return this.condition_insuredFileContact;
	}
	
	public void setCondition_insuredFileContact(String s){
		this.condition_insuredFileContact = s;
	}
	
	private String query_insuredFileContact;
		
	public String getQuery_insuredFileContact(){
		return this.query_insuredFileContact;
	}
	
	public void setQuery_insuredFileContact(String s){
		this.query_insuredFileContact = s;
	}
	
	private String condition_insuredFileConTel;
	
	public String getCondition_insuredFileConTel(){
		return this.condition_insuredFileConTel;
	}
	
	public void setCondition_insuredFileConTel(String s){
		this.condition_insuredFileConTel = s;
	}
	
	private String query_insuredFileConTel;
		
	public String getQuery_insuredFileConTel(){
		return this.query_insuredFileConTel;
	}
	
	public void setQuery_insuredFileConTel(String s){
		this.query_insuredFileConTel = s;
	}
	
	private String condition_insuredFileConMobile;
	
	public String getCondition_insuredFileConMobile(){
		return this.condition_insuredFileConMobile;
	}
	
	public void setCondition_insuredFileConMobile(String s){
		this.condition_insuredFileConMobile = s;
	}
	
	private String query_insuredFileConMobile;
		
	public String getQuery_insuredFileConMobile(){
		return this.query_insuredFileConMobile;
	}
	
	public void setQuery_insuredFileConMobile(String s){
		this.query_insuredFileConMobile = s;
	}
	
	private String condition_insuredFileBegin;
	
	public String getCondition_insuredFileBegin(){
		return this.condition_insuredFileBegin;
	}
	
	public void setCondition_insuredFileBegin(String s){
		this.condition_insuredFileBegin = s;
	}
	
	private String query_insuredFileBegin;
		
	public String getQuery_insuredFileBegin(){
		return this.query_insuredFileBegin;
	}
	
	public void setQuery_insuredFileBegin(String s){
		this.query_insuredFileBegin = s;
	}
	
	private String condition_insuredFileEnd;
	
	public String getCondition_insuredFileEnd(){
		return this.condition_insuredFileEnd;
	}
	
	public void setCondition_insuredFileEnd(String s){
		this.condition_insuredFileEnd = s;
	}
	
	private String query_insuredFileEnd;
		
	public String getQuery_insuredFileEnd(){
		return this.query_insuredFileEnd;
	}
	
	public void setQuery_insuredFileEnd(String s){
		this.query_insuredFileEnd = s;
	}
	
	private String condition_insuredFileStatus;
	
	public String getCondition_insuredFileStatus(){
		return this.condition_insuredFileStatus;
	}
	
	public void setCondition_insuredFileStatus(String s){
		this.condition_insuredFileStatus = s;
	}
	
	private String query_insuredFileStatus;
		
	public String getQuery_insuredFileStatus(){
		return this.query_insuredFileStatus;
	}
	
	public void setQuery_insuredFileStatus(String s){
		this.query_insuredFileStatus = s;
	}
	
	private String condition_insuredFileDuijie;
	
	public String getCondition_insuredFileDuijie(){
		return this.condition_insuredFileDuijie;
	}
	
	public void setCondition_insuredFileDuijie(String s){
		this.condition_insuredFileDuijie = s;
	}
	
	private String query_insuredFileDuijie;
		
	public String getQuery_insuredFileDuijie(){
		return this.query_insuredFileDuijie;
	}
	
	public void setQuery_insuredFileDuijie(String s){
		this.query_insuredFileDuijie = s;
	}
	
	private String condition_insuredFileDuijieFlag;
	
	public String getCondition_insuredFileDuijieFlag(){
		return this.condition_insuredFileDuijieFlag;
	}
	
	public void setCondition_insuredFileDuijieFlag(String s){
		this.condition_insuredFileDuijieFlag = s;
	}
	
	private String query_insuredFileDuijieFlag;
		
	public String getQuery_insuredFileDuijieFlag(){
		return this.query_insuredFileDuijieFlag;
	}
	
	public void setQuery_insuredFileDuijieFlag(String s){
		this.query_insuredFileDuijieFlag = s;
	}
	
	private String condition_insuredFileRemark;
	
	public String getCondition_insuredFileRemark(){
		return this.condition_insuredFileRemark;
	}
	
	public void setCondition_insuredFileRemark(String s){
		this.condition_insuredFileRemark = s;
	}
	
	private String query_insuredFileRemark;
		
	public String getQuery_insuredFileRemark(){
		return this.query_insuredFileRemark;
	}
	
	public void setQuery_insuredFileRemark(String s){
		this.query_insuredFileRemark = s;
	}
	
	private String condition_insuredFileTotal;
	
	public String getCondition_insuredFileTotal(){
		return this.condition_insuredFileTotal;
	}
	
	public void setCondition_insuredFileTotal(String s){
		this.condition_insuredFileTotal = s;
	}
	
	private String query_insuredFileTotal;
		
	public String getQuery_insuredFileTotal(){
		return this.query_insuredFileTotal;
	}
	
	public void setQuery_insuredFileTotal(String s){
		this.query_insuredFileTotal = s;
	}
	
	private String condition_insuredFileEmerg;
	
	public String getCondition_insuredFileEmerg(){
		return this.condition_insuredFileEmerg;
	}
	
	public void setCondition_insuredFileEmerg(String s){
		this.condition_insuredFileEmerg = s;
	}
	
	private String query_insuredFileEmerg;
		
	public String getQuery_insuredFileEmerg(){
		return this.query_insuredFileEmerg;
	}
	
	public void setQuery_insuredFileEmerg(String s){
		this.query_insuredFileEmerg = s;
	}
	
	private String condition_insuredFileHospital;
	
	public String getCondition_insuredFileHospital(){
		return this.condition_insuredFileHospital;
	}
	
	public void setCondition_insuredFileHospital(String s){
		this.condition_insuredFileHospital = s;
	}
	
	private String query_insuredFileHospital;
		
	public String getQuery_insuredFileHospital(){
		return this.query_insuredFileHospital;
	}
	
	public void setQuery_insuredFileHospital(String s){
		this.query_insuredFileHospital = s;
	}
	
	private String condition_insuredFileExam;
	
	public String getCondition_insuredFileExam(){
		return this.condition_insuredFileExam;
	}
	
	public void setCondition_insuredFileExam(String s){
		this.condition_insuredFileExam = s;
	}
	
	private String query_insuredFileExam;
		
	public String getQuery_insuredFileExam(){
		return this.query_insuredFileExam;
	}
	
	public void setQuery_insuredFileExam(String s){
		this.query_insuredFileExam = s;
	}
	
	private String condition_insuredFileConsumer;
	
	public String getCondition_insuredFileConsumer(){
		return this.condition_insuredFileConsumer;
	}
	
	public void setCondition_insuredFileConsumer(String s){
		this.condition_insuredFileConsumer = s;
	}
	
	private String query_insuredFileConsumer;
		
	public String getQuery_insuredFileConsumer(){
		return this.query_insuredFileConsumer;
	}
	
	public void setQuery_insuredFileConsumer(String s){
		this.query_insuredFileConsumer = s;
	}
	
	private String condition_insuredFileConsRule;
	
	public String getCondition_insuredFileConsRule(){
		return this.condition_insuredFileConsRule;
	}
	
	public void setCondition_insuredFileConsRule(String s){
		this.condition_insuredFileConsRule = s;
	}
	
	private String query_insuredFileConsRule;
		
	public String getQuery_insuredFileConsRule(){
		return this.query_insuredFileConsRule;
	}
	
	public void setQuery_insuredFileConsRule(String s){
		this.query_insuredFileConsRule = s;
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
