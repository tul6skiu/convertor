package com.test.converteruploader.controller;

import com.test.converteruploader.model.UserService;
import com.test.converteruploader.model.UserServiceImpl;
import com.test.converteruploader.model.entity.*;
import com.test.converteruploader.repository.HistoryRepository;
import com.test.converteruploader.repository.ValCursRepository;
import com.test.converteruploader.repository.ValuteRepository;
import com.test.converteruploader.service.ServiceCollectedValData;
import org.HdrHistogram.Histogram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static java.lang.Double.parseDouble;

@Controller
public class UserController {

    @Autowired
    public UserController(UserService userService, UserServiceImpl userServiceImpl,
                          ValuteRepository valuteRepo,
                          HistoryRepository historyRepo,
                          ServiceCollectedValData serviceCollectedValData,
                          ValCursRepository cursRepo) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
        this.valuteRepo = valuteRepo;
        this.historyRepo = historyRepo;
        this.serviceCollectedValData = serviceCollectedValData;
        this.cursRepo = cursRepo;
    }

    private ServiceCollectedValData serviceCollectedValData;
    private UserService userService;
    private UserServiceImpl userServiceImpl;
    private ValuteRepository valuteRepo;
    private ValCursRepository cursRepo;
    private HistoryRepository historyRepo;

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
        var model = new ModelAndView();
        boolean isActivated = userServiceImpl.isActivateUser(code);

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
        Mono<ValCurs> currentValutes = serviceCollectedValData
                .gettingCurrencyData()
                .map(e -> {
                    try {
                        return serviceCollectedValData.whenJavaGotFromXmlStr(e);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    return null;
                });
        cursRepo.save(Objects.requireNonNull(currentValutes.block()));
        List<Valute> valutes = valuteRepo.findAll();

        model.addObject("converter", new ConvertRequest());
        model.addObject("valutName" , valutes);
        model.setViewName("home/home");
        return model;
    }

    @RequestMapping(value = {"/access_denied"}, method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }


    @PostMapping("/convert")
    public String greetingSubmit(@ModelAttribute ConvertRequest item, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String from = item.getCurrentName();
        String to = item.getTargetName();
        Valute fromValute = valuteRepo.findValuteByName(from);
        Valute toValute = valuteRepo.findValuteByName(to);

        double res = from.equals(to) ?
            item.getCount() :
            item.getCount() * parseDouble(fromValute.getValue().replace(",", ".")) /
                parseDouble(toValute.getValue().replace(",", "."));

        BigDecimal countResult = BigDecimal.valueOf(res);
        createdHistoryAndSave(item, countResult, auth, fromValute);
        model.addAttribute("result", countResult);
        return "result";
    }


    @RequestMapping("/home/history")
    public String list(ModelMap model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<History> history = historyRepo.findAllByUserName(auth.getName());
        model.addAttribute("page", history);

        return "home/history";
    }


    private void createdHistoryAndSave(ConvertRequest item,BigDecimal countResult, Authentication auth, Valute valute) {

        History history = new History();

        history.setId(UUID.randomUUID());
        history.setSourceCur(item.getCurrentName());
        history.setTargetCur(item.getTargetName());
        history.setSourceValue(item.getCount());
        history.setTargetValue(countResult);
        history.setDate(LocalDate.now());
        history.setUserName(auth.getName());
        history.setValute(valute);

        historyRepo.save(history);
    }
}
