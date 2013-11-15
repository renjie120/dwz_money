package common.base;

import java.util.Collection;
import java.util.Iterator;

/**
 * 树的工具类.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class TreeTool<T extends TreeAble> {
	public String getTreeStr(Collection<T> c) {
		StringBuffer buffer = new StringBuffer("[");
		Iterator<T> it = c.iterator();
		while (it.hasNext()) {
			T vo = it.next();
			buffer.append("{'id':'" + vo.getTreeId() + "','pId':'"
					+ vo.getParentId() + "','name':'" + vo.getTreeName()
					+ "'},");
		}
		if (buffer.length() > 1)
			buffer = buffer.deleteCharAt(buffer.lastIndexOf(","));
		buffer.append("]");
		return buffer.toString();
	}
}
