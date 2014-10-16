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
	private String columnType;
	// 数据库中的列名
	private String column;
	private String textarea;
	private String selectType;
	// 是否可见.
	private String visible;
	private String query;
	// 非空.
	private String notnull;
	// 属性描述
	private String desc;
	// jsp中描述属性的样式（number）
	private String clas;
	// 长度
	private String length;
	/**
	 * 是否是主键.
	 */
	private String iskey;
	/**
	 * 最大长度
	 */
	private String maxLength;
	/**
	 * 控件类型：默认为input，可选：resource depart customer areatext select checkbox radio
	 */
	private String inputType;
	/**
	 * 下拉菜单的可选文本值
	 */
	private String names;
	/**
	 * 多行文本里面的列数
	 */
	private String cols;
	/**
	 * 多行文本里面的行数
	 */
	private String rows;

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	/**
	 * 下拉菜单的对应值
	 */
	private String values;

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	/**
	 * 最小长度
	 */
	private String minLength;

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	public String getMinLength() {
		return minLength;
	}

	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}

	// 列类型
	private String type;
	// 节点类型：属性，或者id列
	private String nodeType;
	// 列宽度
	private String width;

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

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

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getNotnull() {
		return notnull;
	}

	public void setNotnull(String notnull) {
		this.notnull = notnull;
	}

	public String getIskey() {
		return iskey;
	}

	public void setIskey(String iskey) {
		this.iskey = iskey;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

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
