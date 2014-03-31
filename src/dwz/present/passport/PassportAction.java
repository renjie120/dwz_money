package dwz.present.passport;

import java.io.UnsupportedEncodingException;

import dwz.framework.core.exception.AuthenticationException;
import dwz.framework.core.passport.Passport;
import dwz.framework.el.ServerInfo;
import dwz.present.BaseAction;

public class PassportAction extends BaseAction {

	private String backToUrl;

	public String login() {
		Passport passport = Passport.getPassport(request);
		boolean loginOk = false;

		try {
			if (isSkipVC() || this.verifyValidationCode(getValidationCode())) {
				passport.login(request, response);
				loginOk = true;
			} else {
				setStatusCode(300);
				setMessage(this.getText("msg.validation.code.match"));
			}

		} catch (AuthenticationException e) {
			setStatusCode(300);
			setMessage(e.getLocalizedMessage());
		}

		if (backToUrl == null || backToUrl.trim().length() == 0) {
			backToUrl = "/management/index!index.do";
		} else {
			try {
				backToUrl = java.net.URLDecoder.decode(backToUrl, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} 

		if (ServerInfo.isAjax(request)) {
			return OPERATION_DONE;
		}
		
		if (loginOk) {
			return SUCCESS;
		}

		return INPUT;
	}
	
	public String login2() { 
		return "login2";
	}

	public String logout() {
		Passport passport = Passport.getPassport(request);

		passport.logout(request, response);

		if (backToUrl == null) {
			backToUrl = "/management/index!login.do";
		}
		request.getSession(false);
//	  	String chk="false";
//	  	session.putValue("Enter",chk);
//        response.setHeader("refresh","0;URL=/management/index!login.do");
        return "logout";
	}

	public String getBackToUrl() {
		return backToUrl;
	}

	public void setBackToUrl(String backToUrl) {
		this.backToUrl = backToUrl;
	}

}
