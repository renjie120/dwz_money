
package ido.addmoneydetail;
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
 * 关于充值记录明细的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class AddMoneyDetailManagerImpl extends AbstractBusinessObjectManager implements
		AddMoneyDetailManager {

	private AddMoneyDetailDao addmoneydetaildao = null;

	/**
	 * 构造函数.
	 */
	public AddMoneyDetailManagerImpl(AddMoneyDetailDao addmoneydetaildao) {
		this.addmoneydetaildao = addmoneydetaildao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchAddMoneyDetailNum(Map<AddMoneyDetailSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.addmoneydetaildao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}


	public void importFromExcel(File file) {
		NPOIReader excel = null;
		try {
			AllSelect allSelect = (AllSelect) SpringContextUtil
			.getBean(BeanManagerKey.allSelectManager.toString());
			ParamSelect select_shopstatus = allSelect
					.getParamsByType(AllSelectContants.ADDMONEYTYPE.getName());
			excel = new NPOIReader(file);
			int index = excel.getSheetNames().indexOf("Sheet0");
			String[][] contents = excel.read(index, true, true);
			for (int i = 1; i < contents.length; i++) {
				AddMoneyDetailVO vo = new AddMoneyDetailVO();
				//导入投保用户号
				String iuserIdStr = contents[i][0];
				vo.setIuserId(iuserIdStr);
				
				//导入充值字段
				String addTypeStr = contents[i][1];
				vo.setAddType(select_shopstatus.getValue(addTypeStr));
				
				//导入充值金额 
				String addMoneyStr = contents[i][2];
				vo.setAddMoney(addMoneyStr);
				
				//导入投保单号 
				String insuredFileIdStr = contents[i][3];
				vo.setInsuredFileId(insuredFileIdStr);
				
				this.addmoneydetaildao.insert(vo); 
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
	public Collection<AddMoneyDetail> searchAddMoneyDetail(Map<AddMoneyDetailSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<AddMoneyDetail> eaList = new ArrayList<AddMoneyDetail>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<AddMoneyDetailVO> voList = this.addmoneydetaildao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_addmoneytype = allSelect
				.getParamsByType(AllSelectContants.ADDMONEYTYPE.getName());
		
		for (AddMoneyDetailVO po : voList) {
			po.setAddType(select_addmoneytype.getName("" + po.getAddType()));
			po.setCreateUserName(CacheUtil.getSystemUserName(""+po.getCreateUser()));
			eaList.add(new  AddMoneyDetailImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<AddMoneyDetailSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( addmoneydetail) "
						: "select  addmoneydetail ").append("from AddMoneyDetailVO as addmoneydetail ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<AddMoneyDetailSearchFields, Object> entry : criterias
					.entrySet()) {
				AddMoneyDetailSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case IUSERID:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.iuserId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ADDTYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.addType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ADDMONEY:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.addMoney = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSUREDFILEID:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.insuredFileId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ADDTIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.addTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.updateTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case IUSERID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.iuserId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case IUSERID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.iuserId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case ADDTYPE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.addType  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ADDTYPE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.addType =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case ADDMONEY_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.addMoney  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ADDMONEY_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.addMoney =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case INSUREDFILEID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.insuredFileId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case INSUREDFILEID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.insuredFileId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case ADDTIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.addTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ADDTIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.addTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.createTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.updateUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.updateUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.updateTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  addmoneydetail.updateTime =  ? "); 
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

		AddMoneyDetailOrderByFields orderBy = AddMoneyDetailOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = AddMoneyDetailOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by addmoneydetail.sno");
			break;
			case IUSERID:
				 sb.append(" order by addmoneydetail.iuserId");
			break;
			case ADDTYPE:
				 sb.append(" order by addmoneydetail.addType");
			break;
			case ADDMONEY:
				 sb.append(" order by addmoneydetail.addMoney");
			break;
			case INSUREDFILEID:
				 sb.append(" order by addmoneydetail.insuredFileId");
			break;
			case ADDTIME:
				 sb.append(" order by addmoneydetail.addTime");
			break;
			case CREATEUSER:
				 sb.append(" order by addmoneydetail.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by addmoneydetail.createTime");
			break;
			case UPDATEUSER:
				 sb.append(" order by addmoneydetail.updateUser");
			break;
			case UPDATETIME:
				 sb.append(" order by addmoneydetail.updateTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createAddMoneyDetail(AddMoneyDetail addmoneydetail) throws ValidateFieldsException {
		AddMoneyDetailImpl addmoneydetailImpl = (AddMoneyDetailImpl) addmoneydetail;
		this.addmoneydetaildao.insert(addmoneydetailImpl.getAddMoneyDetailVO());
	}

	public void removeAddMoneyDetails(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			AddMoneyDetailVO vo = this.addmoneydetaildao.findByPrimaryKey(Integer.parseInt(s));
			this.addmoneydetaildao.delete(vo);
		}
	}

	public void updateAddMoneyDetail(AddMoneyDetail addmoneydetail) throws ValidateFieldsException {
		AddMoneyDetailImpl addmoneydetailImpl = (AddMoneyDetailImpl) addmoneydetail;

		AddMoneyDetailVO po = addmoneydetailImpl.getAddMoneyDetailVO();
		this.addmoneydetaildao.update(po);
	}

	public AddMoneyDetail getAddMoneyDetail(int id) {
		Collection<AddMoneyDetailVO> addmoneydetails = this.addmoneydetaildao.findRecordById(id);

		if (addmoneydetails == null || addmoneydetails.size() < 1)
			return null;

		AddMoneyDetailVO addmoneydetail = addmoneydetails.toArray(new AddMoneyDetailVO[addmoneydetails.size()])[0];

		return new AddMoneyDetailImpl(addmoneydetail);
	}

}
