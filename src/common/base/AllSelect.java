package common.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import money.moneytype.MoneyTypeDao;
import money.moneytype.MoneyTypeVO;
import money.params.ParamDao;
import money.params.ParamVO;
import money.paramtype.ParamTypeDao;
import money.paramtype.ParamTypeVO;

import common.cache.Cache;
import common.cache.CacheManager;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.business.BusinessObjectManager;

/**
 * 全部的下拉菜单.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class AllSelect extends AbstractBusinessObjectManager implements BusinessObjectManager{
	public static final String BEANNAME = "allSelectManager";
	ParamTypeDao paramTypeDao = null;
	ParamDao paramDao = null;
	MoneyTypeDao moneyTypeDao = null;
	public AllSelect(ParamTypeDao paramTypeDao,ParamDao paramDao,MoneyTypeDao moneyTypeDao) {
		this.paramTypeDao = paramTypeDao; 
		this.paramDao = paramDao; 
		this.moneyTypeDao = moneyTypeDao;  
	}

	/**
	 * 得到全部的参数类型id和参数代码的映射.
	 * @return
	 */
	public ParamSelect getAllParamTypeCode() {
		if (CacheManager.getCacheInfo("allparamtypecode") == null) {
			ParamSelect ans = null;
			Collection<ParamTypeVO> all = this.paramTypeDao.findAllTypeCode();
			Iterator<ParamTypeVO> it  =all.iterator();
			Collection<ParamTypeVO> newALl = new ArrayList();
			while(it.hasNext()){
				ParamTypeVO co = it.next();
				co.setParameterTypeName(co.getCode());
				newALl.add(co); 
			}
			ans = new ParamSelect(all);

			Cache c = new Cache();
			c.setKey("allparamtypecode");
			c.setValue(ans);
			c.setName("全部参数代码");
			CacheManager.putCache("allparamtypecode", c);

			return ans;
		} else
			return (ParamSelect) CacheManager.getCacheInfo("allparamtypecode")
					.getValue();
	}
	
	/**
	 * 缓存全部的参数！
	 */
	public void cacheAllParams(){
		ParamSelect select =getAllParamTypeCode();
		List all = select.names;
		//初始化参数类型的时候，同时将参数类型下面的全部属性一起加载到缓存中！！
		if(all!=null){
			Iterator<String> it = all.iterator();
			while(it.hasNext()){ 
				String cacheId = it.next();
				log.info("缓存..."+cacheId);
				getParamsByType(cacheId);
			}
		}
	}
	/**
	 * 得到全部的参数类型id和参数类型描述的映射.
	 * @return
	 */
	public ParamSelect getAllParamType() {
		if (CacheManager.getCacheInfo("allparamtype") == null) {
			ParamSelect ans = null;
			Collection<ParamTypeVO> all = this.paramTypeDao.findAll();
			ans = new ParamSelect(all); 
			
			Cache c = new Cache();
			c.setKey("allparamtype");
			c.setValue(ans);
			c.setName("属性类型");
			CacheManager.putCache("allparamtype", c);

			return ans;
		} else
			return (ParamSelect) CacheManager.getCacheInfo("allparamtype")
					.getValue();
	} 
	
	/**
	 * 得到所有的金额类型的映射.
	 * @return
	 */
	public ParamSelect getAllMoneyType() {
		if (CacheManager.getCacheInfo("moneyType") == null) {
			ParamSelect ans = null;
			Collection<MoneyTypeVO> all = this.moneyTypeDao.findAll();
			ans = new ParamSelect(all);

			Cache c = new Cache();
			c.setKey("moneyType");
			c.setValue(ans);
			c.setName("金额类型");
			CacheManager.putCache("moneyType", c);

			return ans;
		} else
			return (ParamSelect) CacheManager.getCacheInfo("moneyType")
					.getValue();
	}

	/**
	 * 查询指定的参数类型的全部参数值.
	 * @param paraType 参数类型流水号
	 * @param paraTypeName 参数类型描述--和paramType里面的code相等.
	 * @return
	 */
	public ParamSelect getParamsByType(int paraType,String paraTypeName) {
		String cacheId = "paramtype" + paraType; 
		if (CacheManager.getCacheInfo(cacheId) == null) {
			ParamSelect ans = null;
			Collection<ParamVO> all = this.paramDao.findParmByType(paraType);
			ans = new ParamSelect(all);

			Cache c = new Cache();
			c.setKey(cacheId);
			c.setValue(ans);
			c.setName("参数:"+paraTypeName);
			CacheManager.putCache(cacheId, c);
			return ans;
		} else
			return (ParamSelect) CacheManager.getCacheInfo(cacheId).getValue();
	}
	
	/**
	 * 得到指定参数类型下面的全部参数.
	 * @param paraType 参数类型流水号.
	 * @return
	 */
	public ParamSelect getParamsByType(int paraType) {
		String cacheId = "paramtype" + paraType; 
		if (CacheManager.getCacheInfo(cacheId) == null) {
			ParamSelect ans = null;
			Collection<ParamVO> all = this.paramDao.findParmByType(paraType);
			ans = new ParamSelect(all);

			Cache c = new Cache();
			c.setKey(cacheId);
			c.setValue(ans); 
			c.setName("参数:"+getAllParamTypeCode().getName(""+paraType));
			CacheManager.putCache(cacheId, c);
			return ans;
		} else
			return (ParamSelect) CacheManager.getCacheInfo(cacheId).getValue();
	}
	
	/**
	 * 根据参数类型描述得到该参数类型下面的全部参数组成的key，value.
	 * @param paraType
	 * @return
	 */
	public ParamSelect getParamsByType(String paraType) { 
		ParamSelect paramTypeCodes = getAllParamTypeCode();
		int val = Integer.parseInt(paramTypeCodes.getValue(paraType)); 
		return getParamsByType(val,paraType);
	} 
}
