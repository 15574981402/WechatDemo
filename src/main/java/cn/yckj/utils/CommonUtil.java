package cn.yckj.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


/**
 * 公共的方法
 * @author hb
 *
 */
public class CommonUtil {
	/**
	 * 验证是否是正数
	 * @param num
	 * @return
	 */
	public static boolean isNumber(String num){
		Pattern pattern = Pattern.compile("^[0-9]*\\.?[0-9]*$");   
		Matcher matcher = pattern.matcher(num);   
		return matcher.matches();  
	}
	 public static int getYear(){
		 return Calendar.getInstance().get(Calendar.YEAR);
	 }
	 /**
		 * 当 intstr 为""或null 返回"1" 或者不能解析为int类型数字返回 "1"
		 */
		public static String nextIntValue(String intstr) {
			if (StringUtils.isBlank(intstr)) {
				return "1";
			}
			int parseInt = 0;
			try {
				parseInt = Integer.parseInt(intstr);
			} catch (Exception e) {
				return "1";
			}
			return parseInt + 1 + "";
		};
		

	

	private static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}
	
	/**
	 * double类型转换为字符类型
	 * @param num
	 * @return
	 */
	public static String doubleTrans1(double num)
	{
	   String restr="";
	   try
	   {
		    if(num % 1.0 == 0)
		    {
		    	restr= String.valueOf((long)num);
		    }
		    else{
		    	restr=String.valueOf(num);
		    }
	    }
	    catch(Exception e){
	    	
	    }
	    return restr;
		   
	}
	
	public static String getDate(){
		DateFormat df = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
		return df.format(new Date());
	}
	
	
}
