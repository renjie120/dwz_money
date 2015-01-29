
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.InsuredUser.InsuredUser"%>
<% 
	InsuredUser vo = (InsuredUser) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/insureduser!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							投保用户编号:
						</label>
							<input name="iuserNo"   class="textInput  required"    type="text"  value="<s:property value="vo.iuserNo"/>" />
					</div>
					 <div class="unit">
						<label>
							所投保险公司 :
						</label>
							<my:newselect tagName="comId"   tableName="Insured_company" nameColumn="com_name" idColumn ="id"  width="100"   selectedValue="<%=vo.getComId() %>"/>									
					</div>
					 <div class="unit">
						<label>
							所属投保单位 :
						</label>
							<input name="unitId"   class="textInput  required"    type="text"  value="<s:property value="vo.unitId"/>" />
					</div>
					 <div class="unit">
						<label>
							状态 :
						</label>
							<my:newselect tagName="iuserStatus"  paraType="yesorno" width="100"  selectedValue="<%=vo.getIuserStatus() %>"/>									
					</div>
					 <div class="unit">
						<label>
							员工号:
						</label>
							<input name="iuserNumber"   class="textInput  required"    type="text"  value="<s:property value="vo.iuserNumber"/>" />
					</div>
					 <div class="unit">
						<label>
							余额:
						</label>
							<input name="leftMoney"   class="number  required"    type="text"  value="<s:property value="vo.leftMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							门急诊额度:
						</label>
							<input name="emergencyMoney"   class="number  required"    type="text"  value="<s:property value="vo.emergencyMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							冻结金额:
						</label>
							<input name="frozenMoney"   class="number  required"    type="text"  value="<s:property value="vo.frozenMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							住院报销额度:
						</label>
							<input name="hospitalMoney"   class="number  required"    type="text"  value="<s:property value="vo.hospitalMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							体检额度:
						</label>
							<input name="tesMoney"   class="number  required"    type="text"  value="<s:property value="vo.tesMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							姓名:
						</label>
							<input name="iuserName"   class="textInput  required"    type="text"  value="<s:property value="vo.iuserName"/>" />
					</div>
					 <div class="unit">
						<label>
							性别:
						</label>
							<my:newselect tagName="iuserIsman"  paraType="yesorno" width="100"  selectedValue="<%=vo.getIuserIsman() %>"/>									
					</div>
					 <div class="unit">
						<label>
							证件号:
						</label>
							<input name="iuserCardno"   class="textInput  "    type="text"  value="<s:property value="vo.iuserCardno"/>" />
					</div>
					 <div class="unit">
						<label>
							手机号:
						</label>
							<input name="iuserPhone"   class="textInput  required"    type="text"  value="<s:property value="vo.iuserPhone"/>" />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="iuserEmail"   class="email  required"    type="text"  value="<s:property value="vo.iuserEmail"/>" />
					</div>
					 <div class="unit">
						<label>
							生日:
						</label>
							<input name="iuserBirthday"   class="textInput  "    type="text"  value="<s:property value="vo.iuserBirthday"/>" />
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<textarea     class="" name="iuserRemark" cols="30" rows="2"><s:property value="vo.iuserRemark"/></textarea>
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

