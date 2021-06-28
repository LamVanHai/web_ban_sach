package com.bookshop.util;

import com.bookshop.entity.User;
import com.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckUtil {

    @Autowired
    private UserRepository userRepository;

    public boolean checkUserName(String userName) {
        User user = userRepository.findByEmail(userName);
        if (user != null) {
            return true;
        }
        return false;
    }

    public boolean checkEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    public boolean checkPhone(String phone) {
        User user = userRepository.findOneByPhone_number(phone);
        if (user != null) {
            return true;
        }
        return false;
    }
    public boolean checkPhonedetail(String phone,Long id){
        User user = userRepository.findOneByPhone_number(phone);
        if(user.getId()==id){
            return true;
        }
        return false;
    }

    public String convertURl(String url) {
        return null;
    }

    public static String convertVND(Long price){
        String prices=price.toString();
        String b="";
        String c="";
        for (int i = prices.length()-1; i >=0 ; i--) {
            b=b+prices.substring(i);
            prices=prices.substring(0,prices.length()-1);
        }
        for (int i=0;i<b.length();i++){
            if(i==3||i==6||i==9){
                c=","+c;
            }
            c=b.substring(i,i+1)+c;

        }
        return c;
    }

}
