package kr.co.inslab.util;

import kr.co.inslab.log.SLog;


public class JsonpUtil {

	public static String checkJsonP(String callback, String result){
		if(callback != null)
			result = callback + "(" + result + ")";
		
		SLog.d("result", result);
		return result;
	}
	
}
