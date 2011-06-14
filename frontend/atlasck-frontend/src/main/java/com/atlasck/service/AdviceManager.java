package com.atlasck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlasck.domain.Question;
import com.atlasck.domain.Visitor;
import com.atlasck.repository.QuestionRepo;
import com.atlasck.repository.VisitorRepo;

/**
 *
 * @author georgi
 *
 */
@Service("adviceManager")
public class AdviceManager {

	@Autowired VisitorRepo visitorRepo;
	@Autowired QuestionRepo questionRepo;

	/**
	 * Adds question from visitor. If visitor not exists
	 * new visitor is created. If visitor exists system updates
	 * visitor's data.
	 * @param visitor
	 */
	public void add(Question question, Visitor visitor) {

		if(visitor.getId() == null) {
			visitor.setEmail(question.getVisitor().getEmail());
			visitor.setNickname(question.getVisitor().getEmail());
			visitorRepo.add(visitor);
		} else {
			visitorRepo.update(visitor);
		}


		question.setVisitor(visitor);
		questionRepo.add(question);
	}

}
