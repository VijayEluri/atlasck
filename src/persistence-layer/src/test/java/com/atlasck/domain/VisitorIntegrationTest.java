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
    public void timeReferencesCreateObject() {
    	Visitor visitor = dod.getNewCustomVisitor();
    
    	Assert.assertNotNull(visitor.getCreatedAt());
    	Assert.assertNotNull(visitor.getUpdatedAt());
    	Assert.assertEquals(visitor.getCreatedAt(), visitor.getUpdatedAt());
    	Assert.assertEquals(VisitorDataOnDemand.VISITOR_EMAIL, visitor.getEmail());
    }
    
    @Test
    public void timeReferencesUpdateObject() {
    	Visitor visitor = dod.updateNewVisitor();
    	Assert.assertTrue(visitor.getCreatedAt().compareTo(visitor.getUpdatedAt()) != 0);
    	Assert.assertEquals(VisitorDataOnDemand.VISITOR_NICKNAME, visitor.getNickname());
    }
}
