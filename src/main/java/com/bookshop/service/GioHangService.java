package com.bookshop.service;

import com.bookshop.dto.GioHang;

import java.util.HashMap;

public interface GioHangService {

    HashMap<Long, GioHang> addCart(Long id, int quanty, HashMap<Long, GioHang> cart);

    HashMap<Long, GioHang> editCart(Long id, int quanty, HashMap<Long, GioHang> cart);

    HashMap<Long, GioHang> deleteCart(Long id, HashMap<Long, GioHang> cart);

    int totalQuanty(HashMap<Long, GioHang> cart);

    int totalPrice(HashMap<Long, GioHang> cart);

}
