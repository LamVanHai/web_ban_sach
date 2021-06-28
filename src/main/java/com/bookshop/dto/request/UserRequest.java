package com.bookshop.dto.request;

import com.bookshop.entity.User_group;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class UserRequest {

    private Long id;

    private String name;

    private String userName;

    private String password;

    private String new_password;

    private String checkPassword;

    private String confirm_password;

    private String email;

    private String address;

    private String phone_number;

    private User_group user_group;

    private String payment_methods;

    private Integer enable;

    private String verification_Code;

    private String siteURL;

    private String note;

    private String province_id;

    private String district_id;

    private String ward_id;

    private String role;

    private String role1;

    private Integer enable1;

}
