package com.atlasck.web.controllers;

import java.util.List;

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

import com.atlasck.domain.Answer;
import com.atlasck.domain.Question;
import com.atlasck.domain.Visitor;
import com.atlasck.repository.AnswerRepo;
import com.atlasck.repository.QuestionRepo;
import com.atlasck.repository.VisitorRepo;
import com.atlasck.service.AdviceManager;

/**
 * Advice model actions
 *
 * @author Georgi Lambov
 */
@Controller
@RequestMapping("/advice/**")
public class AdviceController {

	@Autowired private VisitorRepo visitorRepo;
	@Autowired private AnswerRepo answerRepo;
	@Autowired private QuestionRepo questionRepo;

	@Autowired private AdviceManager adviceManager;

	protected final Log logger = LogFactory.getLog(getClass());

	public static final String IS_QUESTION_POSTED = "question_posted";

	/**
	 * Shows answers to questions
	 *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(ModelMap modelMap) {
		modelMap.addAttribute("actionName", "advice.list");

		List<Answer> answers = answerRepo.getAll();
		modelMap.addAttribute("answers", answers);

		for(Answer answer: answers) {
			//TODO relation object and lazy initialization
			Question question = questionRepo.get(answer.getQuestion().getId());
			Visitor visitor = visitorRepo.get(question.getVisitor().getId());
			question.setVisitor(visitor);

			answer.setQuestion(question);
		}

		return "advice/list";
	}

	/**
	 * Sends questions to the system
	 *
	 * @param modelMap
	 * @param question
	 * @param result
	 * @param req
	 * @return
	 */
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

		//TODO check for escaping on save
		Visitor visitor = visitorRepo.getVisitorByEmail(question.getVisitor().getEmail());
		visitor.setNickname(question.getVisitor().getNickname());
		visitor.setIpAddress(req.getRemoteAddr());
		adviceManager.add(question, visitor);

		return "redirect:/advice/question-sent.html";
	}

	/**
	 * Displays advice form
	 *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="question", method=RequestMethod.GET)
	public String create(ModelMap modelMap, HttpServletRequest request) {
		modelMap.addAttribute("actionName", "advice.list");

		Question question = new Question();
		question.setVisitor(new Visitor());
		modelMap.addAttribute("question", question);

		request.getSession().setAttribute(IS_QUESTION_POSTED, true);

		return "advice/question";
	}

	@RequestMapping(value="question-sent", method=RequestMethod.GET)
	public String successfulForm(ModelMap modelMap, HttpServletRequest request) {
		modelMap.addAttribute("actionName", "advice.list");

		if(request.getSession().getAttribute(IS_QUESTION_POSTED) != null) {
			request.getSession().removeAttribute(IS_QUESTION_POSTED);
			return "advice/question-sent";
		}

		return "redirect:/advice/list.html";
	}

	//TODO refactor following setters in the constructor
	public void setVisitorRepo(VisitorRepo visitorRepo) {
		this.visitorRepo = visitorRepo;
	}

	public void setAdviceManager(AdviceManager adviceManager) {
		this.adviceManager = adviceManager;
	}

	public void setAnswerRepo(AnswerRepo answerRepo) {
		this.answerRepo = answerRepo;
	}

	public void setQuestionRepo(QuestionRepo questionRepo) {
		this.questionRepo = questionRepo;
	}
}
