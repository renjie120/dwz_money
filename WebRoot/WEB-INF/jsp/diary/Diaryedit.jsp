
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.diary.Diary"%>
<%
	Diary vo = (Diary) request.getAttribute("diaryVo");
%>
<div class="pageContent">
	<form method="post" action="/money/diary!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="diaryId"
			value="<s:property value="diaryVo.diaryId"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							日志内容:
						</label>
						<textarea  name="diaryContent" class="required"  cols="50" rows="6"><s:property value="diaryVo.diaryContent"/></textarea>
					</div>
					 <div class="unit">
						<label>
							开始时间:
						</label>
							<input type="text" name="diaryTime" class="date " size="30" readOnly="true"  value="<s:property value="diaryVo.diaryTime"/>" />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							日志类型:
						</label>
							<input name="diaryType" class="textInput  " size="30" type="text"  value="<s:property value="diaryVo.diaryType"/>" />
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

