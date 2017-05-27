package com.gaoyuan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoyuan.base.BaseService;
import com.gaoyuan.bean.Blog;
import com.gaoyuan.bean.CsdnUser;
import com.gaoyuan.bean.PStar;
import com.gaoyuan.bean.PUser;
import com.gaoyuan.bean.Preference;
import com.gaoyuan.dao.CoreDao;
import com.gaoyuan.dao.UserDao;
import com.gaoyuan.service.CoreService;
import com.gaoyuan.service.UserService;
import com.gaoyuan.utils.HttpUtils;
import com.gaoyuan.utils.MyDateUtils;
import com.gaoyuan.utils.MyRegexUtils;
import com.gaoyuan.utils.MyStringUtils;
import com.google.gson.Gson;



@Service
public class UserServiceImpl extends BaseService implements UserService {

	@Autowired
	private UserDao mUserDao;
	@Autowired
	private CoreDao mCoreDao;
	@Override
	public PUser login(String account, String password) {
		PUser pUser=loginCsdnPager(account, password);
		if(pUser==null)return null;
		PUser sqlPUser=mUserDao.selectPUserByCsdnId(pUser.getId_csdn());
		if(sqlPUser!=null){
			//pUser.setId(sqlPUser.getId());
			//pUser.setCreateDate(sqlPUser.getCreateDate());
			//mUserDao.updatePUser(pUser);
		}else{
			pUser.setCreateDate(MyDateUtils.getCurTimeString());
			pUser.setId(MyStringUtils.getGUID());
			mUserDao.addPUser(pUser);
		}
		 PUser selectPUserByCsdnId = mUserDao.selectPUserByCsdnId(pUser.getId_csdn());
		 if(selectPUserByCsdnId==null)return null;
		 
		 String phone=getProceesStr(selectPUserByCsdnId.getPhone());
		 selectPUserByCsdnId.setPhone(phone);
		return selectPUserByCsdnId;
		 
	}
	
	
	   private  String getProceesStr(String str) {
	        if (str == null) return null;
	        if(str.length()<11)return str;
	        //13140904592-->131****4592
	        return str.substring(0,3)+"****"+str.substring(7);
	    }
	   public static void main(String[] args) {
		System.out.println(new UserServiceImpl().getProceesStr("13140904592"));
	}
	
	/**
	 * 模拟登陆核心逻辑
	 */
	private  PUser loginCsdnPager(String username,String password) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpClientContext context = new HttpClientContext();

		String html = HttpUtils
				.sendGet("https://passport.csdn.net/account/login?ref=toolbar",httpClient,context);// 这个是登录的页面
		Document doc = Jsoup.parse(html);
		// 获取表单所在的节点
		Element form = doc.select(".user-pass").get(0);

		// 这里处理验证码
		String yanzhengmaImg = form.getElementsByClass("code-img")
				.toString();
		System.out.println("验证码:"+yanzhengmaImg );

		// 以下三个是服务器给的标记信息，必须具有该信息登录才有效。
		String lt = form.select("input[name=lt]").get(0).val();
		String execution = form.select("input[name=execution]").get(0).val();
		String _eventId = form.select("input[name=_eventId]").get(0).val();

		// 开始构造登录的信息：账号、密码、以及三个标记信息
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));
		nvps.add(new BasicNameValuePair("lt", lt));
		nvps.add(new BasicNameValuePair("execution", execution));
		nvps.add(new BasicNameValuePair("_eventId", _eventId));
		// 开始请求CSDN服务器进行登录操作。一个简单封装，直接获取返回结果
		String ret = HttpUtils.sendPost(
				"https://passport.csdn.net/account/login?ref=toolbar", nvps,httpClient,context);
		// ret中会包含以下信息，进行判断即可。
		if (ret.indexOf("redirect_back") > -1) {
			System.out.println("登陆成功:"+username);
			String json=ret.substring(ret.indexOf("var data = ")+11, ret.indexOf("};")+1);
			Gson gson=new Gson();
			CsdnUser csdnUser = gson.fromJson(json, CsdnUser.class);
			PUser pUser=new PUser();
			pUser.setId_csdn(csdnUser.getUserName()!=null?csdnUser.getUserName():"");
			pUser.setPhone(csdnUser.getMobile()!=null?csdnUser.getMobile():"");
			pUser.setEmail(csdnUser.getEmail()!=null?csdnUser.getEmail():"");
			pUser.setNickName(csdnUser.getNickName()!=null?csdnUser.getNickName():"");
			pUser.setName(csdnUser.getUserName()!=null?csdnUser.getUserName():"");
			pUser.setAvatarAddr(csdnUser.getAvatar()!=null?csdnUser.getAvatar():"");
			return pUser;
		} else if (ret.indexOf("登录太频繁") > -1) {
			System.out.println("登录太频繁:"+username);
			return null;
		} else {
			System.out.println("登陆失败:"+username);
			return null;
		}
	}




	@Override
	public boolean addStar(PStar pStar) {
		pStar.setId(MyStringUtils.getGUID());
		pStar.setCreateTime(MyDateUtils.getCurTimeString());
		return mUserDao.addStar(pStar);
	}




	@Override
	public List<Blog> getPStarByCsdnId(int nowPager, int pagerSize,
			String id_author) { 
		pagerSize=getAvailablePageSize(pagerSize);
		List<PStar> pStarList = mUserDao.getPStarByCsdnId(nowPager,pagerSize,id_author);
		if(pStarList==null||pStarList.isEmpty())return null;
		List<Blog> blogList=new ArrayList<>();
		for (PStar pStar : pStarList) {
			Blog blog=mCoreDao.getBlogByBlogId(pStar.getId_blog());
			if(blog==null)continue;
			blog.setpStar(pStar);
			blogList.add(blog);
		}
		
		return blogList;
	}
	@Override
	public boolean delStar(PStar pStar) {
		return mUserDao.delStar(pStar.getId());
	}

	
	/**
	 * 逻辑：添加之前判断是否存在该条数据，注意：type值不一致也不认为是一条数据。只有添加操作，没有删除和更新操作，简化流程。
	 */
	@Override
	public boolean addPreference(Preference preference) {
		Preference dbPreference=mUserDao.selectPreference(preference.getId_csdn(),preference.getId_blog(),preference.getType());
		if(dbPreference!=null)return false;
		preference.setId(MyStringUtils.getGUID());
		preference.setCreateTime(MyDateUtils.getCurTimeString());
		//设置typeID
		Blog blogByBlogId = mCoreDao.getBlogByBlogId(preference.getId_blog());
		if(blogByBlogId!=null)
		preference.setTypeId(blogByBlogId.getTypeId());
		return mUserDao.addPreference(preference);
	}


	@Override
	public boolean updatePUser(PUser pUser) {
		PUser pUser2=mUserDao.selectPUserByCsdnId(pUser.getId_csdn());
		if(pUser2!=null){
			pUser.setPhone(pUser2.getPhone()!=null?pUser2.getPhone():"未知");
			 mUserDao.addPUser(pUser);
			 return true;
		}
		return false;
	}
}
