package dwz.framework.timer.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import dwz.framework.timer.TaskPeriod;
import dwz.framework.timer.TaskPriority;
import dwz.framework.timer.TaskUnit;

public class TaskUnitImpl implements TaskUnit {

	private String name;
	private String priority;
	private String period;
	private String delay;
	private Class<?> task;

	private Date startTime;
	private Date currentTime;
	private String runnable;

	public TaskUnitImpl(String name, String priority, String period,
			String delay, Class<?> task, String startTime, String runnable) {
		this.name = name;
		this.priority = priority;
		this.period = period;
		this.delay = delay;
		this.task = task;
		this.currentTime = new Date();
		initStartTime(startTime);
		this.runnable = runnable;
	}

	public long getDelay() {
		if (this.delay == null || "".equals(this.delay))
			return 0;

		if ("0".equals(this.delay))
			return 0;

		if (this.delay.indexOf(" ") < 0) {
			return 0;
		}

		String[] time = this.delay.split(" ");

		return Long.parseLong(time[0])
				* TaskPeriod.valueOf(time[1].toUpperCase()).getValue();
	}

	public String getName() {
		return this.name;
	}

	public long getPeriod() {
		if (this.period == null || "".equals(this.period))
			return 0;

		if ("0".equals(this.period))
			return 0;

		if (this.period.indexOf(" ") < 0) {
			return 0;
		}

		String[] time = this.period.split(" ");

		return Long.parseLong(time[0])
				* TaskPeriod.valueOf(time[1].toUpperCase()).getValue();
	}

	public int getPriority() {
		return TaskPriority.valueOf(this.priority).getValue();
	}

	public Date getStartTime() {
		return this.startTime;
	}

	private void initStartTime(String startTime) {
		if ("0".equals(startTime)) {
			this.startTime = new Date(currentTime.getTime() + this.getDelay());
			return;
		}

		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd-hh-mm-ss");
		if (startTime.indexOf("-") == 2) {
			String year = String.valueOf(Calendar.getInstance().get(
					Calendar.YEAR));
			String month = String.valueOf(Calendar.getInstance().get(
					Calendar.MONTH) + 1);
			String day = String.valueOf(Calendar.getInstance().get(
					Calendar.DAY_OF_MONTH));
			if (month.length() == 1) {
				month = "0" + month;
			}
			if (day.length() == 1) {
				day = "0" + day;
			}
			startTime = year + "-" + month + "-" + day + "-" + startTime;
		}

		try {
			this.startTime = format.parse(startTime);
			while (this.getPeriod() > 0
					&& this.startTime.getTime() < this.currentTime.getTime()) {
				if (this.startTime.getTime() < this.currentTime.getTime()) {
					this.startTime = new Date(this.startTime.getTime()
							+ this.getPeriod());
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public Runnable getTask() {
		Class<?> clazz = this.getTaskClass();
		if (clazz == null)
			return null;

		Object obj = null;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return (Runnable) obj;
	}

	public Class<?> getTaskClass() {
		return this.task;
	}

	public boolean isRunnable() {
		if(!"TRUE".equals(this.runnable.toUpperCase())) {
			return false;
		}
		if (this.getPeriod() == 0
				&& this.startTime.getTime() < this.currentTime.getTime()) {
			return false;
		}
		return true;
	}

}
