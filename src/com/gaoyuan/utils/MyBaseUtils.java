package com.gaoyuan.utils;

/**
 * @author 高远</n>
 * 编写日期   2016-11-21下午7:39:02</n>
 * 邮箱  wgyscsf@163.com</n>
 * 博客  http://blog.csdn.net/wgyscsf</n>
 * TODO</n>
 */
public abstract class MyBaseUtils {
	// 一些共用参数
	public String TAG = "请初始化该参数。";

	public MyBaseUtils() {
		TAG = this.getClass().getSimpleName();
	}
}
