
package ido.bindFamily;
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
 * 关于投保用户的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BindFamilyManagerImpl extends AbstractBusinessObjectManager implements
		BindFamilyManager {

	private BindFamilyDao bindfamilydao = null;

	/**
	 * 构造函数.
	 */
	public BindFamilyManagerImpl(BindFamilyDao bindfamilydao) {
		this.bindfamilydao = bindfamilydao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchBindFamilyNum(Map<BindFamilySearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.bindfamilydao.countByQuery(hql,
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
				BindFamilyVO vo = new BindFamilyVO();
				//导入主用户号
				String iuserNoStr = contents[i][0];
				vo.setIuserNo(iuserNoStr);
				
				//导入绑定人
				String bindNameStr = contents[i][1];
				vo.setBindName(bindNameStr);
				
				//导入关系
				String relationStr = contents[i][2];
				vo.setRelation(relationStr);
				
				//导入身份证
				String cardNoStr = contents[i][3];
				vo.setCardNo(cardNoStr);
				
				//导入手机号
				String phoneStr = contents[i][4];
				vo.setPhone(phoneStr);
				
				this.bindfamilydao.insert(vo); 
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
	public Collection<BindFamily> searchBindFamily(Map<BindFamilySearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<BindFamily> eaList = new ArrayList<BindFamily>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<BindFamilyVO> voList = this.bindfamilydao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_bindusertype = allSelect
				.getParamsByType(AllSelectContants.BINDUSERTYPE.getName());
		
		for (BindFamilyVO po : voList) {
			po.setRelation(select_bindusertype.getName("" + po.getRelation())); 
			eaList.add(new  BindFamilyImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<BindFamilySearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( bindfamily) "
						: "select  bindfamily ").append("from BindFamilyVO as bindfamily ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<BindFamilySearchFields, Object> entry : criterias
					.entrySet()) {
				BindFamilySearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case IUSERNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.iuserNo = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case BINDNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.bindName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case RELATION:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.relation = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CARDNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.cardNo = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PHONE:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.phone = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case IUSERNO_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.iuserNo  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case IUSERNO_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.iuserNo =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case BINDNAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.bindName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case BINDNAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.bindName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case RELATION_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.relation  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case RELATION_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.relation =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CARDNO_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.cardNo  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CARDNO_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.cardNo =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case PHONE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.phone  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case PHONE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.phone =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  bindfamily.createTime =  ? "); 
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

		BindFamilyOrderByFields orderBy = BindFamilyOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = BindFamilyOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by bindfamily.sno");
			break;
			case IUSERNO:
				 sb.append(" order by bindfamily.iuserNo");
			break;
			case BINDNAME:
				 sb.append(" order by bindfamily.bindName");
			break;
			case RELATION:
				 sb.append(" order by bindfamily.relation");
			break;
			case CARDNO:
				 sb.append(" order by bindfamily.cardNo");
			break;
			case PHONE:
				 sb.append(" order by bindfamily.phone");
			break;
			case CREATEUSER:
				 sb.append(" order by bindfamily.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by bindfamily.createTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createBindFamily(BindFamily bindfamily) throws ValidateFieldsException {
		BindFamilyImpl bindfamilyImpl = (BindFamilyImpl) bindfamily;
		this.bindfamilydao.insert(bindfamilyImpl.getBindFamilyVO());
	}

	public void removeBindFamilys(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			BindFamilyVO vo = this.bindfamilydao.findByPrimaryKey(Integer.parseInt(s));
			this.bindfamilydao.delete(vo);
		}
	}

	public void updateBindFamily(BindFamily bindfamily) throws ValidateFieldsException {
		BindFamilyImpl bindfamilyImpl = (BindFamilyImpl) bindfamily;

		BindFamilyVO po = bindfamilyImpl.getBindFamilyVO();
		this.bindfamilydao.update(po);
	}

	public BindFamily getBindFamily(int id) {
		Collection<BindFamilyVO> bindfamilys = this.bindfamilydao.findRecordById(id);

		if (bindfamilys == null || bindfamilys.size() < 1)
			return null;

		BindFamilyVO bindfamily = bindfamilys.toArray(new BindFamilyVO[bindfamilys.size()])[0];

		return new BindFamilyImpl(bindfamily);
	}

}
