package com.gaoyuan.bean;

import com.gaoyuan.base.BaseBean;

/**
 * @author 高远</n>
 * 编写日期   2016-12-24下午5:48:08</n>
 * 邮箱  wgyscsf@163.com</n>
 * 博客  http://blog.csdn.net/wgyscsf</n>
 * TODO</n>
 */
public class Classify extends BaseBean {
	private String id;
	private String typeId;
	private String typeName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "Classify [id=" + id + ", typeId=" + typeId + ", typeName="
				+ typeName + "]";
	}

}
