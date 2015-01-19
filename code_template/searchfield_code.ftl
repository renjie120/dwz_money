<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};

/**
 * 关于${model.classDesc}的查询枚举类.
 * @author ${author}
 * ${auth}
 * ${website}
 */
public enum ${model.className?cap_first}SearchFields { 
 <@allbigfield nm=model.attributes />
<#list model.attributes as attr> 
	<#if '${attr.name}'!='${model.keyName}'>
		<#if attr.complexQueryType??> 
			<#if '${attr.complexQueryType}'='date'>
,${attr.name?upper_case}_DATE_EQUALS,${attr.name?upper_case}_DATE_NOT_LATTER,${attr.name?upper_case}_DATE_NOT_EARLY,${attr.name?upper_case}_DATE_LATTER,${attr.name?upper_case}_DATE_EARLY
			<#else>
				<#if '${attr.complexQueryType}'='number'>
,${attr.name?upper_case}_NUM_EQUALS,${attr.name?upper_case}_NUM_SMALL,${attr.name?upper_case}_NUM_NOT_SMALL,${attr.name?upper_case}_NUM_BIG,${attr.name?upper_case}_NUM_NOT_BIG
				<#else>
					<#if '${attr.complexQueryType}'='string'>
,${attr.name?upper_case}_STR_EQUALS,${attr.name?upper_case}_STR_LIKE,${attr.name?upper_case}_STR_NOT_LIKE,${attr.name?upper_case}_STR_NOT_EQUALS
					<#else>
,${attr.name?upper_case}_COM_EQUALS,${attr.name?upper_case}_COM_NOT_EQUALS
					</#if>
				</#if>
			</#if>
		</#if>
	</#if>
</#list>
}
