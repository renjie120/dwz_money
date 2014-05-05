package dwz.present.app.management;

import money.homepage.HomePageUrlManager;
import money.role.UserMenuRightManager;
import money.tree.TreeManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import common.tree.Tree;

import dwz.constants.BeanManagerKey;
import dwz.framework.constants.Constants;
import dwz.framework.constants.user.UserType;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.el.ServerInfo;
import dwz.framework.user.User;
import dwz.framework.user.UserManager;
import dwz.framework.user.impl.UserImpl;
import dwz.present.BaseAction;
import dwz.present.UiModel;

/**
 * 登陆
* @ClassName: IndexAction
* @Description: TODO(这里用一句话描述这个类的作用) 
* @date 2014-3-19 上午09:12:21
*
 */
public class IndexAction extends BaseAction implements ModelDriven<UiModel> {
	private UiModel model;
	private String password;
	private String newPassword;
	private TreeManager tMgr = bf.getManager(BeanManagerKey.treeManager);
	private HomePageUrlManager pMgr = bf
			.getManager(BeanManagerKey.homepageurlManager);
	private UserMenuRightManager userMenuMgr = bf
			.getManager(BeanManagerKey.usermenurightManager);

	public String index() {
		UiModel model = new UiModel();
		setModel(model);
		TreeManager tMgr = bf.getManager(BeanManagerKey.treeManager);
		UserImpl user = (UserImpl) ActionContext.getContext().getSession()
				.get(Constants.AUTHENTICATION_KEY); 
		UserType tp = user.getUserType(); 
		// 得到菜单树
		Tree t = tMgr.initMenuWithRight(user.getUserId(),user.getUserType());
		request.setAttribute("allMenu", t.getDeepTree());
		request.setAttribute("allHomepage", pMgr.searchAllHomePageUrl());
		return INPUT;
	}

	public String login() {
		if (ServerInfo.isAjax(request)) {
			return "loginDialog";
		}
		return "login2";
	}

	public String updPwd() {
		UserManager uMgr = bf.getManager(BeanManagerKey.userManager);
		User user = getAppContext().getUser();
		user.setPassword(newPassword);
		if (!password.equals(user.getPassword())) {
			return ajaxForwardError("The old password is incorrect!");
		}
		try {
			uMgr.updateUser(user);
		} catch (ValidateFieldsException e) {
			return ajaxForwardError(e.getLocalizedMessage());
		}
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public UiModel getModel() {
		return model;
	}

	public void setModel(UiModel model) {
		this.model = model;
	}
}
