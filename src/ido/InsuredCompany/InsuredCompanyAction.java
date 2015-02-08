package ido.InsuredCompany;

import ido.loginfo.LogInfoManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import money.sequence.SystemSequence;
import money.sequence.SystemSequenceManager;

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
 * 关于保险公司的Action操作类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class InsuredCompanyAction extends BaseAction {
	/**
	 * 序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 操作日志接口对象.
	LogInfoManager logMgr = bf.getManager(BeanManagerKey.loginfoManager);
	// 业务接口对象.
	InsuredCompanyManager pMgr = bf
			.getManager(BeanManagerKey.insuredcompanyManager);
	//序列接口对象.
	SystemSequenceManager sMgr = bf.getManager(BeanManagerKey.systemsequenceManager);
	// 业务实体对象
	private InsuredCompany vo;
	// 当前页数
	private int page = 1;
	// 每页显示数量
	private int pageSize = 50;
	// 总页数
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
		try {
			
			request.setAttribute("comNo",  numberFormat.format(sMgr.getSequence(SystemSequence.SEQ_COMPANY, false)+1));
		} catch (ValidateFieldsException e) { 
			e.printStackTrace();
		}
		return "detail";
	}

	/**
	 * 添加保险公司.
	 */
	public String doAdd() {
		try {
			setCurrentUser(false); 
			InsuredCompanyImpl insuredcompanyImpl = new InsuredCompanyImpl(
					comName, comNo, comStatus, comShortName, comPhone,
					comContactName, comContactPhone, ownerCompany, comEmail,
					comAddress, comRemark, createUser, createTime, updateUser,
					updateTime);
			pMgr.createInsuredCompany(insuredcompanyImpl);
			pMgr.addCache(); 
			//序号自增
			sMgr.getSequence(SystemSequence.SEQ_COMPANY, true);
			insertLog(logMgr, "添加保险公司", "/doAdd", "", "",
					JSON.toJSONString(insuredcompanyImpl));
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
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
		insertLog(logMgr, "导入保险公司", "/importExcel", "", "", "");
		writeToPage(response, "导入成功!");
		return null;
	}

	public String doDelete() {
		setCurrentUser(true);
		String ids = request.getParameter("company_ids");
		String[] allId = ids.split(",");
		List<InsuredCompany> allDeleteIds = new ArrayList<InsuredCompany>();
		for (String _id : allId) {
			allDeleteIds.add(pMgr.getInsuredCompany(Integer.parseInt(_id)));
		}
		pMgr.removeInsuredCompanys(ids);
		pMgr.addCache();
		insertLog(logMgr, "删除保险公司", "/doDelete", "", "",
				JSON.toJSONString(allDeleteIds));
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String doZhuxiao() {
		setCurrentUser(true);
		String ids = request.getParameter("company_ids");
		String[] allId = ids.split(",");
		List<InsuredCompany> allDeleteIds = new ArrayList<InsuredCompany>();
		for (String _id : allId) {
			allDeleteIds.add(pMgr.getInsuredCompany(Integer.parseInt(_id)));
		}
		pMgr.zhuxiaoInsuredCompanys(ids); 
		insertLog(logMgr, "注销保险公司", "/doZhuxiao", "", "",
				JSON.toJSONString(allDeleteIds));
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getInsuredCompany(sno); 
		return "editdetail";
	}

	private void setCurrentUser(boolean isUpdate) {
		User currentUser = (UserImpl) request.getSession().getAttribute(
				Constants.AUTHENTICATION_KEY);
		if (!isUpdate) {
			createUser = Integer.parseInt(currentUser.getId());
			createTime = DateTool.toString(DateTool.now(),
					"yyyy-MM-dd HH:mm:ss");
		} else {
			updateUser = Integer.parseInt(currentUser.getId());
			updateTime = DateTool.toString(DateTool.now(),
					"yyyy-MM-dd HH:mm:ss");
		}
	}

	public String doUpdate() {
		try {
			setCurrentUser(true);
			InsuredCompany old = pMgr.getInsuredCompany(sno);
			String oldObj = "";
			String newObj = "";
			if (!compare(old.getSno(), sno)) {
				oldObj += "sno=" + old.getSno() + ";";
				newObj += "sno=" + sno + ";";
			}
			if (!compare(old.getComName(), comName)) {
				oldObj += "comName=" + old.getComName() + ";";
				newObj += "comName=" + comName + ";";
			}
			if (!compare(old.getComNo(), comNo)) {
				oldObj += "comNo=" + old.getComNo() + ";";
				newObj += "comNo=" + comNo + ";";
			}
			if (!compare(old.getComStatus(), comStatus)) {
				oldObj += "comStatus=" + old.getComStatus() + ";";
				newObj += "comStatus=" + comStatus + ";";
			}
			if (!compare(old.getComShortName(), comShortName)) {
				oldObj += "comShortName=" + old.getComShortName() + ";";
				newObj += "comShortName=" + comShortName + ";";
			}
			if (!compare(old.getComPhone(), comPhone)) {
				oldObj += "comPhone=" + old.getComPhone() + ";";
				newObj += "comPhone=" + comPhone + ";";
			}
			if (!compare(old.getComContactName(), comContactName)) {
				oldObj += "comContactName=" + old.getComContactName() + ";";
				newObj += "comContactName=" + comContactName + ";";
			}
			if (!compare(old.getComContactPhone(), comContactPhone)) {
				oldObj += "comContactPhone=" + old.getComContactPhone() + ";";
				newObj += "comContactPhone=" + comContactPhone + ";";
			}
			if (!compare(old.getOwnerCompany(), ownerCompany)) {
				oldObj += "ownerCompany=" + old.getOwnerCompany() + ";";
				newObj += "ownerCompany=" + ownerCompany + ";";
			}
			if (!compare(old.getComEmail(), comEmail)) {
				oldObj += "comEmail=" + old.getComEmail() + ";";
				newObj += "comEmail=" + comEmail + ";";
			}
			if (!compare(old.getComAddress(), comAddress)) {
				oldObj += "comAddress=" + old.getComAddress() + ";";
				newObj += "comAddress=" + comAddress + ";";
			}
			if (!compare(old.getComRemark(), comRemark)) {
				oldObj += "comRemark=" + old.getComRemark() + ";";
				newObj += "comRemark=" + comRemark + ";";
			}
			if (!compare(old.getCreateUser(), createUser)) {
				oldObj += "createUser=" + old.getCreateUser() + ";";
				newObj += "createUser=" + createUser + ";";
			}
			if (!compare(old.getCreateTime(), createTime)) {
				oldObj += "createTime=" + old.getCreateTime() + ";";
				newObj += "createTime=" + createTime + ";";
			}
			if (!compare(old.getUpdateUser(), updateUser)) {
				oldObj += "updateUser=" + old.getUpdateUser() + ";";
				newObj += "updateUser=" + updateUser + ";";
			}
			if (!compare(old.getUpdateTime(), updateTime)) {
				oldObj += "updateTime=" + old.getUpdateTime() + ";";
				newObj += "updateTime=" + updateTime + ";";
			}

			InsuredCompanyImpl insuredcompanyImpl = new InsuredCompanyImpl(sno,
					comName, comNo, comStatus, comShortName, comPhone,
					comContactName, comContactPhone, ownerCompany, comEmail,
					comAddress, comRemark, createUser, createTime, updateUser,
					updateTime);
			pMgr.updateInsuredCompany(insuredcompanyImpl);
			insertLog(
					logMgr,
					"修改保险公司",
					"/doUpdate",
					oldObj,
					newObj,
					"原始记录：" + JSON.toJSONString(old) + "\n新的记录："
							+ JSON.toJSONString(insuredcompanyImpl));
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public enum ExportFiled {
		SNO("流水号"), COMNAME("保险公司名称"), COMNO("保险公司编号 "), COMSTATUS("状态 "), COMSHORTNAME(
				"简称"), COMPHONE("电话"), COMCONTACTNAME("联系人名称"), COMCONTACTPHONE(
				"联系人手机"), OWNERCOMPANY("所属保险公司"), COMEMAIL("邮箱");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}

	public enum ImportFiled {
		COMNAME("保险公司名称"), COMNO("保险公司编号 "), COMSTATUS("状态 "), COMSHORTNAME(
				"简称"), COMPHONE("电话"), COMCONTACTNAME("联系人名称"), COMCONTACTPHONE(
				"联系人手机"), OWNERCOMPANY("所属保险公司"), COMEMAIL("邮箱");
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
	 * 
	 * @return
	 */
	public String beforeQuery() {
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_yesorno_status = allSelect
				.getParamsByType(AllSelectContants.YESORNO_STATUS.getName());
		request.setAttribute("comstatus_list",
				select_yesorno_status.getSelectAbles());
		Cache cache_ownerCompany = CacheManager
				.getCacheInfoNotNull(AllSelectContants.INSUREDCOMPANY_DICT
						.getName());
		ParamSelect select_ownerCompany = (ParamSelect) cache_ownerCompany
				.getValue();
		request.setAttribute("ownercompany_list",
				select_ownerCompany.getSelectAbles());
		return "query";
	}

	/**
	 * 导出界面.
	 * 
	 * @return
	 */
	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=InsuredCompanyList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<InsuredCompanySearchFields, Object> criterias = getCriterias();

		Collection<InsuredCompany> insuredcompanyList = pMgr
				.searchInsuredCompany(criterias, realOrderField(), startIndex,
						numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (InsuredCompany insuredcompany : insuredcompanyList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case SNO:
					e.setCell(filed.ordinal(), insuredcompany.getSno());
					break;
				case COMNAME:
					e.setCell(filed.ordinal(), insuredcompany.getComName());
					break;
				case COMNO:
					e.setCell(filed.ordinal(), insuredcompany.getComNo());
					break;
				case COMSTATUS:
					e.setCell(filed.ordinal(), insuredcompany.getComStatus());
					break;
				case COMSHORTNAME:
					e.setCell(filed.ordinal(), insuredcompany.getComShortName());
					break;
				case COMPHONE:
					e.setCell(filed.ordinal(), insuredcompany.getComPhone());
					break;
				case COMCONTACTNAME:
					e.setCell(filed.ordinal(),
							insuredcompany.getComContactName());
					break;
				case COMCONTACTPHONE:
					e.setCell(filed.ordinal(),
							insuredcompany.getComContactPhone());
					break;
				case OWNERCOMPANY:
					e.setCell(filed.ordinal(), insuredcompany.getOwnerCompany());
					break;
				case COMEMAIL:
					e.setCell(filed.ordinal(), insuredcompany.getComEmail());
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
		Map<InsuredCompanySearchFields, Object> criterias = getCriterias();

		Collection<InsuredCompany> moneyList = pMgr.searchInsuredCompany(
				criterias, realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchInsuredCompanyNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount", count);
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

	private Map<InsuredCompanySearchFields, Object> getCriterias() {
		Map<InsuredCompanySearchFields, Object> criterias = new HashMap<InsuredCompanySearchFields, Object>();
		if (getComName() != null && !"".equals(getComName())
				&& !"-1".equals(getComName()) && !"-2".equals(getComName()))
			criterias.put(InsuredCompanySearchFields.COMNAME, "%"
					+ getComName() + "%");
		if (getComNo() != null && !"".equals(getComNo())
				&& !"-1".equals(getComNo()) && !"-2".equals(getComNo()))
			criterias.put(InsuredCompanySearchFields.COMNO, "%" + getComNo()
					+ "%");
		if (getComStatus() != null && !"".equals(getComStatus())
				&& !"-1".equals(getComStatus()) && !"-2".equals(getComStatus()))
			criterias.put(InsuredCompanySearchFields.COMSTATUS, getComStatus());

		// 下面是高级查询的时候添加的条件
		addComNameCondition(criterias, getCondition_comName(),
				getQuery_comName());
		addComNoCondition(criterias, getCondition_comNo(), getQuery_comNo());
		addComStatusCondition(criterias, getCondition_comStatus(),
				getQuery_comStatus());
		addComShortNameCondition(criterias, getCondition_comShortName(),
				getQuery_comShortName());
		addComPhoneCondition(criterias, getCondition_comPhone(),
				getQuery_comPhone());
		addComContactNameCondition(criterias, getCondition_comContactName(),
				getQuery_comContactName());
		addComContactPhoneCondition(criterias, getCondition_comContactPhone(),
				getQuery_comContactPhone());
		addOwnerCompanyCondition(criterias, getCondition_ownerCompany(),
				getQuery_ownerCompany());
		addComEmailCondition(criterias, getCondition_comEmail(),
				getQuery_comEmail());
		addComAddressCondition(criterias, getCondition_comAddress(),
				getQuery_comAddress());
		addComRemarkCondition(criterias, getCondition_comRemark(),
				getQuery_comRemark());
		addCreateUserCondition(criterias, getCondition_createUser(),
				getQuery_createUser());
		addCreateTimeCondition(criterias, getCondition1_createTime(),
				getQuery1_createTime());
		addCreateTimeCondition(criterias, getCondition2_createTime(),
				getQuery2_createTime());
		addUpdateUserCondition(criterias, getCondition_updateUser(),
				getQuery_updateUser());
		addUpdateTimeCondition(criterias, getCondition1_updateTime(),
				getQuery1_updateTime());
		addUpdateTimeCondition(criterias, getCondition2_updateTime(),
				getQuery2_updateTime());

		return criterias;
	}

	// 下面是添加高级查询的条件
	public void addComNameCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.STR_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMNAME_STR_EQUALS, value);
		} else if (Constants.STR_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMNAME_STR_LIKE, value);
		} else if (Constants.STR_NOT_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMNAME_STR_NOT_LIKE,
					value);
		} else if (Constants.STR_NOT_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMNAME_STR_NOT_EQUALS,
					value);
		}
	}

	public void addComNoCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.STR_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMNO_STR_EQUALS, value);
		} else if (Constants.STR_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMNO_STR_LIKE, value);
		} else if (Constants.STR_NOT_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMNO_STR_NOT_LIKE, value);
		} else if (Constants.STR_NOT_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMNO_STR_NOT_EQUALS,
					value);
		}
	}

	public void addComStatusCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.COM_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMSTATUS_COM_EQUALS,
					value);
		} else if (Constants.COM_NOT_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMSTATUS_COM_NOT_EQUALS,
					value);
		}
	}

	public void addComShortNameCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.STR_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMSHORTNAME_STR_EQUALS,
					value);
		} else if (Constants.STR_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMSHORTNAME_STR_LIKE,
					value);
		} else if (Constants.STR_NOT_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMSHORTNAME_STR_NOT_LIKE,
					value);
		} else if (Constants.STR_NOT_EQUALS.equals(condition)) {
			criterias.put(
					InsuredCompanySearchFields.COMSHORTNAME_STR_NOT_EQUALS,
					value);
		}
	}

	public void addComPhoneCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.STR_EQUALS.equals(condition)) {
			criterias
					.put(InsuredCompanySearchFields.COMPHONE_STR_EQUALS, value);
		} else if (Constants.STR_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMPHONE_STR_LIKE, value);
		} else if (Constants.STR_NOT_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMPHONE_STR_NOT_LIKE,
					value);
		} else if (Constants.STR_NOT_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMPHONE_STR_NOT_EQUALS,
					value);
		}
	}

	public void addComContactNameCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.STR_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMCONTACTNAME_STR_EQUALS,
					value);
		} else if (Constants.STR_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMCONTACTNAME_STR_LIKE,
					value);
		} else if (Constants.STR_NOT_LIKE.equals(condition)) {
			criterias.put(
					InsuredCompanySearchFields.COMCONTACTNAME_STR_NOT_LIKE,
					value);
		} else if (Constants.STR_NOT_EQUALS.equals(condition)) {
			criterias.put(
					InsuredCompanySearchFields.COMCONTACTNAME_STR_NOT_EQUALS,
					value);
		}
	}

	public void addComContactPhoneCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.STR_EQUALS.equals(condition)) {
			criterias.put(
					InsuredCompanySearchFields.COMCONTACTPHONE_STR_EQUALS,
					value);
		} else if (Constants.STR_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMCONTACTPHONE_STR_LIKE,
					value);
		} else if (Constants.STR_NOT_LIKE.equals(condition)) {
			criterias.put(
					InsuredCompanySearchFields.COMCONTACTPHONE_STR_NOT_LIKE,
					value);
		} else if (Constants.STR_NOT_EQUALS.equals(condition)) {
			criterias.put(
					InsuredCompanySearchFields.COMCONTACTPHONE_STR_NOT_EQUALS,
					value);
		}
	}

	public void addOwnerCompanyCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.COM_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.OWNERCOMPANY_COM_EQUALS,
					value);
		} else if (Constants.COM_NOT_EQUALS.equals(condition)) {
			criterias.put(
					InsuredCompanySearchFields.OWNERCOMPANY_COM_NOT_EQUALS,
					value);
		}
	}

	public void addComEmailCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.STR_EQUALS.equals(condition)) {
			criterias
					.put(InsuredCompanySearchFields.COMEMAIL_STR_EQUALS, value);
		} else if (Constants.STR_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMEMAIL_STR_LIKE, value);
		} else if (Constants.STR_NOT_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMEMAIL_STR_NOT_LIKE,
					value);
		} else if (Constants.STR_NOT_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMEMAIL_STR_NOT_EQUALS,
					value);
		}
	}

	public void addComAddressCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.STR_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMADDRESS_STR_EQUALS,
					value);
		} else if (Constants.STR_LIKE.equals(condition)) {
			criterias
					.put(InsuredCompanySearchFields.COMADDRESS_STR_LIKE, value);
		} else if (Constants.STR_NOT_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMADDRESS_STR_NOT_LIKE,
					value);
		} else if (Constants.STR_NOT_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMADDRESS_STR_NOT_EQUALS,
					value);
		}
	}

	public void addComRemarkCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.STR_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMREMARK_STR_EQUALS,
					value);
		} else if (Constants.STR_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMREMARK_STR_LIKE, value);
		} else if (Constants.STR_NOT_LIKE.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMREMARK_STR_NOT_LIKE,
					value);
		} else if (Constants.STR_NOT_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.COMREMARK_STR_NOT_EQUALS,
					value);
		}
	}

	public void addCreateUserCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.COM_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.CREATEUSER_COM_EQUALS,
					value);
		} else if (Constants.COM_NOT_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.CREATEUSER_COM_NOT_EQUALS,
					value);
		}
	}

	public void addCreateTimeCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.DATE_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.CREATETIME_DATE_EQUALS,
					value);
		} else if (Constants.DATE_NOT_LATTER.equals(condition)) {
			criterias.put(
					InsuredCompanySearchFields.CREATETIME_DATE_NOT_LATTER,
					value);
		} else if (Constants.DATE_NOT_EARLY.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.CREATETIME_DATE_NOT_EARLY,
					value);
		} else if (Constants.DATE_LATTER.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.CREATETIME_DATE_LATTER,
					value);
		} else if (Constants.DATE_EARLY.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.CREATETIME_DATE_EARLY,
					value);
		}
	}

	public void addUpdateUserCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.COM_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.UPDATEUSER_COM_EQUALS,
					value);
		} else if (Constants.COM_NOT_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.UPDATEUSER_COM_NOT_EQUALS,
					value);
		}
	}

	public void addUpdateTimeCondition(
			Map<InsuredCompanySearchFields, Object> criterias,
			String condition, String value) {
		if ("-1".equals(condition))
			return;
		if (Constants.DATE_EQUALS.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.UPDATETIME_DATE_EQUALS,
					value);
		} else if (Constants.DATE_NOT_LATTER.equals(condition)) {
			criterias.put(
					InsuredCompanySearchFields.UPDATETIME_DATE_NOT_LATTER,
					value);
		} else if (Constants.DATE_NOT_EARLY.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.UPDATETIME_DATE_NOT_EARLY,
					value);
		} else if (Constants.DATE_LATTER.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.UPDATETIME_DATE_LATTER,
					value);
		} else if (Constants.DATE_EARLY.equals(condition)) {
			criterias.put(InsuredCompanySearchFields.UPDATETIME_DATE_EARLY,
					value);
		}
	}

	public InsuredCompany getVo() {
		return vo;
	}

	public void setVo(InsuredCompany vo) {
		this.vo = vo;
	}

	private Integer sno;

	/**
	 * 获取流水号的属性值.
	 */
	public Integer getSno() {
		return sno;
	}

	/**
	 * 设置流水号的属性值.
	 */
	public void setSno(Integer sno) {
		this.sno = sno;
	}

	private String comName;

	/**
	 * 获取保险公司名称的属性值.
	 */
	public String getComName() {
		return comName;
	}

	/**
	 * 设置保险公司名称的属性值.
	 */
	public void setComName(String comname) {
		this.comName = comname;
	}

	private String comNo;

	/**
	 * 获取保险公司编号 的属性值.
	 */
	public String getComNo() {
		return comNo;
	}

	/**
	 * 设置保险公司编号 的属性值.
	 */
	public void setComNo(String comno) {
		this.comNo = comno;
	}

	private String comStatus;

	/**
	 * 获取状态 的属性值.
	 */
	public String getComStatus() {
		return comStatus;
	}

	/**
	 * 设置状态 的属性值.
	 */
	public void setComStatus(String comstatus) {
		this.comStatus = comstatus;
	}

	private String comShortName;

	/**
	 * 获取简称的属性值.
	 */
	public String getComShortName() {
		return comShortName;
	}

	/**
	 * 设置简称的属性值.
	 */
	public void setComShortName(String comshortname) {
		this.comShortName = comshortname;
	}

	private String comPhone;

	/**
	 * 获取电话的属性值.
	 */
	public String getComPhone() {
		return comPhone;
	}

	/**
	 * 设置电话的属性值.
	 */
	public void setComPhone(String comphone) {
		this.comPhone = comphone;
	}

	private String comContactName;

	/**
	 * 获取联系人名称的属性值.
	 */
	public String getComContactName() {
		return comContactName;
	}

	/**
	 * 设置联系人名称的属性值.
	 */
	public void setComContactName(String comcontactname) {
		this.comContactName = comcontactname;
	}

	private String comContactPhone;

	/**
	 * 获取联系人手机的属性值.
	 */
	public String getComContactPhone() {
		return comContactPhone;
	}

	/**
	 * 设置联系人手机的属性值.
	 */
	public void setComContactPhone(String comcontactphone) {
		this.comContactPhone = comcontactphone;
	}

	private String ownerCompany;

	/**
	 * 获取所属保险公司的属性值.
	 */
	public String getOwnerCompany() {
		return ownerCompany;
	}

	/**
	 * 设置所属保险公司的属性值.
	 */
	public void setOwnerCompany(String ownercompany) {
		this.ownerCompany = ownercompany;
	}

	private String comEmail;

	/**
	 * 获取邮箱的属性值.
	 */
	public String getComEmail() {
		return comEmail;
	}

	/**
	 * 设置邮箱的属性值.
	 */
	public void setComEmail(String comemail) {
		this.comEmail = comemail;
	}

	private String comAddress;

	/**
	 * 获取地址的属性值.
	 */
	public String getComAddress() {
		return comAddress;
	}

	/**
	 * 设置地址的属性值.
	 */
	public void setComAddress(String comaddress) {
		this.comAddress = comaddress;
	}

	private String comRemark;

	/**
	 * 获取备注的属性值.
	 */
	public String getComRemark() {
		return comRemark;
	}

	/**
	 * 设置备注的属性值.
	 */
	public void setComRemark(String comremark) {
		this.comRemark = comremark;
	}

	private int createUser;

	/**
	 * 获取创建用户的属性值.
	 */
	public int getCreateUser() {
		return createUser;
	}

	/**
	 * 设置创建用户的属性值.
	 */
	public void setCreateUser(int createuser) {
		this.createUser = createuser;
	}

	private String createTime;

	/**
	 * 获取创建时间的属性值.
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间的属性值.
	 */
	public void setCreateTime(String createtime) {
		this.createTime = createtime;
	}

	private int updateUser;

	/**
	 * 获取更新用户的属性值.
	 */
	public int getUpdateUser() {
		return updateUser;
	}

	/**
	 * 设置更新用户的属性值.
	 */
	public void setUpdateUser(int updateuser) {
		this.updateUser = updateuser;
	}

	private String updateTime;

	/**
	 * 获取更新时间的属性值.
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间的属性值.
	 */
	public void setUpdateTime(String updatetime) {
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

	/************* 下面自动生成高级查询相关代码 ********************/
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
	
	private String condition_comName;
	
	public String getCondition_comName(){
		return this.condition_comName;
	}
	
	public void setCondition_comName(String s){
		this.condition_comName = s;
	}
	
	private String query_comName;
		
	public String getQuery_comName(){
		return this.query_comName;
	}
	
	public void setQuery_comName(String s){
		this.query_comName = s;
	}
	
	private String condition_comNo;
	
	public String getCondition_comNo(){
		return this.condition_comNo;
	}
	
	public void setCondition_comNo(String s){
		this.condition_comNo = s;
	}
	
	private String query_comNo;
		
	public String getQuery_comNo(){
		return this.query_comNo;
	}
	
	public void setQuery_comNo(String s){
		this.query_comNo = s;
	}
	
	private String condition_comStatus;
	
	public String getCondition_comStatus(){
		return this.condition_comStatus;
	}
	
	public void setCondition_comStatus(String s){
		this.condition_comStatus = s;
	}
	
	private String query_comStatus;
		
	public String getQuery_comStatus(){
		return this.query_comStatus;
	}
	
	public void setQuery_comStatus(String s){
		this.query_comStatus = s;
	}
	
	private String condition_comShortName;
	
	public String getCondition_comShortName(){
		return this.condition_comShortName;
	}
	
	public void setCondition_comShortName(String s){
		this.condition_comShortName = s;
	}
	
	private String query_comShortName;
		
	public String getQuery_comShortName(){
		return this.query_comShortName;
	}
	
	public void setQuery_comShortName(String s){
		this.query_comShortName = s;
	}
	
	private String condition_comPhone;
	
	public String getCondition_comPhone(){
		return this.condition_comPhone;
	}
	
	public void setCondition_comPhone(String s){
		this.condition_comPhone = s;
	}
	
	private String query_comPhone;
		
	public String getQuery_comPhone(){
		return this.query_comPhone;
	}
	
	public void setQuery_comPhone(String s){
		this.query_comPhone = s;
	}
	
	private String condition_comContactName;
	
	public String getCondition_comContactName(){
		return this.condition_comContactName;
	}
	
	public void setCondition_comContactName(String s){
		this.condition_comContactName = s;
	}
	
	private String query_comContactName;
		
	public String getQuery_comContactName(){
		return this.query_comContactName;
	}
	
	public void setQuery_comContactName(String s){
		this.query_comContactName = s;
	}
	
	private String condition_comContactPhone;
	
	public String getCondition_comContactPhone(){
		return this.condition_comContactPhone;
	}
	
	public void setCondition_comContactPhone(String s){
		this.condition_comContactPhone = s;
	}
	
	private String query_comContactPhone;
		
	public String getQuery_comContactPhone(){
		return this.query_comContactPhone;
	}
	
	public void setQuery_comContactPhone(String s){
		this.query_comContactPhone = s;
	}
	
	private String condition_ownerCompany;
	
	public String getCondition_ownerCompany(){
		return this.condition_ownerCompany;
	}
	
	public void setCondition_ownerCompany(String s){
		this.condition_ownerCompany = s;
	}
	
	private String query_ownerCompany;
		
	public String getQuery_ownerCompany(){
		return this.query_ownerCompany;
	}
	
	public void setQuery_ownerCompany(String s){
		this.query_ownerCompany = s;
	}
	
	private String condition_comEmail;
	
	public String getCondition_comEmail(){
		return this.condition_comEmail;
	}
	
	public void setCondition_comEmail(String s){
		this.condition_comEmail = s;
	}
	
	private String query_comEmail;
		
	public String getQuery_comEmail(){
		return this.query_comEmail;
	}
	
	public void setQuery_comEmail(String s){
		this.query_comEmail = s;
	}
	
	private String condition_comAddress;
	
	public String getCondition_comAddress(){
		return this.condition_comAddress;
	}
	
	public void setCondition_comAddress(String s){
		this.condition_comAddress = s;
	}
	
	private String query_comAddress;
		
	public String getQuery_comAddress(){
		return this.query_comAddress;
	}
	
	public void setQuery_comAddress(String s){
		this.query_comAddress = s;
	}
	
	private String condition_comRemark;
	
	public String getCondition_comRemark(){
		return this.condition_comRemark;
	}
	
	public void setCondition_comRemark(String s){
		this.condition_comRemark = s;
	}
	
	private String query_comRemark;
		
	public String getQuery_comRemark(){
		return this.query_comRemark;
	}
	
	public void setQuery_comRemark(String s){
		this.query_comRemark = s;
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
	
	private String condition1_createTime;
	
	public String getCondition1_createTime(){
		return this.condition1_createTime;
	}
	
	public void setCondition1_createTime(String s){
		this.condition1_createTime = s;
	}
	
	private String condition2_createTime;
	
	public String getCondition2_createTime(){
		return this.condition2_createTime;
	}
	
	public void setCondition2_createTime(String s){
		this.condition2_createTime = s;
	}
	
	private String query1_createTime;
	
	public String getQuery1_createTime(){
		return this.query1_createTime;
	}
	
	public void setQuery1_createTime(String s){
		this.query1_createTime = s;
	}
	
	private String query2_createTime;
		
	public String getQuery2_createTime(){
		return this.query2_createTime;
	}
	
	public void setQuery2_createTime(String s){
		this.query2_createTime = s;
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
	
	private String condition1_updateTime;
	
	public String getCondition1_updateTime(){
		return this.condition1_updateTime;
	}
	
	public void setCondition1_updateTime(String s){
		this.condition1_updateTime = s;
	}
	
	private String condition2_updateTime;
	
	public String getCondition2_updateTime(){
		return this.condition2_updateTime;
	}
	
	public void setCondition2_updateTime(String s){
		this.condition2_updateTime = s;
	}
	
	private String query1_updateTime;
	
	public String getQuery1_updateTime(){
		return this.query1_updateTime;
	}
	
	public void setQuery1_updateTime(String s){
		this.query1_updateTime = s;
	}
	
	private String query2_updateTime;
		
	public String getQuery2_updateTime(){
		return this.query2_updateTime;
	}
	
	public void setQuery2_updateTime(String s){
		this.query2_updateTime = s;
	}

}
