
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.BusinessShop.BusinessShop"%>
<% 
	BusinessShop vo = (BusinessShop) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/businessshop!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							商家编号:
						</label>
							<input name="shopmId"   class="textInput  required"   size="30" type="text"  value="<s:property value="vo.shopmId"/>" />
					</div>
					 <div class="unit">
						<label>
							商铺名称 :
						</label>
							<input name="shopName"   class="textInput  required" size="30"    type="text"  value="<s:property value="vo.shopName"/>" />
					</div>
					 <div class="unit">
						<label>
							商铺编号 :
						</label>
							<input name="shopSno"   class="textInput  required"  size="30"   type="text"  value="<s:property value="vo.shopSno"/>" />
					</div>
					 <div class="unit">
						<label>
							商铺状态 :
						</label>
							<my:newselect tagName="shopStatus"  paraType="shopstatus" width="100"  selectedValue="<%=vo.getShopStatus() %>"/>									
					</div>
					 <div class="unit">
						<label>
							联系人名称:
						</label>
							<input name="shopContactName"   class="textInput  required"   size="30"  type="text"  value="<s:property value="vo.shopContactName"/>" />
					</div>
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<input name="shopConPhone"   class="textInput  required"   size="30"  type="text"  value="<s:property value="vo.shopConPhone"/>" />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="shopEmail"   class="email  required"   size="30"  type="text"  value="<s:property value="vo.shopEmail"/>" />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<textarea     class="required" name="shopAddress" cols="30" rows="2"><s:property value="vo.shopAddress"/></textarea>
					</div>
					 <div class="unit">
						<label>
							签约日期:
						</label>
							<input name="shopDate"   class="textInput  required" size="30"    type="text"  value="<s:property value="vo.shopDate"/>" />
					</div>
					 <div class="unit">
						<label>
							经度:
						</label>
							<input name="shopJingdu"   class="textInput  "  size="30"   type="text"  value="<s:property value="vo.shopJingdu"/>" />
					</div>
					 <div class="unit">
						<label>
							纬度:
						</label>
							<input name="shopWeidu"   class="textInput  " size="30"    type="text"  value="<s:property value="vo.shopWeidu"/>" />
					</div>
					 <div class="unit">
						<label>
							省份:
						</label>
							<my:newselect tagName="shopProvince"   tableName="dict_province" nameColumn="s_provname" idColumn ="n_provid"  width="100"   selectedValue="<%=vo.getShopProvince() %>"/>									
					</div>
					 <div class="unit">
						<label>
							市:
						</label>
							<my:newselect tagName="shopCity"   tableName="dict_city" nameColumn="s_cityname" idColumn ="n_cityid"  width="100"   selectedValue="<%=vo.getShopCity() %>"/>									
					</div>
					 <div class="unit">
						<label>
							区县:
						</label>
							<my:newselect tagName="shopxian"  paraType="yesorno" width="100"  selectedValue="<%=vo.getShopxian() %>"/>									
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<input name="shopRemark"   class="textInput  "  size="30"   type="text"  value="<s:property value="vo.shopRemark"/>" />
					</div>
					 <div class="unit">
						<label>
							主营:
						</label>
							<input name="shopMain"   class="textInput  " size="30"    type="text"  value="<s:property value="vo.shopMain"/>" />
					</div>
					 <div class="unit">
						<label>
							简介:
						</label>
							<input name="shopIntroduce"   class="textInput  "  size="30"   type="text"  value="<s:property value="vo.shopIntroduce"/>" />
					</div>
					 <div class="unit">
						<label>
							特色:
						</label>
							<input name="shopSpecial"   class="textInput  "   size="30"  type="text"  value="<s:property value="vo.shopSpecial"/>" />
					</div>
					 <div class="unit">
						<label>
							创建用户:
						</label>
							<input name="createUser"   class="textInput  " readonly='true'  size="30"  type="text"  value="<s:property value="vo.createUserName"/>" />
					</div>
					 <div class="unit">
						<label>
							创建时间:
						</label>
							<input name="createTime"   class="textInput  " readonly='true'  size="30"  type="text"  value="<s:property value="vo.createTime"/>" />
					</div>
					 <div class="unit">
						<label>
							更新用户:
						</label>
							<input name="updateUser"   class="textInput  " readonly='true' size="30"   type="text"  value="<s:property value="vo.updateUserName"/>" />
					</div>
					 <div class="unit">
						<label>
							更新时间:
						</label>
							<input name="updateTime"   class="textInput  " readonly='true' size="30"   type="text"  value="<s:property value="vo.updateTime"/>" />
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

