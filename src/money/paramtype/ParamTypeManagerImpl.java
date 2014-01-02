
package money.paramtype;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于参数类型的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ParamTypeManagerImpl extends AbstractBusinessObjectManager implements
		ParamTypeManager {

	private ParamTypeDao paramtypedao = null;

	/**
	 * 构造函数.
	 */
	public ParamTypeManagerImpl(ParamTypeDao paramtypedao) {
		this.paramtypedao = paramtypedao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchParamTypeNum(Map<ParamTypeSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.paramtypedao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<ParamType> searchParamType(Map<ParamTypeSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<ParamType> eaList = new ArrayList<ParamType>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<ParamTypeVO> voList = this.paramtypedao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (ParamTypeVO po : voList) {
			eaList.add(new  ParamTypeImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<ParamTypeSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct paramtype) "
						: "select distinct paramtype ").append("from ParamTypeVO as paramtype ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<ParamTypeSearchFields, Object> entry : criterias
					.entrySet()) {
				ParamTypeSearchFields fd = entry.getKey();
				switch (fd) {
					case PARAMTYPEID:
						sb.append(count == 0 ? " where" : " and").append(
								"  paramtype.paramTypeId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PARAMTYPENAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  paramtype.paramTypeName like ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ORDERID:
						sb.append(count == 0 ? " where" : " and").append(
								"  paramtype.orderId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CODE:
						sb.append(count == 0 ? " where" : " and").append(
								"  paramtype.code like ? ");
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

		ParamTypeOrderByFields orderBy = ParamTypeOrderByFields.PARAMTYPEID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = ParamTypeOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case PARAMTYPEID:
				 sb.append(" order by paramtype.paramTypeId");
			break;
			case PARAMTYPENAME:
				 sb.append(" order by paramtype.paramTypeName");
			break;
			case ORDERID:
				 sb.append(" order by paramtype.orderId");
			break;
			case CODE:
				 sb.append(" order by paramtype.code");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createParamType(ParamType paramtype) throws ValidateFieldsException {
		ParamTypeImpl paramtypeImpl = (ParamTypeImpl) paramtype;
		this.paramtypedao.insert(paramtypeImpl.getParamTypeVO());
	}

	public void removeParamTypes(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			ParamTypeVO vo = this.paramtypedao.findByPrimaryKey(Integer.parseInt(s));
			this.paramtypedao.delete(vo);
		}
	}

	public void updateParamType(ParamType paramtype) throws ValidateFieldsException {
		ParamTypeImpl paramtypeImpl = (ParamTypeImpl) paramtype;

		ParamTypeVO po = paramtypeImpl.getParamTypeVO();
		this.paramtypedao.update(po);
	}

	public ParamType getParamType(int id) {
		Collection<ParamTypeVO> paramtypes = this.paramtypedao.findRecordById(id);

		if (paramtypes == null || paramtypes.size() < 1)
			return null;

		ParamTypeVO paramtype = paramtypes.toArray(new ParamTypeVO[paramtypes.size()])[0];

		return new ParamTypeImpl(paramtype);
	}

}
