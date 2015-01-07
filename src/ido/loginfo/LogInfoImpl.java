
package ido.loginfo;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于操作日志的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class LogInfoImpl implements LogInfo {
	private LogInfoVO loginfoVO = null;
	private static final long serialVersionUID = 1L;

	public LogInfoImpl(LogInfoVO loginfoVO) {
		this.loginfoVO = loginfoVO;
	}

	public LogInfoImpl( int sno , int operUser , String operUserName , String operTime , String operType , String operIp , String operUrl , String operBefore , String operAfter , String operDesc ) {
		this.loginfoVO = new LogInfoVO( sno , operUser , operUserName , operTime , operType , operIp , operUrl , operBefore , operAfter , operDesc );
	} 
	
	public LogInfoImpl(int operUser ,String operUserName ,String operTime ,String operType ,String operIp ,String operUrl ,String operBefore ,String operAfter ,String operDesc ) {
		this.loginfoVO = new LogInfoVO(operUser ,operUserName ,operTime ,operType ,operIp ,operUrl ,operBefore ,operAfter ,operDesc );
	} 

	public LogInfoVO getLogInfoVO() {
		return this.loginfoVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.loginfoVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.loginfoVO.getSno();
 	}
 	/**
 	 * 获取用户id的属性值.
 	 */
 	public  int   getOperUser(){
 		return this.loginfoVO.getOperUser();
 	}
 	/**
 	 * 获取用户的属性值.
 	 */
 	public  String   getOperUserName(){
 		return this.loginfoVO.getOperUserName();
 	}
 	/**
 	 * 获取时间 的属性值.
 	 */
 	public  String   getOperTime(){
 		return this.loginfoVO.getOperTime();
 	}
 	/**
 	 * 获取操作类型的属性值.
 	 */
 	public  String   getOperType(){
 		return this.loginfoVO.getOperType();
 	}
 	/**
 	 * 获取ip地址的属性值.
 	 */
 	public  String   getOperIp(){
 		return this.loginfoVO.getOperIp();
 	}
 	/**
 	 * 获取操作地址的属性值.
 	 */
 	public  String   getOperUrl(){
 		return this.loginfoVO.getOperUrl();
 	}
 	/**
 	 * 获取修改前的属性值.
 	 */
 	public  String   getOperBefore(){
 		return this.loginfoVO.getOperBefore();
 	}
 	/**
 	 * 获取修改后的属性值.
 	 */
 	public  String   getOperAfter(){
 		return this.loginfoVO.getOperAfter();
 	}
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getOperDesc(){
 		return this.loginfoVO.getOperDesc();
 	}
 
}