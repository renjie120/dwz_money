<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public class ${model.className}ManagerImpl extends AbstractBusinessObjectManager implements
		${model.className}Manager {

	private ${dao} ${daoarg} = null;

	public ${model.className}ManagerImpl(${dao} ${daoarg}) {
		this.${daoarg} = ${daoarg};
	}

	public Integer search${model.className}Num(Map<${model.className}SearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.${daoarg}.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	public Collection<${model.className}> search${model.className}(Map<${model.className}SearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<${model.className}> eaList = new ArrayList<${model.className}>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<${vo}> voList = this.${daoarg}.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (${vo} po : voList) {
			eaList.add(new ${model.className}>Impl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<${model.className}SearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct ${model.className?lower_case}) "
						: "select distinct ${model.className?lower_case} ").append("from ${vo} as ${model.className?lower_case} ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<${model.className}SearchFields, Object> entry : criterias
					.entrySet()) {
				${model.className}SearchFields fd = entry.getKey();
				switch (fd) {
				case ORGID:
					sb.append(count == 0 ? " where" : " and").append(
							" org.orgId =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ORGNAME:
					sb.append(count == 0 ? " where" : " and").append(
							" org.orgName like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ORDERCODE:
					sb.append(count == 0 ? " where" : " and").append(
							" org.orderCode like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case PARENTORG:
					sb.append(count == 0 ? " where" : " and").append(
							" org.parentOrg like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ORDERID:
					sb.append(count == 0 ? " where" : " and").append(
							" org.orderId like ? ");
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

		${model.className}OrderByFields orderBy = ${model.className}OrderByFields.${model.keyName?upper_case}_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = OrgOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case ORGID:
			sb.append(" order by org.orgId");
			break;
		case ORGID_DESC:
			sb.append(" order by org.orgId desc");
			break;
		case ORGNAME:
			sb.append(" order by org.orgName");
			break;
		case ORGNAME_DESC:
			sb.append(" order by org.orgName desc");
			break;
		case ORDERCODE:
			sb.append(" order by org.orderCode");
			break;
		case ORDERCODE_DESC:
			sb.append(" order by org.orderCode desc");
			break;
		case PARENTORG:
			sb.append(" order by org.parentOrg");
			break;
		case PARENTORG_DESC:
			sb.append(" order by org.parentOrg desc");
			break;
		case ORDERID:
			sb.append(" order by org.orderId");
			break;
		case ORDERID_DESC:
			sb.append(" order by org.orderId desc");
			break;

		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createOrg(Org org) throws ValidateFieldsException {
		OrgImpl orgImpl = (OrgImpl) org;
		this.${daoarg}.insert(orgImpl.getOrgVO());
	}

	public void removeOrg(String orgIds) {
		String[] idArr = orgIds.split(",");
		for (String s : idArr) {
			${vo} vo = this.${daoarg}.findByPrimaryKey(Integer.parseInt(s));
			this.${daoarg}.delete(vo);
		}
	}

	public void updateOrg(Org org) throws ValidateFieldsException {
		OrgImpl orgImpl = (OrgImpl) org;

		${vo} po = orgImpl.getOrgVO();
		this.${daoarg}.update(po);
	}

	public Org getOrg(Integer id) {
		Collection<${vo}> orgs = this.${daoarg}.findRecordById(id);

		if (orgs == null || orgs.size() < 1)
			return null;

		${vo} org = orgs.toArray(new ${vo}[orgs.size()])[0];

		return new OrgImpl(org);
	}

}
