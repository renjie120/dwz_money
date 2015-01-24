<#include "/com.renjie120.codegenerate.common.ftl">
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/${nm}!doAdd.do"
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
							<input type="text" name="${attr.name}" class="date <#if "${attr.notnull}"='true'>required</#if>" size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
						<#else>
							<#if '${attr.showType}'='textarea'>
							<textarea class="<#if "${attr.notnull}"='true'>required</#if>" name="${attr.name}" <#if '${attr.size}'!=''>size="${attr.size}"</#if> <#if '${attr.rows}'!=''>rows="${attr.rows}"</#if> <#if '${attr.cols}'!=''>cols="${attr.cols}"</#if>></textarea>
							<#else>
								<#if '${attr.showType}'='select'>
							<my:newselect tagName="${attr.name}"  paraType="${attr.selectCode}" width="100" <#if "${attr.allSelect}"='true'>allSelected="true"</#if> />
								<#else>
									<#if '${attr.showType}'='email'>
							<input name="${attr.name}"  class="email <#if "${attr.notnull}"='true'>required</#if>" <#if '${attr.size}'!=''>size="${attr.size}"</#if> type="text"   />
									<#else>
										<#if '${attr.showType}'='digits'>
							<input name="${attr.name}"  class="digits <#if "${attr.notnull}"='true'>required</#if>" <#if '${attr.size}'!=''>size="${attr.size}"</#if> type="text"   />
										<#else>
											<#if '${attr.showType}'='number'>
							<input name="${attr.name}"  class="number <#if "${attr.notnull}"='true'>required</#if>" <#if '${attr.size}'!=''>size="${attr.size}"</#if> type="text"   />
											<#else>
												<#if '${attr.showType}'='dict'>
							<my:newselect tagName="${attr.name}"  tableName="${attr.fromTable}" nameColumn="${attr.nameColumn}" idColumn ="${attr.idCoulmn}" width="100" <#if "${attr.allSelect}"='true'>allSelected="true"</#if> />
												<#else>
													<#if '${attr.showType}'='password'>
							<input name="${attr.name}" class="textInput <#if "${attr.notnull}"='true'>required</#if>" <#if '${attr.size}'!=''>size="${attr.size}"</#if> type="password"   />
													<#else>
							<input name="${attr.name}" class="textInput <#if "${attr.notnull}"='true'>required</#if>" <#if '${attr.size}'!=''>size="${attr.size}"</#if> type="text"   />
													</#if>
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