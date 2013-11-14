package money.moneytype;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface MoneyTypeManager extends BusinessObjectManager {

	public Collection<MoneyType> searchMoneyType(
			Map<MoneyTypeSearchFields, Object> criterias, String orderField,
			int startIndex, int count);

	public Integer searchMoneyTypeNum(
			Map<MoneyTypeSearchFields, Object> criterias);

	@Transactional
	public void createMoneyType(MoneyType moneyType)
			throws ValidateFieldsException;

	@Transactional
	public void updateMoneyType(MoneyType moneyType)
			throws ValidateFieldsException;

	@Transactional
	public void removeMoneyType(String moneyTypeId);

	public MoneyType getMoneyType(Integer id);
}
