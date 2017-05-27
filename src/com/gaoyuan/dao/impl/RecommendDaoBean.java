package com.gaoyuan.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.gaoyuan.base.Constants;
import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.Expert;
import com.gaoyuan.bean.Preference;
import com.gaoyuan.bean.Recommend;
import com.gaoyuan.dao.RecommendDao;


@Repository
public class RecommendDaoBean extends BaseDaoBean implements RecommendDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getDefRecommendBlogs(Recommend recommend) {
		return queryByPageByOrder(Blog.class, recommend.getNowPager(),
				recommend.getPagerSize(), "publishDateTime", "desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getRecommendBlogsByDef(int nowPager, int pagerSize) {
		return queryByPage(Blog.class, nowPager, pagerSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getRecommendBlogsByComments(int nowPager, int pagerSize) {
		Query query;
		query = getSession().createQuery(
				"from " + Blog.class.getSimpleName() + " where "
						+ "commentNums " + ">=? " + " order by " + "commentNums"
						+ " " + "Desc");
		query.setParameter(0, Constants.Recommend.RECOMMEND_COMMENT);
		query.setFirstResult(pagerSize * (nowPager - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	@Override
	public List<Blog> getRecommendBlogsByReadNums(int nowPager, int pagerSize) {
		Query query;
		query = getSession().createQuery(
				"from " + Blog.class.getSimpleName() + " where "
						+ "viewNums" + ">=? " + " order by " + " viewNums "
						+ " " + "Desc");
		query.setParameter(0, Constants.Recommend.RECOMMEND_READNUMS);
		query.setFirstResult(pagerSize * (nowPager - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getRecommendBlogsByDate(int nowPager, int pagerSize) {
		return  queryByPageByOrder(Blog.class, nowPager, pagerSize,"publishDateTime","Desc");
	}

	// select id_expert,typeId from expert where typeId not
	// in(1,14,15,16,17,6,12,2,0) limit 0,2;
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getRecommendBlogsByDef(int nowPager, int pagerSize,
			List<Integer> dislikeType) {
		Query query;
		query = getSession().createQuery(
				"from " + Blog.class.getSimpleName() + " where typeId not in (:dislikeType)");
		query.setParameterList("dislikeType", dislikeType);
		query.setFirstResult(pagerSize * (nowPager - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	@Override
	public List<Blog> getRecommendBlogsByComments(int nowPager, int pagerSize,
			List<Integer> dislikeType) {
		Query query;
		query = getSession().createQuery(
				"from " + Blog.class.getSimpleName() + " where  "+ " commentNums " + ">=? and typeId not in (:dislikeType)" + " order by " + "commentNums"
						+ " " + "Desc");
		query.setParameterList("dislikeType", dislikeType);
		query.setParameter(0, Constants.Recommend.RECOMMEND_COMMENT);
		query.setFirstResult(pagerSize * (nowPager - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	@Override
	public List<Blog> getRecommendBlogsByReadNums(int nowPager, int pagerSize,
			List<Integer> dislikeType) {
		Query query;
		query = getSession().createQuery(
				"from " + Blog.class.getSimpleName() + " where "+ "  viewNums" + ">=? and typeId not in (:dislikeType) " + " order by " + "viewNums"
						+ " " + "Desc");
		query.setParameterList("dislikeType", dislikeType);
		query.setParameter(0, Constants.Recommend.RECOMMEND_READNUMS);
		query.setFirstResult(pagerSize * (nowPager - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	@Override
	public List<Blog> getRecommendBlogsByDate(int nowPager, int pagerSize,
			List<Integer> dislikeType) {
		Query query;
		query = getSession().createQuery(
				"from " + Blog.class.getSimpleName() + " where typeId not in (:dislikeType) "+ " " + " order by " + "publishDateTime"
						+ " " + "Desc");
		query.setParameterList("dislikeType", dislikeType);
		query.setFirstResult(pagerSize * (nowPager - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}
	//select * from preference p,blog b where p.id_blog=b.id_blog and p.type=1 and b.typeId not in(1,3) group by p.id_blog order by count(*) desc;
	@SuppressWarnings("unchecked")
	@Override
	public List<Preference> getPreferenceByDesc(int nowPager, int pagerSize,
			List<Integer> dislikeType) {
		Query query;
		query = getSession().createQuery(
				"from " + Preference.class.getSimpleName() + " where typeId not in (:dislikeType) "+ "group by id_blog " + " order by " + "count(*)"
						+ " " + "Desc");
		query.setParameterList("dislikeType", dislikeType);
		query.setFirstResult(pagerSize * (nowPager - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	@Override
	public int getRecommendBlogsByDefCount(List<Integer> integers) {
		Query query;
		query = getSession().createQuery(
				"select count(*) from " + Blog.class.getSimpleName()
						+ " where typeId not in (:dislikeType)");
		query.setParameterList("dislikeType", integers);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public int getRecommendBlogsByCommentsCount(List<Integer> integers) {
		Query query;
		query = getSession().createQuery(
				"select count(*) from " + Blog.class.getSimpleName() + " where  "+ " commentNums " + ">=? and typeId not in (:dislikeType)" );
		query.setParameterList("dislikeType", integers);
		query.setParameter(0, Constants.Recommend.RECOMMEND_COMMENT);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public int getRecommendBlogsByReadNumsCount(List<Integer> integers) {
		Query query;
		query = getSession().createQuery(
				"select count(*) from " + Blog.class.getSimpleName() + " where "+ "  viewNums" + ">=? and typeId not in (:dislikeType) " );
		query.setParameterList("dislikeType", integers);
		query.setParameter(0, Constants.Recommend.RECOMMEND_READNUMS);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public int getRecommendBlogsByDateCount(List<Integer> integers) {
		Query query;
		query = getSession().createQuery(
				"select count(*) from " + Blog.class.getSimpleName() + " where typeId not in (:dislikeType) "+ " ");
		query.setParameterList("dislikeType", integers);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public int getPreferenceByDescCount(List<Integer> integers) {
		Query query;
		query = getSession().createQuery(
				"select count(*) from " + Preference.class.getSimpleName() + " where typeId not in (:dislikeType) ");
		query.setParameterList("dislikeType", integers);
		return ((Number) query.uniqueResult()).intValue();
	}
}
