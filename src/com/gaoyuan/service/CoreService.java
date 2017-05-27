package com.gaoyuan.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.Expert;

@Transactional
public interface CoreService {


	
	List<Expert> getExpertsByReadNumsDesc(int nowPager, int pagerSize,
			int typeId);


	List<Expert> getExpertsByArtcialNumsDesc(int nowPager, int pagerSize,
			int typeId);


	
	List<Blog> getExpertBlogsByPager(int nowPager, int pagerSize,
			String id_expert);

	List<Blog> searchBlogsByKeyWords(int nowPager, int pagerSize,
			String keyWords, int typeId);


	List<Expert> getExpertsByTypeId(int typeId, int orderId, int nowPager,
			int pagerSize);


	List<Blog> getExpertBlogsByPager(int nowPager, int pagerSize,
			String id_expert, int orderId);
}
