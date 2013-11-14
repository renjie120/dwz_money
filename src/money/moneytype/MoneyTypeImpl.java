package money.moneytype;

import dwz.framework.core.business.BusinessObject;

/**
 * 理财管理类.
 * 
 * @author lsq
 * 
 */
public class MoneyTypeImpl implements MoneyType {
	private MoneyTypeVO moneyTypeVO = null;
	private static final long serialVersionUID = 1L;

	public MoneyTypeImpl(MoneyTypeVO moneyTypeVO) {
		this.moneyTypeVO = moneyTypeVO;
	}

	public MoneyTypeImpl(String moneyTypeDesc, String moneyType,
			String parentCode, String typeCode, int orderId) {
		this.moneyTypeVO = new MoneyTypeVO(moneyTypeDesc, moneyType,
				parentCode, typeCode, orderId);
	}

	public MoneyTypeImpl(int moenyTypeSno, String moneyTypeDesc,
			String moneyType, String parentCode, String typeCode, int orderId) {
		this.moneyTypeVO = new MoneyTypeVO(moenyTypeSno, moneyTypeDesc,
				moneyType, parentCode, typeCode, orderId);
	}

	public MoneyTypeVO getMoneyTypeVO() {
		return this.moneyTypeVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	public Integer getId() {
		return this.moneyTypeVO.getMoenyTypeSno();
	}

	public int getMoenyTypeSno() {
		return this.moneyTypeVO.getMoenyTypeSno();
	}

	public String getMoneyTypeDesc() {
		return this.moneyTypeVO.getMoneyTypeDesc();
	}

	public String getMoneyType() {
		return this.moneyTypeVO.getMoneyType();
	}

	public String getParentCode() {
		return this.moneyTypeVO.getParentCode();
	}

	public String getTypeCode() {
		return this.moneyTypeVO.getTypeCode();
	}

	public int getOrderId() {
		return this.moneyTypeVO.getOrderId();
	}

}
