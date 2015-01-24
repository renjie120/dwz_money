
package ido.city;
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
 * 关于城市字典表的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class CityDictManagerImpl extends AbstractBusinessObjectManager implements
		CityDictManager {

	private CityDictDao citydictdao = null;

	/**
	 * 构造函数.
	 */
	public CityDictManagerImpl(CityDictDao citydictdao) {
		this.citydictdao = citydictdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchCityDictNum(Map<CityDictSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.citydictdao.countByQuery(hql,
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
				CityDictVO vo = new CityDictVO();
				this.citydictdao.insert(vo); 
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
	public Collection<CityDict> searchCityDict(Map<CityDictSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<CityDict> eaList = new ArrayList<CityDict>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<CityDictVO> voList = this.citydictdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		Cache cache_provId = CacheManager.getCacheInfoNotNull(AllSelectContants.PROVINCE_DICT.getName());
		ParamSelect select_provId = (ParamSelect)cache_provId.getValue();
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_yesorno_status = allSelect
				.getParamsByType(AllSelectContants.YESORNO_STATUS.getName());
		
		for (CityDictVO po : voList) {
			po.setProvId(select_provId.getName("" + po.getProvId())); 
			po.setCityState(select_yesorno_status.getName("" + po.getCityState())); 
			eaList.add(new  CityDictImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<CityDictSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( citydict) "
						: "select  citydict ").append("from CityDictVO as citydict ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<CityDictSearchFields, Object> entry : criterias
					.entrySet()) {
				CityDictSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  citydict.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CITYNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  citydict.cityName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PROVID:
						sb.append(count == 0 ? " where" : " and").append(
								"  citydict.provId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CITYSTATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  citydict.cityState = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case CITYNAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  citydict.cityName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CITYNAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  citydict.cityName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case PROVID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  citydict.provId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case PROVID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  citydict.provId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CITYSTATE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  citydict.cityState  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CITYSTATE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  citydict.cityState =  ? "); 
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

		CityDictOrderByFields orderBy = CityDictOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = CityDictOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by citydict.sno");
			break;
			case CITYNAME:
				 sb.append(" order by citydict.cityName");
			break;
			case PROVID:
				 sb.append(" order by citydict.provId");
			break;
			case CITYSTATE:
				 sb.append(" order by citydict.cityState");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createCityDict(CityDict citydict) throws ValidateFieldsException {
		CityDictImpl citydictImpl = (CityDictImpl) citydict;
		this.citydictdao.insert(citydictImpl.getCityDictVO());
	}

	public void removeCityDicts(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			CityDictVO vo = this.citydictdao.findByPrimaryKey(Integer.parseInt(s));
			this.citydictdao.delete(vo);
		}
	}

	public void updateCityDict(CityDict citydict) throws ValidateFieldsException {
		CityDictImpl citydictImpl = (CityDictImpl) citydict;

		CityDictVO po = citydictImpl.getCityDictVO();
		this.citydictdao.update(po);
	}

	public CityDict getCityDict(int id) {
		Collection<CityDictVO> citydicts = this.citydictdao.findRecordById(id);

		if (citydicts == null || citydicts.size() < 1)
			return null;

		CityDictVO citydict = citydicts.toArray(new CityDictVO[citydicts.size()])[0];

		return new CityDictImpl(citydict);
	}

	@Override
	public void addCache() {
		ParamSelect ans = null;
		Collection<CityDictVO> all = this.citydictdao.findAll();
		ans = new ParamSelect(all);
		String _tempCacheId = AllSelectContants.CITY_DICT.getName();
		CacheManager.clearOnly(_tempCacheId);
		Cache c = new Cache();
		c.setKey(_tempCacheId);
		c.setValue(ans);
		c.setName("城市字典表");
		CacheManager.putCache(_tempCacheId, c);
	}
}
