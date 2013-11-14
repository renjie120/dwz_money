<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="/money/question!query.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" width='300px'>
			<div class="unit">
				<label>
					问题分类:
				</label>
				<my:newselect tagName="sort" selections="<%=list1%>" />

			</div>
			<div class="unit">
				<label>
					问题描述:
				</label>
				<input name="questionDesc" class="textInput" type="text" />
			</div>
			<div class="unit">
				<label>
					发现问题时间:
				</label>
				大于
				<input type="text" name="questionDate" class="date" size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a> 小于
				<input type="text" name="questionDate" class="date" size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					问题状�?:
				</label>
				<my:newselect tagName="status" selections="<%=list2%>" />
			</div>
			<div class="unit">
				<label>
					解决问题时间:
				</label>
				大于
				<input type="text" name="consoleDate" class="date" size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a> 小于
				<input type="text" name="consoleDate" class="date" size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					问题答案:
				</label>
				<input name="answer" class="textInput" type="text" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
								查询
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


