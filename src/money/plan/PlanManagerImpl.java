package money.plan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public class PlanManagerImpl extends AbstractBusinessObjectManager implements
		PlanManager {

	private PlanDao planDao = null;

	public PlanManagerImpl(PlanDao planDao) {
		this.planDao = planDao;
	}

	/**
	 * 
	 */
	public Integer searchPlanNum(Map<PlanSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.planDao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	/**
	 * 查询记录.
	 */
	public Collection<Plan> searchPlan(Map<PlanSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<Plan> eaList = new ArrayList<Plan>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<PlanVO> voList = this.planDao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_planType = allSelect
				.getParamsByType(AllSelectContants.PLAN_TYPE.getName());
		ParamSelect select_planStatus = allSelect
				.getParamsByType(AllSelectContants.PLAN_STATUS.getName());

		for (PlanVO po : voList) {
			po.setPlanTypeName(select_planType.getName("" + po.getPlanType()));
			po.setPlanStatusName(select_planStatus.getName(""
					+ po.getPlanStatus()));

			eaList.add(new PlanImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<PlanSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct plan) "
						: "select distinct plan ").append(
				"from PlanVO as plan ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<PlanSearchFields, Object> entry : criterias
					.entrySet()) {
				PlanSearchFields fd = entry.getKey();
				switch (fd) {
				case PLANID:
					sb.append(count == 0 ? " where" : " and").append(
							" plan.planId =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case PLANDATE:
					sb.append(count == 0 ? " where" : " and").append(
							" plan.planDate =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case STARTDATE:
					sb.append(count == 0 ? " where" : " and").append(
							" plan.startDate =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ENDDATE:
					sb.append(count == 0 ? " where" : " and").append(
							" plan.endDate =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case REALSTARTDATE:
					sb.append(count == 0 ? " where" : " and").append(
							" plan.realStartDate =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case REALENDDATE:
					sb.append(count == 0 ? " where" : " and").append(
							" plan.realEndDate =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case PLANDESC:
					sb.append(count == 0 ? " where" : " and").append(
							" plan.planDesc like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case PLANTYPE:
					sb.append(count == 0 ? " where" : " and").append(
							" plan.planType =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case PLANSTATUS:
					sb.append(count == 0 ? " where" : " and").append(
							" plan.planStatus =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case USERID:
					sb.append(count == 0 ? " where" : " and").append(
							" plan.userId =? ");
					argList.add(entry.getValue());
					count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		PlanOrderByFields orderBy = PlanOrderByFields.PLANID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = PlanOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case PLANID:
			sb.append(" order by plan.planId");
			break;
		case PLANID_DESC:
			sb.append(" order by plan.planId desc");
			break;
		case PLANDATE:
			sb.append(" order by plan.planDate");
			break;
		case PLANDATE_DESC:
			sb.append(" order by plan.planDate desc");
			break;
		case STARTDATE:
			sb.append(" order by plan.startDate");
			break;
		case STARTDATE_DESC:
			sb.append(" order by plan.startDate desc");
			break;
		case ENDDATE:
			sb.append(" order by plan.endDate");
			break;
		case ENDDATE_DESC:
			sb.append(" order by plan.endDate desc");
			break;
		case REALSTARTDATE:
			sb.append(" order by plan.realStartDate");
			break;
		case REALSTARTDATE_DESC:
			sb.append(" order by plan.realStartDate desc");
			break;
		case REALENDDATE:
			sb.append(" order by plan.realEndDate");
			break;
		case REALENDDATE_DESC:
			sb.append(" order by plan.realEndDate desc");
			break;
		case PLANDESC:
			sb.append(" order by plan.planDesc");
			break;
		case PLANDESC_DESC:
			sb.append(" order by plan.planDesc desc");
			break;
		case PLANTYPE:
			sb.append(" order by plan.planType");
			break;
		case PLANTYPE_DESC:
			sb.append(" order by plan.planType desc");
			break;
		case PLANSTATUS:
			sb.append(" order by plan.planStatus");
			break;
		case PLANSTATUS_DESC:
			sb.append(" order by plan.planStatus desc");
			break;
		case USERID:
			sb.append(" order by plan.userId");
			break;
		case USERID_DESC:
			sb.append(" order by plan.userId desc");
			break;

		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createPlan(Plan plan) throws ValidateFieldsException {
		PlanImpl planImpl = (PlanImpl) plan;
		this.planDao.insert(planImpl.getPlanVO());
	}

	public void removePlan(String planIds) {
		String[] idArr = planIds.split(",");
		for (String s : idArr) {
			PlanVO vo = this.planDao.findByPrimaryKey(Integer.parseInt(s));
			this.planDao.delete(vo);
		}
	}

	public void updatePlan(Plan plan) throws ValidateFieldsException {
		PlanImpl planImpl = (PlanImpl) plan;

		PlanVO po = planImpl.getPlanVO();
		this.planDao.update(po);
	}

	public Plan getPlan(Integer id) {
		Collection<PlanVO> plans = this.planDao.findRecordById(id);

		if (plans == null || plans.size() < 1)
			return null;

		PlanVO plan = plans.toArray(new PlanVO[plans.size()])[0];

		return new PlanImpl(plan);
	}

}
