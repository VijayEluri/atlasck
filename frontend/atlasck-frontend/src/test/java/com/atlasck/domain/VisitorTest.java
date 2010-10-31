package com.atlasck.domain;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Test
public class VisitorTest {

	@Test
	@Parameters({"visitor.email", "visitor.nickname"})
	public void setAndGetAttributes(String email, String nickname) {
		Visitor visitor = new Visitor();
		visitor.setEmail(email);
		visitor.setNickname(nickname);

		assertEquals(visitor.getEmail(), email, "Should be get email that was set");
		assertEquals(visitor.getNickname(), nickname, "Should be get nickname that was set");
	}

}
