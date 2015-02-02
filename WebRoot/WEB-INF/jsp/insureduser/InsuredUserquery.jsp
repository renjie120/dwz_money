
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent"> 
	<form method="post" action="/money/insureduser!query.do" onsubmit="return navTabSearch(this);" rel=""
		class="pageForm required-validate" ">
		<div class="pageFormContent" layoutH="57">
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="javascript:$.pdialog.closeCurrent();">
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