package common.struts2;

public class JavaBeanTest {
	private String user;
	private String pass;

	public JavaBeanTest(){
		
	}
	
	public JavaBeanTest(String user,String pass){
		this.user = user;
		this.pass = pass;
	}
	
	 
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
