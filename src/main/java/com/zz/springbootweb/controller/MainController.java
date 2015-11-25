package com.zz.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping("main")
    public String main(HttpServletRequest request){
        request.setAttribute("test", "test");
        return "main";
    }
}
