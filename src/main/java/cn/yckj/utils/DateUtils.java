package cn.yckj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.time.DateTimeException;
import java.util.Date;
import java.util.regex.Pattern;



public class DateUtils {

	public static final String DAFAULT_DATE_FORMAT = "yyyy/MM/dd";
	public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
	public static final String TIME_FORMAT_SEC = "HH:mm:ss";
	public static final String DATE_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_MSEC = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATE_FORMAT_MSEC_T = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String DATE_FORMAT_MSEC_T_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String DATE_FORMAT_DAY_SIMPLE = "y/M/d";
	public static final String DATE_FORMAT_LONG_CN = "yyyy年MM月dd日";
	/*** 匹配yyyy/MM/dd */
	//private static final String DAFAULT_DATE_REG = "^[1-9]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])$";
	private static final String DAFAULT_DATE_REG = "^[1-9]\\d{3}/(0{0,1}[1-9]|1[0-2])/(0{0,1}[1-9]|[1-2][0-9]|3[0-1])$";
	/*** 匹配yyyy-MM-dd */
	//private static final String DATE_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
	private static final String DATE_REG = "^[1-9]\\d{3}-(0{0,1}[1-9]|1[0-2])-(0{0,1}[1-9]|[1-2][0-9]|3[0-1])$";
	/** * 匹配HH:mm:ss */
	private static final String TIME_SEC_REG = "^(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";
	/*** 匹配yyyy-MM-dd HH:mm:ss */
	private static final String DATE_TIME_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s"
			+ "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";
	/*** 匹配yyyy-MM-dd HH:mm:ss.SSS */
	private static final String DATE_TIME_MSEC_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s"
			+ "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}$";
	/*** 匹配yyyy-MM-dd'T'HH:mm:ss.SSS */
	private static final String DATE_TIME_MSEC_T_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T"
			+ "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}$";
	/** * 匹配yyyy-MM-dd'T'HH:mm:ss.SSS'Z' */
	//private static final String DATE_TIME_MSEC_T_Z_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T"
	private static final String DATE_TIME_MSEC_T_Z_REG = "^[1-9]\\d{3}-(0{0,1}[1-9]|1[0-2])-(0{0,1}[1-9]|[1-2][0-9]|3[0-1])T"
			+ "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}Z$";
	/*** 匹配y/M/d */
	private static final String DATE_SIMPLE_REG = "^[1-9]\\d{3}/([1-9]|1[0-2])/([1-9]|[1-2][0-9]|3[0-1])$";
	/*** 匹配yyyy年MM月dd日 */
	//private static final String DATE_LONG_CN_REG = "^[1-9]\\d{3}年(0[1-9]|1[0-2])月(0[1-9]|[1-2][0-9]|3[0-1])日$";
	private static final String DATE_LONG_CN_REG = "^[1-9]\\d{3}年(0{0,1}[1-9]|1[0-2])月(0{0,1}[1-9]|[1-2][0-9]|3[0-1])日$";
	/**
	 * 将日期数据转化为指定格式的字符串
	 * 
	 * @param date--转换的日期数据
	 * @param format--转换的格式
	 */
	public static String date2Str(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 采用 DAFAULT_DATE_FORMAT默认字符串转换格式 yyyy/MM/dd
	 */
	public static String date2Str(Date date) {
		return date2Str(date, DAFAULT_DATE_FORMAT);
	}

	/** 采用指定解析模式将日期字符串转换为日期 
	 * @throws Exception */
	public static Date str2Date(String strDate, String dateFormat) throws Exception {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			throw new Exception("[" + strDate + "] parse to [" + dateFormat + "] exception", e);
		}
		return date;
	}

	/**
	 * @return 返回可选模式 当都不匹配时放回空串"" 根据dateStr模式自动匹配转化 可选模式为<br/>
	 *         "yyyy-MM-dd"<br/>
	 *         "yyyy/MM/dd"<br/>
	 *         "HH:mm:ss"<br/>
	 *         "yyyy-MM-dd HH:mm:ss"<br/>
	 *         "yyyy-MM-dd HH:mm:ss.SSS"<br/>
	 *         "yyyy-MM-dd'T'HH:mm:ss.SSS"<br/>
	 *         "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"<br/>
	 *         "y/M/d"<br/>
	 *         "yyyy年MM月dd日"<br/>
	 */
	public static String getMatchDateFormat(String dateStr) {
		if (isMatched(dateStr, DAFAULT_DATE_REG)) {
			return DAFAULT_DATE_FORMAT;
		}
		if (isMatched(dateStr, DATE_REG)) {
			return DATE_FORMAT_DAY;
		}
		if (isMatched(dateStr, TIME_SEC_REG)) {
			return TIME_FORMAT_SEC;
		}
		if (isMatched(dateStr, DATE_TIME_REG)) {
			return DATE_FORMAT_SEC;
		}
		if (isMatched(dateStr, DATE_TIME_MSEC_REG)) {
			return DATE_FORMAT_MSEC;
		}
		if (isMatched(dateStr, DATE_TIME_MSEC_T_REG)) {
			return DATE_FORMAT_MSEC_T;
		}
		if (isMatched(dateStr, DATE_TIME_MSEC_T_Z_REG)) {
			return DATE_FORMAT_MSEC_T_Z;
		}
		if (isMatched(dateStr, DATE_SIMPLE_REG)) {
			return DATE_FORMAT_DAY_SIMPLE;
		}
		if (isMatched(dateStr, DATE_LONG_CN_REG)) {
			return DATE_FORMAT_LONG_CN;
		}
		return "";
	}

	/**
	 * 根据dateStr模式自动匹配转化 可选模式为<br/>
	 * "yyyy-MM-dd"<br/>
	 * "yyyy/MM/dd"<br/>
	 * "HH:mm:ss"<br/>
	 * "yyyy-MM-dd HH:mm:ss"<br/>
	 * "yyyy-MM-dd HH:mm:ss.SSS"<br/>
	 * "yyyy-MM-dd'T'HH:mm:ss.SSS"<br/>
	 * "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"<br/>
	 * "y/M/d"<br/>
	 *  "yyyy年MM月dd日"<br/>
	 * @throws TimeMatchFormatException
	 *             当都不匹配时,会抛出异常
	 */
	public static Date str2Date(String dateStr) throws ParseException {
		dateStr = dateStr.trim();
		String dateFormat = getMatchDateFormat(dateStr);
		if (!"".equals(dateFormat)) {
			return new SimpleDateFormat().parse(dateStr);
		}
		throw new TimeMatchFormatException(String.format("[%s] can not matching right time format", dateStr));
	}

	/**
	 * 根据dateStr模式自动匹配转化 当都不匹配时,会返回null 可选模式为<br/>
	 * "yyyy-MM-dd"<br/>
	 * "yyyy/MM/dd"<br/>
	 * "HH:mm:ss"<br/>
	 * "yyyy-MM-dd HH:mm:ss"<br/>
	 * "yyyy-MM-dd HH:mm:ss.SSS"<br/>
	 * "yyyy-MM-dd'T'HH:mm:ss.SSS"<br/>
	 * "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"<br/>
	 * "y/M/d"<br/>
	 *  "yyyy年MM月dd日"<br/>
	 * @throws Exception 
	 */
	public static Date str2DateUnmatch2Null(String strDate) throws Exception {
		Date date;
		try {
			date = str2Date(strDate);
		} catch (Exception e) {
			throw new Exception("[" + strDate + "] date auto parse exception", e);
		}
		return date;
	}

	public static class TimeMatchFormatException extends RuntimeException {
		private static final long serialVersionUID = 206910143412957809L;
		public TimeMatchFormatException(String message) {
			super(message);
		}
	}
	public static boolean isMatched(String patternStr, String pattern) {
		Pattern compile = Pattern.compile(pattern);
		return compile.matcher(patternStr).matches();
	}
	
	public static String getNowYear(){
		return Calendar.getInstance().get(Calendar.YEAR)+"";
	}
	
}
