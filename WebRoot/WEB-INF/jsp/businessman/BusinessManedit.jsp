
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.BusinessMan.BusinessMan"%>
<% 
	BusinessMan vo = (BusinessMan) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/businessman!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							商家编号:
						</label>
							<input name="shopmSno"   class="textInput  required"    type="text"  value="<s:property value="vo.shopmSno"/>" />
					</div>
					 <div class="unit">
						<label>
							商家名称 :
						</label>
							<input name="shopmName"   class="textInput  required"    type="text"  value="<s:property value="vo.shopmName"/>" />
					</div>
					 <div class="unit">
						<label>
							商家简称 :
						</label>
							<input name="shopmShortName"   class="textInput  required"    type="text"  value="<s:property value="vo.shopmShortName"/>" />
					</div>
					 <div class="unit">
						<label>
							商家类型 :
						</label>
							<my:newselect tagName="shopmType"  paraType="shopman_status" width="100"  selectedValue="<%=vo.getShopmType() %>"/>									
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="shopmEmail"   class="email  required"    type="text"  value="<s:property value="vo.shopmEmail"/>" />
					</div>
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<input name="shopmConPhone"   class="textInput  required"    type="text"  value="<s:property value="vo.shopmConPhone"/>" />
					</div>
					 <div class="unit">
						<label>
							联系人名称:
						</label>
							<input name="shopmContactName"   class="textInput  required"    type="text"  value="<s:property value="vo.shopmContactName"/>" />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<textarea     class="required" name="shopmAddress" cols="30" rows="2"><s:property value="vo.shopmAddress"/></textarea>
					</div>
					 <div class="unit">
						<label>
							开户行:
						</label>
							<input name="openBank"   class="textInput  required"    type="text"  value="<s:property value="vo.openBank"/>" />
					</div>
					 <div class="unit">
						<label>
							户名:
						</label>
							<input name="openBankName"   class="textInput  "    type="text"  value="<s:property value="vo.openBankName"/>" />
					</div>
					 <div class="unit">
						<label>
							银行帐号:
						</label>
							<input name="openBankNo"   class="textInput  "    type="text"  value="<s:property value="vo.openBankNo"/>" />
					</div>
					 <div class="unit">
						<label>
							开票单位:
						</label>
							<input name="openTicketUnit"   class="textInput  "    type="text"  value="<s:property value="vo.openTicketUnit"/>" />
					</div>
					 <div class="unit">
						<label>
							开户所在省:
						</label>
							<my:newselect tagName="OpenBankProvince"   tableName="dict_province" nameColumn="s_provname" idColumn ="n_provid"  width="100"   selectedValue="<%=vo.getOpenBankProvince() %>"/>									
					</div>
					 <div class="unit">
						<label>
							开户所在市:
						</label>
							<my:newselect tagName="OpenBankCity"   tableName="dict_city" nameColumn="s_cityname" idColumn ="n_cityid"  width="100"   selectedValue="<%=vo.getOpenBankCity() %>"/>									
					</div>
					 <div class="unit">
						<label>
							理赔截止日期:
						</label>
							<input name="compensationDay"   class="textInput  "    type="text"  value="<s:property value="vo.compensationDay"/>" />
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

