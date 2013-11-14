package money.moneytype;

import java.io.Serializable;

import common.base.SelectAble;
import common.base.TreeAble;

/**
 * 理财管理类.
 * 
 * @author lsq
 * 
 */
public class MoneyTypeVO implements Serializable,SelectAble,TreeAble {
	private static final long serialVersionUID = 1L;

	public MoneyTypeVO() {

	}

	public MoneyTypeVO(int moenyTypeSno, String moneyTypeDesc,
			String moneyType, String parentCode, String typeCode, int orderId) {
		this.moenyTypeSno = moenyTypeSno;
		this.moneyTypeDesc = moneyTypeDesc;
		this.moneyType = moneyType;
		this.parentCode = parentCode;
		this.typeCode = typeCode;
		this.orderId = orderId;

	}

	public MoneyTypeVO(String moneyTypeDesc, String moneyType,
			String parentCode, String typeCode, int orderId) {
		this.moneyTypeDesc = moneyTypeDesc;
		this.moneyType = moneyType;
		this.parentCode = parentCode;
		this.typeCode = typeCode;
		this.orderId = orderId;

	}

	private int moenyTypeSno;

	public void setMoenyTypeSno(int moenyTypeSno) {
		this.moenyTypeSno = moenyTypeSno;
	}

	public int getMoenyTypeSno() {
		return moenyTypeSno;
	}

	private String moneyTypeDesc;

	public void setMoneyTypeDesc(String moneyTypeDesc) {
		this.moneyTypeDesc = moneyTypeDesc;
	}

	public String getMoneyTypeDesc() {
		return moneyTypeDesc;
	}

	private String moneyType;

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getMoneyType() {
		return moneyType;
	}

	private String parentCode;

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	private String typeCode;

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	private int orderId;

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public String getOptionId() {
		return this.typeCode;
	}

	public String getOptionName() {
		return this.moneyTypeDesc;
	}

	public String getTreeId() { 
		return this.typeCode;
	}

	public String getTreeLabel() { 
		return this.moneyTypeDesc;
	}

	public String getTreeName() { 
		return this.moneyTypeDesc;
	}

	public String getParentId() { 
		return this.parentCode;
	}

}
