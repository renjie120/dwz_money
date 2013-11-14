
package money.paramtype;

import java.util.Collection;

import money.moneytype.MoneyTypeVO;

import dwz.dal.BaseDao;

public interface ParamTypeDao extends BaseDao<ParamTypeVO, Integer> {

	public Collection<ParamTypeVO> findRecordById(int moneySno); 
	
	public Collection<ParamTypeVO> findAll();  

	public Collection<ParamTypeVO> findAllTypeCode();
	
	public void deleteAllById(String id);

	public void updateAllaccessParamTypeVO(ParamTypeVO vo, String id);
}

