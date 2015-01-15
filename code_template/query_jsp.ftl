<#include "/com.renjie120.codegenerate.common.ftl">
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/${nm}!newQuery.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
			<#list model.attributes as attr> 
				<#if '${attr.name}'!='${model.keyName}'>
					<#if '${attr.noadd}'!='true'>
					 <div class="unit">
						<label>
							${attr.desc}:
						</label>
						<#if '${attr.showType}'='date'>
							<my:newselect tagName="condition1_${attr.name}" paraType="query_num" width="140" /><input type="text" name="query1_${attr.name}" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
							<my:newselect tagName="condition2_${attr.name}" paraType="query_num" width="140" /><input type="text" name="query2_${attr.name}" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a> 
						<#else>
							<#if '${attr.showType}'='textarea'>
							<my:newselect tagName="condition_${attr.name}" paraType="query_str" width="140" /><input name="query_${attr.name}" class="textInput" type="text" />
							<#else>
								<#if '${attr.showType}'='select'>
							<my:newselect tagName="condition_${attr.name}"  paraType="common_option" width="100"/>
							<s:iterator value="#request.${attr.name?lower_case}_list"  >
							 <input type="checkbox" name="query_${attr.name}" value='<s:property value="value" />' /> <s:property value="text" />  
							</s:iterator> 
								<#else>
									<#if '${attr.showType}'='email'>
							<my:newselect tagName="condition_${attr.name}" paraType="query_str" width="140" /><input name="${attr.name}" class="email" type="text" /> 
									<#else>
										<#if '${attr.showType}'='digits'>
							<my:newselect tagName="condition1_${attr.name}" paraType="query_num" width="140" /><input name="query1_${attr.name}" class="digits" type="text" /> 
							<my:newselect tagName="condition2_${attr.name}" paraType="query_num" width="140" /><input name="query2_${attr.name}" class="digits" type="text" /> 
										<#else>
											<#if '${attr.showType}'='number'>
							<my:newselect tagName="condition1_${attr.name}" paraType="query_num" width="140" /><input name="query1_${attr.name}" class="number" type="text" /> 
							<my:newselect tagName="condition2_${attr.name}" paraType="query_num" width="140" /><input name="query2_${attr.name}" class="number" type="text" /> 
											<#else>
												<#if '${attr.showType}'='dict'>
							<my:newselect tagName="condition_${attr.name}"  paraType="common_option" width="140"/>
							<s:iterator value="#request.${attr.name?lower_case}_list"  >
							 <input type="checkbox" name="query_${attr.name}" value='<s:property value="value" />' /> <s:property value="text" />  
							</s:iterator>  
												<#else>
							<my:newselect tagName="condition_${attr.name}"  paraType="common_option" width="140"/><input name="query_${attr.name}" class="text" type="text" /> 
												</#if>
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
							<button type="submit">
								保存
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