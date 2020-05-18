package org.itstep.controller;

import org.itstep.data.parse.DataUser;
import org.itstep.dto.UserDto;
import org.itstep.model.ModelEquipment;
import org.itstep.model.User;
import org.itstep.service.analysis.AnalysisService;
import org.itstep.valodator.CheckLoginValidator;
import org.itstep.valodator.PasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {
    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private final PasswordValidator passwordValidator;
    @Autowired
    private final CheckLoginValidator checkLoginValidator;
    @Autowired
    private final DataUser dataUser;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final AnalysisService analysisService;

    public HomeController(PasswordValidator passwordValidator, CheckLoginValidator checkLoginValidator, DataUser dataUser, BCryptPasswordEncoder bCryptPasswordEncoder, AnalysisService analysisService) {
        this.passwordValidator = passwordValidator;
        this.checkLoginValidator = checkLoginValidator;
        this.dataUser = dataUser;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.analysisService = analysisService;
    }

    @GetMapping
    public String home(WebRequest webRequest){
        log.info("GET home controller");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        log.info("GET Form login");
        model.addAttribute("userDto", new UserDto());
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute UserDto userDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        log.info("Post Form login");
        User user = new User();
        user.setLogin(userDto.getCheckLogin());
        user.setPassword(userDto.getCheckPassword());
        int id = dataUser.hasUser(user);
        System.out.println("id log user: " + id);
        if (id == 0) {
           bindingResult.rejectValue("checkLogin", "Логин или пароль не верны", "Логин или пароль не верны");
            return "login";
        }
        model.addAttribute("userDto", dataUser.getOne(id));
        return "redirect:/user";
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
            String encoderPassword = userDto.getPassword();
            userDto.setPassword(bCryptPasswordEncoder.encode(encoderPassword));
            int id = dataUser.save(userDto);
            model.addAttribute("user", dataUser.getOne(id));
            return "redirect:/user";
        }
    }

}
