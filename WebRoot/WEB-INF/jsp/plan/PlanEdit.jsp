
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.plan.Plan"%>
<%
	Plan vo = (Plan) request.getAttribute("planVo");
	String planType = vo.getPlanType() + "";
	String planStatus = vo.getPlanStatus() + "";
%>
<div class="pageContent">
	<form method="post" action="/money/plan!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="planId"
			value="<s:property value="planVo.planId"/>">
		<div class="pageFormContent" width='300px' layoutH="57">
			<div class="unit">
				<label>
					计划时间:
				</label>
				<input type="text" name="planDate" class="date  " size="30"
					value="<s:property value="planVo.planDate"/>" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					开始时间:
				</label>
				<input type="text" name="startDate" class="date  " size="30"
					value="<s:property value="planVo.startDate"/>" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					结束时间:
				</label>
				<input type="text" name="endDate" class="date  " size="30"
					value="<s:property value="planVo.endDate"/>" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					实际开始时间:
				</label>
				<input type="text" name="realStartDate" class="date  " size="30"
					value="<s:property value="planVo.realStartDate"/>" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					实际结束时间:
				</label>
				<input type="text" name="realEndDate" class="date  " size="30"
					value="<s:property value="planVo.realEndDate"/>" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					计划描述:
				</label>
				<input name="planDesc" class="textInput  " size="30" type="text"
					value="<s:property value="planVo.planDesc"/>" />
			</div>
			<div class="unit">
				<label>
					计划类型:
				</label>
				<my:newselect tagName="planType" paraType="plantype"
					selectedValue="<%=planType%>" />
			</div>
			<div class="unit">
				<label>
					计划状态:
				</label>
				<my:newselect tagName="planStatus" paraType="planstatus"
					selectedValue="<%=planStatus%>" />
			</div>
			<div class="unit">
				<label>
					制定人:
				</label>
				<input name="userId" class="textInput  " size="30" type="text"
					value="<s:property value="planVo.userId"/>" />
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

