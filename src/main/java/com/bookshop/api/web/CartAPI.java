package com.bookshop.api.web;


import com.bookshop.constant.MyConstants;
import com.bookshop.dto.request.AddCart;
import com.bookshop.dto.request.UserRequest;
import com.bookshop.dto.response.CartResponse;
import com.bookshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


@RestController
public class CartAPI {

    @Autowired
    private CartService cartService;


    @PostMapping("/api/them-gio-hang")
    public CartResponse themGioHang(HttpSession session, @RequestBody AddCart addCart, HttpServletResponse response) throws IOException {
        return cartService.saveCart(session,addCart);
    }

    @PostMapping("/api/chinh-sua-gio-hang")
    public Long editGioHang(HttpSession session, @RequestBody AddCart addCart) {
        return cartService.editCart(session,addCart);
    }

    @PostMapping("/api/xoa-gio-hang")
    public Long xoaGioHang(HttpSession session, @RequestBody AddCart addCart) {
        return cartService.deleteCart(session,addCart);
    }

    @PostMapping("/api/saveCart")
    private void saveUser(@ModelAttribute UserRequest userRequest, HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
       cartService.saveUser(userRequest,response, MyConstants.thanh_toan);
    }

    @PostMapping("/api/newOrder")
    private void saveOder(@ModelAttribute UserRequest userRequest, HttpServletResponse response, HttpSession session) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        cartService.saveOrder(userRequest,response,session);
    }



}
