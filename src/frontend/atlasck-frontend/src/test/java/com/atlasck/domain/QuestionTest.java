package com.atlasck.domain;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class QuestionTest {

	@Test
	@Parameters({"question.question", "question.title"})
	public void setAndGetAttributes(String quest, String title) {
		Question question = new Question();
		question.setQuestion(quest);
		question.setTitle(title);

		assertEquals(question.getQuestion(), quest, "Should be get email that was set");
		assertEquals(question.getTitle(), title, "Should be get nickname that was set");
	}
}
