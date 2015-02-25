
package money.addRole2;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import money.addRoleDetail.AddRoleDetailDao;
import money.addRoleDetail.AddRoleDetailImpl;

import common.MyJdbcTool;
import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.cache.CacheUtil;
import common.util.NPOIReader;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于新角色授权的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class AddRoleManagerImpl extends AbstractBusinessObjectManager implements
		AddRoleManager {
	private AddRoleDetailDao addroledetaildao = null;

	private AddRoleDao addroledao = null;

	private JdbcTemplate jdbcTemplate = null;
	/**
	 * 构造函数.
	 */
	public AddRoleManagerImpl(AddRoleDao addroledao,AddRoleDetailDao addroledetaildao,
			JdbcTemplate jdbcTemplate) {
		this.addroledao = addroledao;
		this.addroledetaildao = addroledetaildao;
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchAddRoleNum(Map<AddRoleSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.addroledao.countByQuery(hql,
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
				AddRoleVO vo = new AddRoleVO();
				//导入授权对象 
				String roleKeyStr = contents[i][0];
				vo.setRoleKey(roleKeyStr);
				
				this.addroledao.insert(vo); 
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
	public Collection<AddRole> searchAddRole(Map<AddRoleSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<AddRole> eaList = new ArrayList<AddRole>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<AddRoleVO> voList = this.addroledao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_roletype = allSelect
				.getParamsByType(AllSelectContants.ROLETYPE.getName());
		ParamSelect userTypes = allSelect
		.getParamsByType(AllSelectContants.AIDUYONGHU.getName());

		for (AddRoleVO po : voList) {
			po.setRoleType(select_roletype.getName("" + po.getRoleType())); 
			po.setRoleKey(userTypes.getName("" + po.getRoleKey())); 
			if(po.getRoleIds()!=null&&!"".equals(po.getRoleIds().trim())){
				String ids = po.getRoleIds().trim();
				String[] idArr = ids.split(",");
				StringBuilder names = new StringBuilder(100);
				for(String i:idArr){
					names.append(CacheUtil.getRoleName(i)).append(",");
				}
				if(names.length()>1)
					po.setRoleIds(names.substring(0, names.length()-1).toString());
			}
			eaList.add(new  AddRoleImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<AddRoleSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( addrole) "
						: "select  addrole ").append("from AddRoleVO as addrole ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<AddRoleSearchFields, Object> entry : criterias
					.entrySet()) {
				AddRoleSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ROLETYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.roleType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ROLEKEY:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.roleKey = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ROLEIDS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.roleIds = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.updateTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case ROLETYPE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.roleType  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ROLETYPE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.roleType =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case ROLEKEY_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.roleKey  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ROLEKEY_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.roleKey =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case ROLEIDS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.roleIds  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ROLEIDS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.roleIds =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.createTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.updateUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.updateUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.updateTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addrole.updateTime =  ? "); 
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

		AddRoleOrderByFields orderBy = AddRoleOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = AddRoleOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by addrole.sno");
			break;
			case ROLETYPE:
				 sb.append(" order by addrole.roleType");
			break;
			case ROLEKEY:
				 sb.append(" order by addrole.roleKey");
			break;
			case ROLEIDS:
				 sb.append(" order by addrole.roleIds");
			break;
			case CREATEUSER:
				 sb.append(" order by addrole.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by addrole.createTime");
			break;
			case UPDATEUSER:
				 sb.append(" order by addrole.updateUser");
			break;
			case UPDATETIME:
				 sb.append(" order by addrole.updateTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	} 

	public void createAddRole(AddRole addrole) throws ValidateFieldsException {
		AddRoleImpl addroleImpl = (AddRoleImpl) addrole;
		addRoleByUserType(addrole);
		this.addroledao.insert(addroleImpl.getAddRoleVO());
	}

	private void deleteRoleByUserType(final String roleType,final String roleKey){
		jdbcTemplate.update("delete from  new_role_detail where role_type=? and role_key=? ",
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setString(1, roleType);
						ps.setString(2, roleKey); 
					}
				});
	}
	
	private void addRoleByUserType(AddRole addrole){
		String roleIds = addrole.getRoleIds();
		//循环插入到新的明细表中.
		if(roleIds!=null&&!"".equals(roleIds.trim())){
			String[] ids = roleIds.split(",");
			for(String _i:ids){
				AddRoleDetailImpl addroledetailImpl = 
					new AddRoleDetailImpl(addrole.getRoleType() ,addrole.getRoleKey() ,_i );
				this.addroledetaildao.insert(addroledetailImpl.getAddRoleDetailVO());
			}
		}
	}
	
	public void removeAddRoles(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			AddRoleVO vo = this.addroledao.findByPrimaryKey(Integer.parseInt(s)); 
			deleteRoleByUserType(vo.getRoleType(),vo.getRoleKey());
			this.addroledao.delete(vo);
		}
	}

	public void updateAddRole(AddRole addrole) throws ValidateFieldsException {
		AddRoleImpl addroleImpl = (AddRoleImpl) addrole;

		AddRoleVO po = addroleImpl.getAddRoleVO();
		//先删除原来的角色数据
		deleteRoleByUserType(po.getRoleType(),po.getRoleKey());
		//在插入新的角色数据
		addRoleByUserType(addrole);
		this.addroledao.update(po);
	}

	public AddRole getAddRole(int id) {
		Collection<AddRoleVO> addroles = this.addroledao.findRecordById(id);

		if (addroles == null || addroles.size() < 1)
			return null;

		AddRoleVO addrole = addroles.toArray(new AddRoleVO[addroles.size()])[0];

		return new AddRoleImpl(addrole);
	}

}
