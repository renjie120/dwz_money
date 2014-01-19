
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="money.question.Question"%>
<%@ page import="common.util.CommonUtil"%>
<%@ page import="money.question.QuestionImpl"%>
<%@ page import="money.question.QuestionVO"%>
<%@ page import="money.question.QuestionQueryVO"%>
<% 

	Question vo = (Question) request.getAttribute("questionVo");
	QuestionQueryVO questionQueryVO = (QuestionQueryVO) request.getAttribute("questionQueryVO"); 
	if(vo==null)
		vo = new QuestionImpl(new QuestionVO());
	String sort = vo.getSort()+"";
	String status = vo.getStatus()+"";
	String questionDesc = CommonUtil.notBlank(vo.getQuestionDesc());
%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/question!query.do" onsubmit="return navTabSearch(this);"  > 
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="sort" value="${sort}" />
	<input type="hidden" name="status" value="${status}" />
	<input type="hidden" name="questionDesc" value="${questionDesc}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" /> 
	<input type="hidden" name="questionSortConditionC" value="${questionSortConditionC}" />
	<input type="hidden" name="questionSortC" value="${questionSortC}" />
	<input type="hidden" name="questionDescConditionC" value="${questionDescConditionC}" />
	<input type="hidden" name="questionDescC" value="${questionDescC}" /> 
	<input type="hidden" name="smallQuestionDateC" value="${smallQuestionDateC}" /> 
	<input type="hidden" name="bigQuestionDateC" value="${bigQuestionDateC}" />
	<input type="hidden" name="questionStatusConditionC" value="${questionStatusConditionC}" />
	<input type="hidden" name="questionStatusC" value="${questionStatusC}" />
	<input type="hidden" name="smallConsoleDateC" value="${smallConsoleDateC}" />
	<input type="hidden" name="bigConsoleDateC" value="${bigConsoleDateC}" />
	<input type="hidden" name="answerConditionC" value="${answerConditionC}" />
	<input type="hidden" name="answerC" value="${answerC}" />  
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"  
		action="/money/question!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						问题分类
					</td>
					<td>
						<my:newselect tagName="sort"  paraType="questionSort" width="100" allSelected="true"
							selectedValue="<%=sort %>"/>
					</td>
					<td>
						问题描述
					</td>
					<td>
						<input type="text" name="questionDesc" class="text" size="20" value="<%=questionDesc %>"/>
					</td>
					<td>
						问题状态
					</td>
					<td>
						<my:newselect tagName="status" paraType="questionStatus"  width="100" allSelected="true"
						selectedValue="<%=status %>"/>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									检索
								</button>
							</div>
						</div>
					</li>
					<li>
						<a class="button" href="/money/question!beforeQuery.do" target="dialog"
							mask="true" height="320" title="高级检索"><span>高级检索</span> </a>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="/money/question!beforeAdd.do" target="dialog" mask="true"
					height="350" title="添加问题信息"><span>添加</span> </a>
			</li>
			<%
			if (isAdmin) {
			%>
			<li>
				<a class="delete" href="/money/question!doDelete.do"
					postType="string" target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<%}else{ %>
				<li>
				<a class="delete" href="#" target="dwzExport" targetType="navTab" title="您没有删除权限,请使用管理员admin登陆!"><span>删除</span>
				</a>
			</li>
			<%} %>
			<%
			if (isAdmin) {
			%>
			<li>
				<a class="edit"
					href="/money/question!beforeUpdate.do?questionId={questionId}" mask="true"
					target="dialog" title="修改问题信息" height="350"><span>修改</span> </a>
			</li>
			<%}else{ %>
			<li>
				<a class="edit" href="#" target="dwzExport" targetType="navTab" title="您没有修改权限,请使用管理员admin登陆!"><span>修改</span>
				</a>
			</li>
			<%} %>
			<li class="line">
				line
			</li>
			<li>
				<a class="icon" href="/money/question!export.do" target="dwzExport"
					targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span> </a>
			</li>
		</ul>
	</div>
	<table class="table"  layoutH='-138'>
		<thead>
			<tr>
				<th width='30'>
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th  width='50'>
					问题id
				</th>
				<th   width='80' orderField="SORT" class="asc">
					问题分类
				</th> 
				<th  width='100' orderField="QUESTIONDATE" class="asc">
					发现问题时间
				</th>
				<th width='300'>
					问题描述
				</th> 
				<th width='80'  >
					提问人
				</th>
				<th width='80' orderField="STATUS" class="desc">
					问题状态
				</th>
				<th width='100'  orderField="CONSOLEDATE" class="asc">
					解决问题时间
				</th>
				<th width='200'  orderField="CONSOLEDATE" class="asc">
					问题答案
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="questionId" rel="<s:property value="questionId" />">
					<td>
						<input name="ids" value="<s:property value="questionId" />"
							type="checkbox">
					</td>
					<td>
					<s:property value="questionId" />
					</td>
					<td>
					<s:property value="sortName" />
					</td> 
					<td>
					<s:property value="questionDate" />
					</td>
					<td><div style='width:200px' title='<s:property value="questionDesc"/>'><s:property value="questionDesc"/></div></td> 
					<td>
					<s:property value="submit" />
					</td>
					<td>
					<s:property value="statusName" />
					</td>
					<td>
					<s:property value="consoleDate" />
					</td>
					<td><div style='width:200px' title='<s:property value="answer"/>'><s:property value="answer"/></div></td> 
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20"
					<%if((request.getAttribute("numPerPage")+"").equals("20")){%>
					selected <%} %>>
					20
				</option>
				<option value="50"
					<%if((request.getAttribute("numPerPage")+"").equals("50")){%>
					selected <%} %>>
					50
				</option>
				<option value="100"
					<%if((request.getAttribute("numPerPage")+"").equals("100")){%>
					selected <%} %>>
					100
				</option>
				<option value="200"
					<%if((request.getAttribute("numPerPage")+"").equals("200")){%>
					selected <%} %>>
					200
				</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>

