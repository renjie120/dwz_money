
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent"> 
	<form method="post" action="/money/businessgroup!query.do" onsubmit="return navTabSearch(this);" rel=""
		class="pageForm required-validate" ">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							集团编号:
						</label>
							<my:newselect tagName="condition_groupSno" selectFlag="true" paraType="query_str" width="140" /><input name="query_groupSno" class="textInput" type="text" />
					</div> 
					 <div class="unit">
						<label>
							集团名称 :
						</label>
							<my:newselect tagName="condition_groupName" selectFlag="true" paraType="query_str" width="140" /><input name="query_groupName" class="textInput" type="text" />
					</div> 
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<my:newselect tagName="condition_groupEmail" selectFlag="true" paraType="query_str" width="140" /><input name="query_groupEmail" class="textInput" type="text" />
					</div> 
					 <div class="unit">
						<label>
							联系人名称:
						</label>
							<my:newselect tagName="condition_groupContact" selectFlag="true" paraType="query_str" width="140" /><input name="query_groupContact" class="textInput" type="text" />
					</div> 
					 <div class="unit">
						<label>
							联系电话:
						</label>
							<my:newselect tagName="condition_groupContactPhone" selectFlag="true" paraType="query_str" width="140" /><input name="query_groupContactPhone" class="textInput" type="text" />
					</div> 
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<my:newselect tagName="condition_groupContactMobile" selectFlag="true" paraType="query_str" width="140" /><input name="query_groupContactMobile" class="textInput" type="text" />
					</div> 
					 <div class="unit">
						<label>
							状态:
						</label>
							<my:newselect tagName="condition_groupStatus" selectFlag="true"   paraType="common_option" width="100"/>
							<s:iterator value="#request.groupstatus_list"  >
							 <input type="checkbox" name="query_groupStatus" value='<s:property value="value" />' /> <s:property value="text" />  
							</s:iterator> 
					</div> 
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