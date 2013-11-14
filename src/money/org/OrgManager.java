package money.org;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface OrgManager extends BusinessObjectManager {

	public Collection<Org> searchOrg(Map<OrgSearchFields, Object> criterias,
			String orderField, int startIndex, int count);

	public Integer searchOrgNum(Map<OrgSearchFields, Object> criterias);

	@Transactional
	public void createOrg(Org org) throws ValidateFieldsException;

	@Transactional
	public void updateOrg(Org org) throws ValidateFieldsException;

	@Transactional
	public void removeOrg(String orgId);

	public Org getOrg(Integer id);
}
