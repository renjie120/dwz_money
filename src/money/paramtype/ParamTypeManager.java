
package money.paramtype;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import common.base.ParamSelect;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface ParamTypeManager extends BusinessObjectManager {  

	public Collection<ParamType> searchParamType(Map<ParamTypeSearchFields, Object> criterias,
			String orderField, int startIndex, int count);  
	public Integer searchParamTypeNum(Map<ParamTypeSearchFields, Object> criterias); 

	public boolean canDeleteType(Integer id);  
	
	@Transactional
	public void createParamType(ParamType paramType)
			throws ValidateFieldsException;

	@Transactional
	public void updateParamType(ParamType paramType) throws ValidateFieldsException;

	/**
	 * 删除参数类型
	 * @param paramTypeId
	 * @return 返回删除成功的记录数.
	 */
	@Transactional
	public int removeParamType(String paramTypeId);
	
	public ParamType getParamType(Integer id);
}

