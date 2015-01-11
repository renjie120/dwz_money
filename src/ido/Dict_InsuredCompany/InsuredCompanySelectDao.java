
package ido.Dict_InsuredCompany;

import java.util.Collection;

import dwz.dal.BaseDao; 

/**
 * 关于保险公司字典表的数据库操作接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface InsuredCompanySelectDao extends BaseDao<InsuredCompanySelectVO, Integer> {
	/**
	 * 根据主键查询全部集合.
	 */
	public Collection<InsuredCompanySelectVO> findRecordById(int sno); 
	
	/**
	 * 查询全部集合.
	 */
	public Collection<InsuredCompanySelectVO> findAll(); 
	
	/**
	 * 根据条件查询全部集合.
	 */
	public Collection<InsuredCompanySelectVO> findParmByType(int paramType);  

	/**
	 * 根据主键删除数据.
	 */
	public void deleteAllById(int sno);

	/**
	 * 根据主键更新数据.
	 */
	public void updateAllaccessInsuredCompanySelectVO(InsuredCompanySelectVO vo, int sno);
}
