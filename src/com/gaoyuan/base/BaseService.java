package com.gaoyuan.base;

import java.util.Random;

/**
 * @author wgyscsf
 * @email wgyscsf@163.com
 * @dateTime 2017 2017-4-10 下午2:05:57
 * @details 
 */
public class BaseService extends BaseClass {
	/**
	 * 获取不同领域的专家类型。具体返回值类型由爬虫系统决定。
	 * 参数如下：1：移动开发；14：Web前端；15：架构设计；16：编程语言；17:互联网；6：
	 * 数据库；12：系统运维；2：云计算；3：研发管理；0：所有分类集合（default）。
	 */
	protected int getExpertType(int typeId) {
		if (typeId == 1) {
			return 1;
		} else if (typeId == 2) {
			return 14;
		} else if (typeId == 3) {
			return 15;
		} else if (typeId == 4) {
			return 16;
		} else if (typeId == 5) {
			return 17;
		} else if (typeId == 6) {
			return 6;
		} else if (typeId == 7) {
			return 12;
		} else if (typeId == 8) {
			return 2;
		} else if (typeId == 9) {
			return 3;
		}
		return 0;
	}
	protected int getAvailablePageSize(int pageSize){
		if(pageSize>0&&pageSize<=Constants.Pager.MAX_PERPAGER_SIZE)
			return pageSize;
		else if(pageSize<=0)
			return Constants.Pager.DEF_PERPAGER_SIZE;
		else 
			return Constants.Pager.MAX_PERPAGER_SIZE;
		
	}
}
