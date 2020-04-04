package com.project.ny.service;

import com.project.ny.model.UserBean;

public interface LoginService {

    UserBean find(String userId, String password);
}
