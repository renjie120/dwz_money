
package ido.BusinessGroup;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
 * 关于商家集团的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessGroupManagerImpl extends AbstractBusinessObjectManager implements
		BusinessGroupManager {

	private BusinessGroupDao businessgroupdao = null;

	/**
	 * 构造函数.
	 */
	public BusinessGroupManagerImpl(BusinessGroupDao businessgroupdao) {
		this.businessgroupdao = businessgroupdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchBusinessGroupNum(Map<BusinessGroupSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.businessgroupdao.countByQuery(hql,
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
				BusinessGroupVO vo = new BusinessGroupVO();
				//导入集团编号
				String groupSnoStr = contents[i][0];
				vo.setGroupSno(groupSnoStr);
				
				//导入集团名称 
				String groupNameStr = contents[i][1];
				vo.setGroupName(groupNameStr);
				
				//导入邮箱
				String groupEmailStr = contents[i][2];
				vo.setGroupEmail(groupEmailStr);
				
				//导入联系人名称
				String groupContactStr = contents[i][3];
				vo.setGroupContact(groupContactStr);
				
				//导入联系电话
				String groupContactPhoneStr = contents[i][4];
				vo.setGroupContactPhone(groupContactPhoneStr);
				
				//导入联系人手机
				String groupContactMobileStr = contents[i][5];
				vo.setGroupContactMobile(groupContactMobileStr);
				
				this.businessgroupdao.insert(vo); 
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
	public Collection<BusinessGroup> searchBusinessGroup(Map<BusinessGroupSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<BusinessGroup> eaList = new ArrayList<BusinessGroup>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<BusinessGroupVO> voList = this.businessgroupdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_shopman_status = allSelect
				.getParamsByType(AllSelectContants.SHOPMAN_STATUS.getName());
		
		for (BusinessGroupVO po : voList) {
			po.setGroupStatus(select_shopman_status.getName("" + po.getGroupStatus())); 
			po.setCreateUserName(CacheUtil.getSystemUserName(""+po.getCreateUser()));
			po.setUpdateUserName(CacheUtil.getSystemUserName(""+po.getUpdateUser()));
			eaList.add(new  BusinessGroupImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<BusinessGroupSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( businessgroup) "
						: "select  businessgroup ").append("from BusinessGroupVO as businessgroup ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<BusinessGroupSearchFields, Object> entry : criterias
					.entrySet()) {
				BusinessGroupSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case GROUPSNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupSno like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case GROUPNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case GROUPEMAIL:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupEmail = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case GROUPCONTACT:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContact = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case GROUPCONTACTPHONE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContactPhone = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case GROUPCONTACTMOBILE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContactMobile = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case GROUPSTATUS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupStatus = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.updateTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case GROUPSNO_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupSno  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPSNO_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupSno like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPSNO_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupSno not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPSNO_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupSno !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPNAME_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupName  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPNAME_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupName like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPNAME_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupName not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPNAME_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupName !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPEMAIL_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupEmail  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPEMAIL_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupEmail like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPEMAIL_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupEmail not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPEMAIL_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupEmail !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPCONTACT_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContact  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPCONTACT_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContact like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPCONTACT_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContact not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPCONTACT_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContact !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPCONTACTPHONE_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContactPhone  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPCONTACTPHONE_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContactPhone like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPCONTACTPHONE_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContactPhone not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPCONTACTPHONE_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContactPhone !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPCONTACTMOBILE_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContactMobile  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPCONTACTMOBILE_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContactMobile like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPCONTACTMOBILE_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContactMobile not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case GROUPCONTACTMOBILE_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupContactMobile !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case GROUPSTATUS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupStatus  not in (  "); 
						String _temp_GroupStatus1 = ""+entry.getValue();
						String[] _temp_arr_GroupStatus1 = _temp_GroupStatus1.split(",");
						int _int_GroupStatus1 = _temp_arr_GroupStatus1.length;
						for(int _i=0;_i<_int_GroupStatus1;_i++){
							sb.append(" ? ,");
							argList.add(_temp_arr_GroupStatus1[_i]); 
						}
						sb = sb.deleteCharAt(sb.length()-1); 
						sb.append(" ) ");
						count++;
					break;
					case GROUPSTATUS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.groupStatus in  (   "); 
						String _temp_GroupStatus2 = ""+entry.getValue();
						String[] _temp_arr_GroupStatus2 = _temp_GroupStatus2.split(",");
						int _int_GroupStatus2 = _temp_arr_GroupStatus2.length;
						for(int _i=0;_i<_int_GroupStatus2;_i++){
							sb.append(" ? ,");
							argList.add(_temp_arr_GroupStatus2[_i]); 
						}
						sb = sb.deleteCharAt(sb.length()-1); 
						sb.append(" ) ");
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.createTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.updateUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.updateUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.updateTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessgroup.updateTime =  ? "); 
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

		BusinessGroupOrderByFields orderBy = BusinessGroupOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = BusinessGroupOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case SNO:
			 sb.append(" order by businessgroup.sno");
		break;
		case SNO_DESC:
			 sb.append(" order by businessgroup.sno desc");
		break;
		case GROUPSNO:
			 sb.append(" order by businessgroup.groupSno");
		break;
		case GROUPSNO_DESC:
			 sb.append(" order by businessgroup.groupSno desc");
		break;
		case GROUPNAME:
			 sb.append(" order by businessgroup.groupName");
		break;
		case GROUPNAME_DESC:
			 sb.append(" order by businessgroup.groupName desc");
		break;
		case GROUPEMAIL:
			 sb.append(" order by businessgroup.groupEmail");
		break;
		case GROUPEMAIL_DESC:
			 sb.append(" order by businessgroup.groupEmail desc");
		break;
		case GROUPCONTACT:
			 sb.append(" order by businessgroup.groupContact");
		break;
		case GROUPCONTACT_DESC:
			 sb.append(" order by businessgroup.groupContact desc");
		break;
		case GROUPCONTACTPHONE:
			 sb.append(" order by businessgroup.groupContactPhone");
		break;
		case GROUPCONTACTPHONE_DESC:
			 sb.append(" order by businessgroup.groupContactPhone desc");
		break;
		case GROUPCONTACTMOBILE:
			 sb.append(" order by businessgroup.groupContactMobile");
		break;
		case GROUPCONTACTMOBILE_DESC:
			 sb.append(" order by businessgroup.groupContactMobile desc");
		break;
		case GROUPSTATUS:
			 sb.append(" order by businessgroup.groupStatus");
		break;
		case GROUPSTATUS_DESC:
			 sb.append(" order by businessgroup.groupStatus desc");
		break;
		case CREATEUSER:
			 sb.append(" order by businessgroup.createUser");
		break;
		case CREATEUSER_DESC:
			 sb.append(" order by businessgroup.createUser desc");
		break;
		case CREATETIME:
			 sb.append(" order by businessgroup.createTime");
		break;
		case CREATETIME_DESC:
			 sb.append(" order by businessgroup.createTime desc");
		break;
		case UPDATEUSER:
			 sb.append(" order by businessgroup.updateUser");
		break;
		case UPDATEUSER_DESC:
			 sb.append(" order by businessgroup.updateUser desc");
		break;
		case UPDATETIME:
			 sb.append(" order by businessgroup.updateTime");
		break;
		case UPDATETIME_DESC:
			 sb.append(" order by businessgroup.updateTime desc");
		break;
		default:
			break;
	}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createBusinessGroup(BusinessGroup businessgroup) throws ValidateFieldsException {
		BusinessGroupImpl businessgroupImpl = (BusinessGroupImpl) businessgroup;
		this.businessgroupdao.insert(businessgroupImpl.getBusinessGroupVO());
	}

	public void removeBusinessGroups(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			BusinessGroupVO vo = this.businessgroupdao.findByPrimaryKey(Integer.parseInt(s));
			this.businessgroupdao.delete(vo);
		}
	}

	public void updateBusinessGroup(BusinessGroup businessgroup) throws ValidateFieldsException {
		BusinessGroupImpl businessgroupImpl = (BusinessGroupImpl) businessgroup;

		BusinessGroupVO po = businessgroupImpl.getBusinessGroupVO();
		this.businessgroupdao.update(po);
	}

	public BusinessGroup getBusinessGroup(int id) {
		Collection<BusinessGroupVO> businessgroups = this.businessgroupdao.findRecordById(id);

		if (businessgroups == null || businessgroups.size() < 1)
			return null;

		BusinessGroupVO businessgroup = businessgroups.toArray(new BusinessGroupVO[businessgroups.size()])[0];
		businessgroup.setCreateUserName(CacheUtil.getSystemUserName(""+businessgroup.getCreateUser()));
		businessgroup.setUpdateUserName(CacheUtil.getSystemUserName(""+businessgroup.getUpdateUser()));
		return new BusinessGroupImpl(businessgroup);
	}

	@Override
	public void addCache() {
		ParamSelect ans = null;
		Collection<BusinessGroupVO> all = this.businessgroupdao.findAll();
		ans = new ParamSelect(all);
		String _tempCacheId = AllSelectContants.BUSINESSGROUP_DICT.getName();
		CacheManager.clearOnly(_tempCacheId);
		Cache c = new Cache();
		c.setKey(_tempCacheId);
		c.setValue(ans);
		c.setName("商家集团");
		CacheManager.putCache(_tempCacheId, c);
	}
}
