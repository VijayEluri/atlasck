package com.atlasck.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/content/**")
@Controller
public class ContentController {

	/**
	 * Renders AboutUs page.
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "about-us", method = RequestMethod.GET)
	public String getAboutUs(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "content.aboutUs");
		return "content/aboutus";
	}

	/**
	 * Renders Services page.
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "services", method = RequestMethod.GET)
	public String getServices(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "content.services");
		return "content/services";
	}

	/**
	 * Renders feedback page.
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "feedback", method = RequestMethod.GET)
	public String getFeedback(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "content.feedback");
		return "content/feedback";
	}

}
