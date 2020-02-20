package com.test.converteruploader.controller;

import com.test.converteruploader.model.UserService;
import com.test.converteruploader.model.UserServiceImpl;
import com.test.converteruploader.model.entity.ConvertRequest;
import com.test.converteruploader.model.entity.Users;
import com.test.converteruploader.model.entity.Valute;
import com.test.converteruploader.repository.ValuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    public UserController(UserService userService, UserServiceImpl userServiceImpl, ValuteRepository valuteRepo) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
        this.valuteRepo = valuteRepo;
    }

    private UserService userService;
    private UserServiceImpl userServiceImpl;
    private ValuteRepository valuteRepo;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");
        return model;
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        Users user = new Users();
        model.addObject("user", user);
        model.setViewName("user/signup");

        return model;
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public ModelAndView creatUser (@Valid Users user) {
        ModelAndView model = new ModelAndView();

        if (!userServiceImpl.addUser(user)) {
            model.addObject("msg", "This email already exists!");
            model.addObject("user", new Users());
            model.setViewName("user/signup");

        }else {
            userServiceImpl.saveUser(user);
            model.addObject("msg", "\n" +
                    "Please send your mail to confirm your account");
            model.addObject("user", new Users());
            model.setViewName("user/signup");
        }
        return model;
    }

    @GetMapping(value = {"/activate/{code}"})
    public ModelAndView activate(@PathVariable String code) {
        ModelAndView model = new ModelAndView();
        boolean isActivated = userService.isActivateUser(code);

        if (isActivated) {
            model.addObject("msg", "User successfully activated");
        }else {
            model.addObject("msg","Activation code is not found!" );
        }
        model.addObject("user", new Users());
        model.setViewName("user/login");
        return model;
    }

    @RequestMapping(value = {"/home/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Valute> valutes = valuteRepo.findAll();

        model.addObject("converter", new ConvertRequest());
        model.addObject("valutName" , valutes);
        model.addAllObjects(new HashMap<>());
        model.setViewName("home/home");
        return model;
    }

    @RequestMapping(value = {"/access_denied"}, method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }
    @RequestMapping(value = {"/convert"}, method = RequestMethod.POST)
    public ModelAndView convertStarted(ConvertRequest item) {
        ModelAndView model = new ModelAndView();
        item.setResult(BigDecimal.valueOf(100.2));
        model.addObject("result" , item);
        model.setViewName("home/home");
        return model;
    }
}
