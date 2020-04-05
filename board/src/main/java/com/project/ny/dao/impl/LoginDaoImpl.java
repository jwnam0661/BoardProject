package com.project.ny.dao.impl;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ny.dao.LoginDao;
import com.project.ny.model.UserBean;

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public UserBean find(String userId, String password) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("userId", userId);
        param.put("password", password);
        UserBean result = sqlSession.selectOne("login.find", param);
        return result;
    }
}
