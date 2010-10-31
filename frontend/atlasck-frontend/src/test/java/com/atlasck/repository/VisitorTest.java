package com.atlasck.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.atlasck.domain.Visitor;

@ContextConfiguration(locations = {"classpath:/META-INF/spring/app-data.xml"})
public class VisitorTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private VisitorRepo visitorRepo;

	private Visitor visitor;

	@BeforeClass
	@Parameters({"visitor.email", "visitor.nickname"})
	public void init(String email, String nickname) {
		Visitor visitor = new Visitor();
		visitor.setEmail(email);
		visitor.setNickname(nickname);

		this.visitor = visitor;
	}

	@Test
	@Transactional
	public void addVisitorTest() {
		int actualRecords = visitorRepo.getAll().size();
		visitorRepo.add(visitor);
		int incrementedRecords = visitorRepo.getAll().size();

		Assert.assertNotNull(visitor.getId(),
				"If visitor is presented, it must have been assigned an id");

		Assert.assertEquals(incrementedRecords, actualRecords+1,
				"After adding 1 visitor, total visitors' count must be incremented by 1");
	}
}
