package com.gaoyuan.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gaoyuan.base.BaseAction;
import com.gaoyuan.base.Constants;
import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.Expert;
import com.gaoyuan.service.CoreService;



@Controller
public class CoreAction extends BaseAction {
	@Autowired
	private CoreService mCoreService;

	/**
	 * 获取不同领域的专家类型。
	 * typeId：1：移动开发；2：Web前端；3：架构设计；4：编程语言；5:互联网；6：
	 * 数据库；7：系统运维；8：云计算；9：研发管理；0：所有分类集合（default）。
	 * orderId:0：默认顺序（default）;1:按照阅读量递减排序；2：按照阅读量递增排序；3：按照创作数递减；4：按照创作递增
	 */
	public String getExpertsByTypeId(){
		JSONObject jsonObject;
		List<Expert> experts;
		int typeId;// 专家类型。
		int orderId;//排序类型
		try {
			jsonObject = getObject(ServletActionContext
					.getRequest());
			// 第一步：校验参数完整性
			if (paramsIsFull(jsonObject,"typeId","orderId", "nowPager")) {
				typeId = getIntParameter(jsonObject, "typeId");
				orderId= getIntParameter(jsonObject, "orderId");
				nowPager = getIntParameter(jsonObject, "nowPager");
				pagerSize = getIntParameter(jsonObject, "pagerSize");
				experts = mCoreService.getExpertsByTypeId(typeId,orderId,nowPager,pagerSize);
				//第二步：判断返回值是否为空
				if (paramsIsValid(experts)) {
					success(experts, null);
				} else {
					empty("数据集合为空");
				}
			} else {
				// 参数不完整，提醒前台完善参数。
				requestError("参数不完整，请确认！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 异常，返回异常信息。
			serviceError("服务器异常，请重试！");

		}
		return SUCCESS;
	}
	
	
	
	/**
	 * 根据专家id获取专家的所有博客文章。
	 * orderId:0：默认顺序（default）;1:按照阅读量递减排序；2：按照阅读量递增排序；
	 * 3：按照创作日期递减；4：按照日期递增;5:按照评论量递减排序；6：按照评论量递增排序；
	 */
	public String getAuthorBlogsByAuthorId() {
		JSONObject jsonObject;
		List<Blog> blogs;
		String id_author;// 专家id。
		int orderId;
		try {
			jsonObject = getObject(ServletActionContext.getRequest());
			// 第一步：校验参数完整性
			if (paramsIsFull(jsonObject, "orderId","id_author","nowPager")) {
				nowPager = getIntParameter(jsonObject, "nowPager");
				pagerSize = getIntParameter(jsonObject, "pagerSize");
				id_author = getStringParameter(jsonObject, "id_author");
				orderId= getIntParameter(jsonObject, "orderId");;
				blogs = mCoreService.getExpertBlogsByPager(nowPager,
						pagerSize, id_author,orderId);
				// 第二步：判断返回值是否为空
				if (paramsIsValid(blogs)) {
					success(blogs, null);
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

	// 分页：搜索博客,请使用：recommendAction_getSearchByKeyWords
	@Deprecated
	public String searchBlogsByKeyWords() {
		JSONObject jsonObject;
		List<Blog> blogs;
		int typeId;// 排序依据
		String keyWords;// 关键字
		try {
			jsonObject = getObject(ServletActionContext.getRequest());
			// 第一步：校验参数完整性
			if (paramsIsFull(jsonObject, "nowPager", "keyWords")) {
				nowPager = getIntParameter(jsonObject, "nowPager");
				pagerSize = getIntParameter(jsonObject, "pagerSize");
				keyWords = getStringParameter(jsonObject, "keyWords");
				typeId = getIntParameter(jsonObject, "typeId");
				if (!paramsIsValid(pagerSize)) {
					pagerSize = Constants.Pager.DEF_PERPAGER_SIZE;
				}
				blogs = mCoreService.searchBlogsByKeyWords(nowPager,
						pagerSize, keyWords, typeId);
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
