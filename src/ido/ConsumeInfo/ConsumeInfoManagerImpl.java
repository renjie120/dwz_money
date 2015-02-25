
package ido.ConsumeInfo;
import ido.BusinessMan.BusinessManVO;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.cache.CacheUtil;
import common.util.NPOIReader;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于消费记录的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ConsumeInfoManagerImpl extends AbstractBusinessObjectManager implements
		ConsumeInfoManager {

	private ConsumeInfoDao consumeinfodao = null;

	/**
	 * 构造函数.
	 */
	public ConsumeInfoManagerImpl(ConsumeInfoDao consumeinfodao) {
		this.consumeinfodao = consumeinfodao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchConsumeInfoNum(Map<ConsumeInfoSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.consumeinfodao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}


	public void importFromExcel(File file) {
		NPOIReader excel = null;
		try {
			excel = new NPOIReader(file);
			int index = excel.getSheetNames().indexOf("Sheet0");
			String[][] contents = excel.read(index, true, true);
			for (int i = 1; i < contents.length; i++) {
				ConsumeInfoVO vo = new ConsumeInfoVO();
				this.consumeinfodao.insert(vo); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<ConsumeInfo> searchConsumeInfo(Map<ConsumeInfoSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<ConsumeInfo> eaList = new ArrayList<ConsumeInfo>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<ConsumeInfoVO> voList = this.consumeinfodao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_paytype = allSelect
				.getParamsByType(AllSelectContants.PAYTYPE.getName());
		
		for (ConsumeInfoVO po : voList) {
			po.setConsumeStatus(select_paytype.getName("" + po.getConsumeStatus())); 
			po.setCreateUserName(CacheUtil.getSystemUserName(""+po.getCreateUser())); 
			po.setUpdateUserName(CacheUtil.getSystemUserName(""+po.getUpdateUser())); 
			eaList.add(new  ConsumeInfoImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<ConsumeInfoSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( consumeinfo) "
						: "select  consumeinfo ").append("from ConsumeInfoVO as consumeinfo ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<ConsumeInfoSearchFields, Object> entry : criterias
					.entrySet()) {
				ConsumeInfoSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case IUSERID:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.iuserId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMID:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.comId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPMID:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.shopmId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPID:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.shopId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OWNERCOM:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.ownerCom = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CONSUMESTATUS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.consumeStatus = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CONSUMEMONEY:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.consumeMoney = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CARDMONEY:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.cardMoney = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CASHMONEY:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.cashMoney = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CONSUMETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.consumeTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.updateTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case IUSERID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.iuserId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case IUSERID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.iuserId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case COMID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.comId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case COMID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.comId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPMID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.shopmId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPMID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.shopmId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.shopId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.shopId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OWNERCOM_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.ownerCom  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OWNERCOM_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.ownerCom =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CONSUMESTATUS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.consumeStatus  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CONSUMESTATUS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.consumeStatus =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CONSUMEMONEY_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.consumeMoney  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CONSUMEMONEY_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.consumeMoney =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CARDMONEY_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.cardMoney  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CARDMONEY_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.cardMoney =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CASHMONEY_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.cashMoney  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CASHMONEY_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.cashMoney =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CONSUMETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.consumeTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CONSUMETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.consumeTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.createTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.updateUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.updateUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.updateTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  consumeinfo.updateTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		ConsumeInfoOrderByFields orderBy = ConsumeInfoOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = ConsumeInfoOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by consumeinfo.sno");
			break;
			case IUSERID:
				 sb.append(" order by consumeinfo.iuserId");
			break;
			case COMID:
				 sb.append(" order by consumeinfo.comId");
			break;
			case SHOPMID:
				 sb.append(" order by consumeinfo.shopmId");
			break;
			case SHOPID:
				 sb.append(" order by consumeinfo.shopId");
			break;
			case OWNERCOM:
				 sb.append(" order by consumeinfo.ownerCom");
			break;
			case CONSUMESTATUS:
				 sb.append(" order by consumeinfo.consumeStatus");
			break;
			case CONSUMEMONEY:
				 sb.append(" order by consumeinfo.consumeMoney");
			break;
			case CARDMONEY:
				 sb.append(" order by consumeinfo.cardMoney");
			break;
			case CASHMONEY:
				 sb.append(" order by consumeinfo.cashMoney");
			break;
			case CONSUMETIME:
				 sb.append(" order by consumeinfo.consumeTime");
			break;
			case CREATEUSER:
				 sb.append(" order by consumeinfo.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by consumeinfo.createTime");
			break;
			case UPDATEUSER:
				 sb.append(" order by consumeinfo.updateUser");
			break;
			case UPDATETIME:
				 sb.append(" order by consumeinfo.updateTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createConsumeInfo(ConsumeInfo consumeinfo) throws ValidateFieldsException {
		ConsumeInfoImpl consumeinfoImpl = (ConsumeInfoImpl) consumeinfo;
		this.consumeinfodao.insert(consumeinfoImpl.getConsumeInfoVO());
	}

	public void removeConsumeInfos(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			ConsumeInfoVO vo = this.consumeinfodao.findByPrimaryKey(Integer.parseInt(s));
			this.consumeinfodao.delete(vo);
		}
	}

	public void updateConsumeInfo(ConsumeInfo consumeinfo) throws ValidateFieldsException {
		ConsumeInfoImpl consumeinfoImpl = (ConsumeInfoImpl) consumeinfo;

		ConsumeInfoVO po = consumeinfoImpl.getConsumeInfoVO();
		this.consumeinfodao.update(po);
	}

	public ConsumeInfo getConsumeInfo(int id) {
		Collection<ConsumeInfoVO> consumeinfos = this.consumeinfodao.findRecordById(id);

		if (consumeinfos == null || consumeinfos.size() < 1)
			return null;

		ConsumeInfoVO consumeinfo = consumeinfos.toArray(new ConsumeInfoVO[consumeinfos.size()])[0];
		consumeinfo.setCreateUserName(CacheUtil.getSystemUserName(""+consumeinfo.getCreateUser()));
		consumeinfo.setUpdateUserName(CacheUtil.getSystemUserName(""+consumeinfo.getUpdateUser()));
		
		return new ConsumeInfoImpl(consumeinfo);
	}

}
