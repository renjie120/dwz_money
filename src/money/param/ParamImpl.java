
package money.param;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于参数的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ParamImpl implements Param {
	private ParamVO paramVO = null;
	private static final long serialVersionUID = 1L;

	public ParamImpl(ParamVO paramVO) {
		this.paramVO = paramVO;
	}

	public ParamImpl( int paramId , int paramType , String paramName , int paramValue , String usevalue , int orderId ) {
		this.paramVO = new ParamVO( paramId , paramType , paramName , paramValue , usevalue , orderId );
	} 
	
	public ParamImpl(int paramType ,String paramName ,int paramValue ,String usevalue ,int orderId ) {
		this.paramVO = new ParamVO(paramType ,paramName ,paramValue ,usevalue ,orderId );
	} 

	public ParamVO getParamVO() {
		return this.paramVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.paramVO.getParamId();
	} 
	
 	/**
 	 * 获取参数流水号的属性值.
 	 */
 	public  Integer   getParamId(){
 		return this.paramVO.getParamId();
 	}
 	/**
 	 * 获取参数类型的属性值.
 	 */
 	public  int   getParamType(){
 		return this.paramVO.getParamType();
 	}
 	/**
 	 * 获取参数描述的属性值.
 	 */
 	public  String   getParamName(){
 		return this.paramVO.getParamName();
 	}
 	/**
 	 * 获取参数值的属性值.
 	 */
 	public  int   getParamValue(){
 		return this.paramVO.getParamValue();
 	}
 	/**
 	 * 获取用户自定义值的属性值.
 	 */
 	public  String   getUsevalue(){
 		return this.paramVO.getUsevalue();
 	}
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public  int   getOrderId(){
 		return this.paramVO.getOrderId();
 	}
 
}