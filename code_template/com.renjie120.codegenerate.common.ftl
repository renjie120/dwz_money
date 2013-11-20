<#-- 将一个数据类型转换为对应的大写类型.除去int，double等类型 全部首字母大写，否则根据是否是key保持本来面目-->
<#macro datatype nm key><#if '${nm}'!='int'&&'${nm}'!='float'&&'${nm}'!='double'&&'${nm}'!='boolean'>${nm?cap_first}<#else><#if key='true'><#if nm='int'>Integer<#elseif nm='double'>Double<#elseif nm='float'>Float<#else>${nm}</#if><#else>${nm}</#if></#if></#macro>

<#-- 将一个数据类型转换为对应的大写类型.除去int，double等类型 全部首字母大写，否则保持本来面目 -->
<#macro datatype2 nm><#if '${nm}'!='int'&&'${nm}'!='float'&&'${nm}'!='double'&&'${nm}'!='boolean'>${nm?cap_first}<#else>${nm}</#if></#macro>

<#assign daoarg>${model.className?lower_case}dao</#assign>
<#assign dao>${model.className?cap_first}Dao</#assign>
<#assign vo>${model.className?cap_first}VO</#assign> 

<#macro arg nm>${nm?lower_case}</#macro>

<#macro big nm>${nm?upper_case}</#macro>

<#macro allfield nm><#assign index=0><#assign size=nm?size><#list nm as attr> <@datatype2 nm=attr.type/> ${attr.name} <#assign index=index+1><#if index<size>,</#if></#list></#macro>

<#macro allfield2 nm><#assign index=0><#assign size=nm?size><#list nm as attr> ${attr.name} <#assign index=index+1><#if index<size>,</#if></#list></#macro>

<#macro allorderfield nm><#assign index=0><#assign size=nm?size><#list nm as attr> ${attr.name?upper_case},  ${attr.name?upper_case}_DESC <#assign index=index+1><#if index<size>,</#if></#list></#macro>

<#macro allbigfield nm><#assign index=0><#assign size=nm?size><#list nm as attr> ${attr.name?upper_case} <#assign index=index+1><#if index<size>,</#if></#list></#macro>

<#macro manager nm>${nm?cap_first}Manager</#macro>

<#assign author="www(水清)">

<#assign auth="任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.">

<#assign website="http://www.iteye.com">