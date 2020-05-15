package org.itstep.controller;

import org.itstep.data.parse.DataUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private final DataUser dataUser;

    public UserController(DataUser dataUser) {
        this.dataUser = dataUser;
    }

    @GetMapping("/{id}")
    public String user(@PathVariable int id, Model model) {
        model.addAttribute("user", dataUser.getOne(id));
        return "user/index";
    }

}
