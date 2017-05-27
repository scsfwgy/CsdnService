package com.gaoyuan.bean;

/**
 * 作者：wgyscsf on 2017/4/17 18:34
 * 邮箱：wgyscsf@163.com
 * 博客：http://blog.csdn.net/wgyscsf
 */
public class CsdnUser {

    /**
     * userId : 52391669
     * isLocked : false
     * mobile : 18439854081
     * userName : wgyscsf
     * email : wgyscsf@163.com
     * password : 0183291bb1584cb7d0d41aee67773d75
     * registerIP : 61.163.131.244
     * isDeleted : false
     * isActived : true
     * role : 0
     * registerTime : May 19, 2015 12:37:26 PM
     * userType : 0
     * lastLoginIP : 222.89.211.154
     * lastLoginTime : Jan 31, 2017 8:30:07 PM
     * loginTimes : 314
     * user_status : 0
     * activeTime : May 19, 2015 12:37:59 PM
     * passwordStrongLevel : 3
     * ucSyncStatus : true
     * nickName : _高远
     * avatar : http://avatar.csdn.net/C/A/9/1_wgyscsf.jpg
     * encryptUserInfo : Duu8rW1HcDcOII5T9vZVX7LqBTHD9ROS786NGKHRn+JFsbLvkuAzkZMl9/jM/jDEJHoOUO1/KvXsooD+iXH9Ye/AnLTnA1K1/8qifjMKzJmilLdZcolL35xYX4nPkXsd
     */

    private int userId;
    private boolean isLocked;
    private String mobile;
    private String userName;
    private String email;
    private String password;
    private String registerIP;
    private boolean isDeleted;
    private boolean isActived;
    private int role;
    private String registerTime;
    private int userType;
    private String lastLoginIP;
    private String lastLoginTime;
    private int loginTimes;
    private int user_status;
    private String activeTime;
    private int passwordStrongLevel;
    private boolean ucSyncStatus;
    private String nickName;
    private String avatar;
    private String encryptUserInfo;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterIP() {
        return registerIP;
    }

    public void setRegisterIP(String registerIP) {
        this.registerIP = registerIP;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isIsActived() {
        return isActived;
    }

    public void setIsActived(boolean isActived) {
        this.isActived = isActived;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(int loginTimes) {
        this.loginTimes = loginTimes;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public int getPasswordStrongLevel() {
        return passwordStrongLevel;
    }

    public void setPasswordStrongLevel(int passwordStrongLevel) {
        this.passwordStrongLevel = passwordStrongLevel;
    }

    public boolean isUcSyncStatus() {
        return ucSyncStatus;
    }

    public void setUcSyncStatus(boolean ucSyncStatus) {
        this.ucSyncStatus = ucSyncStatus;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEncryptUserInfo() {
        return encryptUserInfo;
    }

    public void setEncryptUserInfo(String encryptUserInfo) {
        this.encryptUserInfo = encryptUserInfo;
    }

	@Override
	public String toString() {
		return "CsdnUser [userId=" + userId + ", isLocked=" + isLocked
				+ ", mobile=" + mobile + ", userName=" + userName + ", email="
				+ email + ", password=" + password + ", registerIP="
				+ registerIP + ", isDeleted=" + isDeleted + ", isActived="
				+ isActived + ", role=" + role + ", registerTime="
				+ registerTime + ", userType=" + userType + ", lastLoginIP="
				+ lastLoginIP + ", lastLoginTime=" + lastLoginTime
				+ ", loginTimes=" + loginTimes + ", user_status=" + user_status
				+ ", activeTime=" + activeTime + ", passwordStrongLevel="
				+ passwordStrongLevel + ", ucSyncStatus=" + ucSyncStatus
				+ ", nickName=" + nickName + ", avatar=" + avatar
				+ ", encryptUserInfo=" + encryptUserInfo + "]";
	}
    
    
}
