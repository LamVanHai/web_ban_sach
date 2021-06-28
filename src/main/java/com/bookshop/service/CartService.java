package com.bookshop.service;

import com.bookshop.dto.request.AddCart;
import com.bookshop.dto.request.UserRequest;
import com.bookshop.dto.response.CartResponse;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface CartService {

    CartResponse saveCart(HttpSession session, AddCart addCart);

    Long editCart(HttpSession session, AddCart addCart);

    Long deleteCart(HttpSession session, AddCart addCart);

    void saveOrder(UserRequest userRequest, HttpServletResponse response, HttpSession session) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException;

    void saveUser(UserRequest userRequest, HttpServletResponse response, String path) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException;
}
