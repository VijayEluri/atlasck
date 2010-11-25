package com.atlasck.web.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/advice/**")
public class AdviceController {

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "advice.list");
		return "advice/list";
	}

}
