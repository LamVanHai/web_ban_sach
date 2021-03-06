package com.bookshop.dto;

public class MessageResponse {

    private String message;

    private String fromLogin;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromLogin() {
        return fromLogin;
    }

    public void setFromLogin(String fromLogin) {
        this.fromLogin = fromLogin;
    }

    public MessageResponse( String fromLogin,String message) {
        this.message = message;
        this.fromLogin = fromLogin;
    }
}
