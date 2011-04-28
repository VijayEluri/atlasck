package com.atlasck.repository;

import java.util.List;

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
	public void add(Visitor visitor) {
		sessionFactory.getCurrentSession().save(visitor);
	}

}