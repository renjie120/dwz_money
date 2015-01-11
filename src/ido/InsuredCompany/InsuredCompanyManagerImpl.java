
package ido.InsuredCompany;
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
	
		
		for (InsuredCompanyVO po : voList) {
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

}
