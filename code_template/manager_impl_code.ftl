<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于${model.classDesc}的业务操作实现类.
 * @author ${author}
 * ${auth}
 * ${website}
 */ 
public class ${nm}ManagerImpl extends AbstractBusinessObjectManager implements
		${nm}Manager {

	private ${dao} ${daoarg} = null;

	public ${nm}ManagerImpl(${dao} ${daoarg}) {
		this.${daoarg} = ${daoarg};
	}

	public Integer search${nm}Num(Map<${nm}SearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.${daoarg}.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	public Collection<${nm}> search${nm}(Map<${nm}SearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<${nm}> eaList = new ArrayList<${nm}>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<${vo}> voList = this.${daoarg}.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (${vo} po : voList) {
			eaList.add(new ${nm}>Impl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<${nm}SearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct ${classarg}) "
						: "select distinct ${classarg} ").append("from ${vo} as ${classarg} ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<${nm}SearchFields, Object> entry : criterias
					.entrySet()) {
				${nm}SearchFields fd = entry.getKey();
				switch (fd) {
				<#list model.attributes as attr>
					case ${attr.name?upper_case}:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} <#if '${attr.type}'='string'>like<#else>=</#if> ? ");
						argList.add(entry.getValue());
						count++;
					break;
				</#list> 
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		${nm}OrderByFields orderBy = ${nm}OrderByFields.${model.keyName?upper_case}_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = ${nm}OrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		<#list model.attributes as attr>
			case ${attr.name?upper_case}:
				 sb.append(" order by ${classarg}.${attr.name}");
			break;
		</#list>  
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void create${nm}(${nm} ${classarg}) throws ValidateFieldsException {
		${nm}Impl ${classarg}Impl = (${nm}Impl) ${classarg};
		this.${daoarg}.insert(${classarg}Impl.get${nm}VO());
	}

	public void remove${nm}(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			${vo} vo = this.${daoarg}.findByPrimaryKey(Integer.parseInt(s));
			this.${daoarg}.delete(vo);
		}
	}

	public void update${nm}(${nm} ${classarg}) throws ValidateFieldsException {
		${nm}Impl ${classarg}Impl = (${nm}Impl) ${classarg};

		${vo} po = ${classarg}Impl.get${nm}VO();
		this.${daoarg}.update(po);
	}

	public ${nm} get${nm}(${nm}Integer id) {
		Collection<${vo}> ${classarg}s = this.${daoarg}.findRecordById(id);

		if (${classarg}s == null || ${classarg}s.size() < 1)
			return null;

		${vo} ${classarg} = ${classarg}s.toArray(new ${vo}[${classarg}s.size()])[0];

		return new ${nm}Impl(${classarg});
	}

}
