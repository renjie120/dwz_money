package dwz.persistence.daos;

import java.util.Collection;

import dwz.persistence.beans.SetPreference;
import dwz.dal.BaseDao;

public interface SetPreferenceDao extends BaseDao<SetPreference, Integer> {

	public Collection<SetPreference> findByPrefKey(Integer companyId, String key);

	public Collection<String> findValueByPrefKey(Integer companyId, String key);
}
