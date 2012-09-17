package com.atlasck.backend.web;

import com.atlasck.backend.domain.Questions;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "question", formBackingObject = Questions.class)
@RequestMapping("/question")
@Controller
public class QuestionController {
}
