package com.atlasck.backend.web;

import com.atlasck.backend.domain.Questions;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "questionses", formBackingObject = Questions.class)
@RequestMapping("/questionses")
@Controller
public class QuestionsController {
}
