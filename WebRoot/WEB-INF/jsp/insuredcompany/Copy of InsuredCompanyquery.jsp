
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="/money/insuredcompany!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label> 保险公司名称: </label>
				<my:newselect tagName="query_comName" paraType="query_str"
					width="140" />
				<input name="comName" class="textInput" type="text" />
			</div>
			<div class="unit">
				<label> 保险公司编号 : </label>
				<my:newselect tagName="query_comNo" paraType="query_str" width="140" />
				<input name="comNo" class="textInput " type="text" />
			</div>
			<div class="unit">
				<label> 状态 : </label>
				<label> 
				<s:iterator value="#request.yesorno_status_list"  >
				 <input type="checkbox" name="query_yesorno_status_list" value='<s:property value="value" />' /> <s:property value="text" />  
				</s:iterator>
			</div>
			<div class="unit">
				<label> 简称: </label>
				<my:newselect tagName="query_comShortName" paraType="query_str"
					width="140" />
				<input name="comShortName" class="textInput " type="text" />
			</div>
			<div class="unit">
				<label> 电话: </label>
				<my:newselect tagName="query_comPhone" paraType="query_str"
					width="140" />
				<input name="comPhone" class="digits " type="text" />
			</div>
			<div class="unit">
				<label> 联系人名称: </label>
				<my:newselect tagName="query_comContactName" paraType="query_str"
					width="140" />
				<input name="comContactName" class="textInput " type="text" />
			</div>
			<div class="unit">
				<label> 联系人手机: </label>
				<my:newselect tagName="query_comContactPhone" paraType="query_str"
					width="140" />
				<input name="comContactPhone" class="textInput " type="text" />
			</div>
			<div class="unit">
				<label> 所属保险公司: </label>
				<s:iterator value="#request.cache_ownerCompany_list" >
				 <input type="checkbox" name="query_ownerCompany_list" value='<s:property value="value" />' /> <s:property value="text" />  
				</s:iterator> 
			</div>
			<div class="unit">
				<label> 邮箱: </label>
				<my:newselect tagName="query_comEmail" paraType="query_str"
					width="140" />
				<input name="comEmail" class="email " type="text" />
			</div>
			<div class="unit">
				<label> 地址: </label>
				<my:newselect tagName="query_comAddress" paraType="query_str"
					width="140" />
				<input name="comAddress" class="email " type="text" />
			</div>
			<div class="unit">
				<label> 备注: </label>
				<my:newselect tagName="query_comRemark" paraType="query_str"
					width="140" />
				<input name="comRemark" class="email " type="text" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">查询</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>