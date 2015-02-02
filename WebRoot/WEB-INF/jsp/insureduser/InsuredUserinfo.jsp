
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/insureduser!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							投保用户编号:
						</label>
							<input name="iuserNo" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							所投保险公司 :
						</label>
							<my:newselect tagName="comId"  tableName="dict_Insured_company" nameColumn="com_name" idColumn ="id" width="100"  />
					</div>
					 <div class="unit">
						<label>
							所属投保单位 :
						</label>
							<input name="unitId" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							状态 :
						</label>
							<my:newselect tagName="iuserStatus"  paraType="yesorno" width="100"  />
					</div>
					 <div class="unit">
						<label>
							员工号:
						</label>
							<input name="iuserNumber" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							余额:
						</label>
							<input name="leftMoney"  class="number required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							门急诊额度:
						</label>
							<input name="emergencyMoney"  class="number required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							冻结金额:
						</label>
							<input name="frozenMoney"  class="number required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							住院报销额度:
						</label>
							<input name="hospitalMoney"  class="number required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							体检额度:
						</label>
							<input name="tesMoney"  class="number required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							姓名:
						</label>
							<input name="iuserName" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							性别:
						</label>
							<my:newselect tagName="iuserIsman"  paraType="yesorno" width="100"  />
					</div>
					 <div class="unit">
						<label>
							证件号:
						</label>
							<input name="iuserCardno" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							手机号:
						</label>
							<input name="iuserPhone" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="iuserEmail"  class="email required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							生日:
						</label>
							<input type="text" name="iuserBirthday" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<textarea class="" name="iuserRemark"  rows="4" cols="35"></textarea>
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