package com.atlasck.domain;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author G
 * 
 */
privileged aspect Validations {

	//TODO add proper validation messages
	declare @field: * (Question).body: @NotEmpty(message="notEmpty validation message");
	declare @field: * (Question).title: @NotEmpty;
	declare @field: * (Question).visitor : @Valid;
	
	declare @field: * (Visitor).nickname: @NotEmpty;
	declare @field: * (Visitor).email: @Email;
	declare @field: * (Visitor).email : @NotEmpty;

}
