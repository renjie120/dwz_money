
package ido.InsuredCompany;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.MyJdbcTool;
import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.cache.Cache;
import common.cache.CacheManager;
import common.util.NPOIReader;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于保险公司的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredCompanyManagerImpl extends AbstractBusinessObjectManager implements
		InsuredCompanyManager {

	private InsuredCompanyDao insuredcompanydao = null;

	/**
	 * 构造函数.
	 */
	public InsuredCompanyManagerImpl(InsuredCompanyDao insuredcompanydao) {
		this.insuredcompanydao = insuredcompanydao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchInsuredCompanyNum(Map<InsuredCompanySearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.insuredcompanydao.countByQuery(hql,
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
				InsuredCompanyVO vo = new InsuredCompanyVO();
				//导入保险公司名称
				String comNameStr = contents[i][0];
				vo.setComName(comNameStr);
				
				//导入保险公司编号 
				String comNoStr = contents[i][1];
				vo.setComNo(comNoStr);
				
				//导入状态 
				String comStatusStr = contents[i][2];
				vo.setComStatus(comStatusStr);
				
				//导入简称
				String comShortNameStr = contents[i][3];
				vo.setComShortName(comShortNameStr);
				
				//导入电话
				String comPhoneStr = contents[i][4];
				vo.setComPhone(comPhoneStr);
				
				//导入联系人名称
				String comContactNameStr = contents[i][5];
				vo.setComContactName(comContactNameStr);
				
				//导入联系人手机
				String comContactPhoneStr = contents[i][6];
				vo.setComContactPhone(comContactPhoneStr);
				
				//导入所属保险公司
				String ownerCompanyStr = contents[i][7];
				vo.setOwnerCompany(ownerCompanyStr);
				
				//导入邮箱
				String comEmailStr = contents[i][8];
				vo.setComEmail(comEmailStr);
				
				this.insuredcompanydao.insert(vo); 
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
	public Collection<InsuredCompany> searchInsuredCompany(Map<InsuredCompanySearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<InsuredCompany> eaList = new ArrayList<InsuredCompany>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<InsuredCompanyVO> voList = this.insuredcompanydao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_yesorno_status = allSelect
				.getParamsByType(AllSelectContants.YESORNO_STATUS.getName());
		Cache cache_ownerCompany = CacheManager.getCacheInfoNotNull(AllSelectContants.INSUREDCOMPANY_DICT.getName());
		ParamSelect select_ownerCompany = (ParamSelect)cache_ownerCompany.getValue();
		
		for (InsuredCompanyVO po : voList) {
			po.setComStatus(select_yesorno_status.getName("" + po.getComStatus())); 
			po.setOwnerCompany(select_ownerCompany.getName("" + po.getOwnerCompany())); 
			eaList.add(new  InsuredCompanyImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<InsuredCompanySearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( insuredcompany) "
						: "select  insuredcompany ").append("from InsuredCompanyVO as insuredcompany ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<InsuredCompanySearchFields, Object> entry : criterias
					.entrySet()) {
				InsuredCompanySearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.sno = ? ");
						
						argList.add(entry.getValue());
						count++;
					break; 
					case COMNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case COMNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comNo like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case COMSTATUS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comStatus = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMSHORTNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comShortName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMPHONE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comPhone = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMCONTACTNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comContactName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMCONTACTPHONE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comContactPhone = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OWNERCOMPANY:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.ownerCompany = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMEMAIL:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comEmail = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMADDRESS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comAddress = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMREMARK:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comRemark = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.updateTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					//下面拼接高级查询条件
					case COMNAME_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comName  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMNAME_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comName like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMNAME_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comName not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMNAME_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comName !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMNO_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comNo  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMNO_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comNo like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMNO_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comNo not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMNO_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comNo !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMSTATUS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comStatus  not in (  "); 
						String _temp_ComStatus1 = ""+entry.getValue();
						String[] _temp_arr_ComStatus1 = _temp_ComStatus1.split(",");
						int _int_ComStatus1 = _temp_arr_ComStatus1.length;
						for(int _i=0;_i<_int_ComStatus1;_i++){
							sb.append(" ? ,");
							argList.add(_temp_arr_ComStatus1[_i]); 
						}
						sb = sb.deleteCharAt(sb.length()-1); 
						sb.append(" ) ");
						count++;
					break;
					case COMSTATUS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comStatus in  (   "); 
						String _temp_ComStatus2 = ""+entry.getValue();
						String[] _temp_arr_ComStatus2 = _temp_ComStatus2.split(",");
						int _int_ComStatus2 = _temp_arr_ComStatus2.length;
						for(int _i=0;_i<_int_ComStatus2;_i++){
							sb.append(" ? ,");
							argList.add(_temp_arr_ComStatus2[_i]); 
						}
						sb = sb.deleteCharAt(sb.length()-1); 
						sb.append(" ) ");
						count++;
					break;
					case COMSHORTNAME_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comShortName  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMSHORTNAME_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comShortName like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMSHORTNAME_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comShortName not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMSHORTNAME_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comShortName !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMPHONE_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comPhone  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMPHONE_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comPhone like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMPHONE_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comPhone not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMPHONE_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comPhone !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMCONTACTNAME_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comContactName  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMCONTACTNAME_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comContactName like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMCONTACTNAME_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comContactName not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMCONTACTNAME_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comContactName !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMCONTACTPHONE_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comContactPhone  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMCONTACTPHONE_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comContactPhone like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMCONTACTPHONE_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comContactPhone not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMCONTACTPHONE_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comContactPhone !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OWNERCOMPANY_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.ownerCompany  not in (  "); 
						String _temp_OwnerCompany1 = ""+entry.getValue();
						String[] _temp_arr_OwnerCompany1 = _temp_OwnerCompany1.split(",");
						int _int_OwnerCompany1 = _temp_arr_OwnerCompany1.length;
						for(int _i=0;_i<_int_OwnerCompany1;_i++){
							sb.append(" ? ,");
							argList.add(_temp_arr_OwnerCompany1[_i]); 
						}
						sb = sb.deleteCharAt(sb.length()-1); 
						sb.append(" ) ");
						count++;
					break;
					case OWNERCOMPANY_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.ownerCompany in  (   "); 
						String _temp_OwnerCompany2 = ""+entry.getValue();
						String[] _temp_arr_OwnerCompany2 = _temp_OwnerCompany2.split(",");
						int _int_OwnerCompany2 = _temp_arr_OwnerCompany2.length;
						for(int _i=0;_i<_int_OwnerCompany2;_i++){
							sb.append(" ? ,");
							argList.add(_temp_arr_OwnerCompany2[_i]); 
						}
						sb = sb.deleteCharAt(sb.length()-1); 
						sb.append(" ) ");
						count++;
					break;
					case COMEMAIL_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comEmail  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMEMAIL_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comEmail like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMEMAIL_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comEmail not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMEMAIL_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comEmail !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMADDRESS_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comAddress  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMADDRESS_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comAddress like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMADDRESS_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comAddress not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMADDRESS_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comAddress !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMREMARK_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comRemark  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMREMARK_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comRemark like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMREMARK_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comRemark not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case COMREMARK_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.comRemark !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_DATE_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.createTime =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_DATE_NOT_LATTER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.createTime <=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_DATE_NOT_EARLY:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.createTime >=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_DATE_LATTER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.createTime  > ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_DATE_EARLY:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.createTime <  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.updateUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.updateUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATETIME_DATE_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.updateTime =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_DATE_NOT_LATTER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.updateTime <=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_DATE_NOT_EARLY:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.updateTime >=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_DATE_LATTER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.updateTime  > ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_DATE_EARLY:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompany.updateTime <  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		InsuredCompanyOrderByFields orderBy = InsuredCompanyOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = InsuredCompanyOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by insuredcompany.sno");
			break;
			case COMNAME:
				 sb.append(" order by insuredcompany.comName");
			break;
			case COMNO:
				 sb.append(" order by insuredcompany.comNo");
			break;
			case COMSTATUS:
				 sb.append(" order by insuredcompany.comStatus");
			break;
			case COMSHORTNAME:
				 sb.append(" order by insuredcompany.comShortName");
			break;
			case COMPHONE:
				 sb.append(" order by insuredcompany.comPhone");
			break;
			case COMCONTACTNAME:
				 sb.append(" order by insuredcompany.comContactName");
			break;
			case COMCONTACTPHONE:
				 sb.append(" order by insuredcompany.comContactPhone");
			break;
			case OWNERCOMPANY:
				 sb.append(" order by insuredcompany.ownerCompany");
			break;
			case COMEMAIL:
				 sb.append(" order by insuredcompany.comEmail");
			break;
			case COMADDRESS:
				 sb.append(" order by insuredcompany.comAddress");
			break;
			case COMREMARK:
				 sb.append(" order by insuredcompany.comRemark");
			break;
			case CREATEUSER:
				 sb.append(" order by insuredcompany.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by insuredcompany.createTime");
			break;
			case UPDATEUSER:
				 sb.append(" order by insuredcompany.updateUser");
			break;
			case UPDATETIME:
				 sb.append(" order by insuredcompany.updateTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createInsuredCompany(InsuredCompany insuredcompany) throws ValidateFieldsException {
		InsuredCompanyImpl insuredcompanyImpl = (InsuredCompanyImpl) insuredcompany;
		this.insuredcompanydao.insert(insuredcompanyImpl.getInsuredCompanyVO());
	}

	public void removeInsuredCompanys(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			InsuredCompanyVO vo = this.insuredcompanydao.findByPrimaryKey(Integer.parseInt(s));
			this.insuredcompanydao.delete(vo);
		}
	}

	public void updateInsuredCompany(InsuredCompany insuredcompany) throws ValidateFieldsException {
		InsuredCompanyImpl insuredcompanyImpl = (InsuredCompanyImpl) insuredcompany;

		InsuredCompanyVO po = insuredcompanyImpl.getInsuredCompanyVO();
		this.insuredcompanydao.update(po);
	}

	public InsuredCompany getInsuredCompany(int id) {
		Collection<InsuredCompanyVO> insuredcompanys = this.insuredcompanydao.findRecordById(id);

		if (insuredcompanys == null || insuredcompanys.size() < 1)
			return null;

		InsuredCompanyVO insuredcompany = insuredcompanys.toArray(new InsuredCompanyVO[insuredcompanys.size()])[0];

		return new InsuredCompanyImpl(insuredcompany);
	}

	@Override
	public void zhuxiaoInsuredCompanys(String sno) { 
		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		jdbcTool.exeSql("update insured_company set com_status = 70 where id in ("+sno+")");
	}

}
