
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/insureduser!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/insureduser!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						投保用户编号</td><td>
							<input name="iuserNo"   class="textInput " type="text"  value="<s:property value="vo.iuserNo"/>" />
					</td> 
					<td> 
						所投保险公司 </td><td>
							<input name="comId"   class="textInput " type="text"  value="<s:property value="vo.comId"/>" />
					</td> 
					<td> 
						所属投保单位 </td><td>
							<input name="unitId"   class="textInput " type="text"  value="<s:property value="vo.unitId"/>" />
					</td> 
					<td> 
						状态 </td><td>
							<my:newselect tagName="iuserStatus"  paraType="yesorno" width="100" allSelected="true" />
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
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="/money/insureduser!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/insureduser!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="icon" href="/money/insureduser!initImport.do" target="dialog"><span>从EXCEL导入</span> </a>
			</li> 
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="100"    orderField="IUSERNO" >
						投保用户编号 
				</th> 
				<th width="100"    orderField="COMID" >
						所投保险公司  
				</th> 
				<th width="100"    orderField="UNITID" >
						所属投保单位  
				</th> 
				<th width="100"    orderField="IUSERSTATUS" >
						状态  
				</th> 
				<th width="100"    orderField="IUSERNUMBER" >
						员工号 
				</th> 
				<th width="100"    orderField="LEFTMONEY" >
						余额 
				</th> 
				<th width="100"    orderField="EMERGENCYMONEY" >
						门急诊额度 
				</th> 
				<th width="100"    orderField="FROZENMONEY" >
						冻结金额 
				</th> 
				<th width="100"    orderField="HOSPITALMONEY" >
						住院报销额度 
				</th> 
				<th width="100"    orderField="TESMONEY" >
						体检额度 
				</th> 
				<th width=""    orderField="IUSERNAME" >
						姓名 
				</th> 
				<th width="100"    orderField="IUSERISMAN" >
						性别 
				</th> 
				<th width="100"    orderField="IUSERCARDNO" >
						证件号 
				</th> 
				<th width="100"    orderField="IUSERPHONE" >
						手机号 
				</th> 
				<th width="100"    orderField="IUSEREMAIL" >
						邮箱 
				</th> 
				<th width="100"    orderField="IUSERBIRTHDAY" >
						生日 
				</th> 
				<th width="100"    orderField="IUSERREMARK" >
						备注 
				</th> 
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td style="text-align:center;">
						<input name="ids" value="<s:property value="sno" />"
							type="checkbox">
					</td>
					<td style="text-align:center;">
						<s:property value="iuserNo" />
					</td> 
					<td style="text-align:center;">
						<s:property value="comId" />
					</td> 
					<td style="text-align:center;">
						<s:property value="unitId" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserStatus" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserNumber" />
					</td> 
					<td style="text-align:center;">
						<s:property value="leftMoney" />
					</td> 
					<td style="text-align:center;">
						<s:property value="emergencyMoney" />
					</td> 
					<td style="text-align:center;">
						<s:property value="frozenMoney" />
					</td> 
					<td style="text-align:center;">
						<s:property value="hospitalMoney" />
					</td> 
					<td style="text-align:center;">
						<s:property value="tesMoney" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserName" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserIsman" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserCardno" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserPhone" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserEmail" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserBirthday" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserRemark" />
					</td> 
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value,pageNum:1})">
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
			<span>条，总共${totalCount}条记录</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>

