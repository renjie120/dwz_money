package money.stockmanage;

import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;

import common.util.CommonUtil;

/**
 * 计算一次的交易.
 * 
 * @author Administrator
 * 
 */
public class StockUtil {
	private StockVO stock = new StockVO();
	private String stockName;
	private LinkedList counts;
	private LinkedList stockValues;
	private LinkedList buyCosts;
	private LinkedList sellCosts;
	private double baoben;
	private double zhangting;
	private double chengben;
	private double realChengben;
	private double count;
	private double realMoney;
	private double addMoney;
	private double addValue;
	private double chaValue;
	private double sellCost;
	private double endValue;

	/**
	 * 成本价格
	 * 
	 * @return
	 */
	public double getBaoben() {
		if (this.sellCost > 0D)
			return getDeadLineValue(this.sellCost);

		System.out.println("请输入交易费率！");
		return 0D;
	}

	/**
	 * 涨停价格
	 * 
	 * @param baoben
	 * @return
	 */
	public double getZhangting(double baoben) {
		return CommonUtil.multiply(baoben, 1.10D);
	}

	/**
	 * 成本
	 * 
	 * @return
	 */
	public double getChengben() {
		return getAllMoney();
	}

	/**
	 * 实际成本.
	 * 
	 * @return
	 */
	public double getRealChengben() {
		return getRealAllMoney();
	}

	/**
	 * 总共份额.
	 * 
	 * @return
	 */
	public double getCount() {
		return CommonUtil.getSum(this.counts);
	}

	/**
	 * 最终实际金额.
	 * 
	 * @param endValue
	 * @return
	 */
	public double getRealMoney(double endValue) {
		return CommonUtil.multiply(getCount(), endValue);
	}

	/**
	 * 收益.
	 * 
	 * @param stockValue
	 * @return
	 */
	public double getAddMoney(double stockValue) {
		return CommonUtil.subtract(getRealMoney(stockValue), getAllMoney());
	}

	/**
	 * 收益率.
	 * 
	 * @param stockValue
	 * @return
	 */
	public double getAddValue(double stockValue) {
		return CommonUtil.divide(getAddMoney(stockValue), getAllMoney(), 5,
				RoundingMode.HALF_EVEN);
	}

	public double getChaValue() {
		return this.chaValue;
	}

	public StockUtil(String stockName) {
		this.stock.setStockName(stockName);
		this.stockValues = new LinkedList();
		this.counts = new LinkedList();
		this.buyCosts = new LinkedList();
	}

	private double getBuyCostByStockMoney(double stockMoney, double costValue) {
		double temp = CommonUtil.multiply(stockMoney, costValue);
		if (temp < 5.0D)
			return 5.0D;

		return temp;
	}

	public void addStock(StockVO vo) {
		if ((CommonUtil.notNull(vo.getStockName()))
				&& (CommonUtil.notNull(vo.getStockName()))
				&& (!(vo.getStockName().equals(this.stock.getStockName())))) {
			System.out.println("增仓股票名不与此前股票名一样，不可以进行操作。");
			return;
		}

		this.stockValues.add(Double.valueOf(vo.getStockValue()));
		this.counts.add(Double.valueOf(vo.getCount()));
		this.buyCosts.add(Double.valueOf(vo.getBuyCost())); 
	}

	private double getAllBuyCost() {
		return CommonUtil.getSum(this.buyCosts);
	}

	private double getAllMoney() {
		double ans = 0D;
		Iterator cit = this.counts.iterator();
		Iterator vit = this.stockValues.iterator();
		Iterator bit = this.buyCosts.iterator();
		while (cit.hasNext()) {
			double ct = Double.parseDouble(cit.next().toString());
			double vt = Double.parseDouble(vit.next().toString());
			double bt = Double.parseDouble(bit.next().toString());

			ans = CommonUtil.add(ans,
					CommonUtil.add(CommonUtil.multiply(ct, vt), bt));
		}
		return ans;
	}

	private double getRealAllMoney() {
		return CommonUtil.subtract(getAllMoney(), getAllBuyCost());
	}

	private double getDeadLineValue(double sellCost) {
		return CommonUtil.divide(
				CommonUtil.multiply(getAllMoney(), 1D + sellCost), getCount(),
				5, RoundingMode.HALF_EVEN);
	}

	public double getSellCost() {
		return this.sellCost;
	}

	public void setSellCost(double sellCost) {
		this.sellCost = sellCost;
	}

	public double getEndValue() {
		return this.endValue;
	}

	public void setEndValue(double endValue) {
		this.endValue = endValue;
	}

	public String getStockName() {
		return this.stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
}