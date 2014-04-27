package money.stockmanage;

import common.util.CommonUtil;

public class StockVO {
	private String stockName;
	private double stockValue;
	private double count;
	private double buyCost;
	private double sellCost;

	public StockVO() {
	}

	private double getBuyCostByStockMoney(double stockMoney, double costValue) {
		double temp = CommonUtil.multiply(stockMoney, costValue);
		if (temp < 5.0D)
			return 5.0D;

		return temp;
	}

	public StockVO(double stockValue, double count, double buyCostValue) {
		this.stockValue = stockValue;
		this.count = count;
		double temp = CommonUtil.multiply(stockValue, count);
		this.buyCost = getBuyCostByStockMoney(temp, this.buyCost);
	}

	public String getStockName() {
		return this.stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getStockValue() {
		return this.stockValue;
	}

	public void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}

	public double getCount() {
		return this.count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public double getBuyCost() {
		return this.buyCost;
	}

	public void setBuyCost(double buyCost) {
		double temp = CommonUtil.multiply(this.stockValue, this.count);
		this.buyCost = getBuyCostByStockMoney(temp, buyCost);
	}

	public double getSellCost() {
		return this.sellCost;
	}

	public void setSellCost(double sellCost) {
		this.sellCost = sellCost;
	}
}
