<html>
<head>        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> </head>
<#include "/com.renjie120.codegenerate.common.ftl">
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
<table class="gridtable"> 
<tr><td>keyType</td><td>${model.keyType}</td>  
<td>keyName</td><td>${model.keyName}</td>  
<td>keyColumn</td><td>${model.keyColumn}</td>  </tr> 

<tr><td>keyColumnType</td><td>${model.keyColumnType}</td>  
<td>keyDesc</td><td>${model.keyDesc}</td>  
<td>idKey</td><td>${model.idKey}</td>  </tr> 

<tr><td>addToCache</td><td>${model.addToCache}</td>  
<td>cacheIdColumn</td><td>${model.cacheIdColumn}</td>  
<td>cacheNameColumn</td><td>${model.cacheNameColumn}</td>  </tr> 

<tr><td>cacheName</td><td>${model.cacheName}</td>  
<td>packageName</td><td>${model.packageName}</td>  
<td>classDesc</td><td>${model.classDesc}</td>  </tr> 

<tr><td>idColumn</td><td>${model.idColumn}</td>  
<td>idType</td><td>${model.idType}</td>  
<td>table</td><td>${model.table}</td>  </tr> 

<tr><td>canImport</td><td>${model.canImport}</td>  
<td>canExport</td><td>${model.canExport}</td>  
<td>canAdd</td><td>${model.canAdd}</td>  </tr> 

<tr><td>canUpdate</td><td>${model.canUpdate}</td>  
<td>canDelete</td><td>${model.canDelete}</td>  
<td>importRole</td><td>${model.importRole}</td>  </tr> 

<tr><td>exportRole</td><td>${model.exportRole}</td>  
<td>addRole</td><td>${model.addRole}</td>  
<td>updateRole</td><td>${model.updateRole}</td>  </tr> 

<tr><td>deleteRole</td><td>${model.deleteRole}</td>  
<td>canComplexQuery</td><td colspan="3">${model.canComplexQuery}</td>   </tr> 
</table>
下面测试全部的属性
<table  class="gridtable"><thead><th>name</th><th>noedit</th><th>fromTable</th><th>idCoulmn</th><th>nameColumn</th><th>allSelect</th><th>type</th><th>currentUser</th><th>nodeType</th><th>currentTime</th><th>canExport</th><th>noadd</th><th>size</th><th>querylike</th><th>columnType</th><th>showType</th><th>width</th><th>visible</th><th>query</th><th>brower</th><th>notnull</th><th>column</th><th>browerUrl</th><th>canImport</th><th>notNullInDb</th><th>complexQueryType</th><th>clas</th><th>length</th><th>useCacheId</th><th>desc</th></thead>
<#list model.attributes as attr>
<tr><td>${attr.name} </td><td>${attr.noedit}</td><td>${attr.fromTable}</td><td>${attr.idCoulmn}</td><td>${attr.nameColumn}</td><td>${attr.allSelect}</td><td>${attr.type}</td><td>${attr.currentUser}</td><td>${attr.nodeType}</td><td>${attr.width}</td><td>${attr.currentTime}</td><td>${attr.canExport}</td><td>${attr.noadd}</td><td>${attr.size}</td><td>${attr.querylike}</td><td>${attr.columnType}</td><td>${attr.showType}</td><td>${attr.visible}</td><td>${attr.query}</td><td>${attr.brower}</td><td>${attr.notnull}</td><td>${attr.column}</td><td>${attr.browerUrl}</td><td>${attr.canImport}</td><td>${attr.notNullInDb}</td><td>${attr.complexQueryType}</td><td>${attr.clas}</td><td>${attr.length} </td><td>${attr.useCacheId}</td><td>${attr.desc} </tr></#list>
</table>
</body>
</html>