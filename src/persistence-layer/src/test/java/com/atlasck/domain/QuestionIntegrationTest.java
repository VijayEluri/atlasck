package com.atlasck.domain;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Question.class)
public class QuestionIntegrationTest {

	@Autowired
	private QuestionDataOnDemand dod;
	
    @Test
    public void testMarkerMethod() {
    }
   
    @Test
    public void timeReferencesAutoUpdate() {
    	Question question = dod.createNewCustomQuestion();
    	
    	Assert.assertNotNull(question);
    	Assert.assertNotNull(question.getCreatedAt());
    	Assert.assertNotNull(question.getUpdatedAt());
    }
}
