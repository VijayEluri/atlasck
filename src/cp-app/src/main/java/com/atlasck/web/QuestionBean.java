package com.atlasck.web;

import com.atlasck.domain.Question;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Question.class, beanName = "questionBean")
public class QuestionBean {
}
