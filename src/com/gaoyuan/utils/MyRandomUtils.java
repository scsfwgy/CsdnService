package com.gaoyuan.utils;

import java.util.Random;

/**
 * @author 高远</n>
 * 编写日期   2016-12-24下午6:17:27</n>
 * 邮箱  wgyscsf@163.com</n>
 * 博客  http://blog.csdn.net/wgyscsf</n>
 * TODO</n>
 */
public class MyRandomUtils {
	public static  int getRangeRandomInt(int min,int max){
       
        return new Random().nextInt(max+1 - min) + min;
}
	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			System.out.println(getRangeRandomInt(0,4));
		}
		
	}
}
