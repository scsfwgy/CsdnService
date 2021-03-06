package com.gaoyuan.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/8/7
 *     desc  : 编码解码相关工具类
 * </pre>
 */
public class MyEncodeUtils extends MyBaseUtils {

	private MyEncodeUtils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	/**
	 * URL编码
	 * <p>
	 * 若想自己指定字符集,可以使用{@link #urlEncode(String input, String charset)}方法
	 * </p>
	 * 
	 * @param input
	 *            要编码的字符
	 * @return 编码为UTF-8的字符串
	 */
	public static String urlEncode(String input) {
		return urlEncode(input, "UTF-8");
	}

	/**
	 * URL编码
	 * <p>
	 * 若系统不支持指定的编码字符集,则直接将input原样返回
	 * </p>
	 * 
	 * @param input
	 *            要编码的字符
	 * @param charset
	 *            字符集
	 * @return 编码为字符集的字符串
	 */
	public static String urlEncode(String input, String charset) {
		try {
			return URLEncoder.encode(input, charset);
		} catch (UnsupportedEncodingException e) {
			return input;
		}
	}

	/**
	 * URL解码
	 * <p>
	 * 若想自己指定字符集,可以使用 {@link #urlDecode(String input, String charset)}方法
	 * </p>
	 * 
	 * @param input
	 *            要解码的字符串
	 * @return URL解码后的字符串
	 */
	public static String urlDecode(String input) {
		return urlDecode(input, "UTF-8");
	}

	/**
	 * URL解码
	 * <p>
	 * 若系统不支持指定的解码字符集,则直接将input原样返回
	 * </p>
	 * 
	 * @param input
	 *            要解码的字符串
	 * @param charset
	 *            字符集
	 * @return URL解码为指定字符集的字符串
	 */
	public static String urlDecode(String input, String charset) {
		try {
			return URLDecoder.decode(input, charset);
		} catch (UnsupportedEncodingException e) {
			return input;
		}
	}

	/**
	 * Html编码
	 * 
	 * @param input
	 *            要Html编码的字符串
	 * @return Html编码后的字符串
	 */
	public static String htmlEncode(String input) {

		// 参照Html.escapeHtml()中代码
		StringBuilder out = new StringBuilder();
		for (int i = 0, len = input.length(); i < len; i++) {
			char c = input.charAt(i);
			if (c == '<') {
				out.append("&lt;");
			} else if (c == '>') {
				out.append("&gt;");
			} else if (c == '&') {
				out.append("&amp;");
			} else if (c >= 0xD800 && c <= 0xDFFF) {
				if (c < 0xDC00 && i + 1 < len) {
					char d = input.charAt(i + 1);
					if (d >= 0xDC00 && d <= 0xDFFF) {
						i++;
						int codepoint = 0x010000 | (int) c - 0xD800 << 10
								| (int) d - 0xDC00;
						out.append("&#").append(codepoint).append(";");
					}
				}
			} else if (c > 0x7E || c < ' ') {
				out.append("&#").append((int) c).append(";");
			} else if (c == ' ') {
				while (i + 1 < len && input.charAt(i + 1) == ' ') {
					out.append("&nbsp;");
					i++;
				}
				out.append(' ');
			} else {
				out.append(c);
			}
		}
		return out.toString();
	}

}