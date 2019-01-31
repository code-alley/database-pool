package kr.co.inslab.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String currentTime(){
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date nowDate = new Date();
		return sdFormat.format(nowDate);
	}
	
	public static String currentTime(String fotmat){
		DateFormat sdFormat = new SimpleDateFormat(fotmat);
		Date nowDate = new Date();
		return sdFormat.format(nowDate);
	}
	
}
