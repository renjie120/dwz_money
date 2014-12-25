
package money.yaopin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.AllSelect;
import common.base.AllSelectContants;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于药品销售信息的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class YaopinInfoManagerImpl extends AbstractBusinessObjectManager implements
		YaopinInfoManager {

	private YaopinInfoDao yaopininfodao = null;

	/**
	 * 构造函数.
	 */
	public YaopinInfoManagerImpl(YaopinInfoDao yaopininfodao) {
		this.yaopininfodao = yaopininfodao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchYaopinInfoNum(Map<YaopinInfoSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.yaopininfodao.countByQuery(hql,
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
	public Collection<YaopinInfo> searchYaopinInfo(Map<YaopinInfoSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<YaopinInfo> eaList = new ArrayList<YaopinInfo>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<YaopinInfoVO> voList = this.yaopininfodao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		
		for (YaopinInfoVO po : voList) {
			eaList.add(new  YaopinInfoImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<YaopinInfoSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( yaopininfo) "
						: "select  yaopininfo ").append("from YaopinInfoVO as yaopininfo ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<YaopinInfoSearchFields, Object> entry : criterias
					.entrySet()) {
				YaopinInfoSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMPANYNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.companyName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case ENGNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.engName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case CHNNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.chnName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case CHEMSTRUCT:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.chemStruct = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PRODUCTNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.productName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CUSTOMER:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.customer = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case NUM:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.num = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PRICE:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.price = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CHUNDU:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.chundu = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SALETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.saleTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CAS:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.cas = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CONNECT:
						sb.append(count == 0 ? " where" : " and").append(
								"  yaopininfo.connect = ? ");
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

		YaopinInfoOrderByFields orderBy = YaopinInfoOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = YaopinInfoOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by yaopininfo.sno");
			break;
			case COMPANYNAME:
				 sb.append(" order by yaopininfo.companyName");
			break;
			case ENGNAME:
				 sb.append(" order by yaopininfo.engName");
			break;
			case CHNNAME:
				 sb.append(" order by yaopininfo.chnName");
			break;
			case CHEMSTRUCT:
				 sb.append(" order by yaopininfo.chemStruct");
			break;
			case PRODUCTNAME:
				 sb.append(" order by yaopininfo.productName");
			break;
			case CUSTOMER:
				 sb.append(" order by yaopininfo.customer");
			break;
			case NUM:
				 sb.append(" order by yaopininfo.num");
			break;
			case PRICE:
				 sb.append(" order by yaopininfo.price");
			break;
			case CHUNDU:
				 sb.append(" order by yaopininfo.chundu");
			break;
			case SALETIME:
				 sb.append(" order by yaopininfo.saleTime");
			break;
			case CAS:
				 sb.append(" order by yaopininfo.cas");
			break;
			case CONNECT:
				 sb.append(" order by yaopininfo.connect");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createYaopinInfo(YaopinInfo yaopininfo) throws ValidateFieldsException {
		YaopinInfoImpl yaopininfoImpl = (YaopinInfoImpl) yaopininfo;
		this.yaopininfodao.insert(yaopininfoImpl.getYaopinInfoVO());
	}

	public void removeYaopinInfos(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			YaopinInfoVO vo = this.yaopininfodao.findByPrimaryKey(Integer.parseInt(s));
			this.yaopininfodao.delete(vo);
		}
	}

	public void updateYaopinInfo(YaopinInfo yaopininfo) throws ValidateFieldsException {
		YaopinInfoImpl yaopininfoImpl = (YaopinInfoImpl) yaopininfo;

		YaopinInfoVO po = yaopininfoImpl.getYaopinInfoVO();
		this.yaopininfodao.update(po);
	}

	public YaopinInfo getYaopinInfo(int id) {
		Collection<YaopinInfoVO> yaopininfos = this.yaopininfodao.findRecordById(id);

		if (yaopininfos == null || yaopininfos.size() < 1)
			return null;

		YaopinInfoVO yaopininfo = yaopininfos.toArray(new YaopinInfoVO[yaopininfos.size()])[0];

		return new YaopinInfoImpl(yaopininfo);
	}

}
