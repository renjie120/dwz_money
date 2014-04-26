package money.detail;

import java.io.Serializable;
import java.util.Date;

/**
 * 理财管理类.
 * 
 * @author lsq
 * 
 */
public class MoneyVO implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private int moneySno;
	private Date moneyTime;
	private double money;
	private String moneyType;
	private String moneyDesc;
	private int shopCard;
	private String moneyTypeName;
	private String bookType;
	private String code;
	private Double realMoney;
	private Integer splitSno;

	public Object clone() {
		MoneyVO o = null;
		try {
			o = (MoneyVO) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	public Integer getSplitSno() {
		return splitSno;
	}

	public void setSplitSno(Integer splitSno) {
		this.splitSno = splitSno;
	}
 

	public Double getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MoneyVO() {

	}

	public MoneyVO(double money, Date moneyTime, String moneyType,
			String moneyDesc, int shopCard, String bookType) {
		this.moneyTime = moneyTime;
		this.money = money;
		this.moneyType = moneyType;
		this.moneyDesc = moneyDesc;
		this.shopCard = shopCard;
		this.bookType = bookType;
	}

	public MoneyVO(double money, Date moneyTime, String moneyType,
			String moneyDesc, int shopCard, String bookType, int moneySno) {
		this.moneyTime = moneyTime;
		this.moneyType = moneyType;
		this.money = money;
		this.moneyDesc = moneyDesc;
		this.shopCard = shopCard;
		this.bookType = bookType;
		this.moneySno = moneySno;
	}

	public int getMoneySno() {
		return moneySno;
	}

	public void setMoneySno(int moneySno) {
		this.moneySno = moneySno;
	}

	public Date getMoneyTime() {
		return moneyTime;
	}

	public void setMoneyTime(Date moneyTime) {
		this.moneyTime = moneyTime;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getMoneyDesc() {
		return moneyDesc;
	}

	public void setMoneyDesc(String moneyDesc) {
		this.moneyDesc = moneyDesc;
	}

	public int getShopCard() {
		return shopCard;
	}

	public void setShopCard(int shopCard) {
		this.shopCard = shopCard;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getMoneyTypeName() {
		return moneyTypeName;
	}

	public void setMoneyTypeName(String moneyTypeName) {
		this.moneyTypeName = moneyTypeName;
	}
}
