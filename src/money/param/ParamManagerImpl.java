
package money.param;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于参数的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ParamManagerImpl extends AbstractBusinessObjectManager implements
		ParamManager {

	private ParamDao paramdao = null;

	/**
	 * 构造函数.
	 */
	public ParamManagerImpl(ParamDao paramdao) {
		this.paramdao = paramdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchParamNum(Map<ParamSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.paramdao.countByQuery(hql,
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
	public Collection<Param> searchParam(Map<ParamSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<Param> eaList = new ArrayList<Param>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<ParamVO> voList = this.paramdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (ParamVO po : voList) {
			eaList.add(new  ParamImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<ParamSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( param) "
						: "select  param ").append("from ParamVO as param ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<ParamSearchFields, Object> entry : criterias
					.entrySet()) {
				ParamSearchFields fd = entry.getKey();
				switch (fd) {
					case PARAMID:
						sb.append(count == 0 ? " where" : " and").append(
								"  param.paramId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PARAMTYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  param.paramType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PARAMNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  param.paramName like ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PARAMVALUE:
						sb.append(count == 0 ? " where" : " and").append(
								"  param.paramValue = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USEVALUE:
						sb.append(count == 0 ? " where" : " and").append(
								"  param.usevalue like ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ORDERID:
						sb.append(count == 0 ? " where" : " and").append(
								"  param.orderId = ? ");
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

		ParamOrderByFields orderBy = ParamOrderByFields.PARAMID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = ParamOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case PARAMID:
				 sb.append(" order by param.paramId");
			break;
			case PARAMTYPE:
				 sb.append(" order by param.paramType");
			break;
			case PARAMNAME:
				 sb.append(" order by param.paramName");
			break;
			case PARAMVALUE:
				 sb.append(" order by param.paramValue");
			break;
			case USEVALUE:
				 sb.append(" order by param.usevalue");
			break;
			case ORDERID:
				 sb.append(" order by param.orderId");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createParam(Param param) throws ValidateFieldsException {
		ParamImpl paramImpl = (ParamImpl) param;
		this.paramdao.insert(paramImpl.getParamVO());
	}

	public void removeParams(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			ParamVO vo = this.paramdao.findByPrimaryKey(Integer.parseInt(s));
			this.paramdao.delete(vo);
		}
	}

	public void updateParam(Param param) throws ValidateFieldsException {
		ParamImpl paramImpl = (ParamImpl) param;

		ParamVO po = paramImpl.getParamVO();
		this.paramdao.update(po);
	}

	public Param getParam(int id) {
		Collection<ParamVO> params = this.paramdao.findRecordById(id);

		if (params == null || params.size() < 1)
			return null;

		ParamVO param = params.toArray(new ParamVO[params.size()])[0];

		return new ParamImpl(param);
	}

}
