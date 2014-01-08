
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style> 
<div class="pageContent" style="padding:5px">
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>用户菜单权限</span>
					</a>
					</li>
					<li><a href="javascript:;"><span>用户角色分配</span>
					</a>
					</li>
					<li><a href="javascript:;"><span>角色菜单权限</span>
					</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height:400px">
			<div  > 
				<div layoutH="500"
					style="float:left; display:block; height:400px;overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
					<ul class="tree treeFolder" style='height:400px'>
						<li><a href="javascript">实验室检测</a>
							<ul>
								<li><a href="demo/pagination/list1.html" target="ajax"
									rel="jbsxBox">尿检</a>
								</li>
								<li><a href="demo/pagination/list1.html" target="ajax"
									rel="jbsxBox">HIV检测</a>
								</li>
								<li><a href="demo/pagination/list1.html" target="ajax"
									rel="jbsxBox">HCV检测</a>
								</li>
								<li><a href="demo/pagination/list1.html" target="ajax"
									rel="jbsxBox">TB检测</a>
								</li>
							</ul></li>

					</ul>
				</div>

				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
					<!--#include virtual="list1.html" -->
				</div> 
			</div>

			<div>病人处方</div>

			<div>病人服药情况</div>

			<div>基线调查</div>

			<div>随访</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div> 
</div>




