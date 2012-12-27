package com.atlasck.domain;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Answer.class)
public class AnswerIntegrationTest {

	@Autowired
	private AnswerDataOnDemand dod;
	
    @Test
    public void testMarkerMethod() {
    }
    
    public void timeReferencesAutoUpdate() {
    	Answer answer = dod.createNewCustomAnswer();
    	
    	Assert.assertNotNull(answer);
    	Assert.assertNotNull(answer.getUpdatedAt());
    	Assert.assertNotNull(answer.getCreatedAt());
    }
    
}
