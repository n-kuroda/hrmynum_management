package com.athuman.mynumber.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.athuman.mynumber.web.dao.TACTServiceDAO;
import com.athuman.mynumber.web.model.MyNumber;

@Repository
public class TACTServiceDAOImpl implements TACTServiceDAO {

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<MyNumber> queryMyNumberByHimodukeNo(String himodukeNo) {
		Session session = this.sessionFactory.getCurrentSession();

		List<MyNumber> list = session.createQuery("from MyNumber where HIMODUKE_NO = '" + himodukeNo + "'").list();

		return list;
	}

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
}
