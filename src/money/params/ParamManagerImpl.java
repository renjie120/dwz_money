package money.params;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.AllSelect;
import common.base.ParamSelect;
import common.base.SpringContextUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public class ParamManagerImpl extends AbstractBusinessObjectManager implements
		ParamManager {

	private ParamDao pramaDao = null; 

	public ParamManagerImpl(ParamDao pramaDao) {
		this.pramaDao = pramaDao;
	} 
	
	public Integer searchParamNum(Map<ParamSearchFields, Object> criterias) { 
		if (criterias == null) {
			return 0;
		} 
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.pramaDao.countByQuery(hql,(Object[])quertParas[1]); 
		
		return totalCount.intValue();
	}
	
 
	public Collection<Param> searchParam(Map<ParamSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<Param> eaList = new ArrayList<Param>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString(); 
		//直接根据hql语句进行查询.
		Collection<ParamVO> voList = this.pramaDao.findByQuery(hql,(Object[])quertParas[1], startIndex,
				count);

		if (voList == null || voList.size() == 0)
			return eaList;
		
		AllSelect allSelect = (AllSelect)SpringContextUtil.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select = allSelect.getAllParamType();
		
		for (ParamVO po : voList) { 
			po.setParameterTypeName(select.getName(""+po.getParameterType()));
			eaList.add(new ParamImpl(po));
		}

		return eaList;
	}
 
	private Object[] createQuery(boolean useCount,
			Map<ParamSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder(); 
		sb.append(useCount ? "select count(distinct param) ": "select distinct param ").append("from ParamVO as param ");

		int count = 0;
		List argList = new ArrayList();
		if(criterias.size()>0)
		for (Map.Entry<ParamSearchFields, Object> entry : criterias.entrySet()) {
			ParamSearchFields fd = entry.getKey();
			switch (fd) {  
			case PARAM_ID:
				sb.append(count == 0 ? " where" : " and").append(" param.parameterID=? ");
				argList.add(entry.getValue());
				count++;
				break;
			case PARAM_TYPE:
				sb.append(count == 0 ? " where" : " and").append(" param.parameterType=? ");
				argList.add(entry.getValue());
				count++;
				break;
			case PARAM_VALUE:
				sb.append(count == 0 ? " where" : " and").append(" param.parameterValue like ? ");
				argList.add(entry.getValue());
				count++;
				break;
			case PARAM_NAME:
				sb.append(count == 0 ? " where" : " and").append(" param.parameterName like ? ");
				argList.add(entry.getValue());
				count++;
				break;
			case PARAM_ORDERID:
				sb.append(count == 0 ? " where" : " and").append(" param.orderId=? ");
				argList.add(entry.getValue());
				count++;
				break; 
			case PARAM_SNO:
				sb.append(count == 0 ? " where" : " and").append(" param.parameterSno like ? ");
				argList.add(entry.getValue());
				count++;
				break; 
			default:
				break;
			} 
		}   
		if(!useCount)
			sb.append(" order by parameterType,parameterID  ");
		 return new Object[]{sb.toString(),argList.toArray()};

		/*---下面的没有看懂...
		UserOrderByFields orderBy = UserOrderByFields.INSERT_DATE_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = UserOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case USER_NAME:
			sb.append(" order by sysUser.userName");
			break;
		case USER_NAME_DESC:
			sb.append(" order by sysUser.userName desc");
			break;
		case EMAIL:
			sb.append(" order by sysUser.email");
			break;
		case EMAIL_DESC:
			sb.append(" order by sysUser.email desc");
			break;
		case FIRST_NAME:
			sb.append(" order by sysUser.firstName");
			break;
		case FIRST_NAME_DESC:
			sb.append(" order by sysUser.firstName desc");
			break;
		case INSERT_DATE:
			sb.append(" order by sysUser.insertDate");
			break;
		case INSERT_DATE_DESC:
			sb.append(" order by sysUser.insertDate desc");
			break;
		case STATUS:
			sb.append(" order by sysUser.status");
			break;
		case STATUS_DESC:
			sb.append(" order by sysUser.status desc");
			break;
		}*/ 
	}

	public void createParam(Param param) throws ValidateFieldsException { 
		ParamImpl paramImpl = (ParamImpl) param;  
		this.pramaDao.insert(paramImpl.getParamVO());
	}

	public void removeParam(String moneyId) {
		String[] ids = moneyId.split(",");
		for(String s:ids){
			ParamVO vo = this.pramaDao.findByPrimaryKey(Integer.parseInt(s));
			this.pramaDao.delete(vo);
		} 
	}

	public void updateParam(Param param) throws ValidateFieldsException {
		ParamImpl paramImpl = (ParamImpl) param;

		ParamVO po = paramImpl.getParamVO(); 
		this.pramaDao.update(po);
	}

	public Param getParam(Integer id) {
		Collection<ParamVO> params = this.pramaDao.findRecordById(id);

		if (params == null || params.size() < 1)
			return null;

		ParamVO param = params.toArray(new ParamVO[params.size()])[0];

		return new ParamImpl(param);
	} 
}
