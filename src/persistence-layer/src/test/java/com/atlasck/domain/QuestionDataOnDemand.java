package com.atlasck.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = Question.class)
public class QuestionDataOnDemand {
	
	@Autowired
	private VisitorDataOnDemand visitorDod;
	
	public Question createNewCustomQuestion() {
		Question question = new Question();
		question.setBody("my question");
		question.setVisitor(visitorDod.getRandomVisitor());
		question.setTitle("question title");
		question.setVisible(true);
		
		question.persist();
		
		return question;
	}
}
