package com.project.ny.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.ny.dao.LoginDao;
import com.project.ny.model.UserBean;
import com.project.ny.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Resource(name = "loginDao")
    private LoginDao loginDao;

    @Override
    public UserBean find(String userId, String password) {
        return loginDao.find(userId, password);
    }
}
