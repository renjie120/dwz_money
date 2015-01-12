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
	private String fromTable;
	private String idCoulmn;
	private String nameColumn;
	private String allSelect;
	//使用缓存id.(在表单类型是select的时候才有用)
	private String useCacheId;
	public String getUseCacheId() {
		return useCacheId;
	}

	public void setUseCacheId(String useCacheId) {
		this.useCacheId = useCacheId;
	}

	public String getAllSelect() {
		return allSelect;
	}

	public void setAllSelect(String allSelect) {
		this.allSelect = allSelect;
	}

	public String getFromTable() {
		return fromTable;
	}

	public void setFromTable(String fromTable) {
		this.fromTable = fromTable;
	}

	public String getIdCoulmn() {
		return idCoulmn;
	}

	public void setIdCoulmn(String idCoulmn) {
		this.idCoulmn = idCoulmn;
	}

	public String getNameColumn() {
		return nameColumn;
	}

	public void setNameColumn(String nameColumn) {
		this.nameColumn = nameColumn;
	}

	private String noadd;
	private String currentUser;
	private String currentTime;
	//是否可以导入
	private String canImport;
	//数据库中唯一字段
	private String notNullInDb;
	public String getNotNullInDb() {
		return notNullInDb;
	}

	public void setNotNullInDb(String notNullInDb) {
		this.notNullInDb = notNullInDb;
	}

	//是否可以导出该字段
	private String canExport;
	public String getCanExport() {
		return canExport;
	}

	public void setCanExport(String canExport) {
		this.canExport = canExport;
	}

	public String getCanImport() {
		return canImport;
	}

	public void setCanImport(String canImport) {
		this.canImport = canImport;
	}

	//数据的最大长度.
	private String size;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getNoadd() {
		return noadd;
	}

	public void setNoadd(String noadd) {
		this.noadd = noadd;
	}

	private String querylike;
	private String columnType; 
  
	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	private String showType;
	// 数据库中的列名
	private String column; 
	/**
	 * 浏览框对应的url地址.
	 */
	private String browerUrl;

	public String getBrowerUrl() {
		return browerUrl;
	}

	public void setBrowerUrl(String browerUrl) {
		this.browerUrl = browerUrl;
	}

	// 是否可见.
	private String visible;
	/**
	 * 是否出现在查询条件中
	 */
	private String query;
	/**
	 * 是否出现在浏览框中.
	 */
	private String brower;

	public String getBrower() {
		return brower;
	}

	public void setBrower(String brower) {
		this.brower = brower;
	}

	// 非空.
	private String notnull;
	// 属性描述
	private String desc;
	// jsp中描述属性的样式（number）
	private String clas;
	// 长度
	private String length;
	// 下拉菜单对应的编码
	private String selectCode;
	public String getSelectCode() {
		return selectCode;
	}

	public void setSelectCode(String selectCode) {
		this.selectCode = selectCode;
	}

	/**
	 * 是否是主键.
	 */
	private String iskey;
	/**
	 * 最大长度
	 */
	private String maxLength;
	/**
	 * 控件类型：默认为input，可选：resource depart customer textarea select checkbox radio
	 */
	// private String inputType;
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

	// public String getInputType() {
	// return inputType;
	// }
	//
	// public void setInputType(String inputType) {
	// this.inputType = inputType;
	// }

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

	// 列类型:可选：resource int date double depart customer areatext select checkbox
	// radio
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
 

//	public String getTextarea() {
//		return textarea;
//	}
//
//	public void setTextarea(String textarea) {
//		this.textarea = textarea;
//	}

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
