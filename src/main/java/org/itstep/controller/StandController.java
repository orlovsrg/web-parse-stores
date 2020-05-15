package org.itstep.controller;

import org.itstep.service.analysis.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stand")
public class StandController {

    @Autowired
    private final AnalysisService analysisService;

    public StandController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping(value = "/{type}")
    public String stand(@PathVariable String type, Model model){
        model.addAttribute("map", analysisService.getProductsBy(type));
        return "stand/index";
    }
}
