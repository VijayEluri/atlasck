package com.atlasck.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
		List<Answer> availableAnswers = new ArrayList<Answer>();
		for (Answer answer : Answer.findAllAnswers()) {
			if (answer.getQuestion().getVisible() == true) {
				availableAnswers.add(answer);
			}
		}

		Collections.sort(availableAnswers, new Comparator<Answer>() {

			@Override
			public int compare(Answer o1, Answer o2) {
				return -(o1.getCreatedAt().compareTo(o2.getCreatedAt()));
			}

		});

		uiModel.addAttribute("answers", availableAnswers);

		return "advice/list";
	}

	/**
	 * View single advice list.
	 * 
	 * @param uiModel
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
	public String advice(Model uiModel, @PathVariable("id") int id) {
		uiModel.addAttribute("actionName", "advice.list");

		List<Answer> availableAnswers = new ArrayList<Answer>();
		Answer answer = Answer.findAnswer(id);
		
		if (answer != null && answer.getQuestion().getVisible() == true) {
			availableAnswers.add(answer);
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
	public String create(@Valid Question question, BindingResult result,
			Model uiModel, HttpServletRequest request) {
		if (result.hasErrors()) {
			uiModel.addAttribute("question", question);
			return "advice/question";
		}

		question.getVisitor().setIpAddress(request.getRemoteAddr());

		for (Visitor v : Visitor.findAllVisitors()) {
			if (v.getEmail().toLowerCase()
					.equals(question.getVisitor().getEmail().toLowerCase())) {
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
	public String successfulAddedQuestion(Model uiModel,
			HttpServletRequest request) {
		uiModel.addAttribute("actionName", "advice.list");

		if (request.getSession().getAttribute(IS_QUESTION_POSTED) != null) {
			request.getSession().removeAttribute(IS_QUESTION_POSTED);
			return "advice/question-sent";
		}

		return "advice/list";
	}

}
