
package ido.bindFamily;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于投保用户的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BindFamilyImpl implements BindFamily {
	private BindFamilyVO bindfamilyVO = null;
	private static final long serialVersionUID = 1L;

	public BindFamilyImpl(BindFamilyVO bindfamilyVO) {
		this.bindfamilyVO = bindfamilyVO;
	}

	public BindFamilyImpl( int sno , String iuserNo , String bindName , String relation , String cardNo , String phone , int createUser , String createTime ) {
		this.bindfamilyVO = new BindFamilyVO( sno , iuserNo , bindName , relation , cardNo , phone , createUser , createTime );
	} 
	
	public BindFamilyImpl(String iuserNo ,String bindName ,String relation ,String cardNo ,String phone ,int createUser ,String createTime ) {
		this.bindfamilyVO = new BindFamilyVO(iuserNo ,bindName ,relation ,cardNo ,phone ,createUser ,createTime );
	} 

	public BindFamilyVO getBindFamilyVO() {
		return this.bindfamilyVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.bindfamilyVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.bindfamilyVO.getSno();
 	}
 	/**
 	 * 获取主用户号的属性值.
 	 */
 	public  String   getIuserNo(){
 		return this.bindfamilyVO.getIuserNo();
 	}
 	/**
 	 * 获取绑定人的属性值.
 	 */
 	public  String   getBindName(){
 		return this.bindfamilyVO.getBindName();
 	}
 	/**
 	 * 获取关系的属性值.
 	 */
 	public  String   getRelation(){
 		return this.bindfamilyVO.getRelation();
 	}
 	/**
 	 * 获取身份证的属性值.
 	 */
 	public  String   getCardNo(){
 		return this.bindfamilyVO.getCardNo();
 	}
 	/**
 	 * 获取手机号的属性值.
 	 */
 	public  String   getPhone(){
 		return this.bindfamilyVO.getPhone();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.bindfamilyVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.bindfamilyVO.getCreateTime();
 	}
 
}