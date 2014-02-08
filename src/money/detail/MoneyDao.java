package money.detail;

import java.util.Collection;

import dwz.dal.BaseDao;

public interface MoneyDao extends BaseDao<MoneyVO, Integer> {

	public Collection<MoneyVO> findRecordById(int moneySno); 
	
	/**
	 * 调用hql语句进行查询.
	 * @param sql
	 * @param arguments
	 * @return
	 */
	public Collection<Object[]> hibernateSqlFindByType(String sql,Object[] arguments);
	
	/**
	 * 根据年份进行统计金额数据.
	 * @param sql
	 * @param arguments
	 * @return
	 */
	public Collection<Object[]> commonSqlGroupByYear(String sql);
	
	/**
	 * 统计某一年的金额数据.
	 * @param sql
	 * @param arguments 参数为年份.s
	 * @return
	 */
	public Collection<Object[]> commonSqlGroupByMonth(String sql,Object[] arguments);
	
	public Collection<MoneyVO> findAll(); 

	public void deleteAllById(String id);

	public void updateAllaccessMoneyVO(MoneyVO vo, String id);
}
