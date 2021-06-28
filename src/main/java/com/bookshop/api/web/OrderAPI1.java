package com.bookshop.api.web;

import com.bookshop.dto.request.OrderRequest;
import com.bookshop.dto.request.UserRequest;
import com.bookshop.service.OrderService;
import com.bookshop.service.UserService;
import com.bookshop.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@RestController
public class OrderAPI1 {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/api/huydonhang")
    public ResponseEntity<Void> huyDonHang(@RequestBody OrderRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        OrderRequest orderRequest = orderService.save1(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/api/thanh-toan")
    public ResponseEntity<Void> thanhToan(@ModelAttribute UserRequest userRequest , HttpServletResponse response, HttpSession session) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        userRequest.setId(SecurityUtils.getPrincipal().getId());
        if(userRequest.getAddress()==null||userRequest.getName()==null||userRequest.getDistrict_id()==null||userRequest.getProvince_id()==null||userRequest.getWard_id()==null||userRequest.getPhone_number()==null){
            response.sendRedirect("/gio-hang/xac-nhan-thanh-toan?null");
        }
        else {
            userService.save(userRequest,session);
        }
        return ResponseEntity.noContent().build();
    }

}
