
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.province.ProvinceDict"%>
<% 
	ProvinceDict vo = (ProvinceDict) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/provincedict!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							省份:
						</label>
							<input name="provName"   class="number  "    type="text"  value="<s:property value="vo.provName"/>" />
					</div>
					 <div class="unit">
						<label>
							类型:
						</label>
							<my:newselect tagName="provType"  paraType="prov_type" width="100"  selectedValue="<%=vo.getProvType() %>"/>									
					</div>
					 <div class="unit">
						<label>
							状态 :
						</label>
							<my:newselect tagName="provState"  paraType="yesorno_status" width="100"  selectedValue="<%=vo.getProvState() %>"/>									
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

