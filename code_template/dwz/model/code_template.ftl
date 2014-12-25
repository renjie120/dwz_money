<#include "/com.renjie120.codegenerate.common.ftl">  
<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'>
String ${attr.name} = Util.fromScreen3(fu.getParameter("${attr.name}"),user.getLanguage());//${attr.desc}
	</#if> 
</#list>  

<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'>
	ProcPara += flag+${attr.name};//${attr.desc}
	</#if> 
</#list>  



<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'>
		//记录“${attr.desc}”的变化日志
		strTemp = RecordSet.getString("${attr.column}");
		if(!${attr.name}.equals(strTemp))
		{
			fieldName ="${attr.desc}";
			ProcPara = CustomerID+flag+"1"+flag+"0"+flag+"0";
			ProcPara += flag+fieldName+flag+CurrentDate+flag+CurrentTime+flag+strTemp+flag+${attr.name};
			ProcPara += flag+CurrentUser+flag+SubmiterType+flag+ClientIP;
			RecordSetT.executeProc("CRM_Modify_Insert",ProcPara);
			bNeedUpdate = true;
		}
	</#if> 
</#list>  


select <#list model.attributes as attr>${attr.column},</#list>  from  ${model.table}
