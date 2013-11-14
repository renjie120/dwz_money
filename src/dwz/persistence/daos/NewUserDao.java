package dwz.persistence.daos;

import java.util.Collection;

import dwz.dal.BaseDao;
import dwz.persistence.beans.UserVO;

public interface NewUserDao extends BaseDao<UserVO, String> {

	public Collection<String> findByPublicCredential(String userName,
			String password);

	public Collection<UserVO> findUserById(String userName);

	public Collection<Number> findAllUserNum(String userType);
}
