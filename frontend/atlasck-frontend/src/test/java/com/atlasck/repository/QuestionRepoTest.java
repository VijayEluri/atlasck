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
public class QuestionRepoTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private QuestionRepo questionRepo;

	private Question question;


	@BeforeClass
	@Parameters({"question.question", "question.title"})
	public void init(String quest, String title) {
		Question question = new Question();
		question.setQuestion(quest);
		question.setTitle(title);

		question.setVisitor(new Visitor());

		this.question = question;
	}

	@Transactional
	public void addQuestionTest() {
		int actualRecords = questionRepo.getAll().size();
		questionRepo.add(question);
		int incrementedRecords = questionRepo.getAll().size();

		Assert.assertNotNull(question.getId(),
				"If question is presented, it must have been assigned an id");

		Assert.assertEquals(incrementedRecords, actualRecords+1,
				"After adding 1 question, total questions' count must be incremented by 1");
	}

}
