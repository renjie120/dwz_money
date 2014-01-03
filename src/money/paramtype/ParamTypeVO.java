
package money.paramtype;

import java.io.Serializable;

import common.base.SelectAble;
/**
 * 关于参数类型的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ParamTypeVO implements Serializable,SelectAble {
	private static final long serialVersionUID = 1L;
	
	public ParamTypeVO() {

	}
	
	public ParamTypeVO( int paramTypeId , String paramTypeName , int orderId , String code ) {
		 this.paramTypeId = paramTypeId;
		 this.paramTypeName = paramTypeName;
		 this.orderId = orderId;
		 this.code = code;
	}
	
	public ParamTypeVO(String paramTypeName ,int orderId ,String code ) {
			 this.paramTypeName = paramTypeName;
			 this.orderId = orderId;
			 this.code = code;
	}
	 
	private Integer paramTypeId; 
 	/**
 	 * 获取参数类型流水号的属性值.
 	 */
 	public Integer getParamTypeId(){
 		return paramTypeId;
 	}
 	
 	/**
 	 * 设置参数类型流水号的属性值.
 	 */
 	public void setParamTypeId(Integer paramtypeid){
 		this.paramTypeId = paramtypeid;
 	}
	private String paramTypeName; 
 	/**
 	 * 获取参数类型的属性值.
 	 */
 	public String getParamTypeName(){
 		return paramTypeName;
 	}
 	
 	/**
 	 * 设置参数类型的属性值.
 	 */
 	public void setParamTypeName(String paramtypename){
 		this.paramTypeName = paramtypename;
 	}
	private int orderId; 
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public int getOrderId(){
 		return orderId;
 	}
 	
 	/**
 	 * 设置排序号的属性值.
 	 */
 	public void setOrderId(int orderid){
 		this.orderId = orderid;
 	}
	private String code; 
 	/**
 	 * 获取参数类型编码的属性值.
 	 */
 	public String getCode(){
 		return code;
 	}
 	
 	/**
 	 * 设置参数类型编码的属性值.
 	 */
 	public void setCode(String code){
 		this.code = code;
 	}

 	public String getOptionId() { 
 		return this.getParamTypeId()+"";
	}

	public String getOptionName() { 
		return this.getCode();
	}
}
