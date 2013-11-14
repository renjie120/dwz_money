package money.moneytype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public class MoneyTypeManagerImpl extends AbstractBusinessObjectManager
		implements MoneyTypeManager {

	private MoneyTypeDao moneyTypeDao = null;

	public MoneyTypeManagerImpl(MoneyTypeDao moneyTypeDao) {
		this.moneyTypeDao = moneyTypeDao;
	}

	public Integer searchMoneyTypeNum(
			Map<MoneyTypeSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.moneyTypeDao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	public Collection<MoneyType> searchMoneyType(
			Map<MoneyTypeSearchFields, Object> criterias, String orderField,
			int startIndex, int count) {
		ArrayList<MoneyType> eaList = new ArrayList<MoneyType>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		// 直接根据hql语句进行查询.
		Collection<MoneyTypeVO> voList = this.moneyTypeDao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (MoneyTypeVO po : voList) {
			eaList.add(new MoneyTypeImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<MoneyTypeSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct moneyType) "
						: "select distinct moneyType ").append(
				"from MoneyTypeVO as moneyType ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<MoneyTypeSearchFields, Object> entry : criterias
					.entrySet()) {
				MoneyTypeSearchFields fd = entry.getKey();
				switch (fd) {
				case MOENYTYPESNO:
					sb.append(count == 0 ? " where" : " and").append(
							" moneyType.moenyTypeSno=? ");
					argList.add(entry.getValue());
					count++;
					break;
				case MONEYTYPEDESC:
					sb.append(count == 0 ? " where" : " and").append(
							" moneyType.moneyTypeDesclike ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case MONEYTYPE:
					sb.append(count == 0 ? " where" : " and").append(
							" moneyType.moneyTypelike ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case PARENTCODE:
					sb.append(count == 0 ? " where" : " and").append(
							" moneyType.parentCodelike ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case TYPECODE:
					sb.append(count == 0 ? " where" : " and").append(
							" moneyType.typeCodelike ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ORDERID:
					sb.append(count == 0 ? " where" : " and").append(
							" moneyType.orderId=? ");
					argList.add(entry.getValue());
					count++;
					break;
				default:
					break;
				}
			}
		if (!useCount) {
			sb.append(" order by orderid  ");
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createMoneyType(MoneyType moneyType)
			throws ValidateFieldsException {
		MoneyTypeImpl moneyTypeImpl = (MoneyTypeImpl) moneyType;
		this.moneyTypeDao.insert(moneyTypeImpl.getMoneyTypeVO());
	}

	public void removeMoneyType(String moenyTypeSno) {
		String[] ids = moenyTypeSno.split(",");
		for (String s : ids) {
			MoneyTypeVO vo = this.moneyTypeDao.findByPrimaryKey(Integer
					.parseInt(s));
			this.moneyTypeDao.delete(vo);
		}
	}

	public void updateMoneyType(MoneyType moneyType)
			throws ValidateFieldsException {
		MoneyTypeImpl moneyTypeImpl = (MoneyTypeImpl) moneyType;

		MoneyTypeVO po = moneyTypeImpl.getMoneyTypeVO();
		this.moneyTypeDao.update(po);
	}

	public MoneyType getMoneyType(Integer id) {
		Collection<MoneyTypeVO> moneyTypes = this.moneyTypeDao
				.findRecordById(id);

		if (moneyTypes == null || moneyTypes.size() < 1)
			return null;

		MoneyTypeVO moneyType = moneyTypes.toArray(new MoneyTypeVO[moneyTypes
				.size()])[0];

		return new MoneyTypeImpl(moneyType);
	}

}
