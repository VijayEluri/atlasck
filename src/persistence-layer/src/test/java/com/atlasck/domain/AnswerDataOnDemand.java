package com.atlasck.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = Answer.class)
public class AnswerDataOnDemand {

	@Autowired
	private QuestionDataOnDemand questionDod;
	
	public Answer createNewCustomAnswer() {
		Answer answer = new Answer();
		answer.setAnswer("my-answer");
		answer.setQuestion(questionDod.createNewCustomQuestion());
		
		answer.persist();
		
		return answer;
	}
}
