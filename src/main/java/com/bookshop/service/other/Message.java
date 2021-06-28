package com.bookshop.service.other;

import com.bookshop.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class Message {

    @Autowired
    private MessageUtil messageUtil;

    public void listMessage(HttpServletRequest request, ModelAndView mav){
       if (request.getParameter("message") != null) {
        Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
        mav.addObject("message", message.get("message"));
        mav.addObject("alert", message.get("alert"));
       }
    }
}
