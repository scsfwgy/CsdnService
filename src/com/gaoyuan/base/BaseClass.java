package com.gaoyuan.base;

/**
 * @author wgyscsf
 * @email wgyscsf@163.com
 * @dateTime 2017 2017-4-10 下午2:03:46
 * @details 项目所有类的基类。请谨慎向该类中加东西。主要为了后期重构用。
 */
public class BaseClass {
	protected String TAG = null;
	public BaseClass() {
		TAG = this.getClass().getSimpleName();
	}
}
