package common.codegenerate;

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
	private String idKey;
	private String idColumn;
	private String idType;
	private List<ColumnModel> attributes;
	private String tableName;
	private String entryName;
	private String entryDesc;

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
		tableName = DomUtil.getAttribute(list, "table");
		entryName = DomUtil.getAttribute(list, "name");
		entryDesc = DomUtil.getAttribute(list, "desc");
		NodeList children = list.getChildNodes();
		for (int j = 0; j < children.getLength(); j++) {
			Node child = children.item(j);
			if ("id".equals(child.getNodeName())
					|| "property".equals(child.getNodeName())) {
				ColumnModel config = new ColumnModel();
				if ("id".equals(child.getNodeName())) {
					idKey = DomUtil.getAttribute(child, "name");
					idColumn = DomUtil.getAttribute(child, "column");
					idType = DomUtil.getAttribute(child, "type");
				}
				config.setNodeType(child.getNodeName());
				config.setDesc(DomUtil.getAttribute(child, "desc"));
				config.setName(DomUtil.getAttribute(child, "name"));
				config.setColumn(DomUtil.getAttribute(child, "column"));
				config.setClas(DomUtil.getAttribute(child, "class"));
				config.setLength(DomUtil.getAttribute(child, "length"));
				config.setType(DomUtil.getAttribute(child, "type"));
				attributes.add(config);
			}
		} 
		model.setTableName(tableName);
		model.setEntryName(entryName);
		model.setEntryDesc(entryDesc);
		model.setIdKey(idKey);
		model.setIdColumn(idColumn);
		model.setIdType(idType);
		model.setAttributes(attributes);
		return model;
	}
}
