package com.gaoyuan.utils;

import java.util.List;

/**
 * @author 高远</n>
 * 编写日期   2016-11-21下午10:29:26</n>
 * 邮箱  wgyscsf@163.com</n>
 * 博客  http://blog.csdn.net/wgyscsf</n>
 * TODO</n>
 */
public class MyParameterUtils extends MyBaseUtils {

	// ------------------校验普通参数完整性------------------------
	// 校验参数完整性
	@SuppressWarnings("rawtypes")
	public static boolean paramsIsValid(List objs) {
		if (objs == null || objs.isEmpty() || objs.size() == 0)
			return false;
		return true;
	}

	// 校验参数完整性
	public static boolean paramsIsValid(Object... obj) {
		for (Object object : obj) {
			if (object == null)
				return false;
		}
		return true;
	}

	// 校验参数完整性
	public static boolean paramsIsValid(String... str) {
		for (String string : str) {
			if (string == null || string.equals("") || string.length() == 0)
				return false;
		}
		return true;
	}

	// 校验参数完整性
	public static boolean paramsIsValid(int... i) {
		for (int j : i) {
			if (j == 0)
				return false;
		}
		return true;
	}
}
