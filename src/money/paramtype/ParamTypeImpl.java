
package money.paramtype;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于参数类型的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ParamTypeImpl implements ParamType {
	private ParamTypeVO paramtypeVO = null;
	private static final long serialVersionUID = 1L;

	public ParamTypeImpl(ParamTypeVO paramtypeVO) {
		this.paramtypeVO = paramtypeVO;
	}

	public ParamTypeImpl( int paramTypeId , String paramTypeName , int orderId , String code ) {
		this.paramtypeVO = new ParamTypeVO( paramTypeId , paramTypeName , orderId , code );
	} 
	
	public ParamTypeImpl(String paramTypeName ,int orderId ,String code ) {
		this.paramtypeVO = new ParamTypeVO(paramTypeName ,orderId ,code );
	} 

	public ParamTypeVO getParamTypeVO() {
		return this.paramtypeVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.paramtypeVO.getParamTypeId();
	} 
	
 	/**
 	 * 获取参数类型流水号的属性值.
 	 */
 	public  Integer   getParamTypeId(){
 		return this.paramtypeVO.getParamTypeId();
 	}
 	/**
 	 * 获取参数类型的属性值.
 	 */
 	public  String   getParamTypeName(){
 		return this.paramtypeVO.getParamTypeName();
 	}
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public  int   getOrderId(){
 		return this.paramtypeVO.getOrderId();
 	}
 	/**
 	 * 获取参数类型编码的属性值.
 	 */
 	public  String   getCode(){
 		return this.paramtypeVO.getCode();
 	}
 
}