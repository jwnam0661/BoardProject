package com.project.ny.dao;

import com.project.ny.model.UserBean;

public interface LoginDao {

    UserBean find(String userId, String password);
}
