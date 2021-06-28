package com.bookshop.api.web;

import com.bookshop.dto.GioHang;
import com.bookshop.dto.request.SaveCart;
import com.bookshop.dto.response.UserResponse;
import com.bookshop.entity.Ward;
import com.bookshop.service.DistrictService;
import com.bookshop.service.GioHangService;
import com.bookshop.service.ProvinceService;
import com.bookshop.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ChangeAPI {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    @Autowired
    private GioHangService gioHangService;

    @PostMapping("/api/province")
    private void chageProvince(@RequestBody UserResponse userResponse, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String> stringMap = districtService.findById(userResponse.getProvince_id());
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<option>-- Chọn quận/huyện--</option>");
        for (Map.Entry<String, String> stringMap1 : stringMap.entrySet()
        ) {
            printWriter.println("<option value=\""+stringMap1.getKey()+"\">"+stringMap1.getValue()+"</option>");

        }

    }

    @PostMapping("/api/district")
    private void chageDistrict(@RequestBody UserResponse userResponse,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String> stringMap = wardService.findAllByDistrict_id(userResponse.getDistrict_id());
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<option>-- Chọn xã/phường--</option>");
        for (Map.Entry<String, String> stringMap1 : stringMap.entrySet()
        ) {
            printWriter.println("<option value=\""+stringMap1.getKey()+"\">"+stringMap1.getValue()+"</option>");

        }
    }

    @PostMapping("/api/ward")
    private SaveCart chageWard(@RequestBody UserResponse userResponse, HttpSession session) {
        SaveCart saveCart=new SaveCart();
        HashMap<Long, GioHang> cart = (HashMap<Long, GioHang>) session.getAttribute("Cart");
        int total= gioHangService.totalPrice(cart);
        Ward ward=wardService.findByTransport_fee(userResponse.getWard_id());
        saveCart.setTransport_fee(ward.getTransport_fee());
        saveCart.setTotal(saveCart.getTransport_fee()+total);
        session.setAttribute("transport_fee",ward.getTransport_fee());
        return saveCart;
    }
}
