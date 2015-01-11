<#include "/com.renjie120.codegenerate.common.ftl">
/*${model.classDesc}的建表语句*/
create table ${model.table}(
	${model.keyColumn} <#if model.keyType='int'>  ${model.keyColumnType}  AUTO_INCREMENT <#else> ${model.keyColumnType} </#if>  key,
	<#assign index=0> 
	<#list model.attributes as attr>
		<#if attr.name!='${model.keyName}'> 
	 <#if index!=0>,</#if> 
	 ${attr.column} ${attr.columnType} <#if "${attr.notNullInDb}"='true'>not null</#if> 
		<#assign index=index+1>
		</#if> 
	</#list>
)