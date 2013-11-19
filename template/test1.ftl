名字:${name}
邮箱:${email}
<#assign x="h">
在haha中查询：${indexOf("haha",x)}
在foo中查询：${indexOf("foo",x)}
<#-- 下面测试自定义指令。内置指令compress用来进行空白压缩的！！ -->
<#compress>
<@upper  >
	bar
	<#list ["red","green"] as color>
		${color}
	</#list>
</@upper>
</#compress>