package dwz.framework.timer;

public enum TaskPeriod {
	SECOND(1000L),
	MINUTE(1000L * 60),
	HOUR(1000L * 60 * 60),
	DAY(1000L * 60 * 60 * 24),
	WEEK(1000L * 60 * 60 * 24 * 7),
	MONTH(1000L * 60 * 60 * 24 * 30),
	YEAR(1000L * 60 * 60 * 24 * 365);
	
	private long value;
	
	TaskPeriod(long value) {
		this.value = value;
	}
	
	public long getValue() {
		return this.value;
	}
}
