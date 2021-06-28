package com.bookshop.service;

import com.bookshop.dto.request.UserRequest;
import com.bookshop.dto.response.UserResponse;
import com.bookshop.entity.User;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserService {

    String save(UserRequest userRequest, HttpSession session) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    List<UserResponse> findAll(Pageable pageable);

    List<UserResponse> findByKeyWord(String keyWord, Pageable pageable);

    int getItemKeyWord(String keyWord);

    int getTotalItem();

    User findOneByPhone(String phone);

    UserResponse findById(long id);

    User saveUser(UserRequest userRequest, HttpServletResponse response, String path) throws IOException;

    User updateEnable(String code);

    User setEnable(UserRequest userRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    void deleteById(long[] ids);
}
