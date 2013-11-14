package dwz.framework.user;

import java.util.List;

import dwz.framework.core.business.BusinessObjectManager;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface GridTreeManager extends BusinessObjectManager {

	List getAllDepart(String userId); 

}
