package org.itstep.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {
    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public String home(WebRequest webRequest){
        log.info("GET home controller");
        return "index";
    }

    @GetMapping(value = "/stand/{type}")
    public String stand(@PathVariable String type, Model model){
        log.info("type: {}", type);
        model.addAttribute("type", type);
        return "stand/index";
    }


}
