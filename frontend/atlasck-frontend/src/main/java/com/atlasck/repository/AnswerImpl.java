package com.atlasck.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atlasck.domain.Answer;

/**
 * Answers repository
 *
 * @author Georgi Lambov
 */
@Repository
@Transactional
public class AnswerImpl implements AnswerRepo {

	private SessionFactory sessionFactory;

	@Autowired
	AnswerImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public Answer get(Integer id) {
		return (Answer) sessionFactory.getCurrentSession().get(Answer.class, id);
	}

	@Override
	@Transactional
	public List<?> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Answer a").list();
	}

	@Override
	public void add(Answer answer) {
		sessionFactory.getCurrentSession().save(answer);

	}
}
