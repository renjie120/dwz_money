
package ido.InsuredCompany;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于保险公司的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredCompanyImpl implements InsuredCompany {
	private InsuredCompanyVO insuredcompanyVO = null;
	private static final long serialVersionUID = 1L;

	public InsuredCompanyImpl(InsuredCompanyVO insuredcompanyVO) {
		this.insuredcompanyVO = insuredcompanyVO;
	}

	public InsuredCompanyImpl( int sno , String comName , String comNo , String comShortName , String comPhone , String comContactName , String comContactPhone , String ownerCompany , String comEmail , String comAddress , String comRemark , int createUser , String createTime , int updateUser , String updateTime ) {
		this.insuredcompanyVO = new InsuredCompanyVO( sno , comName , comNo , comShortName , comPhone , comContactName , comContactPhone , ownerCompany , comEmail , comAddress , comRemark , createUser , createTime , updateUser , updateTime );
	} 
	
	public InsuredCompanyImpl(String comName ,String comNo ,String comShortName ,String comPhone ,String comContactName ,String comContactPhone ,String ownerCompany ,String comEmail ,String comAddress ,String comRemark ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.insuredcompanyVO = new InsuredCompanyVO(comName ,comNo ,comShortName ,comPhone ,comContactName ,comContactPhone ,ownerCompany ,comEmail ,comAddress ,comRemark ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public InsuredCompanyVO getInsuredCompanyVO() {
		return this.insuredcompanyVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.insuredcompanyVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.insuredcompanyVO.getSno();
 	}
 	/**
 	 * 获取保险公司名称的属性值.
 	 */
 	public  String   getComName(){
 		return this.insuredcompanyVO.getComName();
 	}
 	/**
 	 * 获取保险公司编号 的属性值.
 	 */
 	public  String   getComNo(){
 		return this.insuredcompanyVO.getComNo();
 	}
 	/**
 	 * 获取简称的属性值.
 	 */
 	public  String   getComShortName(){
 		return this.insuredcompanyVO.getComShortName();
 	}
 	/**
 	 * 获取电话的属性值.
 	 */
 	public  String   getComPhone(){
 		return this.insuredcompanyVO.getComPhone();
 	}
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public  String   getComContactName(){
 		return this.insuredcompanyVO.getComContactName();
 	}
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public  String   getComContactPhone(){
 		return this.insuredcompanyVO.getComContactPhone();
 	}
 	/**
 	 * 获取所属保险公司的属性值.
 	 */
 	public  String   getOwnerCompany(){
 		return this.insuredcompanyVO.getOwnerCompany();
 	}
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getComEmail(){
 		return this.insuredcompanyVO.getComEmail();
 	}
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getComAddress(){
 		return this.insuredcompanyVO.getComAddress();
 	}
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getComRemark(){
 		return this.insuredcompanyVO.getComRemark();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.insuredcompanyVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.insuredcompanyVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.insuredcompanyVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.insuredcompanyVO.getUpdateTime();
 	}
 
}