package com.hengyh.blog2.controller;

import com.hengyh.blog2.security.UserRegistrationService;
import com.hengyh.blog2.vo.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRegistrationService registrationService;

    @Autowired
    public RegistrationController(UserRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String getRegistration() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        registrationService.registration(form);
        return "redirect:/login";
    }
}
