package com.example.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {

    private Long userId;

    private String userName;

    private String userPassword;

    private String userPhone;

    private Integer userSex;

    private String userAvatar;

}
