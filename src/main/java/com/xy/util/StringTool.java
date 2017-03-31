package com.xy.util;

public class StringTool {

	/**
	 * 检查字符串是否为空
	 * 
	 * @param str
	 * @return true 为空；false 不为空
	 */
	public static boolean isEmpty(String str) {
		return null == str || "".equals(str) ? true : false;
	}
	
	/**
	 * 将传入String进行trim
	 * @param str
	 * @return trim的trim字符串
	 */
	public static String trim(String str) {
		return null != str ? str.trim() : str;
	}

}
