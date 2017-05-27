package com.gaoyuan.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.PStar;
import com.gaoyuan.bean.PUser;
import com.gaoyuan.bean.Preference;
import com.gaoyuan.bean.Recommend;


@Transactional
public interface UserService {

	PUser login(String account, String password);

	boolean addStar(PStar pStar);

	List<Blog> getPStarByCsdnId(int nowPager, int pagerSize, String id_author);

	boolean delStar(PStar pStar);

	boolean addPreference(Preference preference);

	boolean updatePUser(PUser pUser);

	
	

}
