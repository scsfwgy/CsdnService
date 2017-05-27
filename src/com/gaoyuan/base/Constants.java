package com.gaoyuan.base;

/**
 * @author wgyscsf
 * @email wgyscsf@163.com
 * @dateTime 2017 2017-4-10 下午2:03:46
 * @details 
 */
public class Constants {
	public static class Config {

	}

	// 博客相关配置
	public static class Blog {
		// 是否是置顶文章
		public static final String type_notTop = 0 + "";// 默认
		public static final String type_top = 1 + "";

		// 文章类型
		public static final String type_original = 0 + "";// 原创
		public static final String type_reprint = 1 + "";// 转载
		public static final String type_translate = 2 + "";// 翻译
		public static final String type_def = 3 + "";// 保留类型，未知

		// 作者
		public static final String author_def = "author_def";// 默认作者名

		// 定义一些常用的地址
		public static final String blogAuthor_rootUrl = "http://blog.csdn.net/";
		public static final String blogList_rootUrl = "http://blog.csdn.net/";
		/*
		 * 定义正则匹配规则
		 */
		// 博主的个人资料网址
		public static final String blogAuthor_regex = "^http://blog.csdn.net/((?!/).)*$";// 匹配出博主地址的正则
		// 博主博客列表，带分页
		public static final String blogList_regex = "^http://blog.csdn.net/\\w+/article/list/[0-9]*[1-9][0-9]*$";
		// 博客详情页
		public static final String blogDetails_regex = "http://blog.csdn.net/\\w+/article/details/\\w+";
		
		//博客专家列表
		public static final String EXPERTS_LIST="http://blog\\.csdn\\.net/peoplelist\\.html\\?channelid=0\\&page=\\w+";
	}

	public static class Pager {
		/*
		 * 规定全局分页，每页显示最多条数
		 */
		public static final int MAX_PERPAGER_SIZE = 30;
		/*
		 * 规定每页默认大小
		 */
		public static final int DEF_PERPAGER_SIZE = 10;
		/*
		 * 规定默认起始页为0
		 */
		public static final int DEF_PAGER_START = 0;
	}
	
	public static class HttpKey {
		// 数据
		public static final String RESULT_DATA = "data";
		// 表示连接状态，在捕获到异常的时候为连接失败。
		public static final String RESULT_CODE = "code";
		// 返回描述
		public static final String RESULT_MESSAGE = "message";
		// 数据大小
		public static final String RESULT_SIZE = "size";


	}

	public static class HttpCode {
		// 正确
		public static final int SUCCESS = 200;
		// 请求失败。
		public static final int REQUEST_ERROR = 501;
		// 服务器异常
		public static final int SERVICE_ERROR = 500;
	}
	public static class Recommend {
		//阅读数必须大于该值才能被推荐
		public static final int RECOMMEND_READNUMS = 8000;
		//评论数必须大于该值才能被推荐
		public static final int RECOMMEND_COMMENT = 20;
	}
	

}
