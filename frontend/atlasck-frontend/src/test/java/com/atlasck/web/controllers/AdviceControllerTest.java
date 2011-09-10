package com.atlasck.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.atlasck.domain.Question;
import com.atlasck.domain.Visitor;
import com.atlasck.repository.AnswerRepo;
import com.atlasck.repository.QuestionRepo;
import com.atlasck.repository.VisitorRepo;
import com.atlasck.service.AdviceManager;

@Test
@ContextConfiguration(locations = {"classpath:/META-INF/spring/app-data.xml", "classpath:/META-INF/spring/app-manager.xml"})
public class AdviceControllerTest extends AbstractTestNGSpringContextTests {

	@Autowired private VisitorRepo visitorRepo;
	@Autowired private AdviceManager adviceManager;
	@Autowired private QuestionRepo questionRepo;
	@Autowired private AnswerRepo answerRepo;
	@Autowired private SessionFactory sessionFactory;

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private AdviceController adviceController;
	private AnnotationMethodHandlerAdapter methodHandlerAdapter;

	private Question question;
	private Visitor visitor;

	@BeforeMethod
	public void init() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		methodHandlerAdapter = new AnnotationMethodHandlerAdapter();

		question = new Question();
		question.setTitle("Title of my question");
		question.setQuestion("Actual question body");
		question.setEmailAnswer(false);

		visitor = new Visitor();
		visitor.setEmail("test_visitor@localhost.com");

		question.setVisitor(visitor);
	}

	@Test
	@Parameters({"getAdviceList"})
	public void list(String page) throws Exception {
		request.setRequestURI("/advice/list");
		request.setMethod("GET");

		adviceController = new AdviceController();
		adviceController.setAnswerRepo(answerRepo);
		ModelAndView modelAndView = methodHandlerAdapter.handle(request, response, adviceController);

		Assert.assertEquals(modelAndView.getViewName(), page, "returned view name should be " + page);
	}

	@Test
	@Parameters({"questionForm"})
	public void create(String page) throws Exception {

		//TODO init request from model map
		request.setRequestURI("/advice/question");
		request.setMethod("POST");
		request.setContentType("application/x-www-form-urlencoded");

		request.setParameter("visitor.email", "test-user@localhost.com");
		request.setParameter("title", "test question's title");
		request.setParameter("question", "this is the test actual test questions");
		request.setParameter("emailAnswer", "on");

		long actualRecords = questionRepo.getAll().size();

		adviceController = new AdviceController();
		adviceController.setVisitorRepo(visitorRepo);
		adviceController.setAdviceManager(adviceManager);


		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute(question);
		DataBinder dataBinder = new DataBinder(modelMap);
		BindingResult result = dataBinder.getBindingResult();
		//adviceController.create(modelMap, question, result, request);

		ModelAndView modelAndView = methodHandlerAdapter.handle(request, response, adviceController);

		Assert.assertEquals(modelAndView.getViewName(), page, "returned view name should be " + page);

		long incrementedRecords = questionRepo.getAll().size();

		Assert.assertEquals(incrementedRecords, actualRecords+1,
			"After adding of one user question, total count must be incremented by on");
	}

	@Test
	@Parameters({"questionForm"})
	public void createForm(String page) throws Exception {
		request.setRequestURI("/advice/question");
		request.setMethod("GET");

		adviceController = new AdviceController();
		ModelAndView modelAndView = methodHandlerAdapter.handle(request, response, adviceController);

		Assert.assertEquals(modelAndView.getViewName(), page, "returned view name should be " + page);
	}

	@AfterClass
	public void clean() {
		//TODO clear database
	}
}
