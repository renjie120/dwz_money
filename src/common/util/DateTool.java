package common.util; 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTool {
	/**
	 * 返回日期格式.默认是'yyyy-MM-dd'.
	 * 
	 * @param date
	 * @return
	 */
	public static String toString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 按照"yyyy-MM-dd"返回日期
	 * @param dateStr
	 * @return
	 */
	public static Date getDate(String dateStr) {
		return getDate(dateStr,"yyyy-MM-dd");
	}
	
	/**
	 * 根据制定格式返回日期对象.
	 * @param dateStr
	 * @param formateStr
	 * @return
	 */
	public static Date getDate(String dateStr, String formateStr) {
		SimpleDateFormat formatter2 = new SimpleDateFormat(formateStr);
		Date date = new Date();
		try {
			date = formatter2.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 返回指定日期格式的字符串
	 * 
	 * @param formate
	 * @return
	 */
	public static String toString(Date date, String formate) {
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		return sdf.format(date);
	}

	/**
	 * 得到指定年份,月份,第几天的date对象.
	 * 
	 * @param year
	 * @param month
	 *            (1到12之间整数)
	 * @param day
	 * @return
	 */
	public static Date getDate(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return calendar.getTime();
	}

	/**
	 * 得到日期所对应的年.
	 * 
	 * @return
	 */
	public static int getYear(Date date) {
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTime(date);
		return ca.get(Calendar.YEAR);
	}

	/**
	 * 得到月份.
	 * 
	 * @return
	 */
	public static int getMonth(Date date) {
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTime(date);
		return ca.get(Calendar.MONTH);
	}

	/**
	 * 得到一年的第几天.
	 * 
	 * @return
	 */
	public static int getDayOfYear(Date date) {
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTime(date);
		return ca.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 得到一月里面的第几天.
	 * 
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTime(date);
		return ca.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到周几.
	 * 
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTime(date);
		return ca.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 得到某年中某星期的指定星期几.
	 * 
	 * @param year
	 * @param week
	 * @param day
	 *            为1到7之间整数:1-sun-->7-sta
	 * @return
	 */
	public static Date getDayInThisWeek(int year, int week, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, day);
		return cal.getTime();
	}

	/**
	 * 得到日期是在一年的第几个星期.
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTime(date);
		return ca.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 得到两个日期的时间间隔
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getTimeInterval(Date date1, Date date2) {
		return date1.getTime() - date2.getTime();
	}

	/**
	 * 得到两个日期的间隔天数.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDayInterval(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / ONEDAYMILLISECONDS);
	}

	/**
	 * 得到几天以后的某一天.
	 * 
	 * @param days
	 *            可以为负数
	 * @return
	 */
	public static Date afterAnyDay(Date date, int days) {
		if(date==null)
			return null;
		Date newDate = new Date();
		long tp = date.getTime();
		tp = tp + days * ONEDAYMILLISECONDS;
		newDate.setTime(tp);
		return newDate;
	}

	/**
	 * 得到起始日期之后的某年(某月)(某日)的新日期
	 * 
	 * @param date
	 *            起始日期
	 * @param years
	 *            年份间隔
	 * @param months
	 *            月份间隔
	 * @param days
	 *            天数间隔
	 * @return
	 */
	public static Date afterAnyDay(Date date, int years, int months, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		cal.add(Calendar.MONTH, months);
		cal.add(Calendar.DATE, months);
		return cal.getTime();
	}

	/**
	 * 返回某一年的某月的天数.
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int maxDayInMonth(int year, int month) {
		Calendar time = Calendar.getInstance();
		// 必须先clear一下,因为默认会得到当前系统时间
		time.clear();
		time.set(Calendar.YEAR, year);
		time.set(Calendar.MONTH, month - 1);
		return time.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回一年里面某一周的开始日期.
	 * @param year
	 * @param week
	 * @return
	 * @throws ParseException
	 */
	public static Date getWeekStart(int year,int week) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy w");
		Date start = null;
		try {
			start = sdf.parse(year+" "+week);
		} catch (ParseException e) { 
			e.printStackTrace();
		}
		return start;
	}
	
	/**
	 * 返回一年里面某一周的结束的日期.
	 * @param year
	 * @param week
	 * @return
	 * @throws ParseException
	 */
	public static Date getWeekEnd(int year,int week){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy w");
		Date end = null;
		try {
			end = sdf.parse(year+" "+week);
		} catch (ParseException e) { 
			e.printStackTrace();
		}
		return afterAnyDay(end,6);
	}
	
	/**
	 * 返回现在.
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date();
	}
	
	/**
	 * 返回今天的日期
	 * @return
	 */
	public static String nowDateStr() {
		return toString(new Date());
	}

	/**
	 * 将日期格式转换为日历.
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * 将日历转换为日期.
	 * 
	 * @param cal
	 * @return
	 */
	public static Date calendarToDate(Calendar cal) {
		return cal.getTime();
	}

	public static final long ONEDAYSECONDS = 86400;
	public static final long ONEDAYMILLISECONDS = 86400000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date now = DateTool.now();
		System.out.println("今天日期是:" + DateTool.toString(now)); 
		
		System.out.println("现在是:"
				+ DateTool.toString(now, "yyyy-MM-dd HH:mm:ss"));
		System.out.println("今天星期是:" + DateTool.toString(now, "E") + ",是本周的第"
				+ DateTool.getDayOfWeek(now) + "天!");
		System.out.println("40天之后是:"
				+ DateTool.toString(DateTool.afterAnyDay(now, 40)));
		Date endDate = DateTool.getDate(2011, 7, 15);
		System.out.println("距离还有:" + DateTool.getDayInterval(now, endDate)
				+ "天到20100715");
		
		String yearNum = DateTool.toString(now, "yyyy");
		String weekNum = DateTool.toString(now, "w");
		System.out.println("今天是:一年的第几个星期::" + weekNum);
	 	System.out.println("本周的第一天是"+DateTool.toString(getWeekStart(Integer.parseInt(yearNum),Integer.parseInt(weekNum))));
		System.out.println("本周的最后一天是"+DateTool.toString(getWeekEnd(Integer.parseInt(yearNum),Integer.parseInt(weekNum)))); 
	}
}