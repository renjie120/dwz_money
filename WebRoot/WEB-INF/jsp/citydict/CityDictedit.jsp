
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.city.CityDict"%>
<% 
	CityDict vo = (CityDict) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/citydict!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							名称:
						</label>
							<input name="cityName"   class="number  "    type="text"  value="<s:property value="vo.cityName"/>" />
					</div>
					 <div class="unit">
						<label>
							省份:
						</label>
							<my:newselect tagName="provId"   tableName="dict_province" nameColumn="s_provname" idColumn ="n_provid"  width="100"   selectedValue="<%=vo.getProvId() %>"/>									
					</div>
					 <div class="unit">
						<label>
							状态 :
						</label>
							<my:newselect tagName="cityState"  paraType="yesorno_status" width="100"  selectedValue="<%=vo.getCityState() %>"/>									
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

