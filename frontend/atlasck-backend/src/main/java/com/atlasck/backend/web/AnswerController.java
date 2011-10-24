package com.atlasck.backend.web;

import com.atlasck.backend.domain.Answers;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "answer", formBackingObject = Answers.class)
@RequestMapping("/answer")
@Controller
public class AnswerController {
}
