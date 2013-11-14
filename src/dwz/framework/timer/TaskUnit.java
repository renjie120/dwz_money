package dwz.framework.timer;

import java.util.Date;

public interface TaskUnit {
	
	String getName();
	
	Runnable getTask();
	
	Class<?> getTaskClass();
	
	int getPriority();
	
	Date getStartTime();
	
	long getPeriod();
	
	long getDelay();
	
	boolean isRunnable();

}
