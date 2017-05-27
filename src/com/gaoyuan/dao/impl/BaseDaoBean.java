package com.gaoyuan.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.gaoyuan.base.BaseClass;
import com.gaoyuan.base.Constants;
import com.gaoyuan.dao.BaseDao;

@Transactional
public class BaseDaoBean extends BaseClass implements BaseDao {

	private SessionFactory sessionFactory;


	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	// ---------------增-------------------
	public void add(Object object) {
		getSession().merge(object);
	}
	// ---------------删--------------------
	// 删（主键为int）
	@SuppressWarnings("rawtypes")
	public void delete(Class clazz, int id) {
		getSession().delete(getSession().load(clazz, new Integer(id)));

	}
	// 删（主键为String）
	@SuppressWarnings("rawtypes")
	public void delete(Class clazz, String id) {
		getSession().delete(getSession().load(clazz, id));
	}
	@SuppressWarnings("rawtypes")
	public int delete(Class clazz, String k1, Object v1) {
		int count;
		Query query = getSession().createQuery(
				"delete " + clazz.getSimpleName() + " where " + k1 + " =? ");
		query.setParameter(0, v1);
		count = query.executeUpdate();
		return count;
	}

	@SuppressWarnings("rawtypes")
	public int delete(Class clazz, String k1, Object v1, String k2, Object v2) {
		int count;
		Query query = getSession().createQuery(
				"delete " + clazz.getSimpleName() + " where " + k1 + " =? and "
						+ k2 + " =?");
		query.setParameter(0, v1);
		query.setParameter(1, v2);
		count = query.executeUpdate();
		return count;
	}

	@SuppressWarnings("rawtypes")
	public int delete(Class clazz, String k1, Object v1, String k2, Object v2,
			String k3, Object v3) {
		int count;
		Query query = getSession().createQuery(
				"delete " + clazz.getSimpleName() + " where " + k1 + " =? and "
						+ k2 + " =? and " + k3 + " =?");
		query.setParameter(0, v1);
		query.setParameter(1, v2);
		query.setParameter(2, v3);
		count = query.executeUpdate();
		return count;
	}

	// ---------------改--------------------
	// ---------------查--------------------
	// 查单个（主键为int）
	public Object query(Class clazz, int id) {
		return getSession().get(clazz, new Integer(id));
	}

	// 查单个（主键为String）
	public Object query(Class clazz, String id) {
		return getSession().get(clazz, id);
	}
	// 查询所有数据，不带分页
	@SuppressWarnings({ "rawtypes" })
	public List queryAll(Class clazz) {
		Query query = getSession().createQuery("from " + clazz.getSimpleName());
		if (query.list() != null && !query.list().isEmpty())
			return query.list();
		else
			return null;
	}

	// 查询所有数据，不带分页
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryAll(Class clazz, String k1, Object v1) {
		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + k1 + "=?");
		query.setParameter(0, v1);
		if (query.list() != null && !query.list().isEmpty())
			return query.list();
		else
			return null;
	}

	// 查询所有数据，不带分页
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryAll(Class clazz, String k1, Object v1, String k2, Object v2) {
		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + k1 + "=? and "
						+ k2 + " =?");
		query.setParameter(0, v1);
		query.setParameter(1, v2);
		if (query.list() != null && !query.list().isEmpty())
			return query.list();
		else
			return null;
	}

	// 查询所有数据，不带分页
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryAll(Class clazz, String k1, Object v1, String k2,
			Object v2, String k3, Object v3) {
		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + k1 + "=? and "
						+ k2 + " =? and " + k3 + " =?");
		query.setParameter(0, v1);
		query.setParameter(1, v2);
		query.setParameter(2, v3);
		if (query.list() != null && !query.list().isEmpty())
			return query.list();
		else
			return null;
	}

	// 查询所有数据，不带分页
	@SuppressWarnings({ "rawtypes" })
	public List queryAllByOrder(Class clazz, String orderK, String orderV) {
		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + "order by " + orderK + " "
						+ orderV);
		if (query.list() != null && !query.list().isEmpty())
			return query.list();
		else
			return null;
	}

	// 查询所有数据，不带分页
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryAllByOrder(Class clazz, String k1, Object v1,
			String orderK,
			String orderV) {
		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + k1 + "=? "
						+ "order by " + orderK + " " + orderV);
		query.setParameter(0, v1);
		if (query.list() != null && !query.list().isEmpty())
			return query.list();
		else
			return null;
	}

	// 查询所有数据，不带分页
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryAllByOrder(Class clazz, String k1, Object v1, String k2,
			Object v2, String orderK, String orderV) {
		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + k1 + "=? and "
						+ k2 + " =? " + "order by " + orderK + " " + orderV);
		query.setParameter(0, v1);
		query.setParameter(1, v2);
		if (query.list() != null && !query.list().isEmpty())
			return query.list();
		else
			return null;
	}

	// 查询所有数据，不带分页
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryAllByOrder(Class clazz, String k1, Object v1, String k2,
			Object v2, String k3, Object v3, String orderK, String orderV) {
		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + k1 + "=? and "
						+ k2 + " =? and " + k3 + " =? " + "order by " + orderK
						+ " " + orderV);
		query.setParameter(0, v1);
		query.setParameter(1, v2);
		query.setParameter(2, v3);
		if (query.list() != null && !query.list().isEmpty())
			return query.list();
		else
			return null;
	}

	// 分页查询相关方法
	@SuppressWarnings("rawtypes")
	public List queryByPage(Class clazz, int nowPage, int pagerSize) {
//		int myPagerSize;
//		myPagerSize = processPagerSize(pagerSize);

		Query query = getSession().createQuery("from " + clazz.getSimpleName());
		query.setFirstResult(pagerSize * (nowPage - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}
	// 分页查询相关方法
	@SuppressWarnings("rawtypes")
	public List queryByPage(Class clazz, String k1, Object v1, int nowPage,
			int pagerSize) {
//		int myPagerSize;
//		myPagerSize = processPagerSize(pagerSize);

		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + k1 + "=? ");
		query.setParameter(0, v1);
		query.setFirstResult(pagerSize * (nowPage - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	// 分页查询相关方法
	@SuppressWarnings("rawtypes")
	public List queryByPage(Class clazz, String k1, Object v1, String k2,
			Object v2, int nowPage,
			int pagerSize) {
//		int myPagerSize;
//		myPagerSize = processPagerSize(pagerSize);

		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + k1 + "=? and "
						+ k2 + " =?");
		query.setParameter(0, v1);
		query.setParameter(1, v2);
		query.setFirstResult(pagerSize * (nowPage - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	// 分页查询相关方法
	@SuppressWarnings("rawtypes")
	public List queryByPage(Class clazz, String k1, Object v1, String k2,
			Object v2, String k3, Object v3, int nowPage,
			int pagerSize) {
//		int myPagerSize;
//		myPagerSize = processPagerSize(pagerSize);

		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + k1 + "=? and "
						+ k2 + "=? and " + k3 + " =?");
		query.setParameter(0, v1);
		query.setParameter(1, v2);
		query.setParameter(2, v3);
		query.setFirstResult(pagerSize * (nowPage - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}
	@SuppressWarnings("rawtypes")
	public List queryByPageByOrder(Class clazz, int nowPage, int pagerSize,
			String orderK, String orderV){
		return queryByPageByOrder( clazz,  nowPage,  pagerSize,
				 orderK,  orderV, false);
	}
	// 分页查询相关方法
	@SuppressWarnings("rawtypes")
	public List queryByPageByOrder(Class clazz, int nowPage, int pagerSize,
			String orderK, String orderV,boolean isCast) {
//		int myPagerSize;
//		myPagerSize = processPagerSize(pagerSize);
		Query query ;
		if(!isCast){
			query = getSession().createQuery(
					"from " + clazz.getSimpleName() + " order by " + orderK + " "
							+ orderV);
		}else{
			 query = getSession().createQuery(
						"from " + clazz.getSimpleName() + " order by CAST("+orderK+" as int) " + orderV);
		}
		query.setFirstResult(pagerSize * (nowPage - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public List queryByPageByOrder(Class clazz, String k1, Object v1,
			int nowPage, int pagerSize, String orderK, String orderV) {
		return queryByPageByOrder(clazz, k1, v1, nowPage, pagerSize, orderK,
				orderV, false);
	}
	// 分页查询相关方法
	@SuppressWarnings("rawtypes")
	public List queryByPageByOrder(Class clazz, String k1, Object v1,
			int nowPage,
			int pagerSize, String orderK, String orderV,boolean isCast) {
		Query query;
		if(!isCast){
			 query = getSession().createQuery(
					"from " + clazz.getSimpleName() + " where " + k1 + "=? "
							+ " order by " + orderK + " " + orderV);
		}else{
			 query = getSession().createQuery(
					"from " + clazz.getSimpleName() + " where " + k1 + "=? "
							+ " order by CAST("+orderK+" as int) " + orderV);
		}
		
		query.setParameter(0, v1);
		query.setFirstResult(pagerSize * (nowPage - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	public List<?> queryByPageByOrder(Class<?> clazz, String k1, Object v1,
			String k2, Object v2, int nowPage, int pagerSize, String orderK,
			String orderV) {
		return queryByPageByOrder(clazz, k1, v1, k2, v2, nowPage, pagerSize,
				orderK, orderV, false);
	}
	// 分页查询相关方法
	@SuppressWarnings("rawtypes")
	public List queryByPageByOrder(Class clazz, String k1, Object v1,
			String k2,
			Object v2, int nowPage, int pagerSize, String orderK, String orderV,boolean isCast) {
		//int myPagerSize;
		//myPagerSize = processPagerSize(pagerSize);
		Query query ;
		if(!isCast)
		 query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + k1 + "=? and "
						+ k2 + " =?" + " order by " + orderK + " " + orderV);
		else
			 query = getSession().createQuery(
						"from " + clazz.getSimpleName() + " where " + k1 + "=? and "
								+ k2 + " =?" + " order by CAST("+orderK+" as int) " + orderV);
		query.setParameter(0, v1);
		query.setParameter(1, v2);
		query.setFirstResult(pagerSize * (nowPage - 1+1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public List queryByPageByOrder(Class clazz, String k1, Object v1,
			String k2, Object v2, String k3, Object v3, int nowPage,
			int pagerSize, String orderK, String orderV) {
		return queryByPageByOrder(clazz, k1, v1, k2, v2, k3, v3, nowPage,
				pagerSize, orderK, orderV, false);
	}

	// 分页查询相关方法
	@SuppressWarnings("rawtypes")
	public List queryByPageByOrder(Class clazz, String k1, Object v1,
			String k2, Object v2, String k3, Object v3, int nowPage,
			int pagerSize, String orderK, String orderV, boolean isCast) {
		int myPagerSize;
		// myPagerSize = processPagerSize(pagerSize);
		Query query;
		if (!isCast)
			query = getSession().createQuery(
					"from " + clazz.getSimpleName() + " where " + k1
							+ "=? and " + k2 + "=? and " + k3 + " =?"
							+ " order by " + orderK + " " + orderV);
		else
			query = getSession()
					.createQuery(
							"from " + clazz.getSimpleName() + " where " + k1
									+ "=? and " + k2 + "=? and " + k3 + " =?"
									+ " order by CAST(" + orderK + " as int) "
									+ orderV);
		query.setParameter(0, v1);
		query.setParameter(1, v2);
		query.setParameter(2, v3);
		query.setFirstResult(pagerSize * (nowPage - 1 + 1));// 设置起始行
		query.setMaxResults(pagerSize);// 每页条数
		if (query.list() != null && !query.list().isEmpty()) {
			return query.list();
		} else {
			return null;
		}
	}

	// **********************inner meathod***************
	/**
	 * 计算每页大小
	 */
//	private int processPagerSize(int pagerSize) {
//		// 判断是否给了页大小
//		if (pagerSize <= 0) {
//			return Constants.Pager.DEF_PERPAGER_SIZE;
//		} else if (pagerSize > Constants.Pager.MAX_PERPAGER_SIZE) {
//			// 页容量是否超过了最大值
//			return Constants.Pager.MAX_PERPAGER_SIZE;
//		} else {
//			return pagerSize;
//		}
//	}
}
