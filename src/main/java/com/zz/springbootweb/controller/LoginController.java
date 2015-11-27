package com.zz.springbootweb.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        Object object = subject.getPrincipal();
        if (object == null) {
            return "login";
        }
        return "redirect:/";
    }

}
