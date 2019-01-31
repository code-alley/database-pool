package kr.co.inslab.log;

import kr.co.inslab.util.DateUtil;

/**
 * @author jdkim
 *
 */
public class SLog {

	public static String project = "noname";
	/**
	 * debug log
	 * @param msg
	 */
	public static void d(String msg){
		System.out.println("[DEBUG] " + DateUtil.currentTime() + " | " + project + " | " + msg );
	}

	/**
	 * debug log 
	 * @param title
	 * @param msg
	 */
	public static void d(String title, String msg){
		System.out.println("[DEBUG] " + DateUtil.currentTime() + " | " + project + " | " + title + " | " + msg );
	}

	/**
	 * debug log 
	 * @param title
	 * @param number
	 */
	public static void d(String title, int number){
		System.out.println("[DEBUG] " + DateUtil.currentTime() + " | " + project + " | " + title + " | " + number );
	}

	/**
	 * debug log
	 * @param obj
	 * @param msg
	 */
	public static void d(Object obj, String msg){
		System.out.println("[DEBUG] " + DateUtil.currentTime() + " | " + project + " | " + obj.getClass().getSimpleName() +" | " + msg );
	}

	/**
	 * error log
	 * @param msg
	 */
	public static void e(String msg){
		System.out.println("[ERROR] " + DateUtil.currentTime() + " | " + project + " | " + msg );
	}

	/**
	 * information log 
	 * @param msg
	 */
	public static void i(String msg){
		System.out.println("[INFO] " + DateUtil.currentTime() + " | " + project + " | " + msg );
	}



}
