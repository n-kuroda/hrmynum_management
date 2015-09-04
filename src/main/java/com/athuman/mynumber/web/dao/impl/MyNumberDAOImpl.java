package com.athuman.mynumber.web.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.athuman.mynumber.web.dao.MyNumberDAO;
import com.athuman.mynumber.web.model.MyNumber;

@Repository
public class MyNumberDAOImpl implements MyNumberDAO {

	private SessionFactory sessionFactory;

	@Override
	public String addMyNumber(MyNumber myNumber) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.persist(myNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
}
