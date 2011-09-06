package com.atlasck.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atlasck.domain.Question;

/**
 * 
 * @author Georgi Lambov
 *
 */
@Repository
@Transactional
public class QuestionImpl implements QuestionRepo {

	private SessionFactory sessionFactory;

	@Autowired
	QuestionImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public Question get(Integer id) {
		return (Question) sessionFactory.getCurrentSession().get(Question.class, id);
	}

	@Override
	@Transactional
	public List<?> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Question q").list();
	}

	@Override
	public void add(Question question) {

		//TODO move that to aspects
		Date d = new Date();
		if(question.getCreatedAt() == null) question.setCreatedAt(d);
		question.setUpdatedAt(d);

		sessionFactory.getCurrentSession().save(question);
	}

}
