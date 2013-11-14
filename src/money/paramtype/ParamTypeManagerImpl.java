
package money.paramtype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public class ParamTypeManagerImpl extends AbstractBusinessObjectManager implements
		ParamTypeManager {

	private ParamTypeDao paramTypeDao = null;

	public ParamTypeManagerImpl(ParamTypeDao paramTypeDao) {
		this.paramTypeDao = paramTypeDao;
	} 
	
	public Integer searchParamTypeNum(Map<ParamTypeSearchFields, Object> criterias) { 
		if (criterias == null) {
			return 0;
		} 
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.paramTypeDao.countByQuery(hql,(Object[])quertParas[1]); 
		
		return totalCount.intValue();
	}
	
 
	public Collection<ParamType> searchParamType(Map<ParamTypeSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<ParamType> eaList = new ArrayList<ParamType>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString(); 
		//直接根据hql语句进行查询.
		Collection<ParamTypeVO> voList = this.paramTypeDao.findByQuery(hql,(Object[])quertParas[1], startIndex,
				count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (ParamTypeVO po : voList) {
			eaList.add(new ParamTypeImpl(po));
		}

		return eaList;
	}
 
	private Object[] createQuery(boolean useCount,
			Map<ParamTypeSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder(); 
		sb.append(useCount ? "select count(distinct paramType) ": "select distinct paramType ").append("from ParamTypeVO as paramType ");

		int count = 0;
		List argList = new ArrayList();
		if(criterias.size()>0)
		for (Map.Entry<ParamTypeSearchFields, Object> entry : criterias.entrySet()) {
			ParamTypeSearchFields fd = entry.getKey();
			switch (fd) {  
			case PPARAMETER_TYPE_ID:
				sb.append(count == 0 ? " where" : " and").append(" paramType.parameterTypeId=? ");
				argList.add(entry.getValue());
				count++;
				break;
			case PARAMETER_TYPE_NAME:
				sb.append(count == 0 ? " where" : " and").append(" paramType.parameterTypeName like ? ");
				argList.add(entry.getValue());
				count++;
				break;
			case ORDERID:
				sb.append(count == 0 ? " where" : " and").append(" paramType.orderid = ? ");
				argList.add(entry.getValue());
				count++;
				break; 
			case CODE:
				sb.append(count == 0 ? " where" : " and").append(" paramType.code = ? ");
				argList.add(entry.getValue());
				count++;
				break; 
			default:
				break;
			} 
		}   
		if(!useCount)
			sb.append(" order by orderid  ");
		 return new Object[]{sb.toString(),argList.toArray()}; 
	}

	public void createParamType(ParamType paramType) throws ValidateFieldsException { 
		ParamTypeImpl paramTypeImpl = (ParamTypeImpl) paramType;  
		this.paramTypeDao.insert(paramTypeImpl.getParamTypeVO());
	}
 
	public int removeParamType(String paramTypes) {
		String[] ids = paramTypes.split(",");
		int count = ids.length; 
		for(String s:ids){
			int deleteId = Integer.parseInt(s); 
			if(canDeleteType(deleteId)){
				ParamTypeVO vo = this.paramTypeDao.findByPrimaryKey(deleteId);
				this.paramTypeDao.delete(vo);
			}else{
				count--;
			}
		} 
		return count;
	}

	public void updateParamType(ParamType paramType) throws ValidateFieldsException {
		ParamTypeImpl paramTypeImpl = (ParamTypeImpl) paramType;

		ParamTypeVO po = paramTypeImpl.getParamTypeVO(); 
		this.paramTypeDao.update(po);
	}

	public ParamType getParamType(Integer id) {
		Collection<ParamTypeVO> paramTypes = this.paramTypeDao.findRecordById(id);

		if (paramTypes == null || paramTypes.size() < 1)
			return null;

		ParamTypeVO paramType = paramTypes.toArray(new ParamTypeVO[paramTypes.size()])[0];

		return new ParamTypeImpl(paramType);
	}



	public boolean canDeleteType(Integer id) { 
		String hql = "select count(param.parameterID) from ParamVO as param where param.parameterType = "+id; 
		if (this.paramTypeDao.countByQuery(hql) > 0)
			return false;
		else
			return true;
	}
}

