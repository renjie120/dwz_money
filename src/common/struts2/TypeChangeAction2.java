package common.struts2;

import java.util.Date;

import com.opensymphony.xwork2.Action;
import common.util.DateUtil;
import dwz.present.BaseAction;
 

/**
 * 测试struts2的类型转换的类.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class TypeChangeAction2 extends BaseAction{
	private User[] user;
	private Date birth; 
	 

	public User[] getUser() {
		return user;
	}

	public void setUser(User[] user) {
		this.user = user;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String saveUser() throws Exception{
		StringBuffer result = new StringBuffer(50);
		result.append("[");
		for (int i = 0; i < user.length; i++) {
			User u = user[i];
			result.append("username:" + u.getUserId() + ",pass="
					+ u.getPass());
			if (i < user.length - 1) {
				result.append(",");
			}
		}
		throw new Exception("123");
		//System.out.println(result.toString());
		//return "success";
	}
	
	public String initUser() throws Exception{
		System.out.println("初始化用户界面的方法....");
		return "init";
	}

	public String execute() throws Exception { 
		return INPUT;
	}
}
