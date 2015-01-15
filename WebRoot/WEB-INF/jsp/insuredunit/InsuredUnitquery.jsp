
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/insuredunit!query.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							编号:
						</label>
							<my:newselect tagName="condition_unitCode"  paraType="common_option" width="140"/><input name="query_unitCode" class="text" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							投保单位 :
						</label>
							<my:newselect tagName="condition_unitName"  paraType="common_option" width="140"/><input name="query_unitName" class="text" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							联系人:
						</label>
							<my:newselect tagName="condition_contactName"  paraType="common_option" width="140"/><input name="query_contactName" class="text" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							手机:
						</label>
							<my:newselect tagName="condition1_contactMobile" paraType="query_num" width="140" /><input name="query_contactMobile" class="digits" type="text" /> 
							<my:newselect tagName="condition2_contactMobile" paraType="query_num" width="140" /><input name="query_contactMobile" class="digits" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<my:newselect tagName="condition_contactEmail" paraType="query_str" width="140" /><input name="contactEmail" class="email" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							上级单位:
						</label>
							<my:newselect tagName="condition_unitParentId"  paraType="common_option" width="140"/><input name="query_unitParentId" class="text" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							是否显示:
						</label>
							<my:newselect tagName="condition_unitState"  paraType="common_option" width="100"/><my:newselect tagName="condition_unitState"  paraType="yesorno" width="100"  />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<my:newselect tagName="condition_unitAddress" paraType="query_str" width="140" /><input name="query_unitAddress" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<my:newselect tagName="condition_unitRemark" paraType="query_str" width="140" /><input name="query_unitRemark" class="textInput" type="text" />
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