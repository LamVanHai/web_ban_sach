package com.bookshop.util;

import com.bookshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class SendMail {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendMailForGotPassword(String to, String content, String subject) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);

//        message.setFrom("hailv113bg@gmail.com");
            message.setSubject(subject);
            message.setText(content);
//        mailSender.send(message);
            javaMailSender.send(message);
        } catch (Exception e) {
            return null;
        }
        return "success";
    }

    public Boolean changePassword(String newPassword, PasswordEncoder passwordEncoder, String oldPassword) {
        return passwordEncoder.matches(newPassword, oldPassword);
    }

    public void emailVerify(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String subject = "Xac thuc E-mail";
        String senderName = "BookShop";
        String mailContent = "<p>Gui " + user.getName() + ",/<p>";
        mailContent += "<p> Vui long click vao Link ben duoi de xac thuc email cua ban :</p>";
        String veryfyURL = siteURL + "verify?code=" + user.getVerification_Code();

        mailContent += "<h3><a href=\""+veryfyURL+"\">XAC THUC</a></h3>";
        mailContent += "<p> Cam on ban <br> BookShop</p>";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("hailv113bg@gmail.com",senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);

        helper.setText(mailContent,true);

        javaMailSender.send(message);
    }
}
