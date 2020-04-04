package com.project.ny.model;

import java.sql.Date;

import lombok.Data;

@Data
public class UserBean {

    private String userId;
    private String password;
    private String userName;
    private String userNameKana;
    private String nickname;
    private Date joinDate;
    private Date lastLoginDate;
    private String position;
    private int rankId;
    private String address;
    private String email;

}
