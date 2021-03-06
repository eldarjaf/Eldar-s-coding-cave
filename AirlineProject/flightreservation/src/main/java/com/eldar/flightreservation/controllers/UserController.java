package com.eldar.flightreservation.controllers;

import com.eldar.flightreservation.entities.User;
import com.eldar.flightreservation.repos.UserRepository;
import com.eldar.flightreservation.services.SecurityService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Slf4j
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "/showReg", method = RequestMethod.GET)
    public String showRegistrationPage() {
        log.debug("Inside showRegistrationPage()");

        return "login/registerUser";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult result) {
        log.debug("Inside register()" + user);
        if (result.hasErrors()) {
            return "login/registerUser";
        }
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
        return "login/fancy-login";
    }

    @RequestMapping(value = "/showLogin", method = RequestMethod.GET)
    public String showLoginPage() {
        log.debug("Inside showLoginPage()");

        return "login/fancy-login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        ModelMap modelMap) {
        log.debug("Inside login() and email is: " + email);

        boolean loginResponse = securityService.login(email, password);

        log.debug("Is it authenticated? :" + loginResponse);
        if (loginResponse) {
            return "findFlights";
        } else {
            modelMap.addAttribute("msg", "Invalid user name or password. Please, try again.");
            return "login/fancy-login";
        }
    }
}
