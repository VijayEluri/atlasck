package com.atlasck.web;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atlasck.domain.Answer;
import com.atlasck.domain.Question;
import com.atlasck.domain.Visitor;

@RequestMapping("/advice/**")
@Controller
public class AdviceController {

	private static final String IS_QUESTION_POSTED = "question_posted";

	/**
	 * Displays questions with their answers.
	 * 
	 * @param modelMap
	 * @return
	 */
	// TODO duplicated annotations to aspects
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = "text/html")
	public String list(Model uiModel) {
		uiModel.addAttribute("actionName", "advice.list");

		// TODO replace with criteria query
		Set<Answer> availableAnswers = new HashSet<Answer>();

		for (Answer answer : Answer.findAllAnswers()) {
			if(answer.getQuestion().getVisible() == true) {
				availableAnswers.add(answer);
			}
		}

		uiModel.addAttribute("answers", availableAnswers);
		
		return "advice/list";
	}

	/**
	 * Records questions.
	 * 
	 * @param question
	 * @param result
	 * @param uiModel
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	@Transactional
	public String create(@Valid Question question, BindingResult result, Model uiModel, HttpServletRequest request) {
		if (result.hasErrors()) {
			uiModel.addAttribute("question", question);
			return "advice/question";
		}

		question.getVisitor().setIpAddress(request.getRemoteAddr());

		for (Visitor v : Visitor.findAllVisitors()) {
			if (v.getEmail().toLowerCase().equals(question.getVisitor().getEmail().toLowerCase())) {
				v.setNickname(question.getVisitor().getNickname());
				v.setIpAddress(question.getVisitor().getIpAddress());
				question.setVisitor(v);
				v.merge();
				break;
			}
		}

		if (question.getVisitor().getId() == null) {
			question.getVisitor().persist();
		}

		question.persist();

		return "redirect:question-sent.html";
	}

	/**
	 * Creates question form.
	 * 
	 * @param modMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "question", method = RequestMethod.GET, produces = "text/html")
	public String createForm(Model uiModel, HttpServletRequest request) {
		uiModel.addAttribute("actionName", "advice.list");

		uiModel.addAttribute("question", new Question());
		request.getSession().setAttribute(IS_QUESTION_POSTED, true);

		return "advice/question";
	}

	@RequestMapping(value = "question-sent", method = RequestMethod.GET, produces = "text/html")
	public String successfulAddedQuestion(Model uiModel, HttpServletRequest request) {
		uiModel.addAttribute("actionName", "advice.list");

		if (request.getSession().getAttribute(IS_QUESTION_POSTED) != null) {
			request.getSession().removeAttribute(IS_QUESTION_POSTED);
			return "advice/question-sent";
		}

		return "advice/list";
	}

	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<String>
	 * handleIOException(Exception ex) {
	 * 
	 * // prepare responseEntity ResponseEntity<String> r = new
	 * ResponseEntity<String>(ex.toString(), HttpStatus.OK);
	 * 
	 * return r; }
	 */
}
