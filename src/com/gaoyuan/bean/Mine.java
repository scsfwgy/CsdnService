package com.gaoyuan.bean;

import com.gaoyuan.base.BaseBean;

/**
 * @author 高远</n>
 * 编写日期   2016-9-26下午4:39:34</n>
 * 邮箱  wgyscsf@163.com</n>
 * 博客  http://blog.csdn.net/wgyscsf</n>
 * TODO</n>
 */
public class Mine extends BaseBean {
	private String id;
	private String id_mine;
	private String nickName;
	private String headImg;
	private String name;
	private String sex;
	private String birthday;
	
	private String focusNums;
	private String fansNums;

	private String sign;// 个人签名
	
	private String personDetail;// 包括行业、工作、位置（位置包括：国家、省、市、区）
	private String trade;// 行业
	private String job;
	private String location;// 位置，包括国家、省、市、区
	private String nation;
	private String province;
	private String city;// 市
	private String area;// 区
	private String createTime;
	private String stamp;// 时间戳

	// 备用字段
	private String spare1;
	private String spare2;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_mine() {
		return id_mine;
	}
	public void setId_mine(String id_mine) {
		this.id_mine = id_mine;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getFocusNums() {
		return focusNums;
	}
	public void setFocusNums(String focusNums) {
		this.focusNums = focusNums;
	}
	public String getFansNums() {
		return fansNums;
	}
	public void setFansNums(String fansNums) {
		this.fansNums = fansNums;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPersonDetail() {
		return personDetail;
	}
	public void setPersonDetail(String personDetail) {
		this.personDetail = personDetail;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getSpare1() {
		return spare1;
	}
	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}
	public String getSpare2() {
		return spare2;
	}
	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}

	@Override
	public String toString() {
		return "Mine [id=" + id + ", id_mine=" + id_mine + ", nickName="
				+ nickName + ", headImg=" + headImg + ", name=" + name
				+ ", sex=" + sex + ", birthday=" + birthday + ", focusNums="
				+ focusNums + ", fansNums=" + fansNums + ", sign=" + sign
				+ ", personDetail=" + personDetail + ", trade=" + trade
				+ ", job=" + job + ", location=" + location + ", nation="
				+ nation + ", province=" + province + ", city=" + city
				+ ", area=" + area + ", createTime=" + createTime + ", stamp="
				+ stamp + ", spare1=" + spare1 + ", spare2=" + spare2 + "]";
	}


}
