
package ido.Dict_InsuredCompany;
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
import common.util.NPOIReader;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于保险公司字典表的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredCompanySelectManagerImpl extends AbstractBusinessObjectManager implements
		InsuredCompanySelectManager {

	private InsuredCompanySelectDao insuredcompanyselectdao = null;

	/**
	 * 构造函数.
	 */
	public InsuredCompanySelectManagerImpl(InsuredCompanySelectDao insuredcompanyselectdao) {
		this.insuredcompanyselectdao = insuredcompanyselectdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchInsuredCompanySelectNum(Map<InsuredCompanySelectSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.insuredcompanyselectdao.countByQuery(hql,
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
				InsuredCompanySelectVO vo = new InsuredCompanySelectVO();
				
				this.insuredcompanyselectdao.insert(vo); 
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
	public Collection<InsuredCompanySelect> searchInsuredCompanySelect(Map<InsuredCompanySelectSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<InsuredCompanySelect> eaList = new ArrayList<InsuredCompanySelect>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<InsuredCompanySelectVO> voList = this.insuredcompanyselectdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_yesorno = allSelect
				.getParamsByType(AllSelectContants.YESORNO.getName());
		
		for (InsuredCompanySelectVO po : voList) {
			po.setComStatus(select_yesorno.getName("" + po.getComStatus())); 
			eaList.add(new  InsuredCompanySelectImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<InsuredCompanySelectSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( insuredcompanyselect) "
						: "select  insuredcompanyselect ").append("from InsuredCompanySelectVO as insuredcompanyselect ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<InsuredCompanySelectSearchFields, Object> entry : criterias
					.entrySet()) {
				InsuredCompanySelectSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompanyselect.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompanyselect.comName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case COMSTATUS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompanyselect.comStatus = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompanyselect.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompanyselect.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompanyselect.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredcompanyselect.updateTime = ? ");
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

		InsuredCompanySelectOrderByFields orderBy = InsuredCompanySelectOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = InsuredCompanySelectOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by insuredcompanyselect.sno");
			break;
			case COMNAME:
				 sb.append(" order by insuredcompanyselect.comName");
			break;
			case COMSTATUS:
				 sb.append(" order by insuredcompanyselect.comStatus");
			break;
			case CREATEUSER:
				 sb.append(" order by insuredcompanyselect.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by insuredcompanyselect.createTime");
			break;
			case UPDATEUSER:
				 sb.append(" order by insuredcompanyselect.updateUser");
			break;
			case UPDATETIME:
				 sb.append(" order by insuredcompanyselect.updateTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createInsuredCompanySelect(InsuredCompanySelect insuredcompanyselect) throws ValidateFieldsException {
		InsuredCompanySelectImpl insuredcompanyselectImpl = (InsuredCompanySelectImpl) insuredcompanyselect;
		this.insuredcompanyselectdao.insert(insuredcompanyselectImpl.getInsuredCompanySelectVO());
	}

	public void removeInsuredCompanySelects(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			InsuredCompanySelectVO vo = this.insuredcompanyselectdao.findByPrimaryKey(Integer.parseInt(s));
			this.insuredcompanyselectdao.delete(vo);
		}
	}

	public void updateInsuredCompanySelect(InsuredCompanySelect insuredcompanyselect) throws ValidateFieldsException {
		InsuredCompanySelectImpl insuredcompanyselectImpl = (InsuredCompanySelectImpl) insuredcompanyselect;

		InsuredCompanySelectVO po = insuredcompanyselectImpl.getInsuredCompanySelectVO();
		this.insuredcompanyselectdao.update(po);
	}

	public InsuredCompanySelect getInsuredCompanySelect(int id) {
		Collection<InsuredCompanySelectVO> insuredcompanyselects = this.insuredcompanyselectdao.findRecordById(id);

		if (insuredcompanyselects == null || insuredcompanyselects.size() < 1)
			return null;

		InsuredCompanySelectVO insuredcompanyselect = insuredcompanyselects.toArray(new InsuredCompanySelectVO[insuredcompanyselects.size()])[0];

		return new InsuredCompanySelectImpl(insuredcompanyselect);
	} 
	 
	@Override
	public void addCache() {
		ParamSelect ans = null;
		Collection<InsuredCompanySelectVO> all = this.insuredcompanyselectdao.findAll();
		ans = new ParamSelect(all);

		CacheManager.clearOnly(AllSelectContants.INSUREDCOMPANY_DICT.getName());
		Cache c = new Cache(); 
		c.setKey(AllSelectContants.INSUREDCOMPANY_DICT.getName());
		c.setValue(ans);
		c.setName("保险公司业务字典"); 
		CacheManager.putCache(AllSelectContants.INSUREDCOMPANY_DICT.getName(), c);
	}
}
