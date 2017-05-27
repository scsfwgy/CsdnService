package com.gaoyuan.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.gaoyuan.base.BaseBean;


@Entity
public class PStar extends BaseBean {
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
	private String id;
	private String id_blog;
	private String createTime;
	private String id_csdn;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_blog() {
		return id_blog;
	}
	public void setId_blog(String id_blog) {
		this.id_blog = id_blog;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "PStar [id=" + id + ", id_blog=" + id_blog + ", createTime="
				+ createTime + "]";
	}
	public String getId_csdn() {
		return id_csdn;
	}
	public void setId_csdn(String id_csdn) {
		this.id_csdn = id_csdn;
	}
	
	
	
}
