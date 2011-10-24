package com.atlasck.backend.web;

import com.atlasck.backend.domain.Visitors;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "visitor", formBackingObject = Visitors.class)
@RequestMapping("/visitor")
@Controller
public class VisitorController {
}
