package com.atlasck.web.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/content/*")
public class ContentController {

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(method=RequestMethod.GET, value="aboutus")
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView getAboutUs(ModelMap modelMap) {
		return new ModelAndView("aboutus");
	}
}
