package ido.bindFamily;

import dwz.framework.core.business.BusinessObject;

/**
 * 关于投保用户的业务类接口
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public interface BindFamily extends BusinessObject {
	/**
	 * 获取流水号的属性值.
	 */
	public Integer getSno();

	/**
	 * 获取主用户号的属性值.
	 */
	public String getIuserNo();

	/**
	 * 获取绑定人的属性值.
	 */
	public String getBindName();

	/**
	 * 获取关系的属性值.
	 */
	public String getRelation();

	/**
	 * 获取身份证的属性值.
	 */
	public String getCardNo();

	/**
	 * 获取手机号的属性值.
	 */
	public String getPhone();

	/**
	 * 获取创建用户的属性值.
	 */
	public int getCreateUser();

	/**
	 * 获取创建时间的属性值.
	 */
	public String getCreateTime();
}
