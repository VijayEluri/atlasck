package com.atlasck.domain;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class AnswerTest {

	@Test
	@Parameters({"answer.answer"})
	public void setAndGetAttributes(String answ) {
		Answer answer = new Answer();
		answer.setAnswer(answ);

		assertEquals(answer.getAnswer(), answ, "Should get answer that was set");
	}
}
