package org.itstep.controller;

import org.itstep.model.ModelEquipment;
import org.itstep.service.analysis.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private final AnalysisService analysisService;

    public ProductController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping("/update")
    public String testUser(Model model){
        model.addAttribute("model", new ModelEquipment());
        return "product/update";
    }

    @PostMapping("/update")
    @ResponseBody
    public String testUser(@RequestParam String type, ModelEquipment modelEquipment){
        System.out.println("type: " + type);
        System.out.println("modelEquipment: " + modelEquipment);
        analysisService.checkProduct(type, modelEquipment);
        return "redirect:/user";
    }
}
