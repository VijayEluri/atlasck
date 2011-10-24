package com.atlasck.backend.web;

import com.atlasck.backend.domain.CurrentVersion;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "current-version", formBackingObject = CurrentVersion.class)
@RequestMapping("/current-version")
@Controller
public class CurrentVersionController {
}
