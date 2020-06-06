package org.itstep.controller;

import org.itstep.service.parse.ParserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private final ParserManager parserManager;

    public AdminController(ParserManager parserManager) {
        this.parserManager = parserManager;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String index(){
        log.info("In ParseController method index");
        return "admin/index";
    }

    @GetMapping(value = "/parsing")
    public String parse(){
        log.info("In ParseController parse ");
        parserManager.parse();
        return "redirect:/user";
    }
}
