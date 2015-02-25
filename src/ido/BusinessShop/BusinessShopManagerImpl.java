
package ido.BusinessShop;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.cache.Cache;
import common.cache.CacheManager;
import common.cache.CacheUtil;
import common.util.NPOIReader;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于商铺的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessShopManagerImpl extends AbstractBusinessObjectManager implements
		BusinessShopManager {

	private BusinessShopDao businessshopdao = null;

	/**
	 * 构造函数.
	 */
	public BusinessShopManagerImpl(BusinessShopDao businessshopdao) {
		this.businessshopdao = businessshopdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchBusinessShopNum(Map<BusinessShopSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.businessshopdao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}


	public void importFromExcel(File file) {
		NPOIReader excel = null;
		try {
			AllSelect allSelect = (AllSelect) SpringContextUtil
			.getBean(BeanManagerKey.allSelectManager.toString());
			ParamSelect select_shopstatus = allSelect
					.getParamsByType(AllSelectContants.SHOPSTATUS.getName());
			excel = new NPOIReader(file);
			int index = excel.getSheetNames().indexOf("Sheet0");
			String[][] contents = excel.read(index, true, true);
			for (int i = 1; i < contents.length; i++) {
				BusinessShopVO vo = new BusinessShopVO();
				//导入商家编号
				String shopmIdStr = contents[i][0];
				vo.setShopmId(shopmIdStr);
				
				//导入商铺名称 
				String shopNameStr = contents[i][1];
				vo.setShopName(shopNameStr);
				
				//导入商铺编号 
				String shopSnoStr = contents[i][2];
				vo.setShopSno(shopSnoStr);
				
				//导入联系人名称
				String shopContactNameStr = contents[i][3];
				vo.setShopContactName(shopContactNameStr);
				
				//导入联系人手机
				String shopConPhoneStr = contents[i][4];
				vo.setShopConPhone(shopConPhoneStr);
				
				//导入邮箱
				String shopEmailStr = contents[i][5];
				vo.setShopEmail(shopEmailStr);
				
				//导入地址
				String shopAddressStr = contents[i][6];
				vo.setShopAddress(shopAddressStr);
				
				
				//商铺状态
				String shopStatusStr = contents[i][7];
				vo.setShopStatus(select_shopstatus.getValue(shopStatusStr));
				
				this.businessshopdao.insert(vo); 
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
	public Collection<BusinessShop> searchBusinessShop(Map<BusinessShopSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<BusinessShop> eaList = new ArrayList<BusinessShop>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<BusinessShopVO> voList = this.businessshopdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_shopstatus = allSelect
				.getParamsByType(AllSelectContants.SHOPSTATUS.getName());
		Cache cache_shopProvince = CacheManager.getCacheInfoNotNull(AllSelectContants.PROVINCE_DICT.getName());
		ParamSelect select_shopProvince = (ParamSelect)cache_shopProvince.getValue();
		Cache cache_shopCity = CacheManager.getCacheInfoNotNull(AllSelectContants.CITY_DICT.getName());
		ParamSelect select_shopCity = (ParamSelect)cache_shopCity.getValue();
	 
		ParamSelect select_yesorno = allSelect
				.getParamsByType(AllSelectContants.YESORNO.getName());
		
		for (BusinessShopVO po : voList) {
			po.setShopStatus(select_shopstatus.getName("" + po.getShopStatus())); 
			po.setShopProvince(select_shopProvince.getName("" + po.getShopProvince())); 
			po.setShopCity(select_shopCity.getName("" + po.getShopCity())); 
			po.setShopxian(select_yesorno.getName("" + po.getShopxian())); 
			po.setCreateUserName(CacheUtil.getSystemUserName(""+po.getCreateUser()));
			po.setUpdateUserName(CacheUtil.getSystemUserName(""+po.getUpdateUser()));
			eaList.add(new  BusinessShopImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<BusinessShopSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( businessshop) "
						: "select  businessshop ").append("from BusinessShopVO as businessshop ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<BusinessShopSearchFields, Object> entry : criterias
					.entrySet()) {
				BusinessShopSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPMID:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopmId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case SHOPSNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopSno like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case SHOPSTATUS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopStatus = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPCONTACTNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopContactName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPCONPHONE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopConPhone = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPEMAIL:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopEmail = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPADDRESS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopAddress = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPDATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopDate = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPJINGDU:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopJingdu = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPWEIDU:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopWeidu = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPPROVINCE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopProvince = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPCITY:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopCity = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPXIAN:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopxian = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPREMARK:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopRemark = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPMAIN:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopMain = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPINTRODUCE:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopIntroduce = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SHOPSPECIAL:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopSpecial = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.updateTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case SHOPMID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopmId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPMID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopmId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPNAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPNAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPSNO_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopSno  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPSNO_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopSno =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPSTATUS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopStatus  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPSTATUS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopStatus =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPCONTACTNAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopContactName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPCONTACTNAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopContactName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPCONPHONE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopConPhone  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPCONPHONE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopConPhone =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPEMAIL_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopEmail  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPEMAIL_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopEmail =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPADDRESS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopAddress  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPADDRESS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopAddress =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPDATE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopDate  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPDATE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopDate =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPJINGDU_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopJingdu  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPJINGDU_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopJingdu =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPWEIDU_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopWeidu  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPWEIDU_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopWeidu =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPPROVINCE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopProvince  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPPROVINCE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopProvince =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPCITY_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopCity  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPCITY_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopCity =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPXIAN_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopxian  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPXIAN_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopxian =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPREMARK_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopRemark  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPREMARK_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopRemark =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPMAIN_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopMain  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPMAIN_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopMain =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPINTRODUCE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopIntroduce  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPINTRODUCE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopIntroduce =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SHOPSPECIAL_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopSpecial  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SHOPSPECIAL_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.shopSpecial =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.createTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.updateUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.updateUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.updateTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  businessshop.updateTime =  ? "); 
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

		BusinessShopOrderByFields orderBy = BusinessShopOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = BusinessShopOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case SNO:
			 sb.append(" order by businessshop.sno");
		break;
		case SNO_DESC:
			 sb.append(" order by businessshop.sno desc");
		break;
		case SHOPMID:
			 sb.append(" order by businessshop.shopmId");
		break;
		case SHOPMID_DESC:
			 sb.append(" order by businessshop.shopmId desc");
		break;
		case SHOPNAME:
			 sb.append(" order by businessshop.shopName");
		break;
		case SHOPNAME_DESC:
			 sb.append(" order by businessshop.shopName desc");
		break;
		case SHOPSNO:
			 sb.append(" order by businessshop.shopSno");
		break;
		case SHOPSNO_DESC:
			 sb.append(" order by businessshop.shopSno desc");
		break;
		case SHOPSTATUS:
			 sb.append(" order by businessshop.shopStatus");
		break;
		case SHOPSTATUS_DESC:
			 sb.append(" order by businessshop.shopStatus desc");
		break;
		case SHOPCONTACTNAME:
			 sb.append(" order by businessshop.shopContactName");
		break;
		case SHOPCONTACTNAME_DESC:
			 sb.append(" order by businessshop.shopContactName desc");
		break;
		case SHOPCONPHONE:
			 sb.append(" order by businessshop.shopConPhone");
		break;
		case SHOPCONPHONE_DESC:
			 sb.append(" order by businessshop.shopConPhone desc");
		break;
		case SHOPEMAIL:
			 sb.append(" order by businessshop.shopEmail");
		break;
		case SHOPEMAIL_DESC:
			 sb.append(" order by businessshop.shopEmail desc");
		break;
		case SHOPADDRESS:
			 sb.append(" order by businessshop.shopAddress");
		break;
		case SHOPADDRESS_DESC:
			 sb.append(" order by businessshop.shopAddress desc");
		break;
		case SHOPDATE:
			 sb.append(" order by businessshop.shopDate");
		break;
		case SHOPDATE_DESC:
			 sb.append(" order by businessshop.shopDate desc");
		break;
		case SHOPJINGDU:
			 sb.append(" order by businessshop.shopJingdu");
		break;
		case SHOPJINGDU_DESC:
			 sb.append(" order by businessshop.shopJingdu desc");
		break;
		case SHOPWEIDU:
			 sb.append(" order by businessshop.shopWeidu");
		break;
		case SHOPWEIDU_DESC:
			 sb.append(" order by businessshop.shopWeidu desc");
		break;
		case SHOPPROVINCE:
			 sb.append(" order by businessshop.shopProvince");
		break;
		case SHOPPROVINCE_DESC:
			 sb.append(" order by businessshop.shopProvince desc");
		break;
		case SHOPCITY:
			 sb.append(" order by businessshop.shopCity");
		break;
		case SHOPCITY_DESC:
			 sb.append(" order by businessshop.shopCity desc");
		break;
		case SHOPXIAN:
			 sb.append(" order by businessshop.shopxian");
		break;
		case SHOPXIAN_DESC:
			 sb.append(" order by businessshop.shopxian desc");
		break;
		case SHOPREMARK:
			 sb.append(" order by businessshop.shopRemark");
		break;
		case SHOPREMARK_DESC:
			 sb.append(" order by businessshop.shopRemark desc");
		break;
		case SHOPMAIN:
			 sb.append(" order by businessshop.shopMain");
		break;
		case SHOPMAIN_DESC:
			 sb.append(" order by businessshop.shopMain desc");
		break;
		case SHOPINTRODUCE:
			 sb.append(" order by businessshop.shopIntroduce");
		break;
		case SHOPINTRODUCE_DESC:
			 sb.append(" order by businessshop.shopIntroduce desc");
		break;
		case SHOPSPECIAL:
			 sb.append(" order by businessshop.shopSpecial");
		break;
		case SHOPSPECIAL_DESC:
			 sb.append(" order by businessshop.shopSpecial desc");
		break;
		case CREATEUSER:
			 sb.append(" order by businessshop.createUser");
		break;
		case CREATEUSER_DESC:
			 sb.append(" order by businessshop.createUser desc");
		break;
		case CREATETIME:
			 sb.append(" order by businessshop.createTime");
		break;
		case CREATETIME_DESC:
			 sb.append(" order by businessshop.createTime desc");
		break;
		case UPDATEUSER:
			 sb.append(" order by businessshop.updateUser");
		break;
		case UPDATEUSER_DESC:
			 sb.append(" order by businessshop.updateUser desc");
		break;
		case UPDATETIME:
			 sb.append(" order by businessshop.updateTime");
		break;
		case UPDATETIME_DESC:
			 sb.append(" order by businessshop.updateTime desc");
		break;
		default:
			break;
	}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createBusinessShop(BusinessShop businessshop) throws ValidateFieldsException {
		BusinessShopImpl businessshopImpl = (BusinessShopImpl) businessshop;
		this.businessshopdao.insert(businessshopImpl.getBusinessShopVO());
	}

	public void removeBusinessShops(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			BusinessShopVO vo = this.businessshopdao.findByPrimaryKey(Integer.parseInt(s));
			this.businessshopdao.delete(vo);
		}
	}

	public void updateBusinessShop(BusinessShop businessshop) throws ValidateFieldsException {
		BusinessShopImpl businessshopImpl = (BusinessShopImpl) businessshop;

		BusinessShopVO po = businessshopImpl.getBusinessShopVO();
		this.businessshopdao.update(po);
	}

	public BusinessShop getBusinessShop(int id) {
		Collection<BusinessShopVO> businessshops = this.businessshopdao.findRecordById(id);

		if (businessshops == null || businessshops.size() < 1)
			return null;

		BusinessShopVO businessshop = businessshops.toArray(new BusinessShopVO[businessshops.size()])[0];
		businessshop.setCreateUserName(CacheUtil.getSystemUserName(""+businessshop.getCreateUser()));
		businessshop.setUpdateUserName(CacheUtil.getSystemUserName(""+businessshop.getUpdateUser()));
		return new BusinessShopImpl(businessshop);
	}

}
