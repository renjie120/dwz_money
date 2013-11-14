
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.question.Question"%> 
<% 
	Question vo = (Question) request.getAttribute("questionVo");
	String sort = vo.getSort()+"";
	String status = vo.getStatus()+""; 
%> 
<div class="pageContent">
	<form method="post" action="/money/question!doUpdate.do"
		class="pageForm required-validate" id="editquestionInfo" onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="questionId"
			value="<s:property value="questionVo.questionId"/>">

		<div class="pageFormContent"   layoutH="57">
			<div class="unit">
				<label>
					问题分类:
				</label>
				<my:newselect tagName="sort"  paraType="questionSort" 
					selectedValue="<%=sort%>" />
			</div>
			<div class="unit">
				<label>
					问题描述:
				</label>
				<textarea class="editor required" name="questionDesc" rows="3" cols="40"><s:property value="questionVo.questionDesc"/></textarea>   
				<input name="submit" type="hidden" value="<s:property value="questionVo.submit"/>" />
		 	</div> 
			<div class="unit">
				<label>
					发现问题时间:
				</label>
				<input type="text" name="questionDate" readOnly="true" class="date required" size="30"
					value="<s:property value="questionVo.questionDate"/>" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					问题状态:
				</label>
				<my:newselect tagName="status" paraType="questionStatus"
					selectedValue="<%=status%>" />
			</div>
			<div class="unit">
				<label>
					解决问题时间:
				</label>
				<input type="text" name="consoleDate" readOnly="true" class="date" size="30"
					value="<s:property value="questionVo.consoleDate"/>" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					问题答案:
				</label>
				<textarea class="editor" name="answer" rows="3" cols="40"><s:property value="questionVo.answer"/></textarea>    
			</div>   
		</div>
		<div class="formBar">
			<ul>
			<%
			if (isAdmin) {
			%>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit"  >
								保存
							</button>
						</div>
					</div>
				</li>
				<%} %>
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

