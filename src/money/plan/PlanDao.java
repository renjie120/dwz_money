
package money.plan;

import java.util.Collection;

import dwz.dal.BaseDao;

public interface PlanDao extends BaseDao<PlanVO, Integer> {

	public Collection<PlanVO> findRecordById(int planId); 
	
	public Collection<PlanVO> findAll(); 

	public void deleteAllById(String id);

	public void updateAllaccessPlanVO(PlanVO vo, String id);
}

