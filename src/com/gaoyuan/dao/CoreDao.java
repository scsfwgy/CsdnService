package com.gaoyuan.dao;

import java.util.List;

import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.Expert;




public interface CoreDao extends BaseDao {


	/**
	 * 
	 * 
	 * @param dbTypeId
	 */
	List<Expert> getExpertsByReadNumsDesc(int nowPager, int pagerSize,
			int dbTypeId);

	/**
	 * 
	 * 
	 * @param dbTypeId
	 */
	List<Expert> getExpertsByArtcialNumsDesc(int nowPager, int pagerSize,
			int dbTypeId);
	
	List<Blog> getExpertBlogsByPager(int nowPager, int pagerSize,
			String id_expert);


	List<Blog> searchBlogsByKeyWords(int nowPager, int pagerSize,
			String processWord);

	
	Expert getExpertById(String id_author);

	List<Expert> getExpertsByDef(int typeId, int nowPager, int pagerSize);

	List<Expert> getExpertsByReadDesc(int typeId, int nowPager, int pagerSize);

	List<Expert> getExpertsByReadAsc(int typeId, int nowPager, int pagerSize);

	List<Expert> getExpertsByArticlesDesc(int typeId, int nowPager,
			int pagerSize);

	List<Expert> getExpertsByArticlesAsc(int typeId, int nowPager, int pagerSize);

	List<Blog> getExpertBlogsByDef(String id_expert, int nowPager, int pagerSize);

	List<Blog> getExpertBlogsByReadDesc(String id_expert, int nowPager,
			int pagerSize);

	List<Blog> getExpertBlogsByReadAsc(String id_expert, int nowPager,
			int pagerSize);

	List<Blog> getExpertBlogsByDateDesc(String id_expert, int nowPager,
			int pagerSize);

	List<Blog> getExpertBlogsByDateAsc(String id_expert, int nowPager,
			int pagerSize);

	List<Blog> getExpertBlogsByCommitDesc(String id_expert, int nowPager,
			int pagerSize);

	List<Blog> getExpertBlogsByCommitAsc(String id_expert, int nowPager,
			int pagerSize);

	Blog getBlogByBlogId(String id_blog);

	List<Blog> searchBlogByTitle(int nowPager, int pagerSize, String processWord);

	Expert searchExpertByName_CsdnId(int nowPager, int pagerSize, String string);


}
