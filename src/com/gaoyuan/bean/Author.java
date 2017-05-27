package com.gaoyuan.bean;

import com.gaoyuan.base.BaseBean;



/**
 * @author 高远</n>
 * 编写日期   2016-9-26下午9:56:59</n>
 * 邮箱  wgyscsf@163.com</n>
 * 博客  http://blog.csdn.net/wgyscsf</n>
 * TODO</n>
 */
public class Author extends BaseBean {
	private String id;
	private String id_author;
	private String name;
	private String headImg;
	private String viewNums;
	private String points;// 积分
	private String rank;
	private String originalNums;
	private String repuishNums;
	private String translateNums;
	private String commentNums;
	private String title;// 博客标题
	private String descb;// 博客描述

	private boolean isBlogExpert;
	private boolean isPreBlogExpert;// 准专家
	private boolean isPersist;// 持之以恒
	private boolean isColumnUp;// 专栏达人
	private boolean isBlogStars;
	private boolean isMicrMvp;

	private String blogColumns;// 博客专栏，多个直接拼接
	
	private boolean fromExpert;// 额外字段，判断数据是否来自专家

	private String createTime;
	private String stamp;
	// 备用字段
	private String spare1;
	private String spare2;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_author() {
		return id_author;
	}
	public void setId_author(String id_author) {
		this.id_author = id_author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getViewNums() {
		return viewNums;
	}
	public void setViewNums(String viewNums) {
		this.viewNums = viewNums;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getOriginalNums() {
		return originalNums;
	}
	public void setOriginalNums(String originalNums) {
		this.originalNums = originalNums;
	}
	public String getRepuishNums() {
		return repuishNums;
	}
	public void setRepuishNums(String repuishNums) {
		this.repuishNums = repuishNums;
	}
	public String getTranslateNums() {
		return translateNums;
	}
	public void setTranslateNums(String translateNums) {
		this.translateNums = translateNums;
	}
	public String getCommentNums() {
		return commentNums;
	}
	public void setCommentNums(String commentNums) {
		this.commentNums = commentNums;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescb() {
		return descb;
	}
	public void setDescb(String descb) {
		this.descb = descb;
	}
	public boolean isBlogExpert() {
		return isBlogExpert;
	}
	public void setBlogExpert(boolean isBlogExpert) {
		this.isBlogExpert = isBlogExpert;
	}
	public boolean isPreBlogExpert() {
		return isPreBlogExpert;
	}
	public void setPreBlogExpert(boolean isPreBlogExpert) {
		this.isPreBlogExpert = isPreBlogExpert;
	}
	public boolean isPersist() {
		return isPersist;
	}
	public void setPersist(boolean isPersist) {
		this.isPersist = isPersist;
	}
	public boolean isColumnUp() {
		return isColumnUp;
	}
	public void setColumnUp(boolean isColumnUp) {
		this.isColumnUp = isColumnUp;
	}
	public boolean isBlogStars() {
		return isBlogStars;
	}
	public void setBlogStars(boolean isBlogStars) {
		this.isBlogStars = isBlogStars;
	}
	public boolean isMicrMvp() {
		return isMicrMvp;
	}
	public void setMicrMvp(boolean isMicrMvp) {
		this.isMicrMvp = isMicrMvp;
	}
	public String getBlogColumns() {
		return blogColumns;
	}
	public void setBlogColumns(String blogColumns) {
		this.blogColumns = blogColumns;
	}

	public boolean isFromExpert() {
		return fromExpert;
	}

	public void setFromExpert(boolean fromExpert) {
		this.fromExpert = fromExpert;
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
		return "Author [id=" + id + ", id_author=" + id_author + ", name="
				+ name + ", headImg=" + headImg + ", viewNums=" + viewNums
				+ ", points=" + points + ", rank=" + rank + ", originalNums="
				+ originalNums + ", repuishNums=" + repuishNums
				+ ", translateNums=" + translateNums + ", commentNums="
				+ commentNums + ", title=" + title + ", descb=" + descb
				+ ", isBlogExpert=" + isBlogExpert + ", isPreBlogExpert="
				+ isPreBlogExpert + ", isPersist=" + isPersist
				+ ", isColumnUp=" + isColumnUp + ", isBlogStars=" + isBlogStars
				+ ", isMicrMvp=" + isMicrMvp + ", blogColumns=" + blogColumns
				+ ", fromExpert=" + fromExpert + ", createTime=" + createTime
				+ ", stamp=" + stamp + ", spare1=" + spare1 + ", spare2="
				+ spare2 + "]";
	}

}
