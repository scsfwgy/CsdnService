package com.gaoyuan.base;

import java.io.Serializable;

/**
 * @author wgyscsf
 * @email wgyscsf@163.com
 * @dateTime 2017 2017-4-10 下午2:07:09
 * @details 
 */
public class BaseBean extends BaseClass implements Serializable{

	private static final long serialVersionUID = 1L;
	private int nowPager;
	private int pagerSize;

	public int getNowPager() {
		return nowPager;
	}

	public void setNowPager(int nowPager) {
		this.nowPager = nowPager;
	}

	public int getPagerSize() {
		return pagerSize;
	}

	public void setPagerSize(int pagerSize) {
		this.pagerSize = pagerSize;
	}

	@Override
	public String toString() {
		return "BaseModel [nowPager=" + nowPager + ", pagerSize=" + pagerSize
				+ "]";
	}

}
