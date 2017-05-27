package com.gaoyuan.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.gaoyuan.base.Constants;
import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.Expert;
import com.gaoyuan.dao.CoreDao;



@Repository
public class CoreDaoBean extends BaseDaoBean implements CoreDao {


	@SuppressWarnings("unchecked")
	@Override
	public List<Expert> getExpertsByReadNumsDesc(int nowPager, int pagerSize,
			int typeId) {
		if (typeId == 0) {
			return queryByPageByOrder(Expert.class, nowPager, pagerSize,
					"readNums", "desc");
		}
		return queryByPageByOrder(Expert.class, "typeId", typeId, nowPager,
				pagerSize,
				"readNums",
				"desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expert> getExpertsByArtcialNumsDesc(int nowPager,
			int pagerSize, int typeId) {
		return queryByPageByOrder(Expert.class, "typeId", typeId, nowPager,
				pagerSize,
				"articleNums", "desc");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getExpertBlogsByPager(int nowPager, int pagerSize,
			String id_expert) {
		return queryByPageByOrder(Blog.class, "id_author", id_expert, nowPager,
				pagerSize, "publishDateTime", "desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> searchBlogsByKeyWords(int nowPager, int pagerSize,
			String processWord) {
		Query query = getSession().createSQLQuery(
				"select * from Blog where title REGEXP ? or id_author REGEXP ?")
				.addEntity(Blog.class);// "a|b|c"
		query.setParameter(0, processWord);
		query.setParameter(1, processWord);
		query.setFirstResult(pagerSize * (nowPager - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	@Override
	public Expert getExpertById(String id_author) {
		List queryAll = queryAll(Expert.class, "id_expert", id_author);
		if (queryAll != null && !queryAll.isEmpty())
			return (Expert) queryAll(Expert.class, "id_expert", id_author).get(
					0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expert> getExpertsByDef(int typeId, int nowPager, int pagerSize) {
		if (typeId == 0)
			return queryByPage(Expert.class, nowPager, pagerSize);
		else
			return queryByPage(Expert.class, "typeId", typeId, nowPager,
					pagerSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expert> getExpertsByReadDesc(int typeId, int nowPager,
			int pagerSize) {
		if(typeId==0)return queryByPageByOrder(Expert.class, nowPager, pagerSize, "readNums", "Desc",true);
		else
		return queryByPageByOrder(Expert.class, "typeId", typeId, nowPager, pagerSize, "readNums", "Desc",true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expert> getExpertsByReadAsc(int typeId, int nowPager,
			int pagerSize) {
		if(typeId==0) return queryByPageByOrder(Expert.class,  nowPager, pagerSize, "readNums", "Asc",true);
		else
			
		 return queryByPageByOrder(Expert.class, "typeId", typeId, nowPager, pagerSize, "readNums", "Asc",true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expert> getExpertsByArticlesDesc(int typeId, int nowPager,
			int pagerSize) {
		if(typeId==0)
			return queryByPageByOrder(Expert.class, nowPager, pagerSize, "articleNums", "Desc",true);
		else
			
		return queryByPageByOrder(Expert.class, "typeId", typeId, nowPager, pagerSize, "articleNums", "Desc",true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expert> getExpertsByArticlesAsc(int typeId, int nowPager,
			int pagerSize) {
		if (typeId == 0)
			return queryByPageByOrder(Expert.class, nowPager, pagerSize,
					"articleNums", "Asc", true);
		else

			return queryByPageByOrder(Expert.class, "typeId", typeId, nowPager,
					pagerSize, "articleNums", "Asc", true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getExpertBlogsByDef(String id_expert, int nowPager,
			int pagerSize) {
		return queryByPage(Blog.class, "id_author", id_expert, nowPager, pagerSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getExpertBlogsByReadDesc(String id_expert, int nowPager,
			int pagerSize) {
		return queryByPageByOrder(Blog.class,"id_author", id_expert, nowPager, pagerSize, "viewNums", "Desc",true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getExpertBlogsByReadAsc(String id_expert, int nowPager,
			int pagerSize) {
		return queryByPageByOrder(Blog.class,"id_author", id_expert, nowPager, pagerSize, "viewNums", "Asc",true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getExpertBlogsByDateDesc(String id_expert, int nowPager,
			int pagerSize) {
		return queryByPageByOrder(Blog.class,"id_author", id_expert, nowPager, pagerSize, "publishDateTime", "Desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getExpertBlogsByDateAsc(String id_expert, int nowPager,
			int pagerSize) {
		return queryByPageByOrder(Blog.class,"id_author", id_expert, nowPager, pagerSize, "publishDateTime", "Asc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getExpertBlogsByCommitDesc(String id_expert,
			int nowPager, int pagerSize) {
		return queryByPageByOrder(Blog.class,"id_author", id_expert, nowPager, pagerSize, "commentNums", "Desc",true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getExpertBlogsByCommitAsc(String id_expert, int nowPager,
			int pagerSize) {
		return queryByPageByOrder(Blog.class,"id_author", id_expert, nowPager, pagerSize, "commentNums", "Asc",true);
	}

	@Override
	public Blog getBlogByBlogId(String id_blog) {
		List<?> queryAll = queryAll(Blog.class, "id_blog", id_blog);
		if(queryAll==null||queryAll.isEmpty())return null;
		return (Blog) queryAll.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> searchBlogByTitle(int nowPager, int pagerSize,
			String processWord) {
		Query query = getSession().createSQLQuery(
				"select * from Blog where title REGEXP ?")
				.addEntity(Blog.class);// "a|b|c"
		query.setParameter(0, processWord);
		query.setFirstResult(pagerSize * (nowPager - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	@Override
	public Expert searchExpertByName_CsdnId(int nowPager, int pagerSize,
			String str) {
		Query query = getSession().createSQLQuery(
				"select * from Expert where name like ? or id_expert like ?")
				.addEntity(Expert.class);// "a|b|c"
		query.setParameter(0, str);
		query.setParameter(1, str);
		query.setFirstResult(pagerSize * (nowPager - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return  (Expert) query.list().get(0);
		} else {
			return null;
		}
	}
}
