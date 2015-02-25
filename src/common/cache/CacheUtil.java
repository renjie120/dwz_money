package common.cache;

import common.base.AllSelectContants;
import common.base.ParamSelect;

/**
 * 实用的业务字典工具类，工具id取各种各样的东西。
 * 
 * @author Administrator
 * 
 */
public class CacheUtil {
	/**
	 * 返回保险公司名.
	 * @param id
	 * @return
	 */
	public static String getCompanyName(String id) {
		Cache cache = CacheManager.getCacheInfoNotNull(AllSelectContants.INSUREDCOMPANY_DICT.getName());
		ParamSelect select = (ParamSelect)cache.getValue();
		return select.getName(id);
	}
	
	/**
	 * 返回投保用户姓名
	 * @param id
	 * @return
	 */
	public static String getInsuredUserName(String id) {
		Cache cache = CacheManager.getCacheInfoNotNull(AllSelectContants.INSURED_USER.getName());
		ParamSelect select = (ParamSelect)cache.getValue();
		return select.getName(id);
	}
	
	
	/*
	 * 返回角色id对应的名称
	 */
	public static String getRoleName(String id) {
		Cache cache = CacheManager.getCacheInfoNotNull(AllSelectContants.ROLE.getName());
		ParamSelect select = (ParamSelect)cache.getValue();
		return select.getName(id);
	}
	
	/**
	 * 返回爱都投保保险公司名
	 * @param id
	 * @return
	 */
	public static String getInsuredCompanyName(String id) {
		Cache cache = CacheManager.getCacheInfoNotNull(AllSelectContants.INSURED_COM_DICT.getName());
		ParamSelect select = (ParamSelect)cache.getValue();
		return select.getName(id);
	}
	
	/**
	 * 返回系统用户名.
	 * @param id
	 * @return
	 */
	public static String getSystemUserName(String id) {
		Cache cache = CacheManager.getCacheInfoNotNull(AllSelectContants.IDOUSER_DICT.getName());
		ParamSelect select = (ParamSelect)cache.getValue();
		return select.getName(id);
	}
	
	/**
	 * 返回省份名称
	 * @param id
	 * @return
	 */
	public static String getProvinceName(String id) {
		Cache cache = CacheManager.getCacheInfoNotNull(AllSelectContants.PROVINCE_DICT.getName());
		ParamSelect select = (ParamSelect)cache.getValue();
		return select.getName(id);
	}
	
	/**
	 * 返回投保单位名称
	 * @param id
	 * @return
	 */
	public static String getUnitName(String id) {
		Cache cache = CacheManager.getCacheInfoNotNull(AllSelectContants.INSUREDUNIT_DICT.getName());
		ParamSelect select = (ParamSelect)cache.getValue();
		return select.getName(id);
	}
	
	/**
	 * 返回商业集团名称.
	 * @param id
	 * @return
	 */
	public static String getGroupName(String id) {
		Cache cache = CacheManager.getCacheInfoNotNull(AllSelectContants.BUSINESSGROUP_DICT.getName());
		ParamSelect select = (ParamSelect)cache.getValue();
		return select.getName(id);
	}
	
	/**
	 * 返回商家名称.
	 * @param id
	 * @return
	 */
	public static String getBusinessManName(String id) {
		Cache cache = CacheManager.getCacheInfoNotNull(AllSelectContants.BUSINESSMAN_DICT.getName());
		ParamSelect select = (ParamSelect)cache.getValue();
		return select.getName(id);
	}
	
	/**
	 * 返回城市名称.
	 * @param id
	 * @return
	 */
	public static String getCityName(String id) {
		Cache cache = CacheManager.getCacheInfoNotNull(AllSelectContants.CITY_DICT.getName());
		ParamSelect select = (ParamSelect)cache.getValue();
		return select.getName(id);
	}
}
