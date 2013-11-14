package money.params;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface ParamManager extends BusinessObjectManager {  

	public Collection<Param> searchParam(Map<ParamSearchFields, Object> criterias,
			String orderField, int startIndex, int count); 
	
	public Integer searchParamNum(Map<ParamSearchFields, Object> criterias); 
  	
	@Transactional
	public void createParam(Param money)
			throws ValidateFieldsException;

	@Transactional
	public void updateParam(Param money) throws ValidateFieldsException;

	@Transactional
	public void removeParam(String moneyId);
	
	public Param getParam(Integer id);
}
