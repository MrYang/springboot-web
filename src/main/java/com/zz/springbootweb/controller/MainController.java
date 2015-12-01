package com.zz.springbootweb.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequiresPermissions("main:*")
    @RequestMapping({"/", "/main"})
    public String main() {
        return "main";
    }
}
