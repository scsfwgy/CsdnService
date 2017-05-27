package com.gaoyuan.dao;

import org.hibernate.SessionFactory;

public interface BaseDao {
	public void setSessionFactory(SessionFactory sessionFactory);
}
