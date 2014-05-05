
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/orderbasemanager!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							订单:
						</label>
									<input name="orderNo" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							客户名称:
						</label>
									<input name="customerNo" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							功率:
						</label>
									<input name="gongLv" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							电压:
						</label>
									<input name="dianYa" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							世代:
						</label>
									<input name="shiDai" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							整流变压器厂家:
						</label>
									<input name="bianyaChangjia" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							整流变压器型号:
						</label>
									<input name="bianyaXinghao" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							是否重点客户:
						</label>
									<input name="isImport" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							计划开工时间:
						</label>
							<input type="text" name="startDate" class="date required" size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							计划结束时间:
						</label>
							<input type="text" name="endDate" class="date required" size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							当前状态:
						</label>
									<my:newselect tagName="currentState"  paraType="orderstatus" width="100" allSelected="true" />
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