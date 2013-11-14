<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">
<!-- 
function test(){  
 	 $.returnValue('pagerForm','queryQuestionForm');
 	 $('#pagerForm').submit();
	 //navTab.reload();
}
//-->
</script>
<div class="pageContent">
	<form id="queryQuestionForm">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label>
					问题分类：
				</label>
				<my:newselect tagName="questionSortConditionC"  paraType="condition3" />
				<my:newselect tagName="questionSortC" selectFlag="true" paraType="questionSort" />
			</div>
			<div class="unit">
				<label>
					问题描述：
				</label>
				<my:newselect tagName="questionDescConditionC"  paraType="condition3" />
				<input name="questionDescC" class="textInput" selectFlag="true" type="text" />
			</div>
			<div class="unit">
				<label>
					发现问题时间大于：
				</label>
				<input type="text" name="smallQuestionDateC" readOnly="true"
					class="date" size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					发现问题时间小于：
				</label>
				<input type="text" name="bigQuestionDateC" readOnly="true"
					class="date" size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					问题状态：
				</label>
				<my:newselect tagName="questionStatusConditionC"  
					paraType="condition3" />
				<my:newselect tagName="questionStatusC" selectFlag="true" paraType="questionStatus" />
			</div>
			<div class="unit">
				<label>
					解决问题时间大于：
				</label>
				<input type="text" name="smallConsoleDateC" readOnly="true"
					class="date" size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					解决问题时间小于：
				</label>
				<input type="text" name="bigConsoleDateC" readOnly="true"
					class="date" size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					问题答案：
				</label>
				<my:newselect tagName="answerConditionC" paraType="condition3"  />
				<input name="answerC" class="textInput" selectFlag="true" type="text" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="test()">
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

