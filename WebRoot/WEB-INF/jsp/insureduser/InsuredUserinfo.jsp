
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
<div class="pageContent">
	<form method="post" action="/money/insureduser!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							用户号:
						</label>
							<input name="iuserNo" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							姓名:
						</label>
							<input name="iuserName" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							性别:
						</label>
							<my:newselect tagName="iuserIsman"  paraType="sex" width="100"  />
					</div>
					 <div class="unit">
						<label>
							证件号:
						</label>
							<input name="iuserCardno" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							手机号:
						</label>
							<input name="iuserPhone" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							所属投保单位 :
						</label> 
						<input name="unitId" id="unitId" type="hidden" />	
					 <input name="unitName" size="30" id="unitName" type="text" readonly="true" class="required" />
					</div>
					 <div class="unit">
						<label>
							保险公司 :
						</label>
							<my:newselect tagName="comId"  tableName="Insured_company" nameColumn="com_name" idColumn ="id" width="100"  />
					</div>
					 <div class="unit">
						<label>
							状态 :
						</label>
							<my:newselect tagName="iuserStatus"  paraType="toubaouser_status" width="100"  />
					</div>
					 <div class="unit">
						<label>
							员工号:
						</label>
							<input name="iuserNumber" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							余额:
						</label>
							<input name="leftMoney"  class="number required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							门急诊额度:
						</label>
							<input name="emergencyMoney"  class="number required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							冻结金额:
						</label>
							<input name="frozenMoney"  class="number required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							住院报销额度:
						</label>
							<input name="hospitalMoney"  class="number required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							体检额度:
						</label>
							<input name="tesMoney"  class="number required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="iuserEmail"  class="email required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							生日:
						</label>
							<input type="text" name="iuserBirthday" class="date "  size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<textarea class="" name="iuserRemark" size="30" rows="4" cols="35"></textarea>
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