package com.bookshop.dto.response;

import com.bookshop.dto.GioHang;


public class CartResponse {

    private String status;

    private int amount;

    public void setGioHang(GioHang gioHang) {
        this.gioHang = gioHang;
    }

    private GioHang gioHang;

    public CartResponse(String status, int amount) {
        this.status = status;
        this.amount = amount;
    }

    public CartResponse(){};
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
