package com.bookshop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    @RequestMapping(value = "/trang-chu/lien-he",method = RequestMethod.GET)
    public ModelAndView showLienHe(){
        ModelAndView view=new ModelAndView("web/lienhe");
        return view;
    }
}
