<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp" %> 

<div style="float:left; margin:10px">
	<div id="fileQueue" class="fileQueue"></div>
	<input id="testFileInput" type="file" name="image" 
		uploader="uploadify/scripts/uploadify.swf"
		cancelImg="uploadify/cancel.png" 
		script="struts2/ajaxDone.html" 
		scriptData="{PHPSESSID:'xxx', ajax:1}"
		fileQueue="fileQueue"
		onAllComplete="uploadifyAllComplete" 
		fileExt="*.jpg;*.jpeg;*.gif;*.png;"
		fileDesc="*.jpg;*.jpeg;*.gif;*.png;"/>
</div>

<div style="float:left; margin:10px">
	<div id="fileQueue2" class="fileQueue"></div>
	<input id="testFileInput2" type="file" name="image2" 
		uploader="uploadify/scripts/uploadify.swf"
		cancelImg="uploadify/cancel.png" 
		script="struts2/ajaxDone.html" 
		scriptData="{PHPSESSID:'xxx', ajax:1}"
		fileQueue="fileQueue2" />
</div>