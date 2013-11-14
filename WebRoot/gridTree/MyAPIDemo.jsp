
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<table>
  <tr>
    <td><button onclick='reload()'>网页刷新</button> </td>
    <td>$(表格树id).reload();</td>
  </tr>
  <tr>
    <td>
      <button onclick='openNode(this)'>展开节点</button>
      <input style='width:50px' value='2200'>
      <td>$(表格树id).open(id);</td>
    </tr>
    <tr>
      <td>
        <button onclick='closeNode(this)'>关闭节点</button>
        <input style='width:50px'  value='2200'>
        <td>$(表格树id).close(id);</td>
      </tr>
      <tr>
        <td>
          <button onclick='expandAllNodes()'>展开全部节点</button>
        </td>
      </td>
    </td>
    <td>$(表格树id).expandAll();</td>
  </tr>
  <tr>
    <td>
      <button onclick='closeAllNodes()'>关闭全部节点</button>
    </td>
    <td>$(表格树id).closeAll();</td>
  </tr>
  <tr>
    <td>
      <button onclick='setdisable(true)'>禁用表格树</button>
    </td>
    <td>$(表格树id).disabled(true);</td>
  </tr>
  <tr>
    <td>
      <button onclick='setdisable(false)'>启用表格树</button>
    </td>
    <td>$(表格树id).disabled(false);</td>
  </tr>
  <tr>
    <td>
      <button onclick='setHeight(600)'>change高度</button>
    </td>
    <td>$(表格树id).resetHeight(600);</td>
  </tr>
  <tr>
    <td>
      <button onclick='setWidth(600)'>change宽度</button>
    </td>
    <td>$(表格树id).resetWidth(600);</td>
  </tr>
  <tr>
    <td>
      <button onclick='setJsonData()'>设置json串</button>
    </td>
    <td>$(表格树id).setJsonData(json);</td>
  </tr>
  <tr>
    <td>
      <button onclick='getPara()'>表格树参数</button>
    </td>
    <td>$(表格树id).getPara();</td>
  </tr>
  <tr>
    <td>
      <button onclick='reloadTree()'>动态刷新</button>
    </td>
    <td>$(表格树id).reload();</td>
  </tr>
  <tr>
    <td>
      <button onclick='searchTree()'>查询节点</button>
    </td>
    <td>$(表格树id).search(para);</td>
  </tr>
</table>

