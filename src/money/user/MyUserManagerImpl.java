package money.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public class MyUserManagerImpl extends AbstractBusinessObjectManager implements
		MyUserManager {

	private MyUserDao myUserDao = null;

	public MyUserManagerImpl(MyUserDao myUserDao) {
		this.myUserDao = myUserDao;
	}

	public Integer searchUserNum(Map<MyUserSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.myUserDao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	public Collection<IUser> searchUser(
			Map<MyUserSearchFields, Object> criterias, String orderField,
			int startIndex, int count) {
		ArrayList<IUser> eaList = new ArrayList<IUser>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		// 直接根据hql语句进行查询.
		Collection<MyUserVO> voList = this.myUserDao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (MyUserVO po : voList) {
			eaList.add(new MyUserImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<MyUserSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct user) "
						: "select distinct user ").append(
				"from MyUserVO as user where user.userType='2' ");

		int count = 1;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<MyUserSearchFields, Object> entry : criterias
					.entrySet()) {
				MyUserSearchFields fd = entry.getKey();
				switch (fd) {
				case USERNAME:
					sb.append(count == 0 ? " where" : " and").append(
							" user.useretertype=? ");
					argList.add(entry.getValue());
					count++;
					break;
				case LONGINID:
					sb.append(count == 0 ? " where" : " and").append(
							" user.orderid=? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ORGID:
					sb.append(count == 0 ? " where" : " and").append(
							" user.usereterSno like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case EMAIL:
					sb.append(count == 0 ? " where" : " and").append(
							" user.usereterSno like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case PHONE:
					sb.append(count == 0 ? " where" : " and").append(
							" user.usereterSno like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case MOBILE:
					sb.append(count == 0 ? " where" : " and").append(
							" user.usereterSno like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ADDRESS:
					sb.append(count == 0 ? " where" : " and").append(
							" user.usereterSno like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				default:
					break;
				}
			}
		if (!useCount)
			sb.append(" order by orderid  ");
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createUser(IUser user) throws ValidateFieldsException {
		MyUserImpl userImpl = (MyUserImpl) user;
		this.myUserDao.insert(userImpl.getUserVO());
	}

	public void removeUser(String moneyId) {
		String[] ids = moneyId.split(",");
		for (String s : ids) {
			MyUserVO vo = this.myUserDao.findByPrimaryKey(Integer.parseInt(s));
			this.myUserDao.delete(vo);
		}
	}

	public void updateUser(IUser user) throws ValidateFieldsException {
		MyUserImpl userImpl = (MyUserImpl) user;
		MyUserVO po = userImpl.getUserVO();
		this.myUserDao.update(po);
	}

	public IUser getUser(Integer id) {
		Collection<MyUserVO> users = this.myUserDao.findRecordById(id);

		if (users == null || users.size() < 1)
			return null;

		MyUserVO user = users.toArray(new MyUserVO[users.size()])[0];

		return new MyUserImpl(user);
	}

	public void changePwd(String loginId, String pass)
			throws ValidateFieldsException {
		this.myUserDao.updateAllPasswd(pass,loginId);
	} 
}
