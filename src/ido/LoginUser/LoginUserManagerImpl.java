
package ido.LoginUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.File;
import java.util.Map;

import common.util.NPOIReader;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.util.Coder;
import common.util.CommonUtil;
import common.util.DateTool;
import common.util.NPOIReader; 
import common.base.AllSelect;
import common.base.AllSelectContants;
import common.cache.Cache;
import common.cache.CacheManager;
import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于系统用户的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class LoginUserManagerImpl extends AbstractBusinessObjectManager implements
		LoginUserManager {

	private LoginUserDao loginuserdao = null;

	/**
	 * 构造函数.
	 */
	public LoginUserManagerImpl(LoginUserDao loginuserdao) {
		this.loginuserdao = loginuserdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchLoginUserNum(Map<LoginUserSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.loginuserdao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}


	public void importFromExcel(File file) {
		NPOIReader excel = null;
		try {
			excel = new NPOIReader(file);
			int index = excel.getSheetNames().indexOf("Sheet0");
			String[][] contents = excel.read(index, true, true);
			for (int i = 1; i < contents.length; i++) {
				LoginUserVO vo = new LoginUserVO();
				//导入用户姓名
				String userNameStr = contents[i][0];
				vo.setUserName(userNameStr);
				
				//导入联系电话
				String userPhoneStr = contents[i][1];
				vo.setUserPhone(userPhoneStr);
				
				this.loginuserdao.insert(vo); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<LoginUser> searchLoginUser(Map<LoginUserSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<LoginUser> eaList = new ArrayList<LoginUser>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<LoginUserVO> voList = this.loginuserdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_aiduyonghu = allSelect
				.getParamsByType(AllSelectContants.AIDUYONGHU.getName()); 
		ParamSelect select_yesorno_status = allSelect
				.getParamsByType(AllSelectContants.YESORNO_STATUS.getName());
		
		for (LoginUserVO po : voList) {
			po.setUserType(select_aiduyonghu.getName("" + po.getUserType())); 
			po.setUserStatus(select_yesorno_status.getName("" + po.getUserStatus())); 
			eaList.add(new  LoginUserImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<LoginUserSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( loginuser) "
						: "select  loginuser ").append("from LoginUserVO as loginuser ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<LoginUserSearchFields, Object> entry : criterias
					.entrySet()) {
				LoginUserSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERID:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERTYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERUNIT:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userUnit = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERPASS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userPass = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERSTATUS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userStatus = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERPHONE:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userPhone = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USEREMAIL:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userEmail = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERADDRESS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userAddress = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.updateUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case UPDATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.updateTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case USERNAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case USERNAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case USERID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case USERID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case USERTYPE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userType  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case USERTYPE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userType =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case USERUNIT_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userUnit  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case USERUNIT_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userUnit =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case USERPASS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userPass  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case USERPASS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userPass =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case USERSTATUS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userStatus  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case USERSTATUS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userStatus =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case USERPHONE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userPhone  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case USERPHONE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userPhone =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case USEREMAIL_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userEmail  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case USEREMAIL_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userEmail =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case USERADDRESS_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userAddress  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case USERADDRESS_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.userAddress =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.createTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.updateUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.updateUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case UPDATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.updateTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case UPDATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginuser.updateTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		LoginUserOrderByFields orderBy = LoginUserOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = LoginUserOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by loginuser.sno");
			break;
			case USERNAME:
				 sb.append(" order by loginuser.userName");
			break;
			case USERID:
				 sb.append(" order by loginuser.userId");
			break;
			case USERTYPE:
				 sb.append(" order by loginuser.userType");
			break;
			case USERUNIT:
				 sb.append(" order by loginuser.userUnit");
			break;
			case USERPASS:
				 sb.append(" order by loginuser.userPass");
			break;
			case USERSTATUS:
				 sb.append(" order by loginuser.userStatus");
			break;
			case USERPHONE:
				 sb.append(" order by loginuser.userPhone");
			break;
			case USEREMAIL:
				 sb.append(" order by loginuser.userEmail");
			break;
			case USERADDRESS:
				 sb.append(" order by loginuser.userAddress");
			break;
			case CREATEUSER:
				 sb.append(" order by loginuser.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by loginuser.createTime");
			break;
			case UPDATEUSER:
				 sb.append(" order by loginuser.updateUser");
			break;
			case UPDATETIME:
				 sb.append(" order by loginuser.updateTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createLoginUser(LoginUser loginuser) throws ValidateFieldsException {
		LoginUserImpl loginuserImpl = (LoginUserImpl) loginuser;
		this.loginuserdao.insert(loginuserImpl.getLoginUserVO());
	}

	public void removeLoginUsers(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			LoginUserVO vo = this.loginuserdao.findByPrimaryKey(Integer.parseInt(s));
			this.loginuserdao.delete(vo);
		}
	}

	public void updateLoginUser(LoginUser loginuser) throws ValidateFieldsException {
		LoginUserImpl loginuserImpl = (LoginUserImpl) loginuser;

		LoginUserVO po = loginuserImpl.getLoginUserVO();
		this.loginuserdao.update(po);
	}

	public LoginUser getLoginUser(int id) {
		Collection<LoginUserVO> loginusers = this.loginuserdao.findRecordById(id);

		if (loginusers == null || loginusers.size() < 1)
			return null;

		LoginUserVO loginuser = loginusers.toArray(new LoginUserVO[loginusers.size()])[0];

		return new LoginUserImpl(loginuser);
	}

	@Override
	public void addCache() {
		ParamSelect ans = null;
		Collection<LoginUserVO> all = this.loginuserdao.findAll();
		ans = new ParamSelect(all);
		String _tempCacheId = AllSelectContants.IDOUSER_DICT.getName();
		CacheManager.clearOnly(_tempCacheId);
		Cache c = new Cache();
		c.setKey(_tempCacheId);
		c.setValue(ans);
		c.setName("系统用户");
		CacheManager.putCache(_tempCacheId, c);
	}

	@Override
	public void initPassword(String useId) {
		String[] idArr = useId.split(",");
		String newPassword = "";
		try {
			newPassword = Coder.getMyCoder(Coder.INIT_CODER);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		for (String s : idArr) { 
			 this.loginuserdao.updateAllPasswdById(newPassword, Integer.parseInt(s));
		}
	}
}
