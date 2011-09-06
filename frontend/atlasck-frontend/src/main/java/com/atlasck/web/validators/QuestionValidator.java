package com.atlasck.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.atlasck.domain.Question;

/**
 * 
 * @author Georgi Lambov
 *
 */
public class QuestionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Question.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Question question = (Question) target;
		if(question.getQuestion().equals("test")) {
			errors.rejectValue("question", "negativevalue");
		}
	}

}
