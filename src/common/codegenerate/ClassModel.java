package common.codegenerate;

import java.util.List;

/**
 * 类的结构文件.
 * 
 * @author lisq
 * 
 */
public class ClassModel {
	private List attributes = null;
	private String idKey = "";
	private String entryName = "";
	private String entryDesc = "";
	private String idColumn = "";
	private String idType = "";
	private String tableName = "";

	public List getAttributes() {
		return attributes;
	}

	public void setAttributes(List attributes) {
		this.attributes = attributes;
	}

	public String getIdKey() {
		return idKey;
	}

	public void setIdKey(String idKey) {
		this.idKey = idKey;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getEntryDesc() {
		return entryDesc;
	}

	public void setEntryDesc(String entryDesc) {
		this.entryDesc = entryDesc;
	}

	public String getIdColumn() {
		return idColumn;
	}

	public void setIdColumn(String idColumn) {
		this.idColumn = idColumn;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
