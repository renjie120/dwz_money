package common.codegenerate;

import java.util.List;

import common.util.IDatamodelXmlReader;

/**
 * 类的结构文件.
 * 
 * @author lisq
 * 
 */
public class Dom4jClassModel implements IDatamodelXmlReader {
	private String keyName;
	private String keyColumn;
	private String keyType;
	/**
	 * 主键对应的数据库字段类型
	 */
	private String keyColumnType;
	private String keyDesc; 
	private List attributes = null;
	private String idKey = "";
	private String className = "";
	// 是否添加到缓存
	private String addToCache = "";
	private String cacheIdColumn = "";
	private String cacheNameColumn = "";
	private String cacheName = "";
	private String packageName = "";
	private String classDesc = "";
	private String idColumn = "";
	private String idType = "";
	private String table = "";
	private String canImport;
	private String canExport;
	private String canAdd;
	private String canUpdate;
	private String canDelete;
	private String importRole;
	private String exportRole;
	private String addRole;
	private String updateRole;
	private String deleteRole;
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
	public String getKeyColumnType() {
		return keyColumnType;
	}
	public void setKeyColumnType(String keyColumnType) {
		this.keyColumnType = keyColumnType;
	}
	public String getKeyDesc() {
		return keyDesc;
	}
	public void setKeyDesc(String keyDesc) {
		this.keyDesc = keyDesc;
	}
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
	public String getAddToCache() {
		return addToCache;
	}
	public void setAddToCache(String addToCache) {
		this.addToCache = addToCache;
	}
	public String getCacheIdColumn() {
		return cacheIdColumn;
	}
	public void setCacheIdColumn(String cacheIdColumn) {
		this.cacheIdColumn = cacheIdColumn;
	}
	public String getCacheNameColumn() {
		return cacheNameColumn;
	}
	public void setCacheNameColumn(String cacheNameColumn) {
		this.cacheNameColumn = cacheNameColumn;
	}
	public String getCacheName() {
		return cacheName;
	}
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
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
	public String getCanImport() {
		return canImport;
	}
	public void setCanImport(String canImport) {
		this.canImport = canImport;
	}
	public String getCanExport() {
		return canExport;
	}
	public void setCanExport(String canExport) {
		this.canExport = canExport;
	}
	public String getCanAdd() {
		return canAdd;
	}
	public void setCanAdd(String canAdd) {
		this.canAdd = canAdd;
	}
	public String getCanUpdate() {
		return canUpdate;
	}
	public void setCanUpdate(String canUpdate) {
		this.canUpdate = canUpdate;
	}
	public String getCanDelete() {
		return canDelete;
	}
	public void setCanDelete(String canDelete) {
		this.canDelete = canDelete;
	}
	public String getImportRole() {
		return importRole;
	}
	public void setImportRole(String importRole) {
		this.importRole = importRole;
	}
	public String getExportRole() {
		return exportRole;
	}
	public void setExportRole(String exportRole) {
		this.exportRole = exportRole;
	}
	public String getAddRole() {
		return addRole;
	}
	public void setAddRole(String addRole) {
		this.addRole = addRole;
	}
	public String getUpdateRole() {
		return updateRole;
	}
	public void setUpdateRole(String updateRole) {
		this.updateRole = updateRole;
	}
	public String getDeleteRole() {
		return deleteRole;
	}
	public void setDeleteRole(String deleteRole) {
		this.deleteRole = deleteRole;
	}
	public String getCanComplexQuery() {
		return canComplexQuery;
	}
	public void setCanComplexQuery(String canComplexQuery) {
		this.canComplexQuery = canComplexQuery;
	}
	private String canComplexQuery;
}
