
package ido.InsuredFile;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.cache.Cache;
import common.cache.CacheManager;
import common.cache.CacheUtil;
import common.util.NPOIReader;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于投保单的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredFileManagerImpl extends AbstractBusinessObjectManager implements
		InsuredFileManager {

	private InsuredFileDao insuredfiledao = null;

	/**
	 * 构造函数.
	 */
	public InsuredFileManagerImpl(InsuredFileDao insuredfiledao) {
		this.insuredfiledao = insuredfiledao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchInsuredFileNum(Map<InsuredFileSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.insuredfiledao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}


	public void importFromExcel(File file) {
		NPOIReader excel = null;
		try {
			excel = new NPOIReader(file);
			int index = excel.getSheetNames().indexOf("Sheet0");
			String[][] contents = excel.read(index, true, true);
			for (int i = 1; i < contents.length; i++) {
				InsuredFileVO vo = new InsuredFileVO();
				//导入投保单号 
				String insuredFileIdStr = contents[i][0];
				vo.setInsuredFileId(insuredFileIdStr);
				
				//导入保单名称
				String insuredFileNameStr = contents[i][1];
				vo.setInsuredFileName(insuredFileNameStr);
				
				//导入投保单位
				String insuredFileUnitStr = contents[i][2];
				vo.setInsuredFileUnit(insuredFileUnitStr);
				
				//导入保险公司
				String insuredFileCompanyStr = contents[i][3];
				vo.setInsuredFileCompany(insuredFileCompanyStr);
				
				//导入邮箱
				String insuredFileEmailStr = contents[i][4];
				vo.setInsuredFileEmail(insuredFileEmailStr);
				
				//导入联系人
				String insuredFileContactStr = contents[i][5];
				vo.setInsuredFileContact(insuredFileContactStr);
				
				//导入联系电话
				String insuredFileConTelStr = contents[i][6];
				vo.setInsuredFileConTel(insuredFileConTelStr);
				
				//导入联系人手机
				String insuredFileConMobileStr = contents[i][7];
				vo.setInsuredFileConMobile(insuredFileConMobileStr);
				
				this.insuredfiledao.insert(vo); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<InsuredFile> searchInsuredFile(Map<InsuredFileSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<InsuredFile> eaList = new ArrayList<InsuredFile>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<InsuredFileVO> voList = this.insuredfiledao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		Cache cache_insuredFileUnit = CacheManager.getCacheInfoNotNull(AllSelectContants.INSUREDUNIT_DICT.getName());
		ParamSelect select_insuredFileUnit = (ParamSelect)cache_insuredFileUnit.getValue();
		Cache cache_insuredFileCompany = CacheManager.getCacheInfoNotNull(AllSelectContants.INSURED_COM_DICT.getName());
		ParamSelect select_insuredFileCompany = (ParamSelect)cache_insuredFileCompany.getValue();
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_insureFile_state = allSelect
				.getParamsByType(AllSelectContants.INSUREFILE_STATE.getName());
		 ParamSelect select_sys_duijie = allSelect
				.getParamsByType(AllSelectContants.SYS_DUIJIE.getName());
		 ParamSelect select_yesorno = allSelect
				.getParamsByType(AllSelectContants.YESORNO.getName());
		
		for (InsuredFileVO po : voList) {
			po.setInsuredFileUnit(select_insuredFileUnit.getName("" + po.getInsuredFileUnit())); 
			po.setInsuredFileCompany(select_insuredFileCompany.getName("" + po.getInsuredFileCompany())); 
			po.setInsuredFileStatus(select_insureFile_state.getName("" + po.getInsuredFileStatus())); 
			po.setInsuredFileDuijie(select_sys_duijie.getName("" + po.getInsuredFileDuijie())); 
			po.setInsuredFileDuijieFlag(select_yesorno.getName("" + po.getInsuredFileDuijieFlag()));
			po.setCreateUserName(CacheUtil.getSystemUserName(""+po.getCreateUser()));
			po.setUpdateUserName(CacheUtil.getSystemUserName(""+po.getUpdateUser()));
			eaList.add(new  InsuredFileImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<InsuredFileSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( insuredfile) "
						: "select  insuredfile ").append("from InsuredFileVO as insuredfile ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<InsuredFileSearchFields, Object> entry : criterias
					.entrySet()) {
				InsuredFileSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILEID:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILENAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case INSUREDFILEUNIT:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileUnit = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILECOMPANY:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileCompany = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILEEMAIL:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileEmail = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILECONTACT:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileContact = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILECONTEL:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConTel = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILECONMOBILE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConMobile = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILEBEGIN:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileBegin = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILEEND:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileEnd = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILESTATUS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileStatus = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILEDUIJIE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileDuijie = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILEDUIJIEFLAG:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileDuijieFlag = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILEREMARK:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileRemark = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILETOTAL:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileTotal = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILEEMERG:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileEmerg = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILEHOSPITAL:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileHospital = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILEEXAM:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileExam = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILECONSUMER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConsumer = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILECONSRULE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConsRule = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.updateTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case INSUREDFILEID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILENAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILENAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILEUNIT_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileUnit  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEUNIT_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileUnit =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILECOMPANY_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileCompany  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILECOMPANY_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileCompany =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILEEMAIL_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileEmail  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEEMAIL_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileEmail =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILECONTACT_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileContact  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILECONTACT_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileContact =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILECONTEL_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConTel  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILECONTEL_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConTel =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILECONMOBILE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConMobile  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILECONMOBILE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConMobile =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILEBEGIN_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileBegin  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEBEGIN_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileBegin =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILEEND_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileEnd  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEEND_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileEnd =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILESTATUS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileStatus  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILESTATUS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileStatus =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILEDUIJIE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileDuijie  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEDUIJIE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileDuijie =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILEDUIJIEFLAG_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileDuijieFlag  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEDUIJIEFLAG_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileDuijieFlag =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILEREMARK_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileRemark  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEREMARK_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileRemark =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILETOTAL_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileTotal  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILETOTAL_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileTotal =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILEEMERG_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileEmerg  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEEMERG_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileEmerg =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILEHOSPITAL_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileHospital  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEHOSPITAL_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileHospital =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILEEXAM_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileExam  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEEXAM_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileExam =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILECONSUMER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConsumer  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILECONSUMER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConsumer =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILECONSRULE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConsRule  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILECONSRULE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.insuredFileConsRule =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.createTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.updateUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.updateUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.updateTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredfile.updateTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		InsuredFileOrderByFields orderBy = InsuredFileOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = InsuredFileOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case SNO:
			 sb.append(" order by insuredfile.sno");
		break;
		case SNO_DESC:
			 sb.append(" order by insuredfile.sno desc");
		break;
		case INSUREDFILEID:
			 sb.append(" order by insuredfile.insuredFileId");
		break;
		case INSUREDFILEID_DESC:
			 sb.append(" order by insuredfile.insuredFileId desc");
		break;
		case INSUREDFILENAME:
			 sb.append(" order by insuredfile.insuredFileName");
		break;
		case INSUREDFILENAME_DESC:
			 sb.append(" order by insuredfile.insuredFileName desc");
		break;
		case INSUREDFILEUNIT:
			 sb.append(" order by insuredfile.insuredFileUnit");
		break;
		case INSUREDFILEUNIT_DESC:
			 sb.append(" order by insuredfile.insuredFileUnit desc");
		break;
		case INSUREDFILECOMPANY:
			 sb.append(" order by insuredfile.insuredFileCompany");
		break;
		case INSUREDFILECOMPANY_DESC:
			 sb.append(" order by insuredfile.insuredFileCompany desc");
		break;
		case INSUREDFILEEMAIL:
			 sb.append(" order by insuredfile.insuredFileEmail");
		break;
		case INSUREDFILEEMAIL_DESC:
			 sb.append(" order by insuredfile.insuredFileEmail desc");
		break;
		case INSUREDFILECONTACT:
			 sb.append(" order by insuredfile.insuredFileContact");
		break;
		case INSUREDFILECONTACT_DESC:
			 sb.append(" order by insuredfile.insuredFileContact desc");
		break;
		case INSUREDFILECONTEL:
			 sb.append(" order by insuredfile.insuredFileConTel");
		break;
		case INSUREDFILECONTEL_DESC:
			 sb.append(" order by insuredfile.insuredFileConTel desc");
		break;
		case INSUREDFILECONMOBILE:
			 sb.append(" order by insuredfile.insuredFileConMobile");
		break;
		case INSUREDFILECONMOBILE_DESC:
			 sb.append(" order by insuredfile.insuredFileConMobile desc");
		break;
		case INSUREDFILEBEGIN:
			 sb.append(" order by insuredfile.insuredFileBegin");
		break;
		case INSUREDFILEBEGIN_DESC:
			 sb.append(" order by insuredfile.insuredFileBegin desc");
		break;
		case INSUREDFILEEND:
			 sb.append(" order by insuredfile.insuredFileEnd");
		break;
		case INSUREDFILEEND_DESC:
			 sb.append(" order by insuredfile.insuredFileEnd desc");
		break;
		case INSUREDFILESTATUS:
			 sb.append(" order by insuredfile.insuredFileStatus");
		break;
		case INSUREDFILESTATUS_DESC:
			 sb.append(" order by insuredfile.insuredFileStatus desc");
		break;
		case INSUREDFILEDUIJIE:
			 sb.append(" order by insuredfile.insuredFileDuijie");
		break;
		case INSUREDFILEDUIJIE_DESC:
			 sb.append(" order by insuredfile.insuredFileDuijie desc");
		break;
		case INSUREDFILEDUIJIEFLAG:
			 sb.append(" order by insuredfile.insuredFileDuijieFlag");
		break;
		case INSUREDFILEDUIJIEFLAG_DESC:
			 sb.append(" order by insuredfile.insuredFileDuijieFlag desc");
		break;
		case INSUREDFILEREMARK:
			 sb.append(" order by insuredfile.insuredFileRemark");
		break;
		case INSUREDFILEREMARK_DESC:
			 sb.append(" order by insuredfile.insuredFileRemark desc");
		break;
		case INSUREDFILETOTAL:
			 sb.append(" order by insuredfile.insuredFileTotal");
		break;
		case INSUREDFILETOTAL_DESC:
			 sb.append(" order by insuredfile.insuredFileTotal desc");
		break;
		case INSUREDFILEEMERG:
			 sb.append(" order by insuredfile.insuredFileEmerg");
		break;
		case INSUREDFILEEMERG_DESC:
			 sb.append(" order by insuredfile.insuredFileEmerg desc");
		break;
		case INSUREDFILEHOSPITAL:
			 sb.append(" order by insuredfile.insuredFileHospital");
		break;
		case INSUREDFILEHOSPITAL_DESC:
			 sb.append(" order by insuredfile.insuredFileHospital desc");
		break;
		case INSUREDFILEEXAM:
			 sb.append(" order by insuredfile.insuredFileExam");
		break;
		case INSUREDFILEEXAM_DESC:
			 sb.append(" order by insuredfile.insuredFileExam desc");
		break;
		case INSUREDFILECONSUMER:
			 sb.append(" order by insuredfile.insuredFileConsumer");
		break;
		case INSUREDFILECONSUMER_DESC:
			 sb.append(" order by insuredfile.insuredFileConsumer desc");
		break;
		case INSUREDFILECONSRULE:
			 sb.append(" order by insuredfile.insuredFileConsRule");
		break;
		case INSUREDFILECONSRULE_DESC:
			 sb.append(" order by insuredfile.insuredFileConsRule desc");
		break;
		case CREATEUSER:
			 sb.append(" order by insuredfile.createUser");
		break;
		case CREATEUSER_DESC:
			 sb.append(" order by insuredfile.createUser desc");
		break;
		case CREATETIME:
			 sb.append(" order by insuredfile.createTime");
		break;
		case CREATETIME_DESC:
			 sb.append(" order by insuredfile.createTime desc");
		break;
		case UPDATEUSER:
			 sb.append(" order by insuredfile.updateUser");
		break;
		case UPDATEUSER_DESC:
			 sb.append(" order by insuredfile.updateUser desc");
		break;
		case UPDATETIME:
			 sb.append(" order by insuredfile.updateTime");
		break;
		case UPDATETIME_DESC:
			 sb.append(" order by insuredfile.updateTime desc");
		break;
		default:
			break;
	}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createInsuredFile(InsuredFile insuredfile) throws ValidateFieldsException {
		InsuredFileImpl insuredfileImpl = (InsuredFileImpl) insuredfile;
		this.insuredfiledao.insert(insuredfileImpl.getInsuredFileVO());
	}

	public void removeInsuredFiles(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			InsuredFileVO vo = this.insuredfiledao.findByPrimaryKey(Integer.parseInt(s));
			this.insuredfiledao.delete(vo);
		}
	}

	public void updateInsuredFile(InsuredFile insuredfile) throws ValidateFieldsException {
		InsuredFileImpl insuredfileImpl = (InsuredFileImpl) insuredfile;

		InsuredFileVO po = insuredfileImpl.getInsuredFileVO();
		this.insuredfiledao.update(po);
	}

	public InsuredFile getInsuredFile(int id) {
		Collection<InsuredFileVO> insuredfiles = this.insuredfiledao.findRecordById(id);

		if (insuredfiles == null || insuredfiles.size() < 1)
			return null;

		InsuredFileVO insuredfile = insuredfiles.toArray(new InsuredFileVO[insuredfiles.size()])[0];
		insuredfile.setInsuredFileUnitName(CacheUtil.getUnitName(insuredfile.getInsuredFileUnit()));
		return new InsuredFileImpl(insuredfile);
	}

	@Override
	public boolean existed(final String table,final String column,final String value) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
			    .getBean("jdbcTemplate");
		return jdbcTemplate.execute(new ConnectionCallback<Boolean>() { 
			@Override
			public Boolean doInConnection(java.sql.Connection conn)
					throws SQLException, DataAccessException {
				  String sql = "select count(1)  from  "+table +" where  "+column+" =?";
			    PreparedStatement ps = conn.prepareStatement(sql);
			    ps.setString(1, value);
			    ResultSet rs = ps.executeQuery();
			    boolean result = false;
				if(rs.next()){
					int ans = rs.getInt(1);
					if(ans>0)
						result =true;
				} 
				rs.close();
				return result;
			}
		}); 
	}

}
