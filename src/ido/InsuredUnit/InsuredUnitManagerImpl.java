
package ido.InsuredUnit;
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

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于投保单位的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredUnitManagerImpl extends AbstractBusinessObjectManager implements
		InsuredUnitManager {

	private InsuredUnitDao insuredunitdao = null;

	/**
	 * 构造函数.
	 */
	public InsuredUnitManagerImpl(InsuredUnitDao insuredunitdao) {
		this.insuredunitdao = insuredunitdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchInsuredUnitNum(Map<InsuredUnitSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.insuredunitdao.countByQuery(hql,
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
				InsuredUnitVO vo = new InsuredUnitVO();
				
				this.insuredunitdao.insert(vo); 
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
	public Collection<InsuredUnit> searchInsuredUnit(Map<InsuredUnitSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<InsuredUnit> eaList = new ArrayList<InsuredUnit>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<InsuredUnitVO> voList = this.insuredunitdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_yesorno = allSelect
				.getParamsByType(AllSelectContants.YESORNO.getName());
		
		for (InsuredUnitVO po : voList) {
			po.setUnitState(select_yesorno.getName("" + po.getUnitState())); 
			eaList.add(new  InsuredUnitImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<InsuredUnitSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( insuredunit) "
						: "select  insuredunit ").append("from InsuredUnitVO as insuredunit ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<InsuredUnitSearchFields, Object> entry : criterias
					.entrySet()) {
				InsuredUnitSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UNITCODE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.unitCode like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case UNITNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.unitName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case CONTACTNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.contactName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CONTACTMOBILE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.contactMobile = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CONTACTEMAIL:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.contactEmail = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UNITPARENTID:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.unitParentId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UNITSTATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.unitState = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UNITADDRESS:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.unitAddress = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UNITREMARK:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.unitRemark = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  insuredunit.updateTime = ? ");
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

		InsuredUnitOrderByFields orderBy = InsuredUnitOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = InsuredUnitOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by insuredunit.sno");
			break;
			case UNITCODE:
				 sb.append(" order by insuredunit.unitCode");
			break;
			case UNITNAME:
				 sb.append(" order by insuredunit.unitName");
			break;
			case CONTACTNAME:
				 sb.append(" order by insuredunit.contactName");
			break;
			case CONTACTMOBILE:
				 sb.append(" order by insuredunit.contactMobile");
			break;
			case CONTACTEMAIL:
				 sb.append(" order by insuredunit.contactEmail");
			break;
			case UNITPARENTID:
				 sb.append(" order by insuredunit.unitParentId");
			break;
			case UNITSTATE:
				 sb.append(" order by insuredunit.unitState");
			break;
			case UNITADDRESS:
				 sb.append(" order by insuredunit.unitAddress");
			break;
			case UNITREMARK:
				 sb.append(" order by insuredunit.unitRemark");
			break;
			case CREATEUSER:
				 sb.append(" order by insuredunit.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by insuredunit.createTime");
			break;
			case UPDATEUSER:
				 sb.append(" order by insuredunit.updateUser");
			break;
			case UPDATETIME:
				 sb.append(" order by insuredunit.updateTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createInsuredUnit(InsuredUnit insuredunit) throws ValidateFieldsException {
		InsuredUnitImpl insuredunitImpl = (InsuredUnitImpl) insuredunit;
		this.insuredunitdao.insert(insuredunitImpl.getInsuredUnitVO());
	}

	public void removeInsuredUnits(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			InsuredUnitVO vo = this.insuredunitdao.findByPrimaryKey(Integer.parseInt(s));
			this.insuredunitdao.delete(vo);
		}
	}

	public void updateInsuredUnit(InsuredUnit insuredunit) throws ValidateFieldsException {
		InsuredUnitImpl insuredunitImpl = (InsuredUnitImpl) insuredunit;

		InsuredUnitVO po = insuredunitImpl.getInsuredUnitVO();
		this.insuredunitdao.update(po);
	}

	public InsuredUnit getInsuredUnit(int id) {
		Collection<InsuredUnitVO> insuredunits = this.insuredunitdao.findRecordById(id);

		if (insuredunits == null || insuredunits.size() < 1)
			return null;

		InsuredUnitVO insuredunit = insuredunits.toArray(new InsuredUnitVO[insuredunits.size()])[0];

		return new InsuredUnitImpl(insuredunit);
	}

}
