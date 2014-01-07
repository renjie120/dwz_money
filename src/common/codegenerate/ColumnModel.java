package common.codegenerate;


/**
 * 分析配置文件的节点.
 * 
 * @author lsq
 * 
 */
public class ColumnModel {
	// VO中的属性名
	private String name;
	private String noedit;
	private String querylike;
	public String getQuerylike() {
		return querylike;
	}

	public void setQuerylike(String querylike) {
		this.querylike = querylike;
	}

	public String getNoedit() {
		return noedit;
	}

	public void setNoedit(String noedit) {
		this.noedit = noedit;
	}

	// 数据库中的列名
	private String column;
	private String textarea;
	private String selectType;
	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public String getTextarea() {
		return textarea;
	}

	public void setTextarea(String textarea) {
		this.textarea = textarea;
	}

	//是否可见.
	private String visible;
	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}
	private String query;
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	//非空.
	private String notnull;
  

	public String getNotnull() {
		return notnull;
	}

	public void setNotnull(String notnull) {
		this.notnull = notnull;
	}

	/**
	 * 是否是主键.
	 */
	private String iskey;

	public String getIskey() {
		return iskey;
	}

	public void setIskey(String iskey) {
		this.iskey = iskey;
	}

	// 列类型
	private String type;
	// 节点类型：属性，或者id列
	private String nodeType;
	//列宽度
	private String width;
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	// 属性描述
	private String desc;
	// jsp中描述属性的样式（number）
	private String clas;
	// 长度
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
