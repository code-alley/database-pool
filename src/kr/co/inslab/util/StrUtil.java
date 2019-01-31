package kr.co.inslab.util;

import java.util.Random;

public class StrUtil {

	public static  String randomString(int length) {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder(length);
		for (int i=0; i<length; i++) {
			stringBuilder.append((char)('a' + random.nextInt(26)));
		}
		return stringBuilder.toString();
	}
	
}
