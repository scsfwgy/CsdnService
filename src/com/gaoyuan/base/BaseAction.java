package com.gaoyuan.base;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * @author wgyscsf
 * @email wgyscsf@163.com
 * @dateTime 2017 2017-4-10 下午2:05:57
 * @details 
 */
public class BaseAction extends BaseClass {
	// 常用参数
		private Map<String, Object> Rows;
		protected String SUCCESS = "success";
		protected int nowPager;
		protected int pagerSize;
		public Map<String, Object> getRows() {
			return Rows;
		}

		public void setRows(Map<String, Object> rows) {
			Rows = rows;
		}

		// --------------------获取前台参数----------------
		// 获取前台传递过来的json
		public static JSONObject getObject(HttpServletRequest request)
				throws Exception {
			request.setCharacterEncoding("UTF-8");
			ServletInputStream input = request.getInputStream();
			int len = request.getContentLength();
			if (len <= 0) {
				return new JSONObject();
			}
			byte[] buffer = new byte[len];
			input.read(buffer);
			//System.out.println(new String(buffer, "UTF-8"));
			return new JSONObject(new String(buffer, "UTF-8"));
		}

		// 获取前台传递过来的Obj
		public static <T> Object getObject(HttpServletRequest request, Class<T> type)
				throws Exception {
			request.setCharacterEncoding("UTF-8");
			ServletInputStream input = request.getInputStream();
			int len = request.getContentLength();
			if (len <= 0) {
				return null;
			}
			byte[] buffer = new byte[len];
			input.read(buffer);
			//System.out.println(new String(buffer, "UTF-8"));
			return new Gson().fromJson(new String(buffer, "UTF-8"), type);
		}

		// 获取前台传递过来的Obj
		public static <T> Object getObjectByType(HttpServletRequest request,
				Type type) throws Exception {
			request.setCharacterEncoding("UTF-8");
			ServletInputStream input = request.getInputStream();
			int len = request.getContentLength();
			if (len <= 0) {
				return null;
			}
			byte[] buffer = new byte[len];
			input.read(buffer);
			//System.out.println(new String(buffer, "UTF-8"));
			return new Gson().fromJson(new String(buffer, "UTF-8"), type);
		}

		// 获取单个参数
		public static String getStringParameter(JSONObject jsonObject, String key) {
			try {
				if (jsonObject.has(key)) {
					return jsonObject.getString(key);
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		// 获取单个参数
		public static int getIntParameter(JSONObject jsonObject, String key) {
			try {
				if (jsonObject.has(key)) {
					return jsonObject.getInt(key);
				} else {
					return -1;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}

		// -----------------------------------校验前台参数---------------
		// 校验参数完整性
		public static boolean paramsIsFull(JSONObject jsonObject, String... key) {
			for (int i = 0; i < key.length; i++) {
				if (jsonObject.has(key[i])) {

				} else {
					return false;
				}
			}
			return true;
		}

		// ------------------校验普通参数完整性------------------------
		// 校验参数完整性
		@SuppressWarnings("rawtypes")
		public static boolean paramsIsValid(List objs) {
			if (objs == null || objs.isEmpty() || objs.size() == 0)
				return false;
			return true;
		}
		// 校验参数完整性
		public static boolean paramsIsValid(Object... obj) {
			for (Object object : obj) {
				if (object == null)
					return false;
			}
			return true;
		}

		// 校验参数完整性
		public static boolean paramsIsValid(String... str) {
			for (String string : str) {
				if (string == null || string.equals("") || string.length() == 0)
					return false;
			}
			return true;
		}

		// 校验参数完整性
		public static boolean paramsIsValid(int... i) {
			for (int j : i) {
				if (j == 0)
					return false;
			}
			return true;
		}
		public int getPagerSize(int pagerSize) {
			if(pagerSize<=0||pagerSize>Constants.Pager.MAX_PERPAGER_SIZE)
				return Constants.Pager.MAX_PERPAGER_SIZE;
			return pagerSize;
		}
		// -------------------------------定义状态码---------------------
		public void success(Object data){
			success(data, "数据返回正确，为有效bean。");
		}
		public void success(Object data, String msg) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Constants.HttpKey.RESULT_DATA, data);// 数据
			map.put(Constants.HttpKey.RESULT_CODE, Constants.HttpCode.SUCCESS);// 状态码
			// 数据大小
			if(paramsIsValid(data)){
				map.put(Constants.HttpKey.RESULT_SIZE, 1);// size
			}else{
				map.put(Constants.HttpKey.RESULT_SIZE, 0);// size
			}
			// 验证信息
			if (paramsIsValid(msg)) {
				map.put(Constants.HttpKey.RESULT_MESSAGE, msg);// msg
			} else {
				map.put(Constants.HttpKey.RESULT_MESSAGE, "数据返回正确，为有效bean。");// msg
			}
			
			setRows(map);
		}
		public void success(List<?> datas){
			success(datas,  "数据返回正确，为有效list。");
		}
		@SuppressWarnings("rawtypes")
		public void success(List datas, String msg) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Constants.HttpKey.RESULT_DATA, datas);// 数据
			map.put(Constants.HttpKey.RESULT_CODE, Constants.HttpCode.SUCCESS);// 状态码
			// 数据大小
			if (paramsIsValid(datas)) {
				map.put(Constants.HttpKey.RESULT_SIZE, datas.size());// size
			} else {
				map.put(Constants.HttpKey.RESULT_SIZE, 0);// size
			}
			// 验证信息
			if (paramsIsValid(msg)) {
				map.put(Constants.HttpKey.RESULT_MESSAGE, msg);// msg
			} else {
				map.put(Constants.HttpKey.RESULT_MESSAGE, "数据返回正确，为有效list。");// msg
			}
			setRows(map);
			
		}

		public void empty(){
			empty("数据集合为空");
		}
		public void empty(String msg) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Constants.HttpKey.RESULT_CODE, Constants.HttpCode.SUCCESS);// 状态码
			map.put(Constants.HttpKey.RESULT_SIZE, 0);// size
			// 验证信息
			if (paramsIsValid(msg)) {
				map.put(Constants.HttpKey.RESULT_MESSAGE, msg);// msg
			} else {
				map.put(Constants.HttpKey.RESULT_MESSAGE, "数据集合为空");// msg
			}
			setRows(map);

		}
		public void requestError(){
			requestError("请求错误。请检查参数是否符合要求。");
		}
		public void requestError(String msg) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Constants.HttpKey.RESULT_CODE, Constants.HttpCode.REQUEST_ERROR);// 状态码
			if (paramsIsValid(msg)) {
				map.put(Constants.HttpKey.RESULT_MESSAGE, msg);// msg
			} else {
				map.put(Constants.HttpKey.RESULT_MESSAGE, "请求错误。请检查参数是否符合要求。");// msg
			}

			setRows(map);
		}
		public void serviceError(){
			serviceError("服务器异常，请稍后再试。");
		}
		public void serviceError(String msg) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Constants.HttpKey.RESULT_CODE, Constants.HttpCode.SERVICE_ERROR);// 状态码
			// 验证信息
			if (paramsIsValid(msg)) {
				map.put(Constants.HttpKey.RESULT_MESSAGE, msg);// msg
			} else {
				map.put(Constants.HttpKey.RESULT_MESSAGE, "服务器异常，请稍后再试。");// msg
			}
			setRows(map);
		}
}
