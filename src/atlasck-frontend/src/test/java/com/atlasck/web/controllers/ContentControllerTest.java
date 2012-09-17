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
public class ContentControllerTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private ContentController contentController;
	private AnnotationMethodHandlerAdapter methodHandlerAdapter;

	@BeforeMethod
	public void init() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		methodHandlerAdapter = new AnnotationMethodHandlerAdapter();
	}

	@Test(alwaysRun = true)
	@Parameters({"getAboutUs"})
	public void getAboutUs(String page) throws Exception {
		request.setRequestURI("/content/about-us");
		request.setMethod("GET");

		contentController = new ContentController();
		ModelAndView modelAndView = methodHandlerAdapter.handle(request, response, contentController);

		Assert.assertEquals(modelAndView.getViewName(), page, "returned view name should be " + page);
	}


	@Test(alwaysRun = true)
	@Parameters({"getServices"})
	public void getServices(String page) throws Exception {
		request.setRequestURI("/content/services");
		request.setMethod("GET");

		contentController = new ContentController();
		ModelAndView modelAndView = methodHandlerAdapter.handle(request, response, contentController);

		Assert.assertEquals(modelAndView.getViewName(), page, "returned view name should be " + page);
	}

	@Test(alwaysRun = true)
	@Parameters({"getFeedback"})
	public void getFeedback(String page) throws Exception {
		request.setRequestURI("/content/feedback");
		request.setMethod("GET");

		contentController = new ContentController();
		ModelAndView modelAndView = methodHandlerAdapter.handle(request, response, contentController);

		Assert.assertEquals(modelAndView.getViewName(), page, "returned view name should be " + page);
	}
}
