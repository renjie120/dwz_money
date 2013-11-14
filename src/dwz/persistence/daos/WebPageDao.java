package dwz.persistence.daos;

import java.util.Collection;

import dwz.persistence.beans.WebPage;
import dwz.dal.BaseDao;

public interface WebPageDao extends BaseDao<WebPage, Integer> {
	Collection<WebPage> findAllWebPage();

	Collection<WebPage> findWebPageByTarget(String target);
}
