package money.moneytype;

import java.util.Collection;
import java.util.List;

import dwz.dal.BaseDao;

public interface MoneyTypeDao extends BaseDao<MoneyTypeVO, Integer> {

	public Collection<MoneyTypeVO> findRecordById(int moenyTypeSno);
	
	/**
	 * 返回指定节点的孩子节点.
	 * @param moenyTypeSno
	 * @return
	 */
	public Collection<MoneyTypeVO> findChildren(String codeStr);
	
	/**
	 * 返回第一层的节点.
	 * @return
	 */
	public Collection<MoneyTypeVO> findFirstLevel();
	
	/**
	 * 返回孩子节点的数目.
	 * @param moenyTypeSno
	 * @return
	 */
	public List findChildCount(String moenyTypeSno);

	public Collection<MoneyTypeVO> findAll(); 

	public void deleteAllById(String id);

	public void updateAllaccessMoneyTypeVO(MoneyTypeVO vo, String id);
}
