package money.detail;

import java.util.Collection;

import dwz.dal.BaseDao;

public interface MoneyDao extends BaseDao<MoneyVO, Integer> {

	public Collection<MoneyVO> findRecordById(int moneySno); 
	
	public Collection<MoneyVO> findAll(); 

	public void deleteAllById(String id);

	public void updateAllaccessMoneyVO(MoneyVO vo, String id);
}
