package com.atlasck.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atlasck.domain.Question;
import com.atlasck.domain.Visitor;
import com.atlasck.repository.VisitorRepo;
import com.atlasck.service.AdviceManager;

@Controller
@RequestMapping("/advice/**")
public class AdviceController {

	@Autowired private VisitorRepo visitorRepo;
	@Autowired private AdviceManager adviceManager;

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "advice.list");
		return "advice/list";
	}

	@RequestMapping(value="question", method=RequestMethod.POST)
	public String create(ModelMap modelMap, @Valid Question question,
			BindingResult result, HttpServletRequest req) {

		modelMap.addAttribute("actionName", "advice.list");

		if(question == null) throw new IllegalArgumentException();

		if(result.hasErrors()) {
			modelMap.addAttribute("question", question);
			modelMap.addAttribute("result", result);
			return "advice/question";
		}

		Visitor visitor = visitorRepo.getVisitorByEmail(question.getVisitor().getEmail());
		visitor.setIpAddress(req.getRemoteAddr());

		adviceManager.add(question, visitor);

		return "advice/question";
	}

	@RequestMapping(value="question", method=RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "advice.list");

		Question question = new Question();
		question.setVisitor(new Visitor());
		modelMap.addAttribute("question", question);

		return "advice/question";
	}

	public void setVisitorRepo(VisitorRepo visitorRepo) {
		this.visitorRepo = visitorRepo;
	}

	public void setAdviceManager(AdviceManager adviceManager) {
		this.adviceManager = adviceManager;
	}
}
