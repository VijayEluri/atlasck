package com.atlasck.web;

import com.atlasck.domain.Visitor;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Visitor.class, beanName = "visitorBean")
public class VisitorBean {
}
