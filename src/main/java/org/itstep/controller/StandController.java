package org.itstep.controller;

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

import java.util.List;

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
    public String stand(@PathVariable String type, Model model){
        List<Store> stores = analysisService.getStores();

        model.addAttribute("type", type);
        model.addAttribute("subscription", new Subscription());
        model.addAttribute("store", stores);
        model.addAttribute("map", analysisService.getProductsByType(type));
        return "stand/index";
    }

    @GetMapping("/subscription")
    public String subscription(Subscription subscription){
        System.out.println("SUBSCRIPTION: " + subscription);
        subscriptionService.saveSubscription(subscription);
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String deleteSub(@RequestParam int userId, @RequestParam int productId){
        subscriptionService.deleteSubscription(userId, productId);
        return "redirect:/user";
    }
}
