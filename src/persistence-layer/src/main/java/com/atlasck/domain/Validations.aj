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
	declare @field: * (Question).body: @NotEmpty(message="{validation.not_empty}");
	declare @field: * (Question).title: @NotEmpty(message="{validation.not_empty}");
	declare @field: * (Question).visitor : @Valid;
	
	declare @field: * (Visitor).nickname: @NotEmpty(message="{validation.not_empty}");
	declare @field: * (Visitor).email: @Email(message="{validation.email}");
	declare @field: * (Visitor).email : @NotEmpty(message="{validation.not_empty}");

}
