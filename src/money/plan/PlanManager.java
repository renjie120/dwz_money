package money.plan;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface PlanManager extends BusinessObjectManager {

	public Collection<Plan> searchPlan(Map<PlanSearchFields, Object> criterias,
			String orderField, int startIndex, int count);

	public Integer searchPlanNum(Map<PlanSearchFields, Object> criterias);

	@Transactional
	public void createPlan(Plan plan) throws ValidateFieldsException;

	@Transactional
	public void updatePlan(Plan plan) throws ValidateFieldsException;

	@Transactional
	public void removePlan(String planId);

	public Plan getPlan(Integer id);
}
