package com.bookshop.dto.response;

import com.bookshop.entity.User_group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends BaseResponse<UserResponse> {

    private Long id;

    private String name;

    private String userName;

    private String password;

    private String new_password;

    private String confirm_password;

    private String email;

    private String enableText;

    private Integer enable;

    private String address;

    private String sex;

    private String phone_number;

    private User_group user_group;

    private Long user_group_id;

    private String province_id;

    private String district_id;

    private String ward_id;

    private String role;

}
