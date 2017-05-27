package com.gaoyuan.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 高远</n>
 * 编写日期   2016-12-17下午5:38:09</n>
 * 邮箱  wgyscsf@163.com</n>
 * 博客  http://blog.csdn.net/wgyscsf</n>
 * TODO</n>
 */
public class MyNlpUtils {

	// 根据真确度切词获取分词列表。degree：准确度。范围：0到1。0表示获取准确度0%到100%的搜索分词。1表示只获取100%确定的词。
	public static List<String> getListWordsByDesc(String keyWords, double degree) {
		List<String> splitWords = new ArrayList<>();
		String result = null;
		try {
			result = sendGet("http://api.pullword.com/get.php", "source="
					+ URLEncoder.encode(keyWords, "UTF-8") + "&param1="
					+ degree + "&param2=1");// 注意编码处理。
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result == null || result.equals(""))
			return null;
		String[] split = result.split(",");
		for (String string : split) {
			if (string != null && !string.trim().equals("")
					&& string.trim().length() != 0) {
				splitWords.add(string.split(":")[0]);
			}
		}
		// 这里可以考虑对准确度进行排序。
		// Collections.reverse(splitWords);
		return splitWords;
	}

	public static void main(String[] args) {
		System.out.println(getListWordsByDesc("安卓动画开发java开发ssh学习", 0));
	}
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 nae1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));// /这里地方很关键。一定要进行utf-8处理。
			String line;
			while ((line = in.readLine()) != null) {
				result += line + ",";// 加一个分隔符
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
