package com.bookshop.service.impl;

import com.bookshop.dto.GioHang;
import com.bookshop.service.GioHangService;
import com.bookshop.util.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GioHangServiceImpl implements GioHangService {

    @Autowired
    private CartUtil cartUtil;

    @Override
    public HashMap<Long, GioHang> addCart(Long id,int quanty, HashMap<Long, GioHang> cart) {
        return cartUtil.addCart(id,quanty,cart);
    }

    @Override
    public HashMap<Long, GioHang> editCart(Long id, int quanty, HashMap<Long, GioHang> cart) {
        return cartUtil.editCart(id,quanty,cart);
    }

    @Override
    public HashMap<Long, GioHang> deleteCart(Long id, HashMap<Long, GioHang> cart) {
        return cartUtil.deleteCart(id,cart);
    }

    @Override
    public int totalQuanty(HashMap<Long, GioHang> cart) {
        return cartUtil.totalQuanty(cart);
    }

    @Override
    public int totalPrice(HashMap<Long, GioHang> cart) {
        return cartUtil.totalPrice(cart);
    }
}
