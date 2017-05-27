package com.gaoyuan.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.PStar;
import com.gaoyuan.bean.PUser;
import com.gaoyuan.bean.Preference;
import com.gaoyuan.dao.UserDao;
import com.gaoyuan.utils.MyStringUtils;


@Repository
public class UserDaoBean extends BaseDaoBean implements UserDao {

	@Override
	public PUser selectPUserByCsdnId(String id_csdn) {
		List<?> queryAll = queryAll(PUser.class, "id_csdn", id_csdn);
		if(queryAll==null||queryAll.isEmpty())return null;
		return (PUser) queryAll.get(0);
	}

	@Override
	public void updatePUser(PUser pUser) {
		add(pUser);
	}

	@Override
	public void addPUser(PUser pUser) {
		add(pUser);
	}

	@Override
	public boolean addStar(PStar pStar) {
		add(pStar);
		return true;
	}

	@Override
	public boolean delStar(String id) {
		delete(PStar.class, "id", id);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PStar> getPStarByCsdnId(int nowPager, int pagerSize,
			String id_author) {
		return queryByPageByOrder(PStar.class, "id_csdn", id_author, nowPager, pagerSize, "createTime", "desc");
	}

	@Override
	public Preference selectPreference(String id_csdn, String id_blog, int type) {
		@SuppressWarnings("unchecked")
		List<Preference> queryAll = queryAll(Preference.class, "id_csdn", id_csdn, "id_blog", id_blog, "type", type);
		if(queryAll==null||queryAll.isEmpty())return null;
		return queryAll.get(0);
	}

	@Override
	public boolean addPreference(Preference preference) {
		add(preference);
		return true;
	}

}
