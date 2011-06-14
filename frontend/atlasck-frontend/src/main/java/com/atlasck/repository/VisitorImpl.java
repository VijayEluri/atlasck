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
	public Visitor get(Integer id) {
		return (Visitor) sessionFactory.getCurrentSession().get(Visitor.class, id);
	}

	@Override
	public List<?> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Visitor v").list();
	}

	@Override
	public void add(Visitor visitor) {
		//TODO move that to aspects
		Date d = new Date();
		if(visitor.getCreatedAt() == null) visitor.setCreatedAt(d);
		visitor.setUpdatedAt(d);

		sessionFactory.getCurrentSession().save(visitor);
	}

	@Override
	public Visitor getVisitorByEmail(String email) {

		final String QUERY = "from Visitor v where v.email = :email";
		Query q = sessionFactory.getCurrentSession().createQuery(QUERY);
		q.setParameter("email", email);

		if(q.list().size() == 0) return new Visitor();

		return (Visitor) q.list().get(0);
	}

	@Override
	public void update(Visitor visitor) {
		sessionFactory.getCurrentSession().update(visitor);
	}
}