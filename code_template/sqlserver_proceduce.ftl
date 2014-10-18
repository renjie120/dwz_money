<#include "/com.renjie120.codegenerate.common.ftl">
/************插入${model.classDesc}信息的存储过程***********************/
CREATE PROCEDURE  [${model.table}_Insert] (
<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'>
	@${attr.name}    <#if '${attr.columnType}'=''>${attr.type}<#else>${attr.columnType}</#if>,
	</#if> 
</#list> @flag int OUTPUT, @msg varchar(80) OUTPUT) AS
INSERT INTO ${model.table} (<@allfield2notkey_column nm=model.attributes />)
VALUES (<#assign index=0><#list model.attributes as attr><#if '${attr.name}'!='${model.keyName}'><#if index!=0>,</#if>@${attr.name}<#assign index=index+1></#if></#list>) SET @flag = 1 SET @msg = 'OK!' ;

/************删除${model.classDesc}信息的存储过程***********************/
CREATE PROCEDURE  [${model.table}_Delete] (@${model.keyColumn} ${model.keyType}, @flag int OUTPUT, @msg varchar(80) OUTPUT) AS
DELETE ${model.table} WHERE ${model.keyColumn}=@${model.keyColumn} ;

/************查询${model.classDesc}信息的存储过程***********************/
CREATE PROCEDURE [dbo].[${model.table}_SelectByID] (@${model.keyColumn} ${model.keyType}, @flag	[int]	output, @msg	[varchar](80)	output) AS SELECT * FROM ${model.table} WHERE (${model.keyColumn} = @${model.keyColumn}) set @flag = 1 set @msg = 'OK!';

/************更新${model.classDesc}信息的存储过程***********************/
CREATE PROCEDURE  [${model.table}_Update] (
   @${model.keyColumn} ${model.keyType},
<#list model.attributes as attr>   
	<#if '${attr.name}'!='${model.keyName}'>
   @${attr.name}    <#if '${attr.columnType}'=''>${attr.type}<#else>${attr.columnType}</#if>,
	</#if>
</#list> @flag int OUTPUT, @msg varchar(80) OUTPUT) AS
update ${model.table} set  <@allfield2notkey_updatecolumn nm=model.attributes /> 
where (${model.keyColumn}=@${model.keyColumn} ) SET @flag = 1 SET @msg = 'OK!' ;
