
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script src="/js/treeCombox.js" type="text/javascript" />
<script type="text/javascript">
	/**
	 * 在弹出框里面点击关闭按钮，拖放按钮触发本事件.
	 */
	function myOperation() {
		$('#unitName').hideMenu();//隐藏弹出来的树形下拉菜单.
	}
	$(document).ready(function() {  
		var content = {
			action : '/money/tree!getInsuredUnitTree.do',
			nameInput : 'unitName',
			height:'200px',
			idInput : 'unitId' ,
			treeId:"insuredUnitTree"
		}; 
		$('#unitName').treeCombox(content);
	});
</script>
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
							用户号:
						</label>
							<input name="iuserNo" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.iuserNo"/>" />
					</div>
					 <div class="unit">
						<label>
							姓名:
						</label>
							<input name="iuserName" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.iuserName"/>" />
					</div>
					 <div class="unit">
						<label>
							性别:
						</label>
							<my:newselect tagName="iuserIsman"  paraType="sex" width="100"  selectedValue="<%=vo.getIuserIsman() %>"/>									
					</div>
					 <div class="unit">
						<label>
							证件号:
						</label>
							<input name="iuserCardno" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.iuserCardno"/>" />
					</div>
					 <div class="unit">
						<label>
							手机号:
						</label>
							<input name="iuserPhone" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.iuserPhone"/>" />
					</div>
					 <div class="unit">
						<label>
							所属投保单位 :
						</label>
						<input name="unitId" id="unitId" type="hidden" value="<s:property value="vo.unitId"/>"/>	
						 <input name="unitName" size="30" id="unitName" type="text" readonly="true" class="required" value="<s:property value="vo.unitName"/>" />
 
					</div>
					 <div class="unit">
						<label>
							保险公司 :
						</label>
							<my:newselect tagName="comId"   tableName="Insured_company" nameColumn="com_name" idColumn ="id"  width="100"   selectedValue="<%=vo.getComId() %>"/>									
					</div>
					 <div class="unit">
						<label>
							状态 :
						</label>
							<my:newselect tagName="iuserStatus"  paraType="toubaouser_status" width="100"  selectedValue="<%=vo.getIuserStatus() %>"/>									
					</div>
					 <div class="unit">
						<label>
							员工号:
						</label>
							<input name="iuserNumber" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.iuserNumber"/>" />
					</div>
					 <div class="unit">
						<label>
							余额:
						</label>
							<input name="leftMoney" size="30"  class="number  required"    type="text"  value="<s:property value="vo.leftMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							门急诊额度:
						</label>
							<input name="emergencyMoney" size="30"  class="number  required"    type="text"  value="<s:property value="vo.emergencyMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							冻结金额:
						</label>
							<input name="frozenMoney" size="30"  class="number  required"    type="text"  value="<s:property value="vo.frozenMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							住院报销额度:
						</label>
							<input name="hospitalMoney" size="30"  class="number  required"    type="text"  value="<s:property value="vo.hospitalMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							体检额度:
						</label>
							<input name="tesMoney" size="30"  class="number  required"    type="text"  value="<s:property value="vo.tesMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="iuserEmail" size="30"  class="email  required"    type="text"  value="<s:property value="vo.iuserEmail"/>" />
					</div>
					 <div class="unit">
						<label>
							生日:
						</label>
							<input name="iuserBirthday" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.iuserBirthday"/>" />
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<textarea  size="30"   class="" name="iuserRemark" cols="30" rows="2"><s:property value="vo.iuserRemark"/></textarea>
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

