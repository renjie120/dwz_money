package money.plan;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface PlanManager extends BusinessObjectManager {

	/**
	 * 查询实体.
	 * @param criterias
	 * @param orderField
	 * @param startIndex
	 * @param count
	 * @return
	 */
	public Collection<Plan> searchPlan(Map<PlanSearchFields, Object> criterias,
			String orderField, int startIndex, int count);

	/**
	 * 查询满足条件的总记录数.
	 * @param criterias
	 * @return
	 */
	public Integer searchPlanNum(Map<PlanSearchFields, Object> criterias);

	/**
	 * 实例化实体到数据库.
	 * @param plan
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void createPlan(Plan plan) throws ValidateFieldsException;

	/**
	 * 更新实体.
	 * @param plan
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void updatePlan(Plan plan) throws ValidateFieldsException;

	/**
	 * 删除实体.
	 * @param planId
	 */
	@Transactional
	public void removePlan(String planId);

	/**
	 * 返回实体详情.
	 * @param id
	 * @return
	 */
	public Plan getPlan(Integer id);
}
