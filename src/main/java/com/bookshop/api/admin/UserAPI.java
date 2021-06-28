package com.bookshop.api.admin;

import com.bookshop.dto.request.UserRequest;
import com.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

@RestController
public class UserAPI {

    @Autowired
    private UserService userService;

    @PostMapping("/api/users")
    public Long createNew(@RequestBody UserRequest userRequest, HttpSession session) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        userRequest.setEnable(userRequest.getEnable1());
        userRequest.setRole(userRequest.getRole1());
        userService.save(userRequest,session);
        return userRequest.getId();
    }

    @PutMapping("/api/users")
    public Long updateNew(@RequestBody UserRequest userRequest, HttpSession session) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        userRequest.setEnable(userRequest.getEnable1());
        userRequest.setRole(userRequest.getRole1());
        if(userRequest.getNew_password()!=null&&userRequest.getNew_password()!=""){
            userRequest.setPassword(userRequest.getNew_password());
            userRequest.setCheckPassword(userRequest.getNew_password());
        }
        userService.save(userRequest,session);
        return userRequest.getId();
    }

    @DeleteMapping("/api/users")
    public void deleteNew(@RequestBody long[] ids) {
        userService.deleteById(ids);
    }

    @PutMapping("/setEnable")
    public Long enable(@RequestBody UserRequest userRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
         userService.setEnable(userRequest);
         return userRequest.getId();
    }
}
