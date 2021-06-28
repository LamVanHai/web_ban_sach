package com.bookshop.util;

import java.util.Random;

public class RandomPassword {

    private String passWord;

    public RandomPassword() {
        this.passWord = randomPassword();
    }

    public static String randomPassword() {
        Random random1 = new Random();
        String a = "";
        for (int i = 0; i < 6; i++) {
            int m = random1.nextInt(9);
            a += m;
        }
        return a;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
