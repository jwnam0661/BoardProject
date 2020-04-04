package com.project.ny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ny.model.UserBean;
import com.project.ny.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/login.do")
    public String excute() {

        UserBean user = loginService.find("jwnam0661", "rkswl0671");

        return "test/test";
    }

}
