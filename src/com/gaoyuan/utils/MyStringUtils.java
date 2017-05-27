package com.gaoyuan.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
 * 该工具类包含各种常用日期操作的工具类。
 */
public class MyStringUtils extends MyBaseUtils {
	private MyStringUtils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	/**
	 * 判断字符串是否为null或长度为0
	 * 
	 * @param s
	 *            待校验字符串
	 * @return {@code true}: 空<br>
	 *         {@code false}: 不为空
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	/**
	 * 判断字符串是否为null或全为空格
	 * 
	 * @param s
	 *            待校验字符串
	 * @return {@code true}: null或全空格<br>
	 *         {@code false}: 不为null且不全空格
	 */
	public static boolean isSpace(String s) {
		return (s == null || s.trim().length() == 0);
	}

	/**
	 * 判断两字符串是否相等
	 * 
	 * @param a
	 *            待校验字符串a
	 * @param b
	 *            待校验字符串b
	 * @return {@code true}: 相等<br>
	 *         {@code false}: 不相等
	 */
	public static boolean equals(String a, String b) {
		if (a == b)
			return true;
		int length;
		if (a != null && b != null && (length = a.length()) == b.length()) {
			if (a instanceof String && b instanceof String) {
				return a.equals(b);
			} else {
				for (int i = 0; i < length; i++) {
					if (a.charAt(i) != b.charAt(i))
						return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断两字符串忽略大小写是否相等
	 * 
	 * @param a
	 *            待校验字符串a
	 * @param b
	 *            待校验字符串b
	 * @return {@code true}: 相等<br>
	 *         {@code false}: 不相等
	 */
	public static boolean equalsIgnoreCase(String a, String b) {
		return (a == b) || (b != null) && (a.length() == b.length())
				&& a.regionMatches(true, 0, b, 0, b.length());
	}

	/**
	 * null转为长度为0的字符串
	 * 
	 * @param s
	 *            待转字符串
	 * @return s为null转为长度为0字符串，否则不改变
	 */
	public static String null2Length0(String s) {
		return s == null ? "" : s;
	}

	/**
	 * 返回字符串长度
	 * 
	 * @param s
	 *            字符串
	 * @return null返回0，其他返回自身长度
	 */
	public static int length(String s) {
		return s == null ? 0 : s.length();
	}

	/**
	 * 首字母大写
	 * 
	 * @param s
	 *            待转字符串
	 * @return 首字母大写字符串
	 */
	public static String upperFirstLetter(String s) {
		if (isEmpty(s) || !Character.isLowerCase(s.charAt(0)))
			return s;
		return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
	}

	/**
	 * 首字母小写
	 * 
	 * @param s
	 *            待转字符串
	 * @return 首字母小写字符串
	 */
	public static String lowerFirstLetter(String s) {
		if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) {
			return s;
		}
		return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
	}

	/**
	 * 反转字符串
	 * 
	 * @param s
	 *            待反转字符串
	 * @return 反转字符串
	 */
	public static String reverse(String s) {
		int len = length(s);
		if (len <= 1)
			return s;
		int mid = len >> 1;
		char[] chars = s.toCharArray();
		char c;
		for (int i = 0; i < mid; ++i) {
			c = chars[i];
			chars[i] = chars[len - i - 1];
			chars[len - i - 1] = c;
		}
		return new String(chars);
	}

	/**
	 * 转化为半角字符
	 * 
	 * @param s
	 *            待转字符串
	 * @return 半角字符串
	 */
	public static String toDBC(String s) {
		if (isEmpty(s)) {
			return s;
		}
		char[] chars = s.toCharArray();
		for (int i = 0, len = chars.length; i < len; i++) {
			if (chars[i] == 12288) {
				chars[i] = ' ';
			} else if (65281 <= chars[i] && chars[i] <= 65374) {
				chars[i] = (char) (chars[i] - 65248);
			} else {
				chars[i] = chars[i];
			}
		}
		return new String(chars);
	}

	/**
	 * 转化为全角字符
	 * 
	 * @param s
	 *            待转字符串
	 * @return 全角字符串
	 */
	public static String toSBC(String s) {
		if (isEmpty(s)) {
			return s;
		}
		char[] chars = s.toCharArray();
		for (int i = 0, len = chars.length; i < len; i++) {
			if (chars[i] == ' ') {
				chars[i] = (char) 12288;
			} else if (33 <= chars[i] && chars[i] <= 126) {
				chars[i] = (char) (chars[i] + 65248);
			} else {
				chars[i] = chars[i];
			}
		}
		return new String(chars);
	}

	// ********************wgy*********************
	// 获取最后“/”后面的内容
	public static String getLastSlantContent(String fullPath) {
		int pos = fullPath.lastIndexOf("/");
		if (pos != -1) {
			return fullPath.substring(pos + 1);
		} else {
			return null;
		}
	}

	// 获取一个字符串中的数字部分，剔除掉不是数字的
	public static String getStringPureNumber(String str) {
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(str);
		if (matcher.replaceAll("").equals(""))
			return -1 + "";
		return matcher.replaceAll("");
	}

	// 获取一个字符串中的数字部分，剔除掉不是数字的
	public static int getIntPureNumber(String str) {

		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(str);
		String num = matcher.replaceAll("");
		if (!num.equals(""))
			return Integer.parseInt(num);
		return -1;
	}

	// 剔除空格
	public static String getNoTrimStr(String str) {
		return str.replaceAll("\\s*", "");
	}

	// 获取“|”前面的字符
	public static String getBeforeVercitalLine(String str) {
		if (str.indexOf("|") == -1)
			return str;
		return (String) str.substring(0, str.indexOf("|"));
	}

	// 获取“|”/后面面的字符
	public static String getAfterVercitalLine(String str) {
		return (String) str.substring(str.indexOf("|") + 1);
	}

	// 获取“/”前面的字符
	public static String getLastBeforeSprit(String str) {
		if (str.lastIndexOf("/") == -1)
			return str;
		return (String) str.substring(0, str.indexOf("|"));
	}

	// 获取“/”后面面的字符
	public static String getLastAfterSprit(String str) {
		return (String) str.substring(str.lastIndexOf("/") + 1);
	}

	public static String getGUID() {
		return UUID.randomUUID().toString();
	}
}
