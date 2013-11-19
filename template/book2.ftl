<h1>${doc.book.title}</h1>   
<#--下面声明一个全局变量 -->
<#assign book =doc.book>
<#list book.chapter as ch>
	<#--输出一个xml里面的属性，用@ -->
	<h2>${ch.title}  作者:${ch.@author}</h2>
	<#list ch.para as p>
		<p>${p}
	</#list>
</#list>

<#-- 下面循环处理xml全部book的子节点，然后判断类型是不是element，是的话打印对应的节点名称. -->
<#-- node_type可以是：element，text,comment,pi等 -->
<#list book?children as c>
-${c?node_type}<#if c?node_type='element'>当前对象：${c?node_name}
<#-- 如果是element,下面使用@@打印节点的全部属性 -->
${c.@@start_tag} ############### ${c.@@end_tag}  
<#list c.@@ as attr>
	--${attr?node_name} = ${attr}
</#list>
</#if> 
</#list>

<#-- 打印所有chapter的author属性 -->
<#list book.chapter.@author as t>
	${t}
</#list>


