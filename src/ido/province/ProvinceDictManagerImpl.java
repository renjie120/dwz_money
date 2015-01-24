
package ido.province;
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
 * 关于省份字典表的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ProvinceDictManagerImpl extends AbstractBusinessObjectManager implements
		ProvinceDictManager {

	private ProvinceDictDao provincedictdao = null;

	/**
	 * 构造函数.
	 */
	public ProvinceDictManagerImpl(ProvinceDictDao provincedictdao) {
		this.provincedictdao = provincedictdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchProvinceDictNum(Map<ProvinceDictSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.provincedictdao.countByQuery(hql,
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
				ProvinceDictVO vo = new ProvinceDictVO();
				this.provincedictdao.insert(vo); 
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
	public Collection<ProvinceDict> searchProvinceDict(Map<ProvinceDictSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<ProvinceDict> eaList = new ArrayList<ProvinceDict>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<ProvinceDictVO> voList = this.provincedictdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_prov_type = allSelect
				.getParamsByType(AllSelectContants.PROV_TYPE.getName()); 
		ParamSelect select_yesorno_status = allSelect
				.getParamsByType(AllSelectContants.YESORNO_STATUS.getName());
		
		for (ProvinceDictVO po : voList) {
			po.setProvType(select_prov_type.getName("" + po.getProvType())); 
			po.setProvState(select_yesorno_status.getName("" + po.getProvState())); 
			eaList.add(new  ProvinceDictImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<ProvinceDictSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( provincedict) "
						: "select  provincedict ").append("from ProvinceDictVO as provincedict ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<ProvinceDictSearchFields, Object> entry : criterias
					.entrySet()) {
				ProvinceDictSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  provincedict.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PROVNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  provincedict.provName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PROVTYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  provincedict.provType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PROVSTATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  provincedict.provState = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case PROVNAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  provincedict.provName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case PROVNAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  provincedict.provName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case PROVTYPE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  provincedict.provType  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case PROVTYPE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  provincedict.provType =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case PROVSTATE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  provincedict.provState  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case PROVSTATE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  provincedict.provState =  ? "); 
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

		ProvinceDictOrderByFields orderBy = ProvinceDictOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = ProvinceDictOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by provincedict.sno");
			break;
			case PROVNAME:
				 sb.append(" order by provincedict.provName");
			break;
			case PROVTYPE:
				 sb.append(" order by provincedict.provType");
			break;
			case PROVSTATE:
				 sb.append(" order by provincedict.provState");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createProvinceDict(ProvinceDict provincedict) throws ValidateFieldsException {
		ProvinceDictImpl provincedictImpl = (ProvinceDictImpl) provincedict;
		this.provincedictdao.insert(provincedictImpl.getProvinceDictVO());
	}

	public void removeProvinceDicts(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			ProvinceDictVO vo = this.provincedictdao.findByPrimaryKey(Integer.parseInt(s));
			this.provincedictdao.delete(vo);
		}
	}

	public void updateProvinceDict(ProvinceDict provincedict) throws ValidateFieldsException {
		ProvinceDictImpl provincedictImpl = (ProvinceDictImpl) provincedict;

		ProvinceDictVO po = provincedictImpl.getProvinceDictVO();
		this.provincedictdao.update(po);
	}

	public ProvinceDict getProvinceDict(int id) {
		Collection<ProvinceDictVO> provincedicts = this.provincedictdao.findRecordById(id);

		if (provincedicts == null || provincedicts.size() < 1)
			return null;

		ProvinceDictVO provincedict = provincedicts.toArray(new ProvinceDictVO[provincedicts.size()])[0];

		return new ProvinceDictImpl(provincedict);
	}

	@Override
	public void addCache() {
		ParamSelect ans = null;
		Collection<ProvinceDictVO> all = this.provincedictdao.findAll();
		ans = new ParamSelect(all);
		String _tempCacheId = AllSelectContants.PROVINCE_DICT.getName();
		CacheManager.clearOnly(_tempCacheId);
		Cache c = new Cache();
		c.setKey(_tempCacheId);
		c.setValue(ans);
		c.setName("省份字典表");
		CacheManager.putCache(_tempCacheId, c);
	}
}
