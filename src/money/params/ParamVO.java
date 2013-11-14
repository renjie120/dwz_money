package money.params;

import java.io.Serializable;

import common.base.SelectAble;

/**
 * 理财管理类. 
 * @author lsq
 * 
 */
public class ParamVO implements Serializable,SelectAble {
	private static final long serialVersionUID = 1L;
	private int parameterID; 
	private int parameterType;
	private String parameterTypeName;
	private String parameterName;
	private Integer parameterValue;
	private String useValue;
	private int orderId; 

	public Integer getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(Integer parameterValue) {
		this.parameterValue = parameterValue;
	}

	public String getUseValue() {
		return useValue;
	}

	public void setUseValue(String useValue) {
		this.useValue = useValue;
	}

	public ParamVO(){
		
	}
	
	public ParamVO(int parameterType,String parameterName,int orderId,int parameterValue,String useValue){
		this.parameterType = parameterType;
		this.parameterName = parameterName;
		this.orderId = orderId;  
		this.useValue = useValue;
		this.parameterValue = parameterValue;
	}
	
	public ParamVO(int parameterID,int parameterType,String parameterName,int orderId,int parameterValue,String useValue){
		this.parameterID = parameterID;
		this.parameterType = parameterType;
		this.parameterName = parameterName;
		this.orderId = orderId;  
		this.useValue = useValue;
		this.parameterValue = parameterValue;
	}

	public int getParameterID() {
		return parameterID;
	}

	public void setParameterID(int parameterID) {
		this.parameterID = parameterID;
	}

	public int getParameterType() {
		return parameterType;
	}

	public void setParameterType(int parameterType) {
		this.parameterType = parameterType;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
 

	public String getParameterTypeName() {
		return parameterTypeName;
	}

	public void setParameterTypeName(String parameterTypeName) {
		this.parameterTypeName = parameterTypeName;
	}

	public String getOptionId() { 
		if("1".equals(this.useValue))
			return this.parameterValue+"";
		else
			return this.parameterID+"";
	}

	public String getOptionName() { 
		return this.parameterName;
	} 
}
