package dwz.persistence.daos;

import java.util.Collection;
import java.util.Date;

import dwz.persistence.beans.SysSession;
import dwz.dal.BaseDao;

public interface SysSessionDao extends BaseDao<SysSession, String> {

	public Collection<String> findRecordById(String id);

	public void deleteAllTimeout(Date timeout);

	public void deleteAllById(String id);

	public void updateAllaccessDate(Date accessDate, String id);
}
