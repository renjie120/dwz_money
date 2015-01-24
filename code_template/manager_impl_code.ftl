<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};
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
 * 关于${model.classDesc}的业务操作实现类.
 * @author ${author}
 * ${auth}
 * ${website}
 */ 
public class ${model.className}ManagerImpl extends AbstractBusinessObjectManager implements
		${model.className}Manager {

	private ${dao} ${daoarg} = null;

	/**
	 * 构造函数.
	 */
	public ${model.className}ManagerImpl(${dao} ${daoarg}) {
		this.${daoarg} = ${daoarg};
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer search${model.className}Num(Map<${model.className}SearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.${daoarg}.countByQuery(hql,
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
				${vo} vo = new ${vo}();
				<#assign import_index=0>
				<#list model.attributes as attr>
				<#if attr.canImport='true'>
				//导入${attr.desc}
				String ${attr.name}Str = contents[i][${import_index}];
				vo.set${attr.name?cap_first}(${attr.name}Str);
				
				<#assign import_index=import_index+1>
				</#if>
				</#list>
				this.${daoarg}.insert(vo); 
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
	public Collection<${model.className}> search${model.className}(Map<${model.className}SearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<${model.className}> eaList = new ArrayList<${model.className}>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<${vo}> voList = this.${daoarg}.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		<#list model.attributes as attr>
		<#assign has_selec_type=0>
		<#if '${attr.showType}'='select'&&has_selec_type=0>
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		<#assign has_selec_type=1>
		</#if>	
		<#if '${attr.showType}'='select'>
		ParamSelect select_${attr.selectCode} = allSelect
				.getParamsByType(AllSelectContants.${attr.selectCode?upper_case}.getName());
		</#if>
		<#if '${attr.showType}'='dict'>
		Cache cache_${attr.name} = CacheManager.getCacheInfoNotNull(AllSelectContants.${attr.useCacheId?upper_case}.getName());
		ParamSelect select_${attr.name} = (ParamSelect)cache_${attr.name}.getValue();
		</#if>
		</#list>
		
		for (${vo} po : voList) {
			<#list model.attributes as attr> 
			<#if '${attr.showType}'='select'>
			po.set${attr.name?cap_first}(select_${attr.selectCode}.getName("" + po.get${attr.name?cap_first}())); 
			</#if>
			<#if '${attr.showType}'='dict'>
			po.set${attr.name?cap_first}(select_${attr.name}.getName("" + po.get${attr.name?cap_first}())); 
			</#if>
			</#list>
			eaList.add(new  ${model.className}Impl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<${model.className}SearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( ${classarg}) "
						: "select  ${classarg} ").append("from ${vo} as ${classarg} ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<${model.className}SearchFields, Object> entry : criterias
					.entrySet()) {
				${model.className}SearchFields fd = entry.getKey();
				switch (fd) {
				<#list model.attributes as attr>
					case ${attr.name?upper_case}:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} <#if '${attr.type}'='string'&&'${attr.querylike}'='true'>like<#else>=</#if> ? ");
						<#if '${attr.type}'='string'&&'${attr.querylike}'='true'>
						argList.add("%"+entry.getValue()+"%");
						<#else>
						argList.add(entry.getValue());
						</#if>
						count++;
					break;
				</#list> 
				//下面拼接高级查询条件
				<#list model.attributes as attr>
					<#if '${attr.name}'!='${model.keyName}'>
						<#if attr.complexQueryType??> 
							<#if '${attr.complexQueryType}'='date'>
					case ${attr.name?upper_case}_DATE_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ${attr.name?upper_case}_DATE_NOT_LATTER:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} <=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ${attr.name?upper_case}_DATE_NOT_EARLY:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} >=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ${attr.name?upper_case}_DATE_LATTER:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name}  > ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ${attr.name?upper_case}_DATE_EARLY:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} <  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
							<#else>
								<#if '${attr.complexQueryType}'='number'>
					case ${attr.name?upper_case}_NUM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ${attr.name?upper_case}_NUM_SMALL:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} <  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ${attr.name?upper_case}_NUM_NOT_SMALL:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} >=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ${attr.name?upper_case}_NUM_BIG:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} >  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ${attr.name?upper_case}_NUM_NOT_BIG:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} <=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
								<#else>
									<#if '${attr.complexQueryType}'='string'>
					case ${attr.name?upper_case}_STR_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name}  =  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ${attr.name?upper_case}_STR_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case ${attr.name?upper_case}_STR_NOT_LIKE:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} not like  ? "); 
						argList.add("%"+entry.getValue()+"%"); 
						count++;
					break;
					case ${attr.name?upper_case}_STR_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
									<#else>
										<#if '${attr.complexQueryType}'='dict'||'${attr.complexQueryType}'='select'>
					case ${attr.name?upper_case}_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name}  not in (  "); 
						String _temp_${attr.name?cap_first}1 = ""+entry.getValue();
						String[] _temp_arr_${attr.name?cap_first}1 = _temp_${attr.name?cap_first}1.split(",");
						int _int_${attr.name?cap_first}1 = _temp_arr_${attr.name?cap_first}1.length;
						for(int _i=0;_i<_int_${attr.name?cap_first}1;_i++){
							sb.append(" ? ,");
							argList.add(_temp_arr_${attr.name?cap_first}1[_i]); 
						}
						sb = sb.deleteCharAt(sb.length()-1); 
						sb.append(" ) ");
						count++;
					break;
					case ${attr.name?upper_case}_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} in  (   "); 
						String _temp_${attr.name?cap_first}2 = ""+entry.getValue();
						String[] _temp_arr_${attr.name?cap_first}2 = _temp_${attr.name?cap_first}2.split(",");
						int _int_${attr.name?cap_first}2 = _temp_arr_${attr.name?cap_first}2.length;
						for(int _i=0;_i<_int_${attr.name?cap_first}2;_i++){
							sb.append(" ? ,");
							argList.add(_temp_arr_${attr.name?cap_first}2[_i]); 
						}
						sb = sb.deleteCharAt(sb.length()-1); 
						sb.append(" ) ");
						count++;
					break;
										<#else>
					case ${attr.name?upper_case}_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name}  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ${attr.name?upper_case}_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  ${classarg}.${attr.name} =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
										</#if>
									</#if>
								</#if>
							</#if>
						</#if>	 
					</#if>	 
				</#list> 
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		${model.className}OrderByFields orderBy = ${model.className}OrderByFields.${model.keyName?upper_case}_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = ${model.className}OrderByFields.valueOf(orderField);
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

	public void create${model.className}(${model.className} ${classarg}) throws ValidateFieldsException {
		${model.className}Impl ${classarg}Impl = (${model.className}Impl) ${classarg};
		this.${daoarg}.insert(${classarg}Impl.get${model.className}VO());
	}

	public void remove${model.className}s(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			${vo} vo = this.${daoarg}.findByPrimaryKey(Integer.parseInt(s));
			this.${daoarg}.delete(vo);
		}
	}

	public void update${model.className}(${model.className} ${classarg}) throws ValidateFieldsException {
		${model.className}Impl ${classarg}Impl = (${model.className}Impl) ${classarg};

		${vo} po = ${classarg}Impl.get${model.className}VO();
		this.${daoarg}.update(po);
	}

	public ${model.className} get${model.className}(${model.keyType} id) {
		Collection<${vo}> ${classarg}s = this.${daoarg}.findRecordById(id);

		if (${classarg}s == null || ${classarg}s.size() < 1)
			return null;

		${vo} ${classarg} = ${classarg}s.toArray(new ${vo}[${classarg}s.size()])[0];

		return new ${model.className}Impl(${classarg});
	}

	<#if model.addToCache='true'> 	 
	@Override
	public void addCache() {
		ParamSelect ans = null;
		Collection<${vo}> all = this.${daoarg}.findAll();
		ans = new ParamSelect(all);
		String _tempCacheId = AllSelectContants.${model.cacheName?upper_case}.getName();
		CacheManager.clearOnly(_tempCacheId);
		Cache c = new Cache();
		c.setKey(_tempCacheId);
		c.setValue(ans);
		c.setName("${model.classDesc}");
		CacheManager.putCache(_tempCacheId, c);
	}
	</#if>
}
