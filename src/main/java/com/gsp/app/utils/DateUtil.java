package com.gsp.app.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author dell
 */
public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	public static final String Format_Date = "yyyy-MM-dd";

	public static final String Format_Time = "HH:mm:ss";

	public static final String Format_DateTime = "yyyy-MM-dd HH:mm:ss";

	public static final SimpleDateFormat sdfd = new SimpleDateFormat(Format_Date);//NOSONAR

	public static final SimpleDateFormat sdft = new SimpleDateFormat(Format_Time);//NOSONAR

	public static final SimpleDateFormat sdfdt = new SimpleDateFormat(Format_DateTime);//NOSONAR
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//NOSONAR
	/**
	 * 该类中均为静态方法，直接DateUtil.方法名即可调用
	 */
	/**
	 * 得到以yyyy-MM-dd格式表示的当前日期字符串
	 */
	public static String getCurrentDate() {
		return sdfd.format(new Date());
	}
	
	/**
	 * 得到以yyyy-MM-dd格式表示的前一天日期字符串
	 * @param format
	 * @return
	 */
	public static String getYesterDay() {
		Date d = new Date();
		return sdfd.format(new Date(d.getTime() - 1 * 24 * 60 * 60 * 1000));
	}
	
	/**
	 * 得到以yyyy-MM-dd格式
	 * @param format
	 * @return
	 */
	public static String getDay(Date date) {
		return sdfd.format(date);
	}
	
	/**
	 * 得到以yyyy-MM-dd格式
	 * @param format
	 * @return
	 */
	public static String getDay(Object date) {
		return sdfd.format(Long.parseLong(String.valueOf(date)));
	}
	
	/**
	 * 得到以yyyyMMdd格式
	 * @param format
	 * @return
	 */
	public static String getRefundDay(Date date) {
		return sdf.format(date);
	}

	/**
	 * 得到以format格式表示的当前日期字符串
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 得到以HH:mm:ss表示的当前时间字符串
	 */
	public static String getCurrentTime() {
		return sdft.format(new Date());
	}

	/**
	 * 得到以format格式表示的当前时间字符串
	 */
	public static String getCurrentTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 得到以yyyy-MM-dd HH:mm:ss表示的当前时间字符串
	 */
	public static String getCurrentDateTime() {
		String format = DateUtil.Format_Date + " " + DateUtil.Format_Time;
		return getCurrentDateTime(format);
	}

	public static int getDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	// 获取某一个月的天数
	public static int getMaxDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DATE);
	}

	// 获取某月的第一天
	public static String getFirstDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return sdfd.format(cal.getTime());
	}

	public static int getDayOfYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	public static int getDayOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	public static int getDayOfWeek(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date));
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date));
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfYear(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date));
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	public static String getCurrentDateTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 输出只带日期的字符串
	 */
	public static String toString(Date date) {
		if (date == null) {
			return "";
		}
		return sdfd.format(date);
	}

	/**
	 * 输出带有日期和时间的字符串
	 */
	public static String toDateTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return sdfdt.format(date);
	}

	/**
	 * 按指定的format输出日期字符串
	 */
	public static String toString(Date date, String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(date);
	}

	/**
	 * 输出只带时间的字符串
	 */
	public static String toTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return sdft.format(date);
	}
	
	/**
	 * 两个时间相减得到相差月数
	 * */
	public static Long compareMonth(Date date1, Date date2) {
		Long x= Math.abs(date1.getTime()-date2.getTime());
		return x/1000/60/60/24/30;
	}
	
	/**
	 * 根据月数获取年，精确小数点两位
	 * */
	public static Double compareYear(Date date1, Date date2) {
		DecimalFormat df = new DecimalFormat("######0.00");
		Long year = compareMonth(date1,date2);
		Double x = year/12.0;
		return Double.parseDouble(df.format(x));
	}
	
	/**
	 * 两个时间相减得到相差小时数
	 * */
	public static Long compareTime(Date date1, Date date2) {
		Long x= Math.abs(date1.getTime()-date2.getTime());
		return x/1000/60/60;
	}
	
	/**
	 * 两个时间相减得到相差分钟数
	 * */
	public static Long compareMinute(Date date1, Date date2) {
		Long x= Math.abs(date1.getTime()-date2.getTime());
		return x/1000/60;
	}

	public static int compare(String date1, String date2) {
		return compare(date1, date2, Format_Date);
	}

	public static int compareTime(String time1, String time2) {
		return compareTime(time1, time2, Format_Time);
	}

	public static int compare(String date1, String date2, String format) {
		Date d1 = parse(date1);
		Date d2 = parse(date2);
		return d1.compareTo(d2);
	}

	public static int compareTime(String time1, String time2, String format) {
		String[] arr1 = time1.split(":");
		String[] arr2 = time2.split(":");
		if (arr1.length < 2) {
			throw new RuntimeException("错误的时间值:" + time1);
		}
		if (arr2.length < 2) {
			throw new RuntimeException("错误的时间值:" + time2);
		}
		int h1 = Integer.parseInt(arr1[0]);
		int m1 = Integer.parseInt(arr1[1]);
		int h2 = Integer.parseInt(arr2[0]);
		int m2 = Integer.parseInt(arr2[1]);
		int s1 = 0, s2 = 0;
		if (arr1.length == 3) {
			s1 = Integer.parseInt(arr1[2]);
		}
		if (arr2.length == 3) {
			s2 = Integer.parseInt(arr2[2]);
		}
		if (h1 < 0 || h1 > 23 || m1 < 0 || m1 > 59 || s1 < 0 || s1 > 59) {
			throw new RuntimeException("错误的时间值:" + time1);
		}
		if (h2 < 0 || h2 > 23 || m2 < 0 || m2 > 59 || s2 < 0 || s2 > 59) {
			throw new RuntimeException("错误的时间值:" + time2);
		}
		if (h1 != h2) {
			return h1 > h2 ? 1 : -1;
		} else {
			if (m1 == m2) {
				if (s1 == s2) {
					return 0;
				} else {
					return s1 > s2 ? 1 : -1;
				}
			} else {
				return m1 > m2 ? 1 : -1;
			}
		}
	}

	public static boolean isTime(String time) {
		String[] arr = time.split(":");
		if (arr.length < 2) {
			return false;
		}
		try {
			int h = Integer.parseInt(arr[0]);
			int m = Integer.parseInt(arr[1]);
			int s = 0;
			if (arr.length == 3) {
				s = Integer.parseInt(arr[2]);
			}
			if (h < 0 || h > 23 || m < 0 || m > 59 || s < 0 || s > 59) {
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
		return true;
	}

	public static boolean isDate(String date) {
		String[] arr = date.split("-");
		if (arr.length < 3) {
			return false;
		}
		try {
			int y = Integer.parseInt(arr[0]);
			int m = Integer.parseInt(arr[1]);
			int d = Integer.parseInt(arr[2]);
			if (y < 0 || m > 12 || m < 0 || d < 0 || d > 31) {
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
		return true;
	}

	public static boolean isWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int t = cal.get(Calendar.DAY_OF_WEEK);
		if (t == Calendar.SATURDAY || t == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}

	public static boolean isWeekend(String str) {
		return isWeekend(parse(str));
	}

	public static Date parse(String str) {
		try {
			return sdfd.parse(str);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	public static Date parse(String str, String format) {
		try {
			SimpleDateFormat t = new SimpleDateFormat(format);
			return t.parse(str);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	public static Date parseDateTime(String str) {
		if (str.length() == 10) {
			return parse(str);
		}
		try {
			return sdfdt.parse(str);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	public static Date parseDateTime(String str, String format) {
		try {
			SimpleDateFormat t = new SimpleDateFormat(format);
			return t.parse(str);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	/**
	 * 日期date上加count分钟，count为负表示减
	 */
	public static Date addMinute(Date date, int count) {
		return new Date(date.getTime() + 60000L * count);
	}

	/**
	 * 日期date上加count小时，count为负表示减
	 */
	public static Date addHour(Date date, int count) {
		return new Date(date.getTime() + 3600000L * count);
	}

	/**
	 * 日期date上加count天，count为负表示减
	 */
	public static Date addDay(Date date, int count) {
		return new Date(date.getTime() + 86400000L * count);
	}

	/**
	 * 日期date上加count星期，count为负表示减
	 */
	public static Date addWeek(Date date, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.WEEK_OF_YEAR, count);
		return c.getTime();
	}

	/**
	 * 日期date上加count月，count为负表示减
	 */
	public static Date addMonth(Date date, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, count);
		return c.getTime();
	}

	/**
	 * 日期date上加count年，count为负表示减
	 */
	public static Date addYear(Date date, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, count);
		return c.getTime();
	}
	/**
	 * 获得当天0点时间	
	 */
	public static Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();	
		cal.set(Calendar.HOUR_OF_DAY, 0);	
		cal.set(Calendar.SECOND, 0);	
		cal.set(Calendar.MINUTE, 0);	
		cal.set(Calendar.MILLISECOND, 0);	
		return cal.getTime();
	}
	
	/**
	 * 返回本周第一天的日期
	 */
	public static Date getTimesWeekmorning() {
		// 获得本周一0点时间	
		Calendar cal = Calendar.getInstance();		
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), 
		cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);		
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);		
		return cal.getTime();
	}
	
	/**
	 * 本月第一天0点时间	
	 */
	public static Date getTimesMonthmorning() {		
		Calendar cal = Calendar.getInstance();		
		cal.set(cal.get(Calendar.YEAR), 
		cal.get(Calendar.MONDAY), 
		cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);		
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));		
		return cal.getTime();	
		}
	
/*	public static void main(String args []){
		Date date1 = new Date();
		
		
		Date date2 = new Date();
		date2 = addMinute(date2,2);
		
		System.out.println(compareMinute(date1, date2));
	}*/
	
}
