package money.params;

import dwz.framework.core.business.BusinessObject;

/**
 * 理财管理类.
 * 
 * @author lsq
 * 
 */
public class ParamImpl implements Param {
	private ParamVO paramVO = null;
	private static final long serialVersionUID = 1L;

	public ParamImpl(ParamVO paramVO) {
		this.paramVO = paramVO;
	}

	public ParamImpl(int parameterType, String parameterName, int orderId,int parameterValue,String useValue) {
		paramVO = new ParamVO(parameterType, parameterName, orderId,parameterValue,useValue);
	}
	private int parameterValue;
	private String useValue;
	public ParamImpl(int parameterID, int parameterType, String parameterName,
			int orderId,int parameterValue,String useValue) {
		paramVO = new ParamVO(parameterID, parameterType, parameterName,
				orderId,  parameterValue,  useValue);
	}

	public ParamVO getParamVO() {
		return this.paramVO;
	}

	public void copyProperties(BusinessObject orig) {
		// TODO Auto-generated method stub

	}

	public Integer getId() {
		return this.paramVO.getParameterID();
	}

	public int getOrderId() {
		return this.paramVO.getOrderId();
	}

	public String getParameterName() {
		return this.paramVO.getParameterName();
	}

	public int getParameterType() {
		return this.paramVO.getParameterType();
	} 

	public int getParameterID() {
		return this.paramVO.getParameterID();
	}

	public String getParameterTypeName() {
		return this.paramVO.getParameterTypeName();
	}

	public int getParameterValue() {
		return this.paramVO.getParameterValue();
	} 

	public String getUseValue() {
		return this.paramVO.getUseValue();
	} 
}
