
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/buglist!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							问题类型: 
						</label>
							<my:newselect tagName="bugType"  paraType="bugtype" width="100"  />
					</div>
					 <div class="unit">
						<label>
							问题描述: 
						</label>
							<input name="bugDesc" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							待解决时间: 
						</label>
							<input type="text" name="needTime" class="date "  size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							解决人: 
						</label>
							<input name="consolePeople" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							解决时间: 
						</label>
							<input name="consoleTile" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							备注: 
						</label>
							<input name="remark" class="textInput required" size="30" type="text"   />
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