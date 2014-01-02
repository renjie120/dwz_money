
package money.paramtype;

import java.util.Collection;

import dwz.dal.BaseDao;

/**
 * 关于参数类型的数据库操作接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface ParamTypeDao extends BaseDao<ParamTypeVO, Integer> {
	/**
	 * 根据主键查询全部集合.
	 */
	public Collection<ParamTypeVO> findRecordById(int paramTypeId); 
	

	public Collection<ParamTypeVO> findAllTypeCode(); 
	
	/**
	 * 查询全部集合.
	 */
	public Collection<ParamTypeVO> findAll(); 
	
	/**
	 * 根据条件查询全部集合.
	 */
	public Collection<ParamTypeVO> findParmByType(int paramType);  

	/**
	 * 根据主键删除数据.
	 */
	public void deleteAllById(int paramTypeId);

	/**
	 * 根据主键更新数据.
	 */
	public void updateAllaccessParamTypeVO(ParamTypeVO vo, int paramTypeId);
}
