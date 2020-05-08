package com.project.ny.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserBean {

	@NotEmpty
    private String userId;
	@NotEmpty
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
