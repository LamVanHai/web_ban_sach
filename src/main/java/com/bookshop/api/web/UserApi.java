package com.bookshop.api.web;

import com.bookshop.constant.MyConstants;
import com.bookshop.dto.request.UserRequest;
import com.bookshop.service.CartService;
import com.bookshop.service.UserService;
import com.bookshop.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

@RestController()
public class UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

	@PostMapping("/api/user")
    public String saveUser(@RequestBody UserRequest userRequest, HttpServletRequest request, HttpSession session) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, MalformedURLException {
        String siteURL=request.getRequestURL().toString();
        userRequest.setSiteURL(siteURL);
	    return userService.save(userRequest,session);
    }

    @PostMapping("/api/changepassword")
    public String changePassword(@RequestBody UserRequest userRequest,HttpSession session) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        userRequest.setId(SecurityUtils.getPrincipal().getId());
	    return userService.save(userRequest,session);
    }

    @PostMapping("/api/user_detail")
    public void updateUserDetail(@ModelAttribute UserRequest userRequest, HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
	    userRequest.setId(SecurityUtils.getPrincipal().getId());
	    cartService.saveUser(userRequest,response, MyConstants.thong_tin_ca_nhan);
    }
}
