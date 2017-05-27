package com.gaoyuan.bean;

import com.gaoyuan.base.BaseBean;


/**
 * @author 高远</n>
 * 编写日期   2016-9-26下午4:52:33</n>
 * 邮箱  wgyscsf@163.com</n>
 * 博客  http://blog.csdn.net/wgyscsf</n>
 * TODO</n>
 */
public class Relation extends BaseBean {
	private String id;
	private String followId;// 关注者Id
	private String followedId;// 被关注者id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFollowId() {
		return followId;
	}

	public void setFollowId(String followId) {
		this.followId = followId;
	}

	public String getFollowedId() {
		return followedId;
	}

	public void setFollowedId(String followedId) {
		this.followedId = followedId;
	}

}
