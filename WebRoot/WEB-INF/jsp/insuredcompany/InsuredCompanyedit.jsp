
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.InsuredCompany.InsuredCompany"%>
<% 
	InsuredCompany vo = (InsuredCompany) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/insuredcompany!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							保险公司名称:
						</label>
							<input name="comName"   class="textInput  required"    type="text"  value="<s:property value="vo.comName"/>" />
					</div>
					 <div class="unit">
						<label>
							保险公司编号 :
						</label>
							<input name="comNo"   class="textInput  "    type="text"  value="<s:property value="vo.comNo"/>" />
					</div>
					 <div class="unit">
						<label>
							状态 :
						</label>
							<my:newselect tagName="comStatus"  paraType="yesorno_status" width="100"  selectedValue="<%=vo.getComStatus() %>"/>									
					</div>
					 <div class="unit">
						<label>
							简称:
						</label>
							<input name="comShortName"   class="textInput  "    type="text"  value="<s:property value="vo.comShortName"/>" />
					</div>
					 <div class="unit">
						<label>
							电话:
						</label>
							<input name="comPhone"   class="digits  "   type="text"  value="<s:property value="vo.comPhone"/>" />
					</div>
					 <div class="unit">
						<label>
							联系人名称:
						</label>
							<input name="comContactName"   class="textInput  "    type="text"  value="<s:property value="vo.comContactName"/>" />
					</div>
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<input name="comContactPhone"   class="textInput  "    type="text"  value="<s:property value="vo.comContactPhone"/>" />
					</div>
					 <div class="unit">
						<label>
							所属保险公司:
						</label>
							<my:newselect tagName="ownerCompany"   tableName="dict_Insured_company" nameColumn="com_name" idColumn ="id"  width="100"   selectedValue="<%=vo.getOwnerCompany() %>"/>									
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="comEmail"   class="email  "    type="text"  value="<s:property value="vo.comEmail"/>" />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<textarea     class="" name="comAddress" cols="30" rows="2"><s:property value="vo.comAddress"/></textarea>
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<textarea     class="" name="comRemark" cols="30" rows="2"><s:property value="vo.comRemark"/></textarea>
					</div>
					 <div class="unit">
						<label>
							创建用户:
						</label>
							<input name="createUser"   class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createUser"/>" />
					</div>
					 <div class="unit">
						<label>
							创建时间:
						</label>
							<input name="createTime"   class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createTime"/>" />
					</div>
					 <div class="unit">
						<label>
							更新用户:
						</label>
							<input name="updateUser"   class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.updateUser"/>" />
					</div>
					 <div class="unit">
						<label>
							更新时间:
						</label>
							<input name="updateTime"   class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.updateTime"/>" />
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

