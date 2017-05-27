package com.gaoyuan.utils;

/**
 * @author 高远</n>
 * 编写日期   2016-12-24下午6:17:27</n>
 * 邮箱  wgyscsf@163.com</n>
 * 博客  http://blog.csdn.net/wgyscsf</n>
 * TODO</n>
 */
public class MyPagerUtils {
	public static int getNowPager(int nowPager) {
		return nowPager == 0 ? 1 : nowPager;
	}

	public static int getPagerSize(int pagerSize) {
		return (pagerSize == 0 || pagerSize > 20) ? 10 : pagerSize;
	}
}
