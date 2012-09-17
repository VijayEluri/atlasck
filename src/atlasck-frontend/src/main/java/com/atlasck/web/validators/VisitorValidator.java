package com.atlasck.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atlasck.domain.Visitor;

/**
 *
 * @author Georgi Lambov
 *
 */
public class VisitorValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Visitor.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "email", "email.empty");

		Visitor visitor = (Visitor) arg0;

		if(visitor.getEmail().equals("test@localhost.com")) {
			arg1.rejectValue("email", "validation.email.not_valid");
		}
	}

}
