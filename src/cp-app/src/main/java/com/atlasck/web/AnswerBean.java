package com.atlasck.web;

import com.atlasck.domain.Answer;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Answer.class, beanName = "answerBean")
public class AnswerBean {
}
