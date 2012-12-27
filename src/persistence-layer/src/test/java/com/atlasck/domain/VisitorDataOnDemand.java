package com.atlasck.domain;

import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = Visitor.class)
public class VisitorDataOnDemand {

	static final String VISITOR_EMAIL = "thevisitor@visitor.com";
	static final String VISITOR_NICKNAME = "updated nickname";

	// TODO add date Mockup
	public Visitor getNewCustomVisitor() {
		Visitor visitor = new Visitor();
		visitor.setEmail(VISITOR_EMAIL);
		visitor.setIpAddress("192.168.0.1");
		visitor.setNickname("nickname");

		visitor.persist();
		visitor.flush();
		return visitor;
	}

	public Visitor updateNewVisitor() {
		Visitor visitor = getNewCustomVisitor();
		visitor.setNickname("updated nickname");
		visitor.merge();
		visitor.flush();
		
		return visitor;
	}

}
