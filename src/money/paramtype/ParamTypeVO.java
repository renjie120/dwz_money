package money.paramtype;

import java.io.Serializable;

import common.base.SelectAble;

/**
 * 理财管理类.
 * 
 * @author lsq
 * 
 */
public class ParamTypeVO implements Serializable,SelectAble {
	private static final long serialVersionUID = 1L;
	private int parameterTypeId;
	private String parameterTypeName;
	private int orderId;
	private String code;

	public ParamTypeVO() {

	}

	public ParamTypeVO(int parameterTypeId, String parameterTypeName,
			int orderId,String code) {
		this.parameterTypeId = parameterTypeId;
		this.orderId = orderId;
		this.code = code;
		this.parameterTypeName = parameterTypeName;
	}

	public ParamTypeVO(String parameterTypeName, int orderId,String code) {
		this.orderId = orderId;
		this.code = code;
		this.parameterTypeName = parameterTypeName;

	}

	public int getParameterTypeId() {
		return parameterTypeId;
	}

	public void setParameterTypeId(int parameterTypeId) {
		this.parameterTypeId = parameterTypeId;
	}

	public String getParameterTypeName() {
		return parameterTypeName;
	}

	public void setParameterTypeName(String parameterTypeName) {
		this.parameterTypeName = parameterTypeName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOptionId() { 
		return this.parameterTypeId+"";
	}

	public String getOptionName() { 
		return this.getParameterTypeName();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
