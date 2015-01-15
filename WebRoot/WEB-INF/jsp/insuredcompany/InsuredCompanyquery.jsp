
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/insuredcompany!newQuery.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							保险公司名称:
						</label>
							<my:newselect tagName="condition_comName"  paraType="common_option" width="140"/><input name="query_comName" class="text" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							保险公司编号 :
						</label>
							<my:newselect tagName="condition_comNo"  paraType="common_option" width="140"/><input name="query_comNo" class="text" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							状态 :
						</label>
							<my:newselect tagName="condition_comStatus"  paraType="common_option" width="100"/>
							<s:iterator value="#request.comstatus_list"  >
							 <input type="checkbox" name="query_comStatus" value='<s:property value="value" />' /> <s:property value="text" />  
							</s:iterator> 
					</div>
					 <div class="unit">
						<label>
							简称:
						</label>
							<my:newselect tagName="condition_comShortName"  paraType="common_option" width="140"/><input name="query_comShortName" class="text" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							电话:
						</label>
							<my:newselect tagName="condition1_comPhone" paraType="query_num" width="140" /><input name="query1_comPhone" class="digits" type="text" /> 
							<my:newselect tagName="condition2_comPhone" paraType="query_num" width="140" /><input name="query2_comPhone" class="digits" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							联系人名称:
						</label>
							<my:newselect tagName="condition_comContactName"  paraType="common_option" width="140"/><input name="query_comContactName" class="text" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<my:newselect tagName="condition_comContactPhone"  paraType="common_option" width="140"/><input name="query_comContactPhone" class="text" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							所属保险公司:
						</label>
							<my:newselect tagName="condition_ownerCompany"  paraType="common_option" width="140"/>
							<s:iterator value="#request.ownercompany_list"  >
							 <input type="checkbox" name="query_ownerCompany" value='<s:property value="value" />' /> <s:property value="text" />  
							</s:iterator>  
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<my:newselect tagName="condition_comEmail" paraType="query_str" width="140" /><input name="comEmail" class="email" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<my:newselect tagName="condition_comAddress" paraType="query_str" width="140" /><input name="query_comAddress" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<my:newselect tagName="condition_comRemark" paraType="query_str" width="140" /><input name="query_comRemark" class="textInput" type="text" />
					</div>
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