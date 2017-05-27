package com.gaoyuan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aj.org.objectweb.asm.Type;

import com.gaoyuan.base.BaseService;
import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.Expert;
import com.gaoyuan.bean.Preference;
import com.gaoyuan.bean.Recommend;
import com.gaoyuan.dao.CoreDao;
import com.gaoyuan.dao.RecommendDao;
import com.gaoyuan.service.RecommendService;
import com.gaoyuan.utils.MyNlpUtils;
import com.gaoyuan.utils.MyPagerUtils;
import com.gaoyuan.utils.MyParameterUtils;
import com.gaoyuan.utils.MyRandomUtils;
import com.gaoyuan.utils.MyStringUtils;


@Service
public class RecommendServiceImpl extends BaseService implements RecommendService {

	@Autowired
	private RecommendDao mRecommendDao;
	@Autowired
	private CoreDao mCoreDao;

	@Override
	public List<Blog> getRecommendBlogs(Recommend recommend) {
		recommend
				.setNowPager(MyPagerUtils.getNowPager(recommend.getNowPager()));
		recommend
.setPagerSize(MyPagerUtils.getPagerSize(recommend
				.getPagerSize()));

		List<Blog> blogs = new ArrayList<Blog>();
		// 没有登录时需要推荐的文章
		if (recommend.getId_user() == null) {

			// 依据？
			blogs = mRecommendDao.getDefRecommendBlogs(recommend);
			for (Blog blog : blogs) {
				Expert expert= mCoreDao.getExpertById(blog.getId_author());
				if (expert == null)
					continue;
				blog.setAuthor(expert.getName());
				blog.setHeadImg(expert.getHeadImg());
			}
		} else {

		}
		return blogs;
	}

	/**
	 * 获取没有登录情况下的推荐博客列表。 思路：推荐优质博客即可，这里获取博客专家的博客即可，不用做什么分类。
	 * typeId:0:默认获取；1：获取评论数大于30的博客；2：获取阅读量大于5000的博客；3：获取最新的博客。
	 * 
	 */
	@Override
	public List<Blog> getRecommendBlogsByTypeId(int nowPager, int pagerSize,
			int orderId) {
		pagerSize = getAvailablePageSize(pagerSize);
		// 如果typeId为-1，说明前端没有确认按照何种方式获取，就采用随机方式获取
		if (orderId == -1)
			orderId = MyRandomUtils.getRangeRandomInt(0, 3);
		
		if (orderId == 0) {
			//这里随机设置起始页
			nowPager=MyRandomUtils.getRangeRandomInt(0, 100);
			return mRecommendDao.getRecommendBlogsByDef(nowPager, pagerSize);
		} else if (orderId == 1) {
			//这里随机设置起始页
			nowPager=MyRandomUtils.getRangeRandomInt(0, 20);
			return mRecommendDao.getRecommendBlogsByComments(nowPager,
					pagerSize);
		} else if (orderId == 2) {
			//这里随机设置起始页
			nowPager=MyRandomUtils.getRangeRandomInt(0, 20);
			return mRecommendDao.getRecommendBlogsByReadNums(nowPager,
					pagerSize);
		} else if (orderId == 3) {
			//这里随机设置起始页
			nowPager=MyRandomUtils.getRangeRandomInt(0,100);
			return mRecommendDao.getRecommendBlogsByDate(nowPager, pagerSize);
		}
		return null;
	}

	/*
	 * searchType:搜索指定类型，0：默认搜索，直接搜索标题；1：搜索指定人的博客
	 */
	@Override
	public List<Blog> getSearchByKeyWords(int nowPager, int pagerSize,
			String keyWords, int searchType) {
		pagerSize = getAvailablePageSize(pagerSize);
		// 这里搜索指定博客专家
		if (searchType == 1) {
			Expert expert = mCoreDao.searchExpertByName_CsdnId(0,
					pagerSize, "%" + keyWords + "%");
			//int type = MyRandomUtils.getRangeRandomInt(0, 3);
			if (expert != null) {
				//if (type == 0)
//					return mCoreDao.getExpertBlogsByDef(expert.getId_expert(),
//							nowPager, pagerSize);
//				if (type == 1)
					return mCoreDao.getExpertBlogsByCommitDesc(
							expert.getId_expert(), nowPager, pagerSize);
//				if (type == 2)
//					return mCoreDao.getExpertBlogsByDateDesc(
//							expert.getId_expert(), nowPager, pagerSize);
//				if (type == 3)
//					return mCoreDao.getExpertBlogsByReadDesc(
//							expert.getId_expert(), nowPager, pagerSize);
			}
			return null;
		}

		// 下面开始搜索博客
		// 第一步：获取有效关键字。
		// TODO:重点在这里。
		String processWord = porcessKeyWords(keyWords);
		if (processWord.equals("error")) {
			processWord = keyWords;
		}

		/*
		 * 搜索的表与字段： 1、博客表，字段：title 2、博客专家表，字段：name、job、typeName
		 * 3、博主表,字段：title、descb、csdn_id【暂不处理】
		 */
		// List<Expert> expertListByExpertSearch = mCoreDao
		// .searchExpertByName_Job_TypeName(nowPager, pagerSize,
		// processWord);
		// List<Blog> blogListByBlogSearch =
		// mCoreDao.searchBlogByTitle(nowPager,
		// pagerSize, processWord);
		//
		// // 查询
		// List<Blog> blogFromExpert;
		// if (expertListByExpertSearch != null
		// && !expertListByExpertSearch.isEmpty())
		// for (Expert expert : expertListByExpertSearch) {
		// List<Blog>
		// }

		// temp
		List<Blog> blogs = mCoreDao.searchBlogsByKeyWords(nowPager, pagerSize,
				processWord);
		if (!MyParameterUtils.paramsIsValid(blogs))
			return null;
		// 封装
		for (Blog blog : blogs) {
			Expert expert = mCoreDao.getExpertById(blog.getId_author());
			if (expert == null)
				continue;
			blog.setAuthor(expert.getName());
			blog.setHeadImg(expert.getHeadImg());
		}

		return blogs;
	}
	
	/**
	 * 切词处理
	 */
	private String porcessKeyWords(String keyWords) {
		// 加工字符串
		// keyWords = MyRegexUtils.getNoRepeatStr(keyWords);// 去重
		keyWords=keyWords.substring(0,keyWords.length()<=10?keyWords.length():10);//只取前10个字。
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
		System.out.println(new RecommendServiceImpl().porcessKeyWords("郭林"));
	}

	/**
	 * 这里做各种逻辑处理为了让用户看到不同的优质内容。推荐之前判断总条数，进行预判随机获取随机页码。
	 */
	@Override
	public List<Blog> getRecommendBlogsByTypeId(int nowPager, int pagerSize,
			int orderId, List<Integer> dislikeType) {
		// 总条数
		int count = 0;
		// 总页数
		int totalPager = 0;
		pagerSize = getAvailablePageSize(pagerSize);
		List<Integer> integers = new ArrayList<Integer>();
		integers.addAll(dislikeType);

		// 转化typeId
		if (!(integers.size() == 1 && integers.get(0) == -1))
			for (int i = 0; i < dislikeType.size(); i++) {
				integers.set(i, getExpertType(integers.get(i)));
			}
		System.out.println("不喜欢的模块" + integers);

		// 如果typeId为-1，说明前端没有确认按照何种方式获取，就采用随机方式分配
		if (orderId == -1)
			orderId = MyRandomUtils.getRangeRandomInt(0, 4);
		System.out.println("推荐类型：" + orderId);
		if (orderId == 0) {
			// 这里随机设置起始页
			// System.out.println(System.currentTimeMillis());
			count = mRecommendDao.getRecommendBlogsByDefCount(integers);
			totalPager = getTotalPager(count, pagerSize);
			nowPager = MyRandomUtils.getRangeRandomInt(0, totalPager - 1);
			// System.out.println(System.currentTimeMillis());
			System.out.println("getRecommendBlogsByTypeId当前页码：" + nowPager);
			return mRecommendDao.getRecommendBlogsByDef(nowPager, pagerSize,
					integers);
		} else if (orderId == 1) {
			count = mRecommendDao.getRecommendBlogsByCommentsCount(integers);
			totalPager = getTotalPager(count, pagerSize);
			nowPager = MyRandomUtils.getRangeRandomInt(0, totalPager - 1);
			System.out.println("getRecommendBlogsByTypeId当前页码：" + nowPager);
			List<Blog> recommendBlogsByComments = mRecommendDao
					.getRecommendBlogsByComments(nowPager, pagerSize, integers);
			if (recommendBlogsByComments == null
					|| recommendBlogsByComments.size() < pagerSize) {
				return getRecommendBlogsByTypeId(nowPager, pagerSize, -1, dislikeType);// 注意-1
			} else {
				return recommendBlogsByComments;
			}
		} else if (orderId == 2) {
			count = mRecommendDao.getRecommendBlogsByReadNumsCount(integers);
			totalPager = getTotalPager(count, pagerSize);
			nowPager = MyRandomUtils.getRangeRandomInt(0, totalPager - 1);
			System.out.println("getRecommendBlogsByTypeId当前页码：" + nowPager);
			List<Blog> recommendBlogsByReadNums = mRecommendDao
					.getRecommendBlogsByReadNums(nowPager, pagerSize, integers);
			if (recommendBlogsByReadNums == null
					|| recommendBlogsByReadNums.size() < pagerSize) {
				return getRecommendBlogsByTypeId(nowPager, pagerSize, -1, dislikeType);// 注意-1
			} else {
				return recommendBlogsByReadNums;
			}
		} else if (orderId == 3) {
			count = mRecommendDao.getRecommendBlogsByDateCount(integers);
			totalPager = getTotalPager(count, pagerSize);
			nowPager = MyRandomUtils.getRangeRandomInt(0, (totalPager - 1)/2);
			System.out.println("getRecommendBlogsByTypeId当前页码：" + nowPager);
			return mRecommendDao.getRecommendBlogsByDate(nowPager, pagerSize,
					integers);
		} else if (orderId == 4) {
			count = mRecommendDao.getPreferenceByDescCount(integers);
			totalPager = getTotalPager(count, pagerSize);
			nowPager = MyRandomUtils.getRangeRandomInt(0, totalPager - 1);
			System.out.println("getRecommendBlogsByTypeId当前页码：" + nowPager);
			// 首先根据dislikeType去筛选用户推荐的博客，按照博客推荐次数从高到底推荐
			List<Preference> preferences = mRecommendDao.getPreferenceByDesc(
					nowPager, pagerSize, integers);
			// 如果获取的数量少于pagerSize，不执行。
			if (preferences == null || preferences.size() < pagerSize) {
				return getRecommendBlogsByTypeId(nowPager, pagerSize, -1, dislikeType);// 注意-1
			} else {
				List<Blog> blogs = new ArrayList<>();
				for (Preference preference : preferences) {
					Blog blogByBlogId = mCoreDao.getBlogByBlogId(preference
							.getId_blog());
					if (blogByBlogId != null)
						blogs.add(blogByBlogId);
				}
				return blogs;
			}
		}
		return null;
	}

	private int getTotalPager(int count, int pagerSize) {
		 int totalpage = count / pagerSize;
		  if (count % pagerSize > 0) {
		   totalpage++;
		  }
		return totalpage;
	}
}
