package com.atlasck.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atlasck.domain.CurrentVersion;

@Repository
@Transactional
public class CurrentVersionImpl
implements CurrentVersionRepo {

	private SessionFactory sessionFactory;

	@Autowired
	CurrentVersionImpl (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public CurrentVersion get(Integer id) {
		return (CurrentVersion) sessionFactory.getCurrentSession().get(CurrentVersion.class, id);
	}

	@Override
	@Transactional
	public List<?> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from CurrentVersion c").list();
	}

	@Override
	public void add(CurrentVersion currentVersion) {
		sessionFactory.getCurrentSession().save(currentVersion);
	}
}
