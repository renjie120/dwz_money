
package money.org;

import java.util.Collection;

import dwz.dal.BaseDao;

public interface OrgDao extends BaseDao<OrgVO, Integer> {

	public Collection<OrgVO> findRecordById(int orgId); 
	
	public Collection<OrgVO> findAll(); 

	public void deleteAllById(String id);

	public void updateAllaccessOrgVO(OrgVO vo, String id);
}

