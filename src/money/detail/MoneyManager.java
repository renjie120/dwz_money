package money.detail;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface MoneyManager extends BusinessObjectManager {  

	public Collection<Money> searchMoney(Map<MoneySearchFields, Object> criterias,
			String orderField, int startIndex, int count); 
	
	public Integer searchMoneyNum(Map<MoneySearchFields, Object> criterias); 

	@Transactional
	public void createMoney(Money money)
			throws ValidateFieldsException;

	@Transactional
	public void updateMoney(Money money) throws ValidateFieldsException;

	@Transactional
	public void removeMoney(String moneyId);
	
	public Money getMoney(Integer id);
	
	@Transactional
	public void importFromExcel(File file);
	
	@Transactional
	public String syn(String method,String arg,String data);
}
