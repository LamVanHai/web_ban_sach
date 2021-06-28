package com.bookshop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ForgetPasswordController {

    @RequestMapping(value = "/quen-mat-khau")
    public ModelAndView forgotPassword(HttpSession session){
        String email= (String) session.getAttribute("email");
        ModelAndView mv=new ModelAndView("web/forgotpassword");
        mv.addObject("email",email);
        return mv;
    }
}
