package com.gaoyuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoyuan.base.BaseService;
import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.Expert;
import com.gaoyuan.dao.CoreDao;
import com.gaoyuan.service.CoreService;
import com.gaoyuan.utils.MyNlpUtils;
import com.gaoyuan.utils.MyParameterUtils;

@Service
public class CoreServiceImpl extends BaseService implements CoreService {

	@Autowired
	private CoreDao coreDao;


	@Override
	public List<Expert> getExpertsByReadNumsDesc(int nowPager, int pagerSize,
			int typeId) {
		// 包装typeId
		int dbTypeId = 0;// default。
		dbTypeId = getExpertType(typeId);

		return coreDao
				.getExpertsByReadNumsDesc(nowPager, pagerSize, dbTypeId);
	}
	@Override
	public List<Expert> getExpertsByArtcialNumsDesc(int nowPager,
			int pagerSize, int typeId) {
		// 包装typeId
		int dbTypeId = 0;// default。
		dbTypeId = getExpertType(typeId);
		return coreDao.getExpertsByArtcialNumsDesc(nowPager, pagerSize,
				dbTypeId);
	}


	
	@Override
	public List<Blog> getExpertBlogsByPager(int nowPager, int pagerSize,
			String id_expert) {
		pagerSize=getAvailablePageSize(pagerSize);
		return coreDao.getExpertBlogsByPager(nowPager, pagerSize, id_expert);
	}
	@Override
	public List<Blog> searchBlogsByKeyWords(int nowPager, int pagerSize,
			String keyWords, int typeId) {
		// 第一步：获取有效关键字。
		// TODO:重点在这里。
		String processWord = porcessKeyWords(keyWords);
		if (processWord.equals("error")) {
			return null;
		}
		// 第二步排序依据，定义：：0：默认顺序（Def）、1：按照文章标题匹配度、2：按照作者名字匹配度、3：按照阅读量、4：按照评论量、5：随机（每次都不确定）
		// temp
		List<Blog> blogs = coreDao.searchBlogsByKeyWords(nowPager, pagerSize,
				processWord);
		if (!MyParameterUtils.paramsIsValid(blogs))
			return null;
		// 封装
		for (Blog blog : blogs) {
			Expert expert = coreDao.getExpertById(blog.getId_author());
			if(expert==null)continue;
			blog.setAuthor(expert.getName());
			blog.setHeadImg(expert.getHeadImg());
		}

		return blogs;
	}

	/**
	 * TODO
	 */
	private String porcessKeyWords(String keyWords) {
		// 加工字符串
		// keyWords = MyRegexUtils.getNoRepeatStr(keyWords);// 去重
		// 获取切词结果
		List<String> listWordsByDesc = MyNlpUtils.getListWordsByDesc(keyWords,
				0);
		if (listWordsByDesc == null)
			return null;
		String processWord = "";
		for (int i = 0; i < listWordsByDesc.size(); i++) {
			if (i != listWordsByDesc.size() - 1)
				processWord += listWordsByDesc.get(i) + "|";
			else
				processWord += listWordsByDesc.get(i);
		}
		System.out.println("将要去数据库匹配的分词：" + processWord);
		return processWord;
	}

	public static void main(String[] args) {
		new CoreServiceImpl().porcessKeyWords("CAST函数用于将某种数据类型的表达式显式转换为另一种数据类型。CAST()函数的参数是一个表达式，它包括用AS关键字分隔的源值和目标数据类型。");

	}
	@Override
	public List<Expert> getExpertsByTypeId(int typeId, int orderId,
			int nowPager, int pagerSize) {
		pagerSize=getAvailablePageSize(pagerSize);
		typeId=getExpertType(typeId);
		if(orderId==0){
			return coreDao.getExpertsByDef(typeId,nowPager,pagerSize);
		}else if(orderId==1){
			return coreDao.getExpertsByReadDesc(typeId,nowPager,pagerSize);
		}else if(orderId==2){
			return coreDao.getExpertsByReadAsc(typeId,nowPager,pagerSize);
		}else if(orderId==3){
			return coreDao.getExpertsByArticlesDesc(typeId,nowPager,pagerSize);
		}else if(orderId==4){
			return coreDao.getExpertsByArticlesAsc(typeId,nowPager,pagerSize);
		}
		
		return null;
	}

	
	/**
	 * 根据专家id获取专家的所有博客文章。
	 * orderId:0：默认顺序（default）;1:按照阅读量递减排序；2：按照阅读量递增排序；
	 * 3：按照创作日期递减；4：按照日期递增;5:按照评论量递减排序；6：按照评论量递增排序；
	 */
	@Override
	public List<Blog> getExpertBlogsByPager(int nowPager, int pagerSize,
			String id_expert, int orderId) {
		pagerSize=getAvailablePageSize(pagerSize);
		if(orderId==0){
			return coreDao.getExpertBlogsByDef(id_expert,nowPager,pagerSize);
		}else if(orderId==1){
			return coreDao.getExpertBlogsByReadDesc(id_expert,nowPager,pagerSize);
		}else if(orderId==2){
			return coreDao.getExpertBlogsByReadAsc(id_expert,nowPager,pagerSize);
		}else if(orderId==3){
			return coreDao.getExpertBlogsByDateDesc(id_expert,nowPager,pagerSize);
		}else if(orderId==4){
			return coreDao.getExpertBlogsByDateAsc(id_expert,nowPager,pagerSize);
		}else if(orderId==5){
			return coreDao.getExpertBlogsByCommitDesc(id_expert,nowPager,pagerSize);
		}else if(orderId==6){
			return coreDao.getExpertBlogsByCommitAsc(id_expert,nowPager,pagerSize);
		}
		
		return null;
	}


}
