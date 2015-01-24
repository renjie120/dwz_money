
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
							<my:newselect tagName="condition_comName" selectFlag="true" paraType="query_str" width="140" /><input name="query_comName" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							保险公司编号 :
						</label>
							<my:newselect tagName="condition_comNo" selectFlag="true" paraType="query_str" width="140" /><input name="query_comNo" class="textInput" type="text" />
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
							<my:newselect tagName="condition_comShortName" selectFlag="true" paraType="query_str" width="140" /><input name="query_comShortName" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							电话:
						</label>
							<my:newselect tagName="condition_comPhone" selectFlag="true" paraType="query_str" width="140" /><input name="query_comPhone" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							联系人名称:
						</label>
							<my:newselect tagName="condition_comContactName" selectFlag="true" paraType="query_str" width="140" /><input name="query_comContactName" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<my:newselect tagName="condition_comContactPhone" selectFlag="true" paraType="query_str" width="140" /><input name="query_comContactPhone" class="textInput" type="text" />
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
							<my:newselect tagName="condition_comEmail" selectFlag="true" paraType="query_str" width="140" /><input name="query_comEmail" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<my:newselect tagName="condition_comAddress" selectFlag="true" paraType="query_str" width="140" /><input name="query_comAddress" class="textInput" type="text" />
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<my:newselect tagName="condition_comRemark" selectFlag="true" paraType="query_str" width="140" /><input name="query_comRemark" class="textInput" type="text" />
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