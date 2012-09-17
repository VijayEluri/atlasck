package com.atlasck.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.atlasck.domain.CurrentVersion;

@ContextConfiguration(locations = {"classpath:/META-INF/spring/app-data.xml"})
public class CurrentVersionTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private CurrentVersionRepo currentVersionRepo;

	private CurrentVersion currentVersion;

	@BeforeClass
	@Parameters({"currentVersion.username", "currentVersion.passwd"})
	public void init(String username, String passwd) {
		CurrentVersion currentVersion = new CurrentVersion();
		currentVersion.setUsername(username);
		currentVersion.setPasswd(passwd);

		this.currentVersion = currentVersion;
	}

	@Test
	@Transactional
	public void addCurrentVersion() {
		int actualRecords = currentVersionRepo.getAll().size();
		currentVersionRepo.add(currentVersion);
		int incrementedRecords = currentVersionRepo.getAll().size();

		Assert.assertNotNull(currentVersion.getId(),
			"If link is persisted it must have been assigned an id");

		Assert.assertEquals(incrementedRecords, actualRecords+1,
			"After adding 1 currentVersion, total currentVersions' count must be incremented by 1");
	}
}
