package com.atlasck.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atlasck.domain.Answer;
import com.atlasck.domain.AnswerPeer;
import com.atlasck.utility.HibernateUtils;

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
	public List<Answer> getAll() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria c = currentSession.createCriteria(Answer.class)
			.addOrder(Order.desc(AnswerPeer.CREATED_AT));
		
		List<Answer> answers = HibernateUtils.listAndCast(c);
		
		return answers;
	}

	@Override
	public void add(Answer answer) {
		sessionFactory.getCurrentSession().save(answer);

	}
}
