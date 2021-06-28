package com.bookshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

    @RequestMapping(value = "/quan-tri/tro-chuyen")
    public ModelAndView chat(){
        ModelAndView modelAndView=new ModelAndView("admin/message/message");
        return modelAndView;
    }
}
