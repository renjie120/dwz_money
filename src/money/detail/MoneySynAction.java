package money.detail;

import dwz.constants.BeanManagerKey;
import dwz.present.BaseAction;

/**
 * 处理同步信息.
 * @author lsq
 * 
 */
public class MoneySynAction extends BaseAction {
	private String data;
	private String method;
	private String arg;
	MoneyManager mMgr = bf.getManager(BeanManagerKey.moneyManager);
 
	/**
	 * 处理同步信息.
	 * 
	 * @return
	 */
	public String doAdd() {
		writeToPage(response, mMgr.syn(method, arg,data));
		return null;
	}
 

	public String getArg() {
		return arg;
	}


	public void setArg(String arg) {
		this.arg = arg;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
