package dwz.framework.http.session.mdb.mysqldb.schedule;

public interface Session extends Runnable {

	public int getTimeout();
	
	public boolean isAutoEnable();

	public int getAutoInterval();

}
