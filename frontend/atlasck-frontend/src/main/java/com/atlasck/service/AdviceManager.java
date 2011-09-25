package com.atlasck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlasck.domain.Question;
import com.atlasck.domain.Visitor;
import com.atlasck.repository.QuestionRepo;
import com.atlasck.repository.VisitorRepo;

/**
 * Implements Advice specific logic
 *
 * @author Georgi Lambov
 *
 */
@Service("adviceManager")
public class AdviceManager {

	@Autowired VisitorRepo visitorRepo;
	@Autowired QuestionRepo questionRepo;

	/**
	 * Adds question and record visitor's data to the system.
	 *
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
