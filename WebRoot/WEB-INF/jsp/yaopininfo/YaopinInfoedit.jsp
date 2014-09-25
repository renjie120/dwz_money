
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.yaopin.YaopinInfo"%>
<% 
	YaopinInfo vo = (YaopinInfo) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/yaopininfo!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							业务实体:
						</label>
									<input name="companyName" class="textInput  required"  size="30" type="text"  value="<s:property value="vo.companyName"/>" />
					</div>
					 <div class="unit">
						<label>
							物料名称（英文）:
						</label>
									<input name="engName" class="textInput  "  size="30" type="text"  value="<s:property value="vo.engName"/>" />
					</div>
					 <div class="unit">
						<label>
							物料名称（中文）:
						</label>
									<input name="chnName" class="textInput  "  size="30" type="text"  value="<s:property value="vo.chnName"/>" />
					</div>
					 <div class="unit">
						<label>
							结构式:
						</label>
									<input name="chemStruct" class="textInput  "  size="30" type="text"  value="<s:property value="vo.chemStruct"/>" />
					</div>
					 <div class="unit">
						<label>
							生产厂家:
						</label>
									<input name="productName" class="textInput  "  size="30" type="text"  value="<s:property value="vo.productName"/>" />
					</div>
					 <div class="unit">
						<label>
							厂家销售客户:
						</label>
									<input name="customer" class="textInput  "  size="30" type="text"  value="<s:property value="vo.customer"/>" />
					</div>
					 <div class="unit">
						<label>
							数量:
						</label>
									<input name="num" class="textInput  "  size="30" type="text"  value="<s:property value="vo.num"/>" />
					</div>
					 <div class="unit">
						<label>
							销售价格:
						</label>
									<input name="price" class="textInput  "  size="30" type="text"  value="<s:property value="vo.price"/>" />
					</div>
					 <div class="unit">
						<label>
							纯度:
						</label>
									<input name="chundu" class="textInput  "  size="30" type="text"  value="<s:property value="vo.chundu"/>" />
					</div>
					 <div class="unit">
						<label>
							销售时间:
						</label>
							<input type="text" name="saleTime" class="date " size="30" readOnly="true"  value="<s:property value="vo.saleTime"/>" />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							CAS:
						</label>
									<input name="cas" class="textInput  "  size="30" type="text"  value="<s:property value="vo.cas"/>" />
					</div>
					 <div class="unit">
						<label>
							厂家联系人:
						</label>
									<input name="connect" class="textInput  "  size="30" type="text"  value="<s:property value="vo.connect"/>" />
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

