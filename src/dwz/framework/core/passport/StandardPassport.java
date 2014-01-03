package dwz.framework.core.passport;

import java.util.Collection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import money.myuser.MyUserDao;
import money.myuser.MyUserVO;

import common.util.Coder;

import dwz.constants.BeanDaoKey;
import dwz.framework.constants.Constants;
import dwz.framework.core.exception.AuthenticationException;
import dwz.framework.core.factory.BusinessFactory;

public class StandardPassport extends Passport {
	public void login(HttpServletRequest request, String appUserName,
			String appPassword, String appUserType) throws AuthenticationException{ 
		
		Collection<MyUserVO> ids = null;
		MyUserDao userDao = BusinessFactory.getFactory().getDao(BeanDaoKey.myuserDao);
		
		ids = userDao.findByLoginId2(appUserName); 
		if (ids == null || ids.size() < 1) {
			System.out.println("login failed: " + appUserType + ": "
					+ appUserName);
			throw new AuthenticationException("msg.login.failure1");
		} 
		MyUserVO userVo = ids.iterator().next();
		String pass = userVo.getPassword();  
		if(!Coder.fromMyCoder(pass).equals(appPassword)){
			
			System.out.println("login failed: " + appUserType + ": "
					+ appUserName);
			throw new AuthenticationException("msg.login.failure2");
		} 
		setSessionUser(request, userVo.getUseId());
		
	}

	@Override
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String appUserName = request.getParameter(Constants.PASSPORT_USERNAME);
		String appPassword = request.getParameter(Constants.PASSPORT_PASSWORD);
		String appUserType = request.getParameter(Constants.PASSPORT_USER_TYPE);

		login(request, appUserName, appPassword, appUserType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		// String sid = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constants.AUTHENTICATION_TOKEN.equals(cookie.getName())) {
					// sid = cookie.getValue();
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
		}

		HttpSession session = request.getSession();
		// session.invalidate();
		session.removeAttribute(Constants.AUTHENTICATION_KEY);
	}

}
