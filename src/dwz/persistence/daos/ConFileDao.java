package dwz.persistence.daos;

import java.util.Collection;
import java.util.Date;

import dwz.persistence.beans.ConFile;
import dwz.dal.BaseDao;

public interface ConFileDao extends BaseDao<ConFile, String> {

	public void deleteAllByPath(String path);

	public Collection<String> findIdsByPath(String path);
	
	public Collection<Number> findBetweenDateFileCount(Date startDate, Date endDate);
}
