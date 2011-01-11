package com.atlasck.web.controllers;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atlasck.domain.Question;

@Controller
@RequestMapping("/advice/**")
public class AdviceController {

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "advice.list");
		return "advice/list";
	}

	@RequestMapping(value="question", method=RequestMethod.POST)
	public String create(ModelMap modelMap, @Valid Question question, BindingResult result) {
		modelMap.addAttribute("actionName", "advice.list");

		if(result.hasErrors()) {
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result.hasErrors());
		}

		logger.info("post question form");

		return "advice/question";
	}

	@RequestMapping(value="question", method=RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "advice.list");
		modelMap.addAttribute("question", new Question());

		logger.info("create question form");

		return "advice/question";
	}

}
