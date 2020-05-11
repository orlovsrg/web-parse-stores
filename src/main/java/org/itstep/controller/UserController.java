package org.itstep.controller;

import org.itstep.data.parse.DataUser;
import org.itstep.dto.UserDto;
import org.itstep.model.User;
import org.itstep.valodator.CheckLoginValidator;
import org.itstep.valodator.PasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private final PasswordValidator passwordValidator;
    @Autowired
    private final CheckLoginValidator checkLoginValidator;
    @Autowired
    private final DataUser dataUser;

    public UserController(PasswordValidator passwordValidator, CheckLoginValidator checkLoginValidator, DataUser dataUser) {
        this.passwordValidator = passwordValidator;
        this.checkLoginValidator = checkLoginValidator;
        this.dataUser = dataUser;
    }

    @GetMapping
    public String login(Model model) {
        log.info("GET Form login");
        model.addAttribute("userDto", new UserDto());
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute UserDto userDto, BindingResult bindingResult, Model model) {
        log.info("Post Form login");
        User user = new User();
        user.setLogin(userDto.getCheckLogin());
        user.setPassword(userDto.getCheckPassword());
        int id = dataUser.hasUser(user);
        if (id == 0) {
            bindingResult.rejectValue("checkLogin", "Логин или пароль не совпадают", "Логин или пароль не совпадают");
            return "login";
        }
        model.addAttribute("userDto", dataUser.getOne(id));
        return "redirect:/user/" + id;
    }

    @PostMapping(value = "registration")
    public String registration(@Validated @ModelAttribute UserDto userDto,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        checkLoginValidator.validate(userDto, bindingResult);
        passwordValidator.validate(userDto, bindingResult);
        log.info("UserDto: {}", userDto);
        if (bindingResult.hasErrors()) {
            return "login";
        } else {
            int id = dataUser.save(userDto);
            model.addAttribute("user", dataUser.getOne(id));
            return "redirect:/user/" + id;
        }
    }

    @GetMapping("/{id}")
    public String user(@PathVariable int id, Model model) {
        model.addAttribute("user", dataUser.getOne(id));
        return "user/index";
    }
}
