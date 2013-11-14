package dwz.persistence.daos;

import java.util.Collection;

import dwz.dal.BaseDao;
import dwz.persistence.beans.InvCategory;

public interface InvCategoryDao extends BaseDao<InvCategory,Integer> {

	Collection<InvCategory> findAllInvCategory();
	 
}
