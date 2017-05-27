package com.gaoyuan.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gaoyuan.base.BaseAction;
import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.Expert;
import com.gaoyuan.bean.PStar;
import com.gaoyuan.bean.PUser;
import com.gaoyuan.bean.Preference;
import com.gaoyuan.service.RecommendService;
import com.gaoyuan.service.UserService;

/**
 * @author wgyscsf
 * @email wgyscsf@163.com
 * @dateTime 2017 2017-4-17 下午5:11:48
 * @details
 */
@Controller
public class UserAction extends BaseAction {
	@Autowired
	private UserService mUserService;

	/**
	 * 登录，验证用户信息。 三种登录方式：csdn_id+密码；手机号+密码；邮箱+密码。需要获取用户的三种方式之一+密码。
	 * 注意：严禁保存用户密码，仅仅拿来做匹配工作。
	 * 
	 */
	public String login() {
		JSONObject jsonObject;
		PUser pUser;
		String account;
		String password;
		try {
			jsonObject = getObject(ServletActionContext.getRequest());
			// 第一步：校验参数完整性
			if (paramsIsFull(jsonObject, "account", "password")) {
				account = getStringParameter(jsonObject, "account");
				password = getStringParameter(jsonObject, "password");
				pUser = mUserService.login(account, password);
				// 第二步：判断返回值是否为空
				if (paramsIsValid(pUser)) {
					success(pUser);
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
	// 更新用户信息
	public String updatePUser() {
		PUser pUser;
		boolean isAdd;
		try {
			pUser = (PUser) getObject(ServletActionContext.getRequest(),
					PUser.class);
			// 第一步：校验参数完整性
			if (paramsIsValid(pUser.getId())) {
				isAdd = mUserService.updatePUser(pUser);
				// 第二步：判断返回值是否为空
				if (isAdd) {
					success(isAdd);
				} else {
					success(isAdd);
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
	// 保存收藏
	public String addStar() {
		PStar pStar;
		boolean isAdd;
		try {
			pStar = (PStar) getObject(ServletActionContext.getRequest(),
					PStar.class);
			// 第一步：校验参数完整性
			if (paramsIsValid(pStar.getId_blog(), pStar.getId_csdn())) {
				isAdd = mUserService.addStar(pStar);
				// 第二步：判断返回值是否为空
				if (isAdd) {
					success(isAdd);
				} else {
					success(isAdd);
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

	// 保存收藏
	public String delStar() {
		PStar pStar;
		boolean isDel;
		try {
			pStar = (PStar) getObject(ServletActionContext.getRequest(),
					PStar.class);
			// 第一步：校验参数完整性
			if (paramsIsValid(pStar.getId_blog(), pStar.getId_csdn())) {
				isDel = mUserService.delStar(pStar);
				// 第二步：判断返回值是否为空
				if (isDel) {
					success(isDel);
				} else {
					success(isDel);
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

	/**
	 * 根据CsdnId获取收藏列表
	 */
	public String getPStarByCsdnId() {
		JSONObject jsonObject;
		List<Blog> blogs;
		String id_author;// 专家id。
		try {
			jsonObject = getObject(ServletActionContext.getRequest());
			// 第一步：校验参数完整性
			if (paramsIsFull(jsonObject, "id_author", "nowPager")) {
				nowPager = getIntParameter(jsonObject, "nowPager");
				pagerSize = getIntParameter(jsonObject, "pagerSize");
				id_author = getStringParameter(jsonObject, "id_author");
				blogs = mUserService.getPStarByCsdnId(nowPager, pagerSize,
						id_author);
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
	
		// 添加偏好：喜欢某个文章不喜欢
		public String addPreference() {
			Preference preference;
			boolean isAdd;
			try {
				preference = (Preference) getObject(ServletActionContext.getRequest(),
						Preference.class);
				// 第一步：校验参数完整性
				if (paramsIsValid(preference.getId_blog(), preference.getId_csdn())&&checkPreferenceType(preference.getType())) {
					isAdd = mUserService.addPreference(preference);
					// 第二步：判断返回值是否为空
					if (isAdd) {
						success(isAdd);
					} else {
						success(isAdd);
					}
				} else {
					// 参数不完整，提醒前台完善参数。
					requestError("参数不完整或者type取值不正确");
				}
			} catch (Exception e) {
				e.printStackTrace();
				// 异常，返回异常信息。
				serviceError();

			}
			return SUCCESS;
		}

		private boolean checkPreferenceType(int type) {
			if(type==1||type==2)return true;
			return false;
		}
}
