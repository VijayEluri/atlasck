package com.atlasck.domain;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Visitor.class)
public class VisitorIntegrationTest {
	
	@Autowired
	private VisitorDataOnDemand dod;
	
    @Test
    public void testMarkerMethod() {
    }
    
    @Test
    public void timeReferencesAutoUpdate() {
    	Visitor visitor = dod.getNewCustomVisitor();
    
    	Assert.assertNotNull(visitor.getCreatedAt());
    	Assert.assertNotNull(visitor.getUpdatedAt());
    	Assert.assertNotNull(visitor.getEmail(), VisitorDataOnDemand.VISITOR_EMAIL);
    }
}
