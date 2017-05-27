package com.gaoyuan.dao;

import java.util.List;

import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.Preference;
import com.gaoyuan.bean.Recommend;



public interface RecommendDao extends BaseDao {

	
	List<Blog> getDefRecommendBlogs(Recommend recommend);

	List<Blog> getRecommendBlogsByDef(int nowPager, int pagerSize);

	List<Blog> getRecommendBlogsByComments(int nowPager, int pagerSize);

	List<Blog> getRecommendBlogsByReadNums(int nowPager, int pagerSize);

	List<Blog> getRecommendBlogsByDate(int nowPager, int pagerSize);

	List<Blog> getRecommendBlogsByDef(int nowPager, int pagerSize,
			List<Integer> dislikeType);

	List<Blog> getRecommendBlogsByComments(int nowPager, int pagerSize,
			List<Integer> dislikeType);

	List<Blog> getRecommendBlogsByReadNums(int nowPager, int pagerSize,
			List<Integer> dislikeType);

	List<Blog> getRecommendBlogsByDate(int nowPager, int pagerSize,
			List<Integer> dislikeType);

	List<Preference> getPreferenceByDesc(int nowPager, int pagerSize,
			List<Integer> dislikeType);

	int getRecommendBlogsByDefCount(List<Integer> integers);

	int getRecommendBlogsByCommentsCount(List<Integer> integers);

	int getRecommendBlogsByReadNumsCount(List<Integer> integers);

	int getRecommendBlogsByDateCount(List<Integer> integers);

	int getPreferenceByDescCount(List<Integer> integers);


}
