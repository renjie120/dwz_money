<#include "/com.renjie120.codegenerate.common.ftl">
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">

<hibernate-mapping>
	<class name="money.${class2}.${vo}" table="${model.table}">
		<id name="${model.keyName}" column="${model.keyColumn}" type="${model.keyType}">
			<generator class="increment"></generator>
		</id>
		<#list model.attributes as attr>
			<property name="${attr.name}" column="${attr.name}" type="${attr.type}" />
		</#list> 
	</class>

	<query name="money.${model.className?uncap_first}.${vo}.findRecordById">
		<![CDATA[from ${vo} as ${class2}VO where ${model.keyName} = ?]]>
	</query>
</hibernate-mapping>