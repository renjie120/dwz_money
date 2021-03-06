﻿package money.detail;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface MoneyManager extends BusinessObjectManager {

	public Collection<Money> searchMoney(
			Map<MoneySearchFields, Object> criterias, String orderField,
			int startIndex, int count);

	public Integer searchMoneyNum(Map<MoneySearchFields, Object> criterias);

	/**
	 * 根据条件按照分类重新金额总数.
	 * 
	 * @param criterias
	 * @return
	 */
	public Collection<Money> searchMoneyByType(
			Map<MoneySearchFields, Object> criterias);

	/**
	 * 根据年份进行分组统计金额数据.
	 * 
	 * @return
	 */
	public Collection<Object[]> reportMoneyGroupByYear();

	/**
	 * 根据年份，月份进行统计金额数据.
	 * 
	 * @param year
	 * @return
	 */
	public Collection<Object[]> reportMoneyGroupByMonth(int year);

	@Transactional
	public void createMoney(Money money) throws ValidateFieldsException;

	@Transactional
	public void createMoney(Money money, int splitMonths)
			throws ValidateFieldsException;

	@Transactional
	public void updateMoney(Money money) throws ValidateFieldsException;

	@Transactional
	public void updateMoney(Money money,int splitMonth) throws ValidateFieldsException;

	@Transactional
	public void removeMoney(String moneyId);

	public Money getMoney(Integer id);

	@Transactional
	public void importFromExcel(File file);

	@Transactional
	public String syn(String method, String arg, String data);
}
