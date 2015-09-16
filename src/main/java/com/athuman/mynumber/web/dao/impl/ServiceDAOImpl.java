package com.athuman.mynumber.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.athuman.mynumber.web.dao.ServiceDAO;
import com.athuman.mynumber.web.model.MyNumber;
import com.athuman.mynumber.web.util.ConstValues;

@Repository
public class ServiceDAOImpl implements ServiceDAO {

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<MyNumber> queryMyNumberByHimodukeNo(String himodukeNo) {
		Session session = this.sessionFactory.getCurrentSession();

		List<MyNumber> list = session.createQuery("from MyNumber where HIMODUKE_NO = '" + himodukeNo + "'").list();

		return list;
	}

	@Override
	public String addMyNumber(MyNumber myNumber) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.persist(myNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return ConstValues.SAVE_DB_FAIL;
		}
		return ConstValues.SAVE_DB_OK;
	}

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
}
