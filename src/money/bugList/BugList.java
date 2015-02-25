
package money.bugList;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于问题清单的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface BugList extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取问题类型的属性值.
 	 */
 	public  String   getBugType();
 	/**
 	 * 获取问题描述的属性值.
 	 */
 	public  String   getBugDesc();
 	/**
 	 * 获取问题发现人的属性值.
 	 */
 	public  int   getCreateUser();
 	/**
 	 * 获取发现时间的属性值.
 	 */
 	public  String   getCreateTime();
 	/**
 	 * 获取待解决时间的属性值.
 	 */
 	public  String   getNeedTime();
 	/**
 	 * 获取解决人的属性值.
 	 */
 	public  String   getConsolePeople();
 	/**
 	 * 获取解决时间的属性值.
 	 */
 	public  String   getConsoleTile();
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getRemark();
}
