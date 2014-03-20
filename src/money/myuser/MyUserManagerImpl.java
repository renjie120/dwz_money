package money.myuser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.util.Coder;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于用户信息表的业务操作实现类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class MyUserManagerImpl extends AbstractBusinessObjectManager implements
		MyUserManager {

	private MyUserDao myuserdao = null;

	/**
	 * 构造函数.
	 */
	public MyUserManagerImpl(MyUserDao myuserdao) {
		this.myuserdao = myuserdao;
	}

	/**
	 * 查询总数.
	 * 
	 * @param criterias
	 *            查询条件
	 * @return
	 */
	public Integer searchMyUserNum(Map<MyUserSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.myuserdao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	/**
	 * 根据条件查询分页信息.
	 * 
	 * @param criterias
	 *            条件
	 * @param orderField
	 *            排序列
	 * @param startIndex
	 *            开始索引
	 * @param count
	 *            总数
	 * @return
	 */
	public Collection<MyUser> searchMyUser(
			Map<MyUserSearchFields, Object> criterias, String orderField,
			int startIndex, int count) {
		ArrayList<MyUser> eaList = new ArrayList<MyUser>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<MyUserVO> voList = this.myuserdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select1 = allSelect
				.getParamsByType(AllSelectContants.USER_TYPE.getName()); 
		if (voList == null || voList.size() == 0)
			return eaList; 
		for (MyUserVO po : voList) {
			po.setUserTypeName(select1.getName("" + po.getUserType()));
			eaList.add(new MyUserImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<MyUserSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( myuser) "
						: "select new MyUserVO(myuser.useId , myuser.userName , myuser.password , myuser.loginId , myuser.orgId , myuser.email , "
								+ "myuser.phone , myuser.mobile , myuser.userType , myuser.address , myuser.orderId ,orgVo.orgName)  ")
				.append("from MyUserVO as myuser,OrgVO as orgVo ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<MyUserSearchFields, Object> entry : criterias
					.entrySet()) {
				MyUserSearchFields fd = entry.getKey();
				switch (fd) {
				case USEID:
					sb.append(count == 0 ? " where" : " and").append(
							"  myuser.useId = ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case USERNAME:
					sb.append(count == 0 ? " where" : " and").append(
							"  myuser.userName like ? ");
					argList.add("%" + entry.getValue() + "%");
					count++;
					break;
				case LOGINID:
					sb.append(count == 0 ? " where" : " and").append(
							"  myuser.loginId like ? ");
					argList.add("%" + entry.getValue() + "%");
					count++;
					break;
				case ORGID:
					sb.append(count == 0 ? " where" : " and").append(
							"  myuser.orgId = ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case EMAIL:
					sb.append(count == 0 ? " where" : " and").append(
							"  myuser.email like ? ");
					argList.add("%" + entry.getValue() + "%");
					count++;
					break;
				case PHONE:
					sb.append(count == 0 ? " where" : " and").append(
							"  myuser.phone like ? ");
					argList.add("%" + entry.getValue() + "%");
					count++;
					break;
				case USERTYPE:
					sb.append(count == 0 ? " where" : " and").append(
							"  myuser.userType like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ADDRESS:
					sb.append(count == 0 ? " where" : " and").append(
							"  myuser.address like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ORDERID:
					sb.append(count == 0 ? " where" : " and").append(
							"  myuser.orderId = ? ");
					argList.add(entry.getValue());
					count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		} else {
			sb.append(count == 0 ? " where " : " and").append(
					"  myuser.orgId=orgVo.orgId ");
		}

		MyUserOrderByFields orderBy = MyUserOrderByFields.USEID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = MyUserOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case USEID:
			sb.append(" order by myuser.useId");
			break;
		case USERNAME:
			sb.append(" order by myuser.userName");
			break;
		case PASSWORD:
			sb.append(" order by myuser.password");
			break;
		case LOGINID:
			sb.append(" order by myuser.loginId");
			break;
		case ORGID:
			sb.append(" order by myuser.orgId");
			break;
		case EMAIL:
			sb.append(" order by myuser.email");
			break;
		case PHONE:
			sb.append(" order by myuser.phone");
			break;
		case MOBILE:
			sb.append(" order by myuser.mobile");
			break;
		case USERTYPE:
			sb.append(" order by myuser.userType");
			break;
		case ADDRESS:
			sb.append(" order by myuser.address");
			break;
		case ORDERID:
			sb.append(" order by myuser.orderId");
			break;
		default:
			break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createMyUser(MyUser myuser) throws ValidateFieldsException {
		MyUserImpl myuserImpl = (MyUserImpl) myuser;
		this.myuserdao.insert(myuserImpl.getMyUserVO());
	}

	public void removeMyUsers(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			MyUserVO vo = this.myuserdao.findByPrimaryKey(Integer.parseInt(s));
			this.myuserdao.delete(vo);
		}
	}
	
	public void initPassword(String ids) {
		String[] idArr = ids.split(",");
		String newPassword = "";
		try {
			newPassword = Coder.toMyCoder("111111");
		} catch (Exception e) { 
			e.printStackTrace();
		}
		for (String s : idArr) { 
			 this.myuserdao.updateAllPasswdById(newPassword, Integer.parseInt(s));
		}
	}

	public void updateMyUser(MyUser myuser) throws ValidateFieldsException {
		MyUserImpl myuserImpl = (MyUserImpl) myuser;

		MyUserVO po = myuserImpl.getMyUserVO();
		this.myuserdao.update(po);
	}

	public MyUser getMyUser(int id) {
		Collection<MyUserVO> myusers = this.myuserdao.findRecordById(id);

		if (myusers == null || myusers.size() < 1)
			return null;

		MyUserVO myuser = myusers.toArray(new MyUserVO[myusers.size()])[0];

		return new MyUserImpl(myuser);
	}

	public MyUser getSimpleMyUser(int id) {
		Collection<MyUserVO> myusers = this.myuserdao.findRecordById2(id);

		if (myusers == null || myusers.size() < 1)
			return null;

		MyUserVO myuser = myusers.toArray(new MyUserVO[myusers.size()])[0];

		return new MyUserImpl(myuser);
	}

	@Override
	public void updatePassword(String pass, String userId) {
		this.myuserdao.updateAllPasswd(pass, userId);
	}

}
