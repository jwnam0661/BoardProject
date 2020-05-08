package com.project.ny.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
            @ModelAttribute @Valid UserBean user,
            BindingResult result,
            Model model) {

    	if(result.hasErrors()) {
    		List<ObjectError> list = result.getAllErrors();
    		for( ObjectError error : list ) {
    			System.out.println(error);
    		}
    		return "login";
    	}

        UserBean loginUser = loginService.find(user.getUserId(), user.getPassword());
        model.addAttribute("loginUser", loginUser);

        return "topPage";
    }

}
