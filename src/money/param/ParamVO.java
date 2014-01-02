
package money.param;

import java.io.Serializable;

import common.base.SelectAble;
/**
 * 关于参数的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ParamVO implements Serializable,SelectAble  {
	private static final long serialVersionUID = 1L;
	
	public ParamVO() {

	}
	
	public ParamVO( int paramId , int paramType , String paramName , int paramValue , String usevalue , int orderId ) {
		 this.paramId = paramId;
		 this.paramType = paramType;
		 this.paramName = paramName;
		 this.paramValue = paramValue;
		 this.usevalue = usevalue;
		 this.orderId = orderId;
	}
	
	public ParamVO(int paramType ,String paramName ,int paramValue ,String usevalue ,int orderId ) {
			 this.paramType = paramType;
			 this.paramName = paramName;
			 this.paramValue = paramValue;
			 this.usevalue = usevalue;
			 this.orderId = orderId;
	}
	 
	private Integer paramId; 
 	/**
 	 * 获取参数流水号的属性值.
 	 */
 	public Integer getParamId(){
 		return paramId;
 	}
 	
 	/**
 	 * 设置参数流水号的属性值.
 	 */
 	public void setParamId(Integer paramid){
 		this.paramId = paramid;
 	}
	private int paramType; 
 	/**
 	 * 获取参数类型的属性值.
 	 */
 	public int getParamType(){
 		return paramType;
 	}
 	
 	/**
 	 * 设置参数类型的属性值.
 	 */
 	public void setParamType(int paramtype){
 		this.paramType = paramtype;
 	}
	private String paramName; 
 	/**
 	 * 获取参数描述的属性值.
 	 */
 	public String getParamName(){
 		return paramName;
 	}
 	
 	/**
 	 * 设置参数描述的属性值.
 	 */
 	public void setParamName(String paramname){
 		this.paramName = paramname;
 	}
	private int paramValue; 
 	/**
 	 * 获取参数值的属性值.
 	 */
 	public int getParamValue(){
 		return paramValue;
 	}
 	
 	/**
 	 * 设置参数值的属性值.
 	 */
 	public void setParamValue(int paramvalue){
 		this.paramValue = paramvalue;
 	}
	private String usevalue; 
 	/**
 	 * 获取用户自定义值的属性值.
 	 */
 	public String getUsevalue(){
 		return usevalue;
 	}
 	
 	/**
 	 * 设置用户自定义值的属性值.
 	 */
 	public void setUsevalue(String usevalue){
 		this.usevalue = usevalue;
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
 	
	@Override
	public String getOptionId() {
		// TODO Auto-generated method stub
		return this.getParamId()+"";
	}

	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return this.getParamName()+"";
	}
}
