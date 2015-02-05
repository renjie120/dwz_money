
package money.sequence;

import java.util.Collection;

import dwz.dal.BaseDao; 

/**
 * 关于系统序列号的数据库操作接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface SystemSequenceDao extends BaseDao<SystemSequenceVO, Integer> {
	/**
	 * 根据主键查询全部集合.
	 */
	public Collection<SystemSequenceVO> findRecordById(int sno); 
	
	/**
	 * 查询全部集合.
	 */
	public Collection<SystemSequenceVO> findAll(); 
	
	/**
	 * 根据条件查询全部集合.
	 */
	public Collection<SystemSequenceVO> findParmByType(int paramType);  

	/**
	 * 根据主键删除数据.
	 */
	public void deleteAllById(int sno);

	/**
	 * 根据主键更新数据.
	 */
	public void updateAllaccessSystemSequenceVO(SystemSequenceVO vo, int sno);
}
