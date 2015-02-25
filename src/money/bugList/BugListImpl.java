
package money.bugList;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于问题清单的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BugListImpl implements BugList {
	private BugListVO buglistVO = null;
	private static final long serialVersionUID = 1L;

	public BugListImpl(BugListVO buglistVO) {
		this.buglistVO = buglistVO;
	}

	public BugListImpl( int sno , String bugType , String bugDesc , int createUser , String createTime , String needTime , String consolePeople , String consoleTile , String remark ) {
		this.buglistVO = new BugListVO( sno , bugType , bugDesc , createUser , createTime , needTime , consolePeople , consoleTile , remark );
	} 
	
	public BugListImpl(String bugType ,String bugDesc ,int createUser ,String createTime ,String needTime ,String consolePeople ,String consoleTile ,String remark ) {
		this.buglistVO = new BugListVO(bugType ,bugDesc ,createUser ,createTime ,needTime ,consolePeople ,consoleTile ,remark );
	} 

	public BugListVO getBugListVO() {
		return this.buglistVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.buglistVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.buglistVO.getSno();
 	}
 	/**
 	 * 获取问题类型的属性值.
 	 */
 	public  String   getBugType(){
 		return this.buglistVO.getBugType();
 	}
 	/**
 	 * 获取问题描述的属性值.
 	 */
 	public  String   getBugDesc(){
 		return this.buglistVO.getBugDesc();
 	}
 	/**
 	 * 获取问题发现人的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.buglistVO.getCreateUser();
 	}
 	/**
 	 * 获取发现时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.buglistVO.getCreateTime();
 	}
 	/**
 	 * 获取待解决时间的属性值.
 	 */
 	public  String   getNeedTime(){
 		return this.buglistVO.getNeedTime();
 	}
 	/**
 	 * 获取解决人的属性值.
 	 */
 	public  String   getConsolePeople(){
 		return this.buglistVO.getConsolePeople();
 	}
 	/**
 	 * 获取解决时间的属性值.
 	 */
 	public  String   getConsoleTile(){
 		return this.buglistVO.getConsoleTile();
 	}
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getRemark(){
 		return this.buglistVO.getRemark();
 	}
 
}