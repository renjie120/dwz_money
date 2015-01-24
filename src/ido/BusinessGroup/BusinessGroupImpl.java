
package ido.BusinessGroup;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于商家集团的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessGroupImpl implements BusinessGroup {
	private BusinessGroupVO businessgroupVO = null;
	private static final long serialVersionUID = 1L;

	public BusinessGroupImpl(BusinessGroupVO businessgroupVO) {
		this.businessgroupVO = businessgroupVO;
	}

	public BusinessGroupImpl( int sno , String groupSno , String groupName , String groupEmail , String groupContact , String groupContactPhone , String groupContactMobile , String groupStatus , int createUser , String createTime , int updateUser , String updateTime ) {
		this.businessgroupVO = new BusinessGroupVO( sno , groupSno , groupName , groupEmail , groupContact , groupContactPhone , groupContactMobile , groupStatus , createUser , createTime , updateUser , updateTime );
	} 
	
	public BusinessGroupImpl(String groupSno ,String groupName ,String groupEmail ,String groupContact ,String groupContactPhone ,String groupContactMobile ,String groupStatus ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.businessgroupVO = new BusinessGroupVO(groupSno ,groupName ,groupEmail ,groupContact ,groupContactPhone ,groupContactMobile ,groupStatus ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public BusinessGroupVO getBusinessGroupVO() {
		return this.businessgroupVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.businessgroupVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.businessgroupVO.getSno();
 	}
 	/**
 	 * 获取集团编号的属性值.
 	 */
 	public  String   getGroupSno(){
 		return this.businessgroupVO.getGroupSno();
 	}
 	/**
 	 * 获取集团名称 的属性值.
 	 */
 	public  String   getGroupName(){
 		return this.businessgroupVO.getGroupName();
 	}
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getGroupEmail(){
 		return this.businessgroupVO.getGroupEmail();
 	}
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public  String   getGroupContact(){
 		return this.businessgroupVO.getGroupContact();
 	}
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public  String   getGroupContactPhone(){
 		return this.businessgroupVO.getGroupContactPhone();
 	}
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public  String   getGroupContactMobile(){
 		return this.businessgroupVO.getGroupContactMobile();
 	}
 	/**
 	 * 获取状态的属性值.
 	 */
 	public  String   getGroupStatus(){
 		return this.businessgroupVO.getGroupStatus();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.businessgroupVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.businessgroupVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.businessgroupVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.businessgroupVO.getUpdateTime();
 	}
 
}