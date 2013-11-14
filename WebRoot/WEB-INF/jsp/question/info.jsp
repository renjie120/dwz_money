<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>  
<div class="pageContent">
	<form method="post" action="/money/question!doAdd.do" id="questionInfo"
		class="pageForm required-validate" onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent"  width='300px' layoutH="57">
			<div class="unit">
				<label>
					问题分类:
				</label>
				<my:newselect tagName="sort" paraType="questionSort" selectFlag="true"/> 
			</div>
			<div class="unit">
				<label>
					问题描述:
				</label>
				<textarea class="editor required" name="questionDesc" rows="3" cols="40"></textarea>  
			</div> 
			<div class="unit">
				<label>
					发现问题时间:
				</label>
				<input type="text" name="questionDate" readOnly="true"  class="date required" size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					问题状态:
				</label>
				<my:newselect tagName="status" paraType="questionStatus" /> 
			</div> 
			<div class="unit">
				<label>
					解决问题时间:
				</label>
				<input type="text" name="consoleDate" readOnly="true" class="date" size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					问题答案:
				</label>
				<textarea class="editor" name="answer" rows="3" cols="40"></textarea> 
			</div>   
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" >
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

