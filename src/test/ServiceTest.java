package test;

import java.util.Arrays;


/**
 * @author wgyscsf
 * @email wgyscsf@163.com
 * @dateTime 2017 2017-4-10 下午9:47:55
 * @details 
 */
public class ServiceTest {
	public static void main(String[] args) {
		String str="";
		String[] split = str.split("_");
		System.out.println(Arrays.toString(split)+split.length);
	}
	private T test(){
		T t = new T();
		t.name = "hahha";
		return t;
	}
	private static  class T{
		private String name;
	}
}
