package common.util;

import java.util.List;

import common.codegenerate.ColumnModel;

/**
 * 解析datamodel文件的接口.
 * 
 * @author Administrator
 * 
 */
public interface IDatamodelXmlReader {
	/**
	 * 得到如果是有缓存，缓存对应的id数据库列.
	 * 
	 * @return
	 */
	public String getCacheIdColumn();

	/**
	 * 缓存对应的name数据库列
	 * @return
	 */
	public String getCacheNameColumn();

	/**
	 * 缓存名称
	 * @return
	 */
	public String getCacheName();

	/**
	 * 对应的数据库表名
	 * 
	 * @return
	 */
	public String getTable();

	/**
	 * 得到对应的类名.
	 * 
	 * @return
	 */
	public String getClassName(); 

	/**
	 * 得到类的描述.
	 * 
	 * @return
	 */
	public String getClassDesc();

	/**
	 * 得到包名
	 * 
	 * @return
	 */
	public String getPackageName();

	/**
	 * 得到全部的字段属性配置列表
	 * 
	 * @return
	 */
	public List<ColumnModel>  getAttributes();

	/**
	 * 是否支持导入
	 * @return
	 */
	public String getCanImport();

	/**
	 * 是否支持导出
	 * @return
	 */
	public String getCanExport();

	/**
	 * 是否有添加按钮
	 * @return
	 */
	public String getCanAdd();

	/**
	 * 是否可以更新
	 * @return
	 */
	public String getCanUpdate();

	/**
	 * 是否可以删除
	 * @return
	 */
	public String getCanDelete();

	/**
	 * 是否有导入的权限控制，有的话是权限id
	 * @return
	 */
	public String getImportRole();

	/**
	 * 是否有导出权限控制
	 * @return
	 */
	public String getExportRole();

	/**
	 * 是否有添加权限控制
	 * @return
	 */
	public String getAddRole();

	/**
	 * 是否有更新权限控制
	 * @return
	 */
	public String getUpdateRole();

	/**
	 * 是否有删除权限控制
	 * @return
	 */
	public String getDeleteRole();

	/**
	 * 是否有复杂查询
	 * @return
	 */
	public String getCanComplexQuery();

	/**
	 * 主键描述
	 * @return
	 */
	public String getKeyName();

	/**
	 * 主键对应数据库字段
	 * @return
	 */
	public String getKeyColumn();

	/**
	 * 主键类型
	 * @return
	 */
	public String getKeyType();
 
	/**
	 * 是否添加到缓存
	 * @return
	 */
	public String getAddToCache();

	/**
	 * 主键对应的数据库列
	 * @return
	 */
	public String getIdColumn();

	/**
	 * 主键对应的类型
	 * @return
	 */
	public String getIdType();
}
