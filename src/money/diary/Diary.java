
package money.diary;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于日志信息表的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface Diary extends BusinessObject {  
 	/**
 	 * 获取日志id的属性值.
 	 */
 	public  Integer   getDiaryId();
 	/**
 	 * 获取日志内容的属性值.
 	 */
 	public  String   getDiaryContent();
 	/**
 	 * 获取开始时间的属性值.
 	 */
 	public  Date   getDiaryTime();
 	/**
 	 * 获取日志类型的属性值.
 	 */
 	public  String   getDiaryType();
}
