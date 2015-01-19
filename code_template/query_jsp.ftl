<#include "/com.renjie120.codegenerate.common.ftl">
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent"> 
	<form method="post" action="/money/${nm}!query.do" onsubmit="return navTabSearch(this);" rel=""
		class="pageForm required-validate" ">
		<div class="pageFormContent" layoutH="57">
			<#list model.attributes as attr> 
				<#if '${attr.name}'!='${model.keyName}'>
					<#if  attr.complexQueryType??>
					 <div class="unit">
						<label>
							${attr.desc}:
						</label>
						<#if '${attr.complexQueryType}'='date'>
							<my:newselect tagName="condition1_${attr.name}" selectFlag="true" paraType="query_num" width="140" /><input type="text" name="query1_${attr.name}" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
							<my:newselect tagName="condition2_${attr.name}" selectFlag="true" paraType="query_num" width="140" /><input type="text" name="query2_${attr.name}" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a> 
						<#else>
							<#if '${attr.complexQueryType}'='string'>
							<my:newselect tagName="condition_${attr.name}" paraType="query_str" width="140" /><input name="query_${attr.name}" class="textInput" type="text" />
							<#else>
								<#if '${attr.complexQueryType}'='select'>
							<my:newselect tagName="condition_${attr.name}" selectFlag="true"   paraType="common_option" width="100"/>
							<s:iterator value="#request.${attr.name?lower_case}_list"  >
							 <input type="checkbox" name="query_${attr.name}" value='<s:property value="value" />' /> <s:property value="text" />  
							</s:iterator> 
								<#else>
									<#if '${attr.complexQueryType}'='number'>
							<my:newselect  selectFlag="true" tagName="condition1_${attr.name}" paraType="query_num" width="140" /><input name="query1_${attr.name}" class="digits" type="text" /> 
							<my:newselect  selectFlag="true" tagName="condition2_${attr.name}" paraType="query_num" width="140" /><input name="query2_${attr.name}" class="digits" type="text" /> 
										<#else>
											<#if '${attr.complexQueryType}'='dict'>
							<my:newselect  selectFlag="true" tagName="condition_${attr.name}"  paraType="common_option" width="140"/>
							<s:iterator value="#request.${attr.name?lower_case}_list"  >
							 <input type="checkbox" name="query_${attr.name}" value='<s:property value="value" />' /> <s:property value="text" />  
							</s:iterator>  
												<#else>
												<#if '${attr.complexQueryType}'='common'>
							<my:newselect  selectFlag="true" tagName="condition_${attr.name}"  paraType="common_option" width="140"/><input name="query_${attr.name}" class="text" type="text" /> 
													</#if>
												</#if>
										</#if>
								</#if>
							</#if>
						</#if>
					</div>
				</#if>
				</#if>
			</#list>  
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="javascript:$.pdialog.closeCurrent();">
								查询
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>