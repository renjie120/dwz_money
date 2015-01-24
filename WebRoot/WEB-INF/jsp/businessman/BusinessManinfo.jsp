
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/businessman!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							商家编号:
						</label>
							<input name="shopmSno" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							商家名称 :
						</label>
							<input name="shopmName" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							商家简称 :
						</label>
							<input name="shopmShortName" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							商家类型 :
						</label>
							<my:newselect tagName="shopmType"  paraType="shopman_status" width="100"  />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="shopmEmail"  class="email required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<input name="shopmConPhone" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系人名称:
						</label>
							<input name="shopmContactName" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<textarea class="required" name="shopmAddress"  rows="4" cols="40"></textarea>
					</div>
					 <div class="unit">
						<label>
							开户行:
						</label>
							<input name="openBank" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							户名:
						</label>
							<input name="openBankName" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							银行帐号:
						</label>
							<input name="openBankNo" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							开票单位:
						</label>
							<input name="openTicketUnit" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							开户所在省:
						</label>
							<my:newselect tagName="OpenBankProvince"  tableName="dict_province" nameColumn="s_provname" idColumn ="n_provid" width="100"  />
					</div>
					 <div class="unit">
						<label>
							开户所在市:
						</label>
							<my:newselect tagName="OpenBankCity"  tableName="dict_city" nameColumn="s_cityname" idColumn ="n_cityid" width="100"  />
					</div>
					 <div class="unit">
						<label>
							理赔截止日期:
						</label>
							<input type="text" name="compensationDay" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
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