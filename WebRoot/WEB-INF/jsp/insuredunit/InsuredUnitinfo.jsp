
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script src="/js/treeCombox.js" type="text/javascript" />

<div class="pageContent">
	<form method="post" action="/money/insuredunit!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label> 编号: </label> <input name="unitCode" size="30" value="${unitCode }"
					class="textInput required" type="text" readOnly="true"/>
			</div>
			<div class="unit">
				<label> 投保单位 : </label> <input name="unitName" class="textInput " size="30"
					type="text" />
			</div>
			<div class="unit">
				<label> 联系人: </label> <input name="contactName" class="textInput " size="30"
					type="text" />
			</div>
			<div class="unit">
				<label> 手机: </label> <input name="contactMobile" class="digits " size="30"
					type="text" />
			</div>
			<div class="unit">
				<label> 邮箱: </label> <input name="contactEmail" class="email " size="30"
					type="text" />
			</div> 
			<div class="unit">
				<label> 上级单位: </label> 
				<input name="unitParentId" id="unitParentId" value="<s:property value='unitParentId'/>" type="hidden" /> 
				<input name="unitParentName" size="30"  value="<%=request.getAttribute("ddd") %>" readonly="true"
					id="unitParentName" type="text"   />
			</div>
			<div class="unit">
				<label> 是否显示: </label>
				<my:newselect tagName="unitState" paraType="yesorno" width="100" />
			</div>
			<div class="unit">
				<label> 地址: </label>
				<textarea class="" name="unitAddress" rows="4" cols="40"></textarea>
			</div>
			<div class="unit">
				<label> 备注: </label>
				<textarea class="" name="unitRemark" rows="4" cols="40"></textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>