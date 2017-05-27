package com.gaoyuan.dao;

import java.util.List;

import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.PStar;
import com.gaoyuan.bean.PUser;
import com.gaoyuan.bean.Preference;
import com.gaoyuan.bean.Recommend;



public interface UserDao extends BaseDao {

	PUser selectPUserByCsdnId(String id_csdn);

	void updatePUser(PUser pUser);

	void addPUser(PUser pUser);

	boolean addStar(PStar pStar);

	boolean delStar(String id);

	List<PStar> getPStarByCsdnId(int nowPager, int pagerSize, String id_author);

	Preference selectPreference(String id_csdn, String id_blog, int type);

	boolean addPreference(Preference preference);

	

}
