package common.codegenerate;

import java.util.List;

/**
 * 类的结构文件.
 * 
 * @author lisq
 * 
 */
public class ClassModel {
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassDesc() {
		return classDesc;
	}

	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
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

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	private String keyName;
	private String keyColumn;
	private String keyType;
	private String keyDesc;
	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyColumn() {
		return keyColumn;
	}

	public void setKeyColumn(String keyColumn) {
		this.keyColumn = keyColumn;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getKeyDesc() {
		return keyDesc;
	}

	public void setKeyDesc(String keyDesc) {
		this.keyDesc = keyDesc;
	}

	private List attributes = null;
	private String idKey = "";
	private String className = "";
	private String packageName = "";
	private String classDesc = "";
	private String idColumn = "";
	private String idType = "";
	private String table = "";

}
