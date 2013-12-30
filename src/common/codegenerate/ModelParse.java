package common.codegenerate;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import common.util.DomUtil;

/**
 * 解析数据说明文件.
 * 
 * @author lisq
 * 
 */
public class ModelParse {
	private String fileName;
	private List<ColumnModel> attributes = new ArrayList<ColumnModel>();
	private String table;
	private String className;
	private String classDesc;
	private String packageName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ClassModel parse() {
		ClassModel model = new ClassModel();
		Document doc = DomUtil.getXmlDocument(fileName);
		Node list = doc.getElementsByTagName("class").item(0);
		table = DomUtil.getAttribute(list, "table");
		packageName = DomUtil.getAttribute(list, "package");
		className = DomUtil.getAttribute(list, "name");
		classDesc = DomUtil.getAttribute(list, "desc");
		NodeList children = list.getChildNodes();
		for (int j = 0; j < children.getLength(); j++) {
			Node child = children.item(j);
			if ("id".equals(child.getNodeName())
					|| "property".equals(child.getNodeName())) {
				ColumnModel config = new ColumnModel();
				if ("id".equals(child.getNodeName())) {
					config.setIskey("true");
					model.setKeyDesc(DomUtil.getAttribute(child, "desc"));
					model.setKeyName(DomUtil.getAttribute(child, "name"));
					model.setKeyColumn(DomUtil.getAttribute(child, "column"));
					model.setKeyType(DomUtil.getAttribute(child, "type"));
				} else
					config.setIskey("false");
				config.setNodeType(child.getNodeName());
				config.setDesc(DomUtil.getAttribute(child, "desc"));
				config.setName(DomUtil.getAttribute(child, "name"));
				config.setWidth(DomUtil.getAttribute(child, "width"));
				config.setColumn(DomUtil.getAttribute(child, "column"));
				config.setType(DomUtil.getAttribute(child, "type"));
				config.setNotnull(DomUtil.getAttribute(child, "notnull"));
				config.setClas(DomUtil.getAttribute(child, "class"));
				config.setLength(DomUtil.getAttribute(child, "length")); 
				attributes.add(config);
			}
		}
		model.setTable(table);
		model.setClassName(className);
		model.setPackageName(packageName);
		model.setClassDesc(classDesc);
		model.setAttributes(attributes);
		return model;
	}
}
