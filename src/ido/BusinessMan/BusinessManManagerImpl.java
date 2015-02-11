
package ido.BusinessMan;
import java.io.File;
import java.sql.PreparedStatement;
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
 * 关于商家的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessManManagerImpl extends AbstractBusinessObjectManager implements
		BusinessManManager {

	private BusinessManDao businessmandao = null;

	/**
	 * 构造函数.
	 */
	public BusinessManManagerImpl(BusinessManDao businessmandao) {
		this.businessmandao = businessmandao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchBusinessManNum(Map<BusinessManSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.businessmandao.countByQuery(hql,
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
				BusinessManVO vo = new BusinessManVO();
				//导入商家编号
				String shopmSnoStr = contents[i][0];
				vo.setShopmSno(shopmSnoStr);
				
				//导入商家名称 
				String shopmNameStr = contents[i][1];
				vo.setShopmName(shopmNameStr);
				
				//导入商家简称 
				String shopmShortNameStr = contents[i][2];
				vo.setShopmShortName(shopmShortNameStr);
				
				//导入邮箱
				String shopmEmailStr = contents[i][3];
				vo.setShopmEmail(shopmEmailStr);
				
				//导入联系人手机
				String shopmConPhoneStr = contents[i][4];
				vo.setShopmConPhone(shopmConPhoneStr);
				
				//导入联系人名称
				String shopmContactNameStr = contents[i][5];
				vo.setShopmContactName(shopmContactNameStr);
				
				//导入地址
				String shopmAddressStr = contents[i][6];
				vo.setShopmAddress(shopmAddressStr);
				
				this.businessmandao.insert(vo); 
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
	public Collection<BusinessMan> searchBusinessMan(Map<BusinessManSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<BusinessMan> eaList = new ArrayList<BusinessMan>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<BusinessManVO> voList = this.businessmandao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_shopman_status = allSelect
				.getParamsByType(AllSelectContants.SHOPMAN_STATUS.getName());
		Cache cache_OpenBankProvince = CacheManager.getCacheInfoNotNull(AllSelectContants.PROVINCE_DICT.getName());
		ParamSelect select_OpenBankProvince = (ParamSelect)cache_OpenBankProvince.getValue();
		Cache cache_OpenBankCity = CacheManager.getCacheInfoNotNull(AllSelectContants.CITY_DICT.getName());
		ParamSelect select_OpenBankCity = (ParamSelect)cache_OpenBankCity.getValue();
		
		for (BusinessManVO po : voList) {
			po.setShopmType(select_shopman_status.getName("" + po.getShopmType())); 
			po.setOpenBankProvince(select_OpenBankProvince.getName("" + po.getOpenBankProvince())); 
			po.setOpenBankCity(select_OpenBankCity.getName("" + po.getOpenBankCity())); 
			po.setCreateUserName(CacheUtil.getSystemUserName(""+po.getCreateUser()));
			po.setUpdateUserName(CacheUtil.getSystemUserName(""+po.getUpdateUser()));
			eaList.add(new  BusinessManImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<BusinessManSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( businessman) "
						: "select  businessman ").append("from BusinessManVO as businessman ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<BusinessManSearchFields, Object> entry : criterias
					.entrySet()) {
				BusinessManSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPMSNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmSno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPMNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case SHOPMSHORTNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmShortName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case SHOPMTYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPMEMAIL:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmEmail = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPMCONPHONE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmConPhone = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPMCONTACTNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmContactName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPMADDRESS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmAddress = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPENBANK:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openBank = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPENBANKNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openBankName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPENBANKNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openBankNo = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPENTICKETUNIT:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openTicketUnit = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPENBANKPROVINCE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.OpenBankProvince = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case GROUPSNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.groupSno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPENBANKCITY:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.OpenBankCity = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMPENSATIONDAY:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.compensationDay = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.updateTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case SHOPMSNO_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmSno  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPMSNO_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmSno =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPMNAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPMNAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPMSHORTNAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmShortName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPMSHORTNAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmShortName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPMTYPE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmType  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPMTYPE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmType =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPMEMAIL_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmEmail  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPMEMAIL_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmEmail =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPMCONPHONE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmConPhone  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPMCONPHONE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmConPhone =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPMCONTACTNAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmContactName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPMCONTACTNAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmContactName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPMADDRESS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmAddress  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPMADDRESS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.shopmAddress =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPENBANK_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openBank  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPENBANK_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openBank =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPENBANKNAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openBankName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPENBANKNAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openBankName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPENBANKNO_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openBankNo  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPENBANKNO_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openBankNo =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPENTICKETUNIT_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openTicketUnit  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPENTICKETUNIT_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.openTicketUnit =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPENBANKPROVINCE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.OpenBankProvince  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPENBANKPROVINCE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.OpenBankProvince =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPENBANKCITY_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.OpenBankCity  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPENBANKCITY_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.OpenBankCity =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case COMPENSATIONDAY_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.compensationDay  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMPENSATIONDAY_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.compensationDay =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.createTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.updateUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.updateUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.updateTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessman.updateTime =  ? "); 
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

		BusinessManOrderByFields orderBy = BusinessManOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = BusinessManOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by businessman.sno");
			break;
			case SHOPMSNO:
				 sb.append(" order by businessman.shopmSno");
			break;
			case SHOPMNAME:
				 sb.append(" order by businessman.shopmName");
			break;
			case SHOPMSHORTNAME:
				 sb.append(" order by businessman.shopmShortName");
			break;
			case SHOPMTYPE:
				 sb.append(" order by businessman.shopmType");
			break;
			case SHOPMEMAIL:
				 sb.append(" order by businessman.shopmEmail");
			break;
			case SHOPMCONPHONE:
				 sb.append(" order by businessman.shopmConPhone");
			break;
			case SHOPMCONTACTNAME:
				 sb.append(" order by businessman.shopmContactName");
			break;
			case SHOPMADDRESS:
				 sb.append(" order by businessman.shopmAddress");
			break;
			case OPENBANK:
				 sb.append(" order by businessman.openBank");
			break;
			case OPENBANKNAME:
				 sb.append(" order by businessman.openBankName");
			break;
			case OPENBANKNO:
				 sb.append(" order by businessman.openBankNo");
			break;
			case OPENTICKETUNIT:
				 sb.append(" order by businessman.openTicketUnit");
			break;
			case OPENBANKPROVINCE:
				 sb.append(" order by businessman.OpenBankProvince");
			break;
			case OPENBANKCITY:
				 sb.append(" order by businessman.OpenBankCity");
			break;
			case COMPENSATIONDAY:
				 sb.append(" order by businessman.compensationDay");
			break;
			case CREATEUSER:
				 sb.append(" order by businessman.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by businessman.createTime");
			break;
			case UPDATEUSER:
				 sb.append(" order by businessman.updateUser");
			break;
			case UPDATETIME:
				 sb.append(" order by businessman.updateTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createBusinessMan(BusinessMan businessman) throws ValidateFieldsException {
		BusinessManImpl businessmanImpl = (BusinessManImpl) businessman;
		this.businessmandao.insert(businessmanImpl.getBusinessManVO());
	}

	public void removeBusinessMans(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			BusinessManVO vo = this.businessmandao.findByPrimaryKey(Integer.parseInt(s));
			this.businessmandao.delete(vo);
		}
	}

	public void updateBusinessMan(BusinessMan businessman) throws ValidateFieldsException {
		BusinessManImpl businessmanImpl = (BusinessManImpl) businessman;

		BusinessManVO po = businessmanImpl.getBusinessManVO();
		this.businessmandao.update(po);
	}

	public BusinessMan getBusinessMan(int id) {
		Collection<BusinessManVO> businessmans = this.businessmandao.findRecordById(id);

		if (businessmans == null || businessmans.size() < 1)
			return null;

		BusinessManVO businessman = businessmans.toArray(new BusinessManVO[businessmans.size()])[0];
		businessman.setCreateUserName(CacheUtil.getSystemUserName(""+businessman.getCreateUser()));
		businessman.setUpdateUserName(CacheUtil.getSystemUserName(""+businessman.getUpdateUser()));
		return new BusinessManImpl(businessman);
	}

	@Override
	public void addCache() {
		ParamSelect ans = null;
		Collection<BusinessManVO> all = this.businessmandao.findAll();
		ans = new ParamSelect(all);
		String _tempCacheId = AllSelectContants.BUSINESSMAN_DICT.getName();
		CacheManager.clearOnly(_tempCacheId);
		Cache c = new Cache();
		c.setKey(_tempCacheId);
		c.setValue(ans);
		c.setName("商家");
		CacheManager.putCache(_tempCacheId, c);
	}

	@Override
	public void addToGroupSno(String ids,final String groupSno) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
			    .getBean("jdbcTemplate");
		String[] allId = ids.split(",");
		for(String id:allId){
			if(id!=null&&!"".equals(id)){
				final String tempID = id;
				jdbcTemplate.execute(new ConnectionCallback () { 
					@Override
					public Object doInConnection(java.sql.Connection conn)
							throws SQLException, DataAccessException {
						String sql = "update business_man set group_sno = ? where  id=?  " ;
					    PreparedStatement ps = conn.prepareStatement(sql);
					    ps.setString(1, groupSno);
					    ps.setString(2, tempID);
					    ps.executeUpdate();  
					    return null ;
					}
				}); 
			}
		}
	}

	@Override
	public void deleteToGroupSno(String ids, String groupSno) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
			    .getBean("jdbcTemplate");
		String[] allId = ids.split(",");
		for(String id:allId){
			if(id!=null&&!"".equals(id)){
				final String tempID = id;
				jdbcTemplate.execute(new ConnectionCallback () { 
					@Override
					public Object doInConnection(java.sql.Connection conn)
							throws SQLException, DataAccessException {
						String sql = "update business_man set group_sno = null where  id=?  " ;
					    PreparedStatement ps = conn.prepareStatement(sql);
					    ps.setString(1, tempID);
					    ps.executeUpdate();  
					    return null ;
					}
				}); 
			}
		}
	}
}
