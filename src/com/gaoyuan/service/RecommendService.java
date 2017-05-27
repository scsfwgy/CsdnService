package com.gaoyuan.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.Recommend;


@Transactional
public interface RecommendService {

	
	List<Blog> getRecommendBlogs(Recommend recommend);

	List<Blog> getRecommendBlogsByTypeId(int nowPager, int pagerSize,
			int orderId);

	List<Blog> getSearchByKeyWords(int nowPager, int pagerSize, String keyWords, int searchType);

	List<Blog> getRecommendBlogsByTypeId(int nowPager, int pagerSize,
			int orderId, List<Integer> integers);


}
