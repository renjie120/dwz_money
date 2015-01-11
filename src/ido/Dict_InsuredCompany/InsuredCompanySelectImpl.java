
package ido.Dict_InsuredCompany;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于保险公司字典表的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredCompanySelectImpl implements InsuredCompanySelect {
	private InsuredCompanySelectVO insuredcompanyselectVO = null;
	private static final long serialVersionUID = 1L;

	public InsuredCompanySelectImpl(InsuredCompanySelectVO insuredcompanyselectVO) {
		this.insuredcompanyselectVO = insuredcompanyselectVO;
	}

	public InsuredCompanySelectImpl( int sno , String comName , String comStatus , int createUser , String createTime , int updateUser , String updateTime ) {
		this.insuredcompanyselectVO = new InsuredCompanySelectVO( sno , comName , comStatus , createUser , createTime , updateUser , updateTime );
	} 
	
	public InsuredCompanySelectImpl(String comName ,String comStatus ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.insuredcompanyselectVO = new InsuredCompanySelectVO(comName ,comStatus ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public InsuredCompanySelectVO getInsuredCompanySelectVO() {
		return this.insuredcompanyselectVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.insuredcompanyselectVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.insuredcompanyselectVO.getSno();
 	}
 	/**
 	 * 获取保险公司名称的属性值.
 	 */
 	public  String   getComName(){
 		return this.insuredcompanyselectVO.getComName();
 	}
 	/**
 	 * 获取是否显示 的属性值.
 	 */
 	public  String   getComStatus(){
 		return this.insuredcompanyselectVO.getComStatus();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.insuredcompanyselectVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.insuredcompanyselectVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.insuredcompanyselectVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.insuredcompanyselectVO.getUpdateTime();
 	}
 
}