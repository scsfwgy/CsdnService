package test;

/**
 * @author wgyscsf
 * @email wgyscsf@163.com
 * @dateTime 2017 2017-5-12 下午12:39:04
 * @details 
 */
public class TestOne {
	public int getA(int a){
//		if(a==1)return a;
//		getA(--a);
//		return -1;
//		if(a==1)return a;
//		return getA(--a);
		try {
			a=a/0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("a的值A:"+a);
			return --a;
		}finally{
			return ++a;
//			 System.out.println("a的值B:"+a);
		}
//		System.out.println("a的值C:"+a);
//		return a;
	}
	public static void main(String[] args) {
		int a=new TestOne().getA(3);
		System.out.println(a);
		
		
	}
}
