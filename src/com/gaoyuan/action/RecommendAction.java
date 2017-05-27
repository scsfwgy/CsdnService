package com.gaoyuan.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gaoyuan.base.BaseAction;
import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.Recommend;
import com.gaoyuan.service.RecommendService;



@Controller
public class RecommendAction extends BaseAction {
	@Autowired
	private RecommendService mRecommendService;
	
	/**
	 * 获取没有登录情况下的推荐博客列表。 思路：推荐优质博客即可，这里获取博客专家的博客即可，不用做什么分类。
	 * typeId:0:默认获取；1：获取评论数大于30的博客；2：获取阅读量大于5000的博客；3：获取最新的博客。
	 * 请使用：getRecommendBlogsByTypeIdByLogined
	 */
	@Deprecated
	public String getRecommendBlogsByTypeId() {
		JSONObject jsonObject;
		List<Blog> blogs;
		int orderId;
		try {
			jsonObject = getObject(ServletActionContext.getRequest());
			// 第一步：校验参数完整性
			if (paramsIsFull(jsonObject, "nowPager")) {
				nowPager = getIntParameter(jsonObject, "nowPager");
				pagerSize = getIntParameter(jsonObject, "pagerSize");
				orderId= getIntParameter(jsonObject, "orderId");
				blogs = mRecommendService.getRecommendBlogsByTypeId(nowPager,
						pagerSize, orderId);
				// 第二步：判断返回值是否为空
				if (paramsIsValid(blogs)) {
					success(blogs);
				} else {
					empty();
				}
			} else {
				// 参数不完整，提醒前台完善参数。
				requestError();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 异常，返回异常信息。
			serviceError();

		}
		return SUCCESS;
	}
	/** 获取没有登录情况下的推荐博客列表。
	 * 思路：推荐优质博客即可，这里获取博客专家的博客即可，不用做什么分类。
	 *  typeId:0:默认获取；1：获取评论数大于30的博客；2：获取阅读量大于5000的博客；3：获取最新的博客;4:获取用户推荐的文章，注意：如果数量少于pagerSize,不执行该类型。
	 * 	dislikeType:1_2_3_...以拼接形式获取。【该字段为登录用户准备，选择的是不喜欢的模块】
	 */
	public String getRecommendBlogsByTypeIdByLogined() {
		JSONObject jsonObject;
		List<Blog> blogs;
		int orderId;
		String dislikeType;
		try {
			jsonObject = getObject(ServletActionContext.getRequest());
			// 第一步：校验参数完整性
			if (paramsIsFull(jsonObject, "nowPager")) {
				nowPager = getIntParameter(jsonObject, "nowPager");
				pagerSize = getIntParameter(jsonObject, "pagerSize");
				orderId= getIntParameter(jsonObject, "orderId");
				dislikeType= getStringParameter(jsonObject, "dislikeType");
				if(dislikeType==null)dislikeType="-1";
				List<Integer> integers=new ArrayList<Integer>();
				try {
					String[] strings=dislikeType.split("_");
					for (int i = 0; i < strings.length; i++) {
						integers.add(Integer.parseInt(strings[i]));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					requestError("请开发人员确认dislikeType字段是否正确");
					return SUCCESS;
				}
				
				blogs = mRecommendService.getRecommendBlogsByTypeId(nowPager,
						pagerSize, orderId,integers);
				// 第二步：判断返回值是否为空
				if (paramsIsValid(blogs)) {
					success(blogs);
				} else {
					empty();
				}
			} else {
				// 参数不完整，提醒前台完善参数。
				requestError();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 异常，返回异常信息。
			serviceError();

		}
		return SUCCESS;
	}
	/** 搜索功能
	 * 
	 */
	public String getSearchByKeyWords() {
		JSONObject jsonObject;
		List<Blog> blogs;
		String keyWords;
		int searchType;
		try {
			jsonObject = getObject(ServletActionContext.getRequest());
			// 第一步：校验参数完整性
			if (paramsIsFull(jsonObject, "nowPager","keyWords")) {
				nowPager = getIntParameter(jsonObject, "nowPager");
				pagerSize = getIntParameter(jsonObject, "pagerSize");
				keyWords= getStringParameter(jsonObject, "keyWords");;
				searchType=getIntParameter(jsonObject, "searchType");
				searchType=searchType==-1?0:searchType;
				blogs = mRecommendService.getSearchByKeyWords(nowPager,
						pagerSize, keyWords,searchType);
				// 第二步：判断返回值是否为空
				if (paramsIsValid(blogs)) {
					success(blogs);
				} else {
					empty();
				}
			} else {
				// 参数不完整，提醒前台完善参数。
				requestError();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 异常，返回异常信息。
			serviceError();

		}
		return SUCCESS;
	}
	// 分页获取：获取推荐文章
	public String getRecommendBlogs() {
		JSONObject jsonObject;
		List<Blog> blogs;
		Recommend recommend;
		try {
			recommend = (Recommend) getObject(
					ServletActionContext.getRequest(),
					Recommend.class);
			// 第一步：校验参数完整性
			if (paramsIsValid(recommend)) {
				blogs = mRecommendService.getRecommendBlogs(recommend);
				// 第二步：判断返回值是否为空
				if (paramsIsValid(blogs)) {
					success(blogs, null);
				} else {
					empty(null);
				}
			} else {
				// 参数不完整，提醒前台完善参数。
				requestError();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 异常，返回异常信息。
			serviceError();

		}
		return SUCCESS;
	}
}
