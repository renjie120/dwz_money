package money.paramtype;

import dwz.framework.core.business.BusinessObject;

/**
 * 理财管理类. 
 * @author lsq
 * 
 */
public class ParamTypeImpl implements ParamType {
	private ParamTypeVO paramTypeVO = null;
	private static final long serialVersionUID = 1L;

	public ParamTypeImpl(ParamTypeVO paramTypeVO) {
		this.paramTypeVO = paramTypeVO;
	}

	public ParamTypeImpl(int parameterTypeId, String parameterTypeName,
			int orderid,String code) { 
		paramTypeVO = new ParamTypeVO(parameterTypeId, parameterTypeName,
				orderid,code);

	}

	public ParamTypeImpl(String parameterTypeName, int orderid,String code) { 
		paramTypeVO = new ParamTypeVO(parameterTypeName, orderid,code);
	}

	public ParamTypeVO getParamTypeVO() {
		return this.paramTypeVO;
	}

	public void copyProperties(BusinessObject orig) {
		// TODO Auto-generated method stub

	}

	public Integer getId() {
		return this.paramTypeVO.getParameterTypeId();
	}

	public int getOrderId() {
		return this.paramTypeVO.getOrderId();
	}

	public int getParameterTypeId() {
		return this.paramTypeVO.getParameterTypeId();
	}

	public String getParameterTypeName() {
		return this.paramTypeVO.getParameterTypeName();
	}

	public String getCode() {
		return this.paramTypeVO.getCode();
	}

}
