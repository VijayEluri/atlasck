package com.atlasck.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.atlasck.domain.Question;
import com.atlasck.domain.Visitor;

@ContextConfiguration(locations = {"classpath:/META-INF/spring/app-data.xml"})
public class QuestionTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private QuestionRepo questionRepo;

	@Autowired
	private VisitorRepo visitorRepo;

	private Question question;
	private Visitor visitor;


	@BeforeClass
	@Parameters({"question.question",
		"question.title", "visitor.nickname", "visitor.email"})
	public void init(String quest,
			String title, String nickname, String email) {

		Question question = new Question();
		question.setQuestion(quest);
		question.setTitle(title);
		this.question = question;

		Visitor visitor = new Visitor();
		visitor.setNickname(nickname);
		visitor.setEmail(email);
		this.visitor = visitor;
	}

	@Test
	@Transactional
	public void addQuestionTest() {
		int actualRecords = questionRepo.getAll().size();

		visitorRepo.add(visitor);
		question.setVisitor(visitor);
		questionRepo.add(question);

		int incrementedRecords = questionRepo.getAll().size();

		Assert.assertNotNull(question.getId(),
			"If question is presented, it must have been assigned an id");

		Assert.assertEquals(incrementedRecords, actualRecords+1,
			"After adding 1 question, total questions' count must be incremented by 1");
	}

}
