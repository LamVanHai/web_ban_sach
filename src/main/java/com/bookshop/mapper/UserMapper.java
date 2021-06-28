package com.bookshop.mapper;

import com.bookshop.dto.response.UserResponse;
import com.bookshop.entity.Role;
import com.bookshop.entity.User;
import com.bookshop.util.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class UserMapper {

    public static UserResponse mapToResponse(User user) {
        UserResponse userResponse=new UserResponse();
        try {
            ReflectionUtil.mapper(user, userResponse);
            if(user.getEnable()==null){
                userResponse.setEnableText("");
            }
            else if(user.getEnable()==0){
                userResponse.setEnableText("Chưa kích hoạt");
            }else if(user.getEnable()==1){
                userResponse.setEnableText("Đã kích hoạt");
            }
            List<Role> list=user.getRoles_user();
            String roles="";
            for (Role role: list
                 ) {
                roles+=role.getName()+",";
            }
            roles=roles.substring(0,roles.length()-1);
            userResponse.setRole(roles);
            return userResponse;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();

            return null;
        }
    }
}
