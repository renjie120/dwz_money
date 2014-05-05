
package money.orderbase;
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
 * 关于订单基本信息的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class OrderBaseManagerManagerImpl extends AbstractBusinessObjectManager implements
		OrderBaseManagerManager {

	private OrderBaseManagerDao orderbasemanagerdao = null;

	/**
	 * 构造函数.
	 */
	public OrderBaseManagerManagerImpl(OrderBaseManagerDao orderbasemanagerdao) {
		this.orderbasemanagerdao = orderbasemanagerdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchOrderBaseManagerNum(Map<OrderBaseManagerSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.orderbasemanagerdao.countByQuery(hql,
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
	public Collection<OrderBaseManager> searchOrderBaseManager(Map<OrderBaseManagerSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<OrderBaseManager> eaList = new ArrayList<OrderBaseManager>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<OrderBaseManagerVO> voList = this.orderbasemanagerdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_orderstatus = allSelect
				.getParamsByType(AllSelectContants.ORDERSTATUS.getName());
		
		for (OrderBaseManagerVO po : voList) {
			po.setCurrentState(select_orderstatus.getName("" + po.getCurrentState())); 
			eaList.add(new  OrderBaseManagerImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<OrderBaseManagerSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( orderbasemanager) "
						: "select  orderbasemanager ").append("from OrderBaseManagerVO as orderbasemanager ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<OrderBaseManagerSearchFields, Object> entry : criterias
					.entrySet()) {
				OrderBaseManagerSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ORDERNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.orderNo = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CUSTOMERNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.customerNo = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case GONGLV:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.gongLv = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case DIANYA:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.dianYa = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHIDAI:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.shiDai = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case BIANYACHANGJIA:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.bianyaChangjia = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case BIANYAXINGHAO:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.bianyaXinghao = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ISIMPORT:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.isImport = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case STARTDATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.startDate = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ENDDATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.endDate = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CURRENTSTATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  orderbasemanager.currentState = ? ");
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

		OrderBaseManagerOrderByFields orderBy = OrderBaseManagerOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = OrderBaseManagerOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by orderbasemanager.sno");
			break;
			case ORDERNO:
				 sb.append(" order by orderbasemanager.orderNo");
			break;
			case CUSTOMERNO:
				 sb.append(" order by orderbasemanager.customerNo");
			break;
			case GONGLV:
				 sb.append(" order by orderbasemanager.gongLv");
			break;
			case DIANYA:
				 sb.append(" order by orderbasemanager.dianYa");
			break;
			case SHIDAI:
				 sb.append(" order by orderbasemanager.shiDai");
			break;
			case BIANYACHANGJIA:
				 sb.append(" order by orderbasemanager.bianyaChangjia");
			break;
			case BIANYAXINGHAO:
				 sb.append(" order by orderbasemanager.bianyaXinghao");
			break;
			case ISIMPORT:
				 sb.append(" order by orderbasemanager.isImport");
			break;
			case STARTDATE:
				 sb.append(" order by orderbasemanager.startDate");
			break;
			case ENDDATE:
				 sb.append(" order by orderbasemanager.endDate");
			break;
			case CURRENTSTATE:
				 sb.append(" order by orderbasemanager.currentState");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createOrderBaseManager(OrderBaseManager orderbasemanager) throws ValidateFieldsException {
		OrderBaseManagerImpl orderbasemanagerImpl = (OrderBaseManagerImpl) orderbasemanager;
		this.orderbasemanagerdao.insert(orderbasemanagerImpl.getOrderBaseManagerVO());
	}

	public void removeOrderBaseManagers(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			OrderBaseManagerVO vo = this.orderbasemanagerdao.findByPrimaryKey(Integer.parseInt(s));
			this.orderbasemanagerdao.delete(vo);
		}
	}

	public void updateOrderBaseManager(OrderBaseManager orderbasemanager) throws ValidateFieldsException {
		OrderBaseManagerImpl orderbasemanagerImpl = (OrderBaseManagerImpl) orderbasemanager;

		OrderBaseManagerVO po = orderbasemanagerImpl.getOrderBaseManagerVO();
		this.orderbasemanagerdao.update(po);
	}

	public OrderBaseManager getOrderBaseManager(int id) {
		Collection<OrderBaseManagerVO> orderbasemanagers = this.orderbasemanagerdao.findRecordById(id);

		if (orderbasemanagers == null || orderbasemanagers.size() < 1)
			return null;

		OrderBaseManagerVO orderbasemanager = orderbasemanagers.toArray(new OrderBaseManagerVO[orderbasemanagers.size()])[0];

		return new OrderBaseManagerImpl(orderbasemanager);
	}

}
