package com.gaoyuan.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.gaoyuan.dao.CoreDao;
import com.gaoyuan.dao.RecommendDao;
import com.gaoyuan.dao.UserDao;


@Aspect
public class ChangeDB extends AbstractJUnit4SpringContextTests{
	@Autowired
	private CoreDao expertDao;
	@Autowired
	private RecommendDao recommendDao;
	@Autowired
	private UserDao userDao;

	@Pointcut("execution(* com.gaoyuan.aop.MyDbImpl.recovery*(..))")
	private void recoveryDB(){}
	
	@Pointcut("execution(* com.gaoyuan.action.*.*(..))")
	private void anyOldTransfer(){}
	
	@Before("anyOldTransfer()")
	public void changedbbefore(){
		SessionFactory sf = (SessionFactory) applicationContext.getBean("sessionFactory");
		setFactory(sf);
	}
	
	@Before("recoveryDB()")
	public void recoveryDb(){
		SessionFactory sf = (SessionFactory) applicationContext.getBean("sessionFactory");
		setFactory(sf);
	}
	
	public void setFactory(SessionFactory sf){
		expertDao.setSessionFactory(sf);
		recommendDao.setSessionFactory(sf);
		userDao.setSessionFactory(sf);
	}
}
