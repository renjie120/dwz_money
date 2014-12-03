<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};

import java.util.Collection;

import dwz.dal.BaseDao; 

/**
 * 关于${model.classDesc}的数据库操作接口
 * @author ${author}
 * ${auth}
 * ${website}
 */ 
public interface ${dao} extends BaseDao<${vo}, Integer> {
	/**
	 * 根据主键查询全部集合.
	 */
	public Collection<${vo}> findRecordById(${model.keyType} ${model.keyName}); 
	
	/**
	 * 查询全部集合.
	 */
	public Collection<${vo}> findAll(); 
	
	/**
	 * 根据条件查询全部集合.
	 */
	public Collection<${vo}> findParmByType(int paramType);  

	/**
	 * 根据主键删除数据.
	 */
	public void deleteAllById(${model.keyType} ${model.keyName});

	/**
	 * 根据主键更新数据.
	 */
	public void updateAllaccess${vo}(${vo} vo, ${model.keyType} ${model.keyName});
}
