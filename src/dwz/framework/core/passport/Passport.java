package dwz.framework.core.passport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import money.myuser.MyUser;
import money.myuser.MyUserManager;

import common.util.Coder;

import dwz.constants.BeanManagerKey;
import dwz.framework.constants.Constants;
import dwz.framework.core.exception.AuthenticationException;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.http.wrapper.AppHttpRequestWrapper;
import dwz.framework.user.impl.UserImpl;
import dwz.persistence.beans.SysUser;

public abstract class Passport {

	private static Passport passport = null;

	private static final Object lock = new Object();

	public static Passport getPassport(HttpServletRequest request) {
		if (passport == null) {
			synchronized (lock) {
				if (passport == null) {
					String passportClass = Constants.STANDARD_PASSPORT_CLASS; 

					Class<?> clazz = null;
					try {
						clazz = Class.forName(passportClass);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					try {
						passport = (Passport) clazz.newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return passport;
	}

	public abstract void login(HttpServletRequest request, String appUserName,
			String appPassword, String appUserType)
			throws AuthenticationException;

	public abstract void login(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException;

	public abstract void logout(HttpServletRequest request,
			HttpServletResponse response);
;
	public static void setSessionUser(HttpServletRequest request, int userId) {
		HttpSession session = request.getSession();
		if (request instanceof AppHttpRequestWrapper) {
			session.setAttribute(Constants.AUTHENTICATION_KEY, userId);
		} else {
			MyUserManager uMgr = BusinessFactory.getFactory().getManager(
					BeanManagerKey.myuserManager);
			MyUser  user = uMgr.getSimpleMyUser(userId);  
			session.setAttribute(Constants.AUTHENTICATION_KEY, new UserImpl(convertUser(user))); 
		}
	}
	
	/**
	 * 将我的用户数据转换为dwz原来的用户接口.
	 * 以后代码也要这样做，使用它的用户接口，可以借鉴他的思想.
	 * @param myUser
	 * @return
	 */
	private static SysUser convertUser(MyUser myUser){
		SysUser ans = new SysUser();
		ans.setUserName(myUser.getUserName());
		ans.setId(myUser.getLoginId());
		ans.setUserId(myUser.getUseId()+""); 
		ans.setPassword(Coder.fromMyCoder(myUser.getPassword()));
		ans.setPhone(myUser.getPhone());
		ans.setEmail(myUser.getEmail());  
		ans.setUserType(myUser.getUserType());
		ans.setUserName(myUser.getUserName());
		return ans;
	} 
}