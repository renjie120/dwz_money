
package money.addRoleDetail;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.File;
import java.util.Map;

import common.util.NPOIReader;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.util.CommonUtil;
import common.util.DateTool;
import common.util.NPOIReader; 
import common.base.AllSelect;
import common.base.AllSelectContants;
import common.cache.Cache;
import common.cache.CacheManager;
import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于新角色授权明细的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class AddRoleDetailManagerImpl extends AbstractBusinessObjectManager implements
		AddRoleDetailManager {

	private AddRoleDetailDao addroledetaildao = null;

	/**
	 * 构造函数.
	 */
	public AddRoleDetailManagerImpl(AddRoleDetailDao addroledetaildao) {
		this.addroledetaildao = addroledetaildao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchAddRoleDetailNum(Map<AddRoleDetailSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.addroledetaildao.countByQuery(hql,
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
				AddRoleDetailVO vo = new AddRoleDetailVO();
				//导入授权对象 
				String roleKeyStr = contents[i][0];
				vo.setRoleKey(roleKeyStr);
				
				this.addroledetaildao.insert(vo); 
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
	public Collection<AddRoleDetail> searchAddRoleDetail(Map<AddRoleDetailSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<AddRoleDetail> eaList = new ArrayList<AddRoleDetail>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<AddRoleDetailVO> voList = this.addroledetaildao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_roletype = allSelect
				.getParamsByType(AllSelectContants.ROLETYPE.getName());
		
		for (AddRoleDetailVO po : voList) {
			po.setRoleType(select_roletype.getName("" + po.getRoleType())); 
			eaList.add(new  AddRoleDetailImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<AddRoleDetailSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( addroledetail) "
						: "select  addroledetail ").append("from AddRoleDetailVO as addroledetail ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<AddRoleDetailSearchFields, Object> entry : criterias
					.entrySet()) {
				AddRoleDetailSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  addroledetail.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ROLETYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  addroledetail.roleType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ROLEKEY:
						sb.append(count == 0 ? " where" : " and").append(
								"  addroledetail.roleKey = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ROLEID:
						sb.append(count == 0 ? " where" : " and").append(
								"  addroledetail.roleId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case ROLETYPE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addroledetail.roleType  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ROLETYPE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addroledetail.roleType =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case ROLEKEY_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addroledetail.roleKey  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ROLEKEY_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addroledetail.roleKey =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case ROLEID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addroledetail.roleId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ROLEID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addroledetail.roleId =  ? "); 
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

		AddRoleDetailOrderByFields orderBy = AddRoleDetailOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = AddRoleDetailOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by addroledetail.sno");
			break;
			case ROLETYPE:
				 sb.append(" order by addroledetail.roleType");
			break;
			case ROLEKEY:
				 sb.append(" order by addroledetail.roleKey");
			break;
			case ROLEID:
				 sb.append(" order by addroledetail.roleId");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createAddRoleDetail(AddRoleDetail addroledetail) throws ValidateFieldsException {
		AddRoleDetailImpl addroledetailImpl = (AddRoleDetailImpl) addroledetail;
		this.addroledetaildao.insert(addroledetailImpl.getAddRoleDetailVO());
	}

	public void removeAddRoleDetails(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			AddRoleDetailVO vo = this.addroledetaildao.findByPrimaryKey(Integer.parseInt(s));
			this.addroledetaildao.delete(vo);
		}
	}

	public void updateAddRoleDetail(AddRoleDetail addroledetail) throws ValidateFieldsException {
		AddRoleDetailImpl addroledetailImpl = (AddRoleDetailImpl) addroledetail;

		AddRoleDetailVO po = addroledetailImpl.getAddRoleDetailVO();
		this.addroledetaildao.update(po);
	}

	public AddRoleDetail getAddRoleDetail(int id) {
		Collection<AddRoleDetailVO> addroledetails = this.addroledetaildao.findRecordById(id);

		if (addroledetails == null || addroledetails.size() < 1)
			return null;

		AddRoleDetailVO addroledetail = addroledetails.toArray(new AddRoleDetailVO[addroledetails.size()])[0];

		return new AddRoleDetailImpl(addroledetail);
	}

}
