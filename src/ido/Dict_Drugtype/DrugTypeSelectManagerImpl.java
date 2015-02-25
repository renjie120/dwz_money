
package ido.Dict_Drugtype;
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
 * 关于药品大类字典表的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class DrugTypeSelectManagerImpl extends AbstractBusinessObjectManager implements
		DrugTypeSelectManager {

	private DrugTypeSelectDao drugtypeselectdao = null;

	/**
	 * 构造函数.
	 */
	public DrugTypeSelectManagerImpl(DrugTypeSelectDao drugtypeselectdao) {
		this.drugtypeselectdao = drugtypeselectdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchDrugTypeSelectNum(Map<DrugTypeSelectSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.drugtypeselectdao.countByQuery(hql,
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
				DrugTypeSelectVO vo = new DrugTypeSelectVO();
				//导入药品类别名称
				String drugTypeStr = contents[i][0];
				vo.setDrugType(drugTypeStr);
				
				this.drugtypeselectdao.insert(vo); 
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
	public Collection<DrugTypeSelect> searchDrugTypeSelect(Map<DrugTypeSelectSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<DrugTypeSelect> eaList = new ArrayList<DrugTypeSelect>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<DrugTypeSelectVO> voList = this.drugtypeselectdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_yesorno = allSelect
				.getParamsByType(AllSelectContants.YESORNO.getName());
		
		for (DrugTypeSelectVO po : voList) {
			po.setTypeStatus(select_yesorno.getName("" + po.getTypeStatus())); 
			eaList.add(new  DrugTypeSelectImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<DrugTypeSelectSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( drugtypeselect) "
						: "select  drugtypeselect ").append("from DrugTypeSelectVO as drugtypeselect ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<DrugTypeSelectSearchFields, Object> entry : criterias
					.entrySet()) {
				DrugTypeSelectSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case DRUGTYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.drugType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case TYPESTATUS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.typeStatus = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.updateTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case DRUGTYPE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.drugType  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case DRUGTYPE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.drugType =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case TYPESTATUS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.typeStatus  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case TYPESTATUS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.typeStatus =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.createTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.updateUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.updateUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.updateTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  drugtypeselect.updateTime =  ? "); 
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

		DrugTypeSelectOrderByFields orderBy = DrugTypeSelectOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = DrugTypeSelectOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by drugtypeselect.sno");
			break;
			case DRUGTYPE:
				 sb.append(" order by drugtypeselect.drugType");
			break;
			case TYPESTATUS:
				 sb.append(" order by drugtypeselect.typeStatus");
			break;
			case CREATEUSER:
				 sb.append(" order by drugtypeselect.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by drugtypeselect.createTime");
			break;
			case UPDATEUSER:
				 sb.append(" order by drugtypeselect.updateUser");
			break;
			case UPDATETIME:
				 sb.append(" order by drugtypeselect.updateTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createDrugTypeSelect(DrugTypeSelect drugtypeselect) throws ValidateFieldsException {
		DrugTypeSelectImpl drugtypeselectImpl = (DrugTypeSelectImpl) drugtypeselect;
		this.drugtypeselectdao.insert(drugtypeselectImpl.getDrugTypeSelectVO());
	}

	public void removeDrugTypeSelects(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			DrugTypeSelectVO vo = this.drugtypeselectdao.findByPrimaryKey(Integer.parseInt(s));
			this.drugtypeselectdao.delete(vo);
		}
	}

	public void updateDrugTypeSelect(DrugTypeSelect drugtypeselect) throws ValidateFieldsException {
		DrugTypeSelectImpl drugtypeselectImpl = (DrugTypeSelectImpl) drugtypeselect;

		DrugTypeSelectVO po = drugtypeselectImpl.getDrugTypeSelectVO();
		this.drugtypeselectdao.update(po);
	}

	public DrugTypeSelect getDrugTypeSelect(int id) {
		Collection<DrugTypeSelectVO> drugtypeselects = this.drugtypeselectdao.findRecordById(id);

		if (drugtypeselects == null || drugtypeselects.size() < 1)
			return null;

		DrugTypeSelectVO drugtypeselect = drugtypeselects.toArray(new DrugTypeSelectVO[drugtypeselects.size()])[0];

		return new DrugTypeSelectImpl(drugtypeselect);
	}

	@Override
	public void addCache() {
		ParamSelect ans = null;
		Collection<DrugTypeSelectVO> all = this.drugtypeselectdao.findAll();
		ans = new ParamSelect(all);
		String _tempCacheId = AllSelectContants.DRUGTYPE_DICT.getName();
		CacheManager.clearOnly(_tempCacheId);
		Cache c = new Cache();
		c.setKey(_tempCacheId);
		c.setValue(ans);
		c.setName("药品大类字典表");
		CacheManager.putCache(_tempCacheId, c);
	}
}
