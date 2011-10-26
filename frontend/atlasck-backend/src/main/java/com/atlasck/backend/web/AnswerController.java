package com.atlasck.backend.web;

import java.util.Collection;

import com.atlasck.backend.domain.Answers;
import com.atlasck.backend.domain.Questions;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "answer", formBackingObject = Answers.class)
@RequestMapping("/answer")
@Controller
public class AnswerController {

	@ModelAttribute("questionses")
    public Collection<Questions> populateQuestionses() {
        return Questions.findAllQuestionses();
    }
}
