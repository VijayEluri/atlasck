package com.atlasck.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.atlasck.domain.Answer;
import com.atlasck.domain.Question;
import com.atlasck.domain.Visitor;

@ContextConfiguration(locations = {"classpath:/META-INF/spring/app-data.xml"})
public class AnswerTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private VisitorRepo visitorRepo;

	@Autowired
	private AnswerRepo answerRepo;

	private Answer answer;
	private Question question;
	private Visitor visitor;

	@BeforeClass
	@Parameters({"question.question",
		"question.title", "answer.answer", "visitor.email", "visitor.nickname"})
	public void init(String quest,
			String title, String answ, String email, String nickname) {
		Question question = new Question();
		question.setQuestion(quest);
		question.setTitle(title);
		this.question = question;

		Visitor visitor = new Visitor();
		visitor.setEmail(email);
		visitor.setNickname(nickname);
		this.visitor = visitor;

		Answer answer = new Answer();
		answer.setAnswer(answ);
		this.answer = answer;
	}

	@Test
	@Transactional
	public void addAnswerTest() {
		int actualRecords = answerRepo.getAll().size();

		visitorRepo.add(visitor);
		question.setVisitor(visitor);
		answer.setQuestion(question);
		answerRepo.add(answer);

		int incrementedRecords = answerRepo.getAll().size();

		Assert.assertNotNull(answer.getId(),
			"If question is presented, it must have been assigned an id");

		Assert.assertEquals(incrementedRecords, actualRecords+1,
			"After adding 1 question, total questions' count must be incremented by 1");
	}
}
