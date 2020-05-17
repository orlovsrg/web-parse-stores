package org.itstep.controller;

import org.itstep.data.parse.DataUser;
import org.itstep.dto.UserDto;
import org.itstep.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private final DataUser dataUser;
    @Autowired
    private final SubscriptionService subscriptionService;

    public UserController(DataUser dataUser, SubscriptionService subscriptionService) {
        this.dataUser = dataUser;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping()
    public String user(Model model) {
        UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("PRINCIPAL: " + userDto);
        model.addAttribute("product", subscriptionService.getAllSubscriptionProduct(userDto.getId()));
        return "user/index";
    }

}
