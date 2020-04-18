package com.project.ny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.ny.annotation.NoAuth;
import com.project.ny.model.UserBean;
import com.project.ny.service.LoginService;

@Controller
@SessionAttributes("loginUser")
@NoAuth
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/login.do")
    public String excute(
            @ModelAttribute("MAINFORM") UserBean user,
            Model model) {

        UserBean loginUser = loginService.find(user.getUserId(), user.getPassword());
        model.addAttribute("loginUser", loginUser);

        return "topPage";
    }

}
