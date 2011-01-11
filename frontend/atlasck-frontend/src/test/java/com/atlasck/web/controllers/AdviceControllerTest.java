package com.atlasck.web.controllers;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class AdviceControllerTest {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private AdviceController adviceController;
	private AnnotationMethodHandlerAdapter methodHandlerAdapter;

	@BeforeMethod
	public void init() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		methodHandlerAdapter = new AnnotationMethodHandlerAdapter();
	}

	@Test(alwaysRun = true)
	@Parameters({"getAdviceList"})
	public void list(String page) throws Exception {
		request.setRequestURI("/advice/list");
		request.setMethod("GET");

		adviceController = new AdviceController();
		ModelAndView modelAndView = methodHandlerAdapter.handle(request, response, adviceController);

		Assert.assertEquals(modelAndView.getViewName(), page, "returned view name should be " + page);
	}

	@Test(alwaysRun = true)
	@Parameters({"questionForm"})
	public void create(String page) throws Exception {
		request.setRequestURI("/advice/question");
		request.setMethod("POST");

		adviceController = new AdviceController();
		ModelAndView modelAndView = methodHandlerAdapter.handle(request, response, adviceController);

		Assert.assertEquals(modelAndView.getViewName(), page, "returned view name should be " + page);
	}

	@Test(alwaysRun = true)
	@Parameters({"questionForm"})
	public void createForm(String page) throws Exception {
		request.setRequestURI("/advice/question");
		request.setMethod("GET");

		adviceController = new AdviceController();
		ModelAndView modelAndView = methodHandlerAdapter.handle(request, response, adviceController);

		Assert.assertEquals(modelAndView.getViewName(), page, "returned view name should be " + page);
	}
}
