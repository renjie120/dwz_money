package dwz.persistence.daos;

import java.util.Collection;
import java.util.Date;

import dwz.persistence.beans.InfNews;
import dwz.dal.BaseDao;

public interface InfNewsDao extends BaseDao<InfNews, Integer> {

	public Collection<InfNews> findByTypePageBreak(String type,
			Integer startIndex, Integer count);

	public Collection<InfNews> findPrevPageBreak(String type, Date updateDate,
			Integer startIndex, Integer count);

	public Collection<InfNews> findNextPageBreak(String type, Date updateDate,
			Integer startIndex, Integer count);
	
	public void updateAllStatus(String status, Integer id);
}
