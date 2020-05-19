package org.itstep.controller;

import org.itstep.dto.ModelEquipmentDto;
import org.itstep.model.Store;
import org.itstep.model.Subscription;
import org.itstep.service.SubscriptionService;
import org.itstep.service.analysis.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Controller
@RequestMapping("/stand")
public class StandController {


    @Autowired
    private final AnalysisService analysisService;

    @Autowired
    private final SubscriptionService subscriptionService;


    public StandController(AnalysisService analysisService, SubscriptionService subscriptionService) {
        this.analysisService = analysisService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping(value = "/by/{type}")
    public String stand(@PathVariable String type,
                        @RequestParam(value = "page", defaultValue = "1") int page,
//                        @RequestParam(value = "act", defaultValue = "+") char act,
                        Model model) {

        List<Store> stores = analysisService.getStores();


        int end = page * 5;
        int start = end - 5;
        int countPage = analysisService.getCountPage(type, end - start);

        Map<String, List<ModelEquipmentDto>> productsByType = analysisService.getProductsByType(type, start, end);

        System.out.println("start: " + start);
        System.out.println("end: " + end);
        System.out.println("page: " + page);

        model.addAttribute("map", productsByType);
        model.addAttribute("type", type);
        model.addAttribute("subscription", new Subscription());
        model.addAttribute("countPage", countPage);

        return "stand/index";
    }

    @GetMapping("/subscription")
    public String subscription(Subscription subscription) {
        System.out.println("SUBSCRIPTION: " + subscription);
        subscriptionService.saveSubscription(subscription);
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String deleteSub(@RequestParam int userId, @RequestParam int productId) {
        subscriptionService.deleteSubscription(userId, productId);
        return "redirect:/user";
    }


}
