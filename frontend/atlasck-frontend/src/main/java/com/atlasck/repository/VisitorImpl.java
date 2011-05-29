package com.atlasck.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atlasck.domain.Visitor;

@Repository
@Transactional
public class VisitorImpl implements VisitorRepo {

	private SessionFactory sessionFactory;

	@Autowired
	VisitorImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public Visitor get(Integer id) {
		return (Visitor) sessionFactory.getCurrentSession().get(Visitor.class, id);
	}

	@Override
	@Transactional
	public List<?> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Visitor v").list();
	}

	@Override
	@Transactional
	public void add(Visitor visitor) {
		//TODO move that to aspects
		Date d = new Date();
		if(visitor.getCreatedAt() == null) visitor.setCreatedAt(d);
		visitor.setUpdatedAt(d);

		sessionFactory.getCurrentSession().save(visitor);
	}

	@Override
	@Transactional
	public Visitor getVisitorByEmail(String email) {

		final String QUERY = "from Visitor v where v.email = :email";
		Query q = sessionFactory.getCurrentSession().createQuery(QUERY);
		q.setParameter("email", email);

		if(q.list().size() == 0) {
			return null;
		}

		return (Visitor) q.list().get(0);
	}

}