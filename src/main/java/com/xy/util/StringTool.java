package com.xy.util;

public class StringTool {

	/**
	 * ����ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 * @return true Ϊ�գ�false ��Ϊ��
	 */
	public static boolean isEmpty(String str) {
		return null == str || "".equals(str) ? true : false;
	}
	
	/**
	 * ������String����trim
	 * @param str
	 * @return trim��trim�ַ���
	 */
	public static String trim(String str) {
		return null != str ? str.trim() : str;
	}

}
