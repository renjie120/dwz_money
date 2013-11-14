package common.util;

/**
 * 分析配置文件的节点.
 * @author lsq
 *
 */
public class Config {
	//VO中的属性名
	private String name;
	//数据库中的列名
	private String column;
	//列类型
	private String type;
	//节点类型：属性，或者id列
	private String nodeType;
	//属性描述
	private String desc;
	//jsp中描述属性的样式（number）
	private String clas;
	//长度
	private String length;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getClas() {
		return clas;
	}
	public void setClas(String clas) {
		this.clas = clas;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
}
