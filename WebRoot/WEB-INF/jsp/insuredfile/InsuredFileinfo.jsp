
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/insuredfile!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							投保单号 : 
						</label>
							<input name="insuredFileId" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							保单名称: 
						</label>
							<input name="insuredFileName" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							投保单位: 
						</label>
							<my:newselect tagName="insuredFileUnit"  tableName="Insured_unit" nameColumn="unit_Name" idColumn ="id" width="100"  />
					</div>
					 <div class="unit">
						<label>
							保险公司: 
						</label>
							<my:newselect tagName="insuredFileCompany"  tableName="Insured_company" nameColumn="com_name" idColumn ="id" width="100"  />
					</div>
					 <div class="unit">
						<label>
							邮箱: 
						</label>
							<input name="insuredFileEmail"  class="email " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系人: 
						</label>
							<input name="insuredFileContact" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系电话: 
						</label>
							<input name="insuredFileConTel" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系人手机: 
						</label>
							<input name="insuredFileConMobile"  class="digits " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							投保日期: 
						</label>
							<input type="text" name="insuredFileBegin" class="date "  size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							到期日期: 
						</label>
							<input type="text" name="insuredFileEnd" class="date "  size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							状态: 
						</label>
							<my:newselect tagName="insuredFileStatus"  paraType="insurefile_state" width="100"  />
					</div>
					 <div class="unit">
						<label>
							系统对接方式: 
						</label>
							<my:newselect tagName="insuredFileDuijie"  paraType="sys_duijie" width="100"  />
					</div>
					 <div class="unit">
						<label>
							系统对接开启: 
						</label>
							<my:newselect tagName="insuredFileDuijieFlag"  paraType="yesorno" width="100"  />
					</div>
					 <div class="unit">
						<label>
							备注: 
						</label>
							<textarea class="" name="insuredFileRemark" size="30" rows="4" cols="40"></textarea>
					</div>
					 <div class="unit">
						<label>
							投保总金额: 
						</label>
							<input name="insuredFileTotal"  class="number " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							门急诊额度: 
						</label>
							<input name="insuredFileEmerg"  class="number " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							住院额度: 
						</label>
							<input name="insuredFileHospital"  class="number " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							体检额度: 
						</label>
							<input name="insuredFileExam"  class="number " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							消费商家控制: 
						</label>
							<input name="insuredFileConsumer" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							消费规则: 
						</label>
							<textarea class="" name="insuredFileConsRule" size="30" rows="4" cols="40"></textarea>
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