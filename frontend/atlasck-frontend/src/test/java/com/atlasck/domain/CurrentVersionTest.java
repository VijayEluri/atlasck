package com.atlasck.domain;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Test
public class CurrentVersionTest {

	@Test
	@Parameters({"currentVersion.username", "currentVersion.passwd"})
	public void setAndGetAttributes(String username, String passwd) {
		CurrentVersion currentVersion = new CurrentVersion();
		currentVersion.setUsername(username);
		currentVersion.setPasswd(passwd);

		assertEquals(currentVersion.getUsername(), username, "Should get username that was set");
		assertEquals(currentVersion.getPasswd(), passwd, "Should get password that was set");
	}
}
