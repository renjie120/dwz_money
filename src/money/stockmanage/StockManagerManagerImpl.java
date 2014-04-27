
package money.stockmanage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于股票交易的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class StockManagerManagerImpl extends AbstractBusinessObjectManager implements
		StockManagerManager {

	private StockManagerDao stockmanagerdao = null;

	/**
	 * 构造函数.
	 */
	public StockManagerManagerImpl(StockManagerDao stockmanagerdao) {
		this.stockmanagerdao = stockmanagerdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchStockManagerNum(Map<StockManagerSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.stockmanagerdao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<StockManager> searchStockManager(Map<StockManagerSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<StockManager> eaList = new ArrayList<StockManager>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<StockManagerVO> voList = this.stockmanagerdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_dealType = allSelect
				.getParamsByType(AllSelectContants.DEAL_TYPE.getName());
		
		for (StockManagerVO po : voList) {
			po.setDealType(select_dealType.getName("" + po.getDealType())); 
			eaList.add(new  StockManagerImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<StockManagerSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( stockmanager) "
						: "select  stockmanager ").append("from StockManagerVO as stockmanager ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<StockManagerSearchFields, Object> entry : criterias
					.entrySet()) {
				StockManagerSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  stockmanager.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case STOCKNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  stockmanager.stockNo = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case STOCKNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  stockmanager.stockName like  ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case DEALDATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  stockmanager.dealDate = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PRICE:
						sb.append(count == 0 ? " where" : " and").append(
								"  stockmanager.price = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case DEALNUMBER:
						sb.append(count == 0 ? " where" : " and").append(
								"  stockmanager.dealNumber = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case FEE:
						sb.append(count == 0 ? " where" : " and").append(
								"  stockmanager.fee = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case DEALTYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  stockmanager.dealType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case DEALGROUP:
						sb.append(count == 0 ? " where" : " and").append(
								"  stockmanager.dealGroup = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		StockManagerOrderByFields orderBy = StockManagerOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = StockManagerOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by stockmanager.sno");
			break;
			case STOCKNO:
				 sb.append(" order by stockmanager.stockNo");
			break;
			case STOCKNAME:
				 sb.append(" order by stockmanager.stockName");
			break;
			case DEALDATE:
				 sb.append(" order by stockmanager.dealDate");
			break;
			case PRICE:
				 sb.append(" order by stockmanager.price");
			break;
			case DEALNUMBER:
				 sb.append(" order by stockmanager.dealNumber");
			break;
			case FEE:
				 sb.append(" order by stockmanager.fee");
			break;
			case DEALTYPE:
				 sb.append(" order by stockmanager.dealType");
			break;
			case DEALGROUP:
				 sb.append(" order by stockmanager.dealGroup");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createStockManager(StockManager stockmanager) throws ValidateFieldsException {
		StockManagerImpl stockmanagerImpl = (StockManagerImpl) stockmanager;
		this.stockmanagerdao.insert(stockmanagerImpl.getStockManagerVO());
	}

	public void removeStockManagers(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			StockManagerVO vo = this.stockmanagerdao.findByPrimaryKey(Integer.parseInt(s));
			this.stockmanagerdao.delete(vo);
		}
	}

	public void updateStockManager(StockManager stockmanager) throws ValidateFieldsException {
		StockManagerImpl stockmanagerImpl = (StockManagerImpl) stockmanager;

		StockManagerVO po = stockmanagerImpl.getStockManagerVO();
		this.stockmanagerdao.update(po);
	}

	public StockManager getStockManager(int id) {
		Collection<StockManagerVO> stockmanagers = this.stockmanagerdao.findRecordById(id);

		if (stockmanagers == null || stockmanagers.size() < 1)
			return null;

		StockManagerVO stockmanager = stockmanagers.toArray(new StockManagerVO[stockmanagers.size()])[0];

		return new StockManagerImpl(stockmanager);
	}

}
