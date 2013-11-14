package dwz.framework.timer;

import java.util.Collection;

public abstract class TaskFactory {
	
	private static final String className = "dwz.framework.timer.impl.DefaultTaskFactory";
	
	private static TaskFactory factory = null;
	
	private static Object lock = new Object();
	
	public static TaskFactory getFactory() {
		if(factory == null) {
			synchronized(lock) {
				if(factory == null) {
					try {
						Class<?> clazz = Class.forName(className);
						factory = (TaskFactory) clazz.newInstance();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return factory;
	}
	
	public abstract void initTasks(String filePath);
	
	public abstract Runnable getTask(String name);
	
	public abstract void startAllTasks();
	
	public abstract void startTasks(Collection<TaskUnit> units);	
	
	public abstract TaskParse getTaskParse();

}
