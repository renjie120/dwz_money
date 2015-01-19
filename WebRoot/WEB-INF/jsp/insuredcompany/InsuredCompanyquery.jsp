
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent"> 
	<form method="post" action="/money/insuredcompany!query.do" onsubmit="return navTabSearch(this);" rel=""
		class="pageForm required-validate" ">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							保险公司名称:
						</label>
							<my:newselect tagName="condition_comName" paraType="query_str" width="140" /><input name="query_comName" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							保险公司编号 :
						</label>
							<my:newselect tagName="condition_comNo" paraType="query_str" width="140" /><input name="query_comNo" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							状态 :
						</label>
							<my:newselect tagName="condition_comStatus" selectFlag="true"   paraType="common_option" width="100"/>
							<s:iterator value="#request.comstatus_list"  >
							 <input type="checkbox" name="query_comStatus" value='<s:property value="value" />' /> <s:property value="text" />  
							</s:iterator> 
					</div>
					 <div class="unit">
						<label>
							简称:
						</label>
							<my:newselect tagName="condition_comShortName" paraType="query_str" width="140" /><input name="query_comShortName" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							电话:
						</label>
							<my:newselect tagName="condition_comPhone" paraType="query_str" width="140" /><input name="query_comPhone" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							联系人名称:
						</label>
							<my:newselect tagName="condition_comContactName" paraType="query_str" width="140" /><input name="query_comContactName" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<my:newselect tagName="condition_comContactPhone" paraType="query_str" width="140" /><input name="query_comContactPhone" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							所属保险公司:
						</label>
							<my:newselect  selectFlag="true" tagName="condition_ownerCompany"  paraType="common_option" width="140"/>
							<s:iterator value="#request.ownercompany_list"  >
							 <input type="checkbox" name="query_ownerCompany" value='<s:property value="value" />' /> <s:property value="text" />  
							</s:iterator>  
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<my:newselect tagName="condition_comEmail" paraType="query_str" width="140" /><input name="query_comEmail" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<my:newselect tagName="condition_comAddress" paraType="query_str" width="140" /><input name="query_comAddress" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<my:newselect tagName="condition_comRemark" paraType="query_str" width="140" /><input name="query_comRemark" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							创建用户:
						</label>
							<my:newselect  selectFlag="true" tagName="condition_createUser"  paraType="common_option" width="140"/><input name="query_createUser" class="text" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							创建时间:
						</label>
							<my:newselect tagName="condition1_createTime" selectFlag="true" paraType="query_num" width="140" /><input type="text" name="query1_createTime" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
							<my:newselect tagName="condition2_createTime" selectFlag="true" paraType="query_num" width="140" /><input type="text" name="query2_createTime" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a> 
					</div>
					 <div class="unit">
						<label>
							更新用户:
						</label>
							<my:newselect  selectFlag="true" tagName="condition_updateUser"  paraType="common_option" width="140"/><input name="query_updateUser" class="text" type="text" /> 
					</div>
					 <div class="unit">
						<label>
							更新时间:
						</label>
							<my:newselect tagName="condition1_updateTime" selectFlag="true" paraType="query_num" width="140" /><input type="text" name="query1_updateTime" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
							<my:newselect tagName="condition2_updateTime" selectFlag="true" paraType="query_num" width="140" /><input type="text" name="query2_updateTime" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a> 
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