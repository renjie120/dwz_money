package money.detail;

import java.util.Date;

import common.util.CommonUtil;

import dwz.framework.core.business.BusinessObject;

/**
 * 理财管理类. 
 * @author lsq
 * 
 */
public class MoneyImpl implements Money {
	private MoneyVO moneyVO = null;
	private static final long serialVersionUID = 1L; 

	public MoneyImpl(MoneyVO moneyVO) {
		this.moneyVO = moneyVO;
	}
	
	public MoneyImpl(Date moneyTime,double money,String moneyType,String moneyDesc,int shopCard,String bookType) {
		 
		moneyVO = new MoneyVO(money,moneyTime, moneyType,moneyDesc,
				shopCard,bookType, shopCard);
	}
	
	public MoneyImpl(Date moneyTime,double money,String moneyType,String moneyDesc,int shopCard,String bookType,Integer moneySno) {
		 
		moneyVO = new MoneyVO(money,moneyTime, moneyType,moneyDesc,
				shopCard,bookType,moneySno);
	}

	public MoneyVO getMoneyVO(){
		return this.moneyVO;
	}
	
	public int getMoneySno() {
		return this.moneyVO.getMoneySno();
	}

	public Date getMoneyTime() {
		return this.moneyVO.getMoneyTime();
	}

	public double getMoney() {
		return this.moneyVO.getMoney();
	}

	public String getMoneyType() {
		return this.moneyVO.getMoneyType();
	}

	public String getMoneyDesc() {
		return CommonUtil.notBlank(this.moneyVO.getMoneyDesc());
	}

	public int getShopCard() {
		return this.moneyVO.getShopCard();
	}

	public String getBookType() {
		return this.moneyVO.getBookType();
	}

	public Integer getId() {
		return this.moneyVO.getMoneySno();
	}

	public void copyProperties(BusinessObject orig) {
		// TODO Auto-generated method stub

	}

	public String getMoneyTypeName() {
		return this.moneyVO.getMoneyTypeName();
	}

	public String getCode() {
		return this.moneyVO.getCode();
	}

	@Override
	public double getRealMoney() {
		return this.moneyVO.getRealMoney();
	}

	@Override
	public Integer getSplitSno() {
		return this.moneyVO.getSplitSno();
	}
}
