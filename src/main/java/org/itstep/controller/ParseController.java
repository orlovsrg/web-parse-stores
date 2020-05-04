package org.itstep.controller;

import org.itstep.service.parse.ParserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/parse")
public class ParseController {
    private final Logger log = LoggerFactory.getLogger(ParseController.class);

    @Autowired
    private final ParserManager parserManager;

    public ParseController(ParserManager parserManager) {
        this.parserManager = parserManager;
    }

    @GetMapping
    public String index(){
        log.info("In ParseController method index");
        return "parse/index";
    }

    @GetMapping(value = "parsing")
    public String parse(){
        log.info("In ParseController parse ");
        parserManager.parse();
        return "parse/index";
    }
}
