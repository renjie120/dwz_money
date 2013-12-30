<#-- 将一个数据类型转换为对应的大写类型.除去int，double等类型 全部首字母大写，否则根据是否是key保持本来面目-->
<#macro datatype nm key><#if '${nm}'!='int'&&'${nm}'!='float'&&'${nm}'!='double'&&'${nm}'!='boolean'>${nm?cap_first}<#else><#if key='true'><#if nm='int'>Integer<#elseif nm='double'>Double<#elseif nm='float'>Float<#else>${nm}</#if><#else>${nm}</#if></#if></#macro>

<#-- 将一个数据类型转换为对应的大写类型.除去int，double等类型 全部首字母大写，否则保持本来面目 -->
<#macro datatype2 nm><#if '${nm}'!='int'&&'${nm}'!='float'&&'${nm}'!='double'&&'${nm}'!='boolean'>${nm?cap_first}<#else>${nm}</#if></#macro>

<#-- 修改类型名称.-->
<#macro changetype nm><#if nm='int'>Integer<#elseif nm='double'>Double<#elseif nm='float'>Float<#else>${nm}</#if></#macro>

 
<#assign numPerPage=r"${numPerPage}"/>
<#assign pageNum=r"${pageNum}"/>
<#assign orderField=r"${param.orderField}"/>
<#assign orderDirection=r"${param.orderDirection}"/> 
<#assign totalCount=r"${totalCount}"/>
<#assign bignm>${model.className?cap_first}</#assign>
<#assign nm>${model.className?lower_case}</#assign>
<#assign classarg>${model.className?lower_case}</#assign>
<#assign daoarg>${model.className?lower_case}dao</#assign>
<#assign dao>${model.className?cap_first}Dao</#assign>
<#assign vo>${model.className?cap_first}VO</#assign>
<#assign class2>${model.className?uncap_first}</#assign>  

<#macro arg nm>${nm?lower_case}</#macro>
 

<#macro big nm>${nm?upper_case}</#macro>

<#-- 全部的属性参数连接字符串 Arg arg1,Arg2 arg2。。。。。-->
<#macro allfield nm><#assign index=0><#assign size=nm?size><#list nm as attr> <@datatype2 nm=attr.type/> ${attr.name} <#assign index=index+1><#if index<size>,</#if></#list></#macro>

<#-- 全部的属性除去主键.参数连接字符串 Arg arg1,Arg2 arg2。。。。。-->
<#macro allfieldnotkey nm><#assign index=0><#assign size=nm?size><#list nm as attr><#if '${attr.name}'!='${model.keyName}'><@datatype2 nm=attr.type/> ${attr.name} <#assign index=index+1><#if index<size>,</#if><#else><#assign index=index+1></#if></#list></#macro>


<#-- 全部的属性的连接字符串 -->
<#macro allfield2 nm><#assign index=0><#assign size=nm?size><#list nm as attr> ${attr.name} <#assign index=index+1><#if index<size>,</#if></#list></#macro>

<#-- 全部的属性除去主键的连接字符串 -->
<#macro allfield2notkey nm><#assign index=0><#assign size=nm?size><#list nm as attr><#if '${attr.name}'!='${model.keyName}'>${attr.name} <#assign index=index+1><#if index<size>,</#if><#else><#assign index=index+1></#if></#list></#macro>


<#-- 全部的属性的排序字符串的连接字符串 -->
<#macro allorderfield nm><#assign index=0><#assign size=nm?size><#list nm as attr> ${attr.name?upper_case},  ${attr.name?upper_case}_DESC <#assign index=index+1><#if index<size>,</#if></#list></#macro>

<#-- 全部的属性的大写的连接字符串. -->
<#macro allbigfield nm><#assign index=0><#assign size=nm?size><#list nm as attr> ${attr.name?upper_case} <#assign index=index+1><#if index<size>,</#if></#list></#macro>

<#-- 得到全部的get和set方法. -->
<#macro allGetAndSet nm>
	<#list nm as attr> 
	private <@datatype nm="${attr.type}" key="${attr.iskey}"/> ${attr.name}; 
 	/**
 	 * 获取${attr.desc}的属性值.
 	 */
 	public <@datatype nm="${attr.type}" key="${attr.iskey}"/> get${attr.name?cap_first}(){
 		return ${attr.name};
 	}
 	
 	/**
 	 * 设置${attr.desc}的属性值.
 	 */
 	public void set${attr.name?cap_first}(<@datatype nm="${attr.type}" key="${attr.iskey}"/> <@arg nm="${attr.name}"/>){
 		this.${attr.name} = <@arg nm="${attr.name}"/>;
 	}
 	 </#list>
</#macro>

<#assign author="www(水清)">

<#assign auth="任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.">

<#assign website="http://www.iteye.com">