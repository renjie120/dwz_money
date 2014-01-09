<#include "/com.renjie120.codegenerate.common.ftl">
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">

<hibernate-mapping>
	<class name="${model.packageName}.${vo}" table="${model.table}">
		<id name="${model.keyName}" column="${model.keyColumn}" type="${model.keyType}">
			<generator class="increment"></generator>
		</id>
		<#list model.attributes as attr>
			<#if attr.name!='${model.keyName}'>
				<property name="${attr.name}" column="${attr.column}" type="${attr.type}" />
			</#if>
		</#list> 
	</class>

	<query name="${model.packageName}.${dao}.findRecordById">
		<![CDATA[from ${vo} as ${class2}Dao where ${model.keyName} = ?]]>
	</query>
</hibernate-mapping>