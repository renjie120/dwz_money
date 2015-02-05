
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.InsuredFile.InsuredFile"%>
<% 
	InsuredFile vo = (InsuredFile) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/insuredfile!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							投保单号 :
						</label>
							<input name="insuredFileId" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.insuredFileId"/>" />
					</div>
					 <div class="unit">
						<label>
							保单名称:
						</label>
							<input name="insuredFileName" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.insuredFileName"/>" />
					</div>
					 <div class="unit">
						<label>
							投保单位:
						</label>
							<my:newselect tagName="insuredFileUnit"   tableName="Insured_unit" nameColumn="unit_Name" idColumn ="id"  width="100"   selectedValue="<%=vo.getInsuredFileUnit() %>"/>									
					</div>
					 <div class="unit">
						<label>
							保险公司:
						</label>
							<my:newselect tagName="insuredFileCompany"   tableName="Insured_company" nameColumn="com_name" idColumn ="id"  width="100"   selectedValue="<%=vo.getInsuredFileCompany() %>"/>									
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="insuredFileEmail" size="30"  class="email  "    type="text"  value="<s:property value="vo.insuredFileEmail"/>" />
					</div>
					 <div class="unit">
						<label>
							联系人:
						</label>
							<input name="insuredFileContact" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.insuredFileContact"/>" />
					</div>
					 <div class="unit">
						<label>
							联系电话:
						</label>
							<input name="insuredFileConTel" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.insuredFileConTel"/>" />
					</div>
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<input name="insuredFileConMobile"size="30"   class="digits  "   type="text"  value="<s:property value="vo.insuredFileConMobile"/>" />
					</div>
					 <div class="unit">
						<label>
							投保日期:
						</label>
							<input name="insuredFileBegin" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.insuredFileBegin"/>" />
					</div>
					 <div class="unit">
						<label>
							到期日期:
						</label>
							<input name="insuredFileEnd" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.insuredFileEnd"/>" />
					</div>
					 <div class="unit">
						<label>
							状态:
						</label>
							<my:newselect tagName="insuredFileStatus"  paraType="insurefile_state" width="100"  selectedValue="<%=vo.getInsuredFileStatus() %>"/>									
					</div>
					 <div class="unit">
						<label>
							系统对接方式:
						</label>
							<my:newselect tagName="insuredFileDuijie"  paraType="sys_duijie" width="100"  selectedValue="<%=vo.getInsuredFileDuijie() %>"/>									
					</div>
					 <div class="unit">
						<label>
							系统对接开启:
						</label>
							<my:newselect tagName="insuredFileDuijieFlag"  paraType="yesorno" width="100"  selectedValue="<%=vo.getInsuredFileDuijieFlag() %>"/>									
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<textarea  size="30"   class="" name="insuredFileRemark" cols="30" rows="2"><s:property value="vo.insuredFileRemark"/></textarea>
					</div>
					 <div class="unit">
						<label>
							投保总金额:
						</label>
							<input name="insuredFileTotal" size="30"  class="number  "    type="text"  value="<s:property value="vo.insuredFileTotal"/>" />
					</div>
					 <div class="unit">
						<label>
							门急诊额度:
						</label>
							<input name="insuredFileEmerg" size="30"  class="number  "    type="text"  value="<s:property value="vo.insuredFileEmerg"/>" />
					</div>
					 <div class="unit">
						<label>
							住院额度:
						</label>
							<input name="insuredFileHospital" size="30"  class="number  "    type="text"  value="<s:property value="vo.insuredFileHospital"/>" />
					</div>
					 <div class="unit">
						<label>
							体检额度:
						</label>
							<input name="insuredFileExam" size="30"  class="number  "    type="text"  value="<s:property value="vo.insuredFileExam"/>" />
					</div>
					 <div class="unit">
						<label>
							消费商家控制:
						</label>
							<input name="insuredFileConsumer" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.insuredFileConsumer"/>" />
					</div>
					 <div class="unit">
						<label>
							消费规则:
						</label>
							<textarea  size="30"   class="" name="insuredFileConsRule" cols="30" rows="2"><s:property value="vo.insuredFileConsRule"/></textarea>
					</div>
					 <div class="unit">
						<label>
							创建用户:
						</label>
							<input name="createUser" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createUser"/>" />
					</div>
					 <div class="unit">
						<label>
							创建时间:
						</label>
							<input name="createTime" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createTime"/>" />
					</div>
					 <div class="unit">
						<label>
							更新用户:
						</label>
							<input name="updateUser" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.updateUser"/>" />
					</div>
					 <div class="unit">
						<label>
							更新时间:
						</label>
							<input name="updateTime" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.updateTime"/>" />
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

