
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.BusinessGroup.BusinessGroup"%>
<% 
	BusinessGroup vo = (BusinessGroup) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/businessgroup!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							集团编号:
						</label>
							<input name="groupSno"   class="textInput  required"    type="text"  value="<s:property value="vo.groupSno"/>" />
					</div>
					 <div class="unit">
						<label>
							集团名称 :
						</label>
							<input name="groupName"   class="textInput  required"    type="text"  value="<s:property value="vo.groupName"/>" />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="groupEmail"   class="email  required"    type="text"  value="<s:property value="vo.groupEmail"/>" />
					</div>
					 <div class="unit">
						<label>
							联系人名称:
						</label>
							<input name="groupContact"   class="textInput  required"    type="text"  value="<s:property value="vo.groupContact"/>" />
					</div>
					 <div class="unit">
						<label>
							联系电话:
						</label>
							<input name="groupContactPhone"   class="textInput  required"    type="text"  value="<s:property value="vo.groupContactPhone"/>" />
					</div>
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<input name="groupContactMobile"   class="digits  required"   type="text"  value="<s:property value="vo.groupContactMobile"/>" />
					</div>
					 <div class="unit">
						<label>
							状态:
						</label>
							<my:newselect tagName="groupStatus"  paraType="shopman_status" width="100"  selectedValue="<%=vo.getGroupStatus() %>"/>									
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

