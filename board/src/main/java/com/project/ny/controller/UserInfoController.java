package com.project.ny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.ny.model.UserBean;

@Controller
@SessionAttributes("loginUser")
public class UserInfoController {

    @RequestMapping("/viewLoginInfo.do")
    public String viewLoginInfo(
            @ModelAttribute("loginUser") UserBean user) {

        return "userInfoPage";
    }

}
