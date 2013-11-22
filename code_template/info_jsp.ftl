
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/menu!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label>
					菜单名:
				</label>
				<input name="menuName" class="textInput  required " size="30"
					type="text" />
			</div>
			<div class="unit">
				<label>
					链接:
				</label>
				<input name="url" class="textInput  " size="30" type="text" />
			</div>
			<div class="unit">
				<label>
					菜单指向:
				</label>
				<my:newselect tagName="target"  paraType="menutarget"  />  
			</div> 
			<div class="unit">
				<label>
					菜单级别:
				</label>
				<my:newselect tagName="level" id="level"  paraType="menulevel"  />  
			</div> 
			<div class="unit">
				<label>
					父级菜单:
				</label> 
				<input class="required" name="moneyTypeName" type="text" readOnly="true"/> 
				<input  name="moneyType" type="hidden"/> 
				<a class="btnLook" href="/money/tree!menuTree.do"
				 lookupGroup="obj"  lookupToPks="true" 
				 lookupPk="moneyType" title='收支类别树'  width='300'></a>  
			</div>
			<div class="unit">
				<label>
					菜单页编码:
				</label>
				<input name="relId" class="textInput  " size="30" type="text" />
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