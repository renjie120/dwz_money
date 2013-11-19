<#compress>
<#recurse doc>  

<#macro book>	
    欢迎使用本书籍---------------<#recurse>
 </#macro> 
 
 <#macro title>
 <#if "${.node.@level}"='1'>-------- 《<#recurse>》<#else>---《<#recurse>》</#if>
 </#macro>
 
<#macro chapter>  
作者:${.node.@author}   <#if  .node.@time[0]?? >创作时间：${.node.@time}</#if>   <#if  .node.@address[0]?? >创作地点：${.node.@address}</#if>  <#recurse>
 </#macro> 
 
<#macro para>	
	细节: <#recurse>
</#macro>
 
 <#macro @text>${.node?html}</#macro>
 </#compress>