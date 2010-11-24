package com.atlasck.web.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/content/**")
public class ContentController {

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value="about-us", method=RequestMethod.GET)
	public String getAboutUs(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "content.aboutUs");
		return "content/aboutus";
	}

	@RequestMapping(value="services", method=RequestMethod.GET)
	public String getServices(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "content.services");
		return "content/services";
	}

	@RequestMapping(value="feedback", method=RequestMethod.GET)
	public String getFeedback(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "content.feedback");
		return "content/feedback";
	}
}
