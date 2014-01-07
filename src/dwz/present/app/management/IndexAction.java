package dwz.present.app.management;

import money.tree.TreeManager;

import com.opensymphony.xwork2.ModelDriven;
import common.tree.Tree;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.el.ServerInfo;
import dwz.framework.user.User;
import dwz.framework.user.UserManager;
import dwz.present.BaseAction;
import dwz.present.UiModel;

public class IndexAction extends BaseAction implements ModelDriven<UiModel>   {
	private UiModel model;
	private String password;
	private String newPassword;
	private TreeManager tMgr = bf.getManager(BeanManagerKey.treeManager);
	public String index() {
		UiModel model = new UiModel(); 
		setModel(model);
		TreeManager tMgr = bf.getManager(BeanManagerKey.treeManager);
		//得到菜单树
		Tree t = tMgr.initMenuCache();
		request.setAttribute("allMenu", t.getDeepTree());
		return INPUT;
	}
	
	public String login() {
		if (ServerInfo.isAjax(request)){
			return "loginDialog";
		}
		return INPUT;
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
