package com.bookshop.api.web;

import com.bookshop.dto.request.UserRequest;
import com.bookshop.service.UserService;
import com.bookshop.util.VerifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@RestController
public class MailAPI {

    @Autowired
    private UserService userService;

    @Autowired
    public JavaMailSender emailSender;

    @PostMapping(value = "api/mail")
    public void sendGmail(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        String email = request.getParameter("email");
        int i;
        if( session.getAttribute("count")==null){
            i=0;
        }
        else {
            i= (int) session.getAttribute("count");
        }
        session.setAttribute("count",++i);
        String g_recaptcha = request.getParameter("g-recaptcha-response");
        boolean valid = VerifyUtils.verify(g_recaptcha);
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        ModelAndView mv = new ModelAndView("forgotpassword");
        if (i<=2){
            if(email==""){
                response.sendRedirect("/quen-mat-khau?emails");
            }
            else {
                response.sendRedirect("/quen-mat-khau?" + userService.save(userRequest,session));
            }
        }
        else {
            if(email==""&&!valid){
                response.sendRedirect("/quen-mat-khau?not_robot");
            }
            else if (email != "" && !valid) {
                session.setAttribute("email", email);
                response.sendRedirect("/quen-mat-khau?not_robot");

            } else if (email == "") {
                response.sendRedirect("/quen-mat-khau?emails");
            } else if (email != "" && valid) {
                response.sendRedirect("/quen-mat-khau?" + userService.save(userRequest,session));
            }
        }

    }
}
