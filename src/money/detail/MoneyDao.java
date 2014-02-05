package money.detail;

import java.util.Collection;

import dwz.dal.BaseDao;

public interface MoneyDao extends BaseDao<MoneyVO, Integer> {

	public Collection<MoneyVO> findRecordById(int moneySno); 
	
	/**
	 * 调用原始sql语句进行查询.
	 * @param sql
	 * @param arguments
	 * @return
	 */
	public Collection<Object[]> hibernateSqlFindByType(String sql,Object[] arguments);
	
	public Collection<MoneyVO> findAll(); 

	public void deleteAllById(String id);

	public void updateAllaccessMoneyVO(MoneyVO vo, String id);
}
