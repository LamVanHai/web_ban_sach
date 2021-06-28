package com.bookshop.controller.web;

import com.bookshop.dto.GioHang;
import com.bookshop.dto.request.UserRequest;
import com.bookshop.dto.response.UserResponse;
import com.bookshop.entity.Ward;
import com.bookshop.service.*;
import com.bookshop.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class CartController {

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

//
//    @Autowired
//    private GioHangService gioHangService;
//
//    @RequestMapping(value = "/them-gio-hang",method = RequestMethod.GET)
//    public ModelAndView themGioHang(HttpSession session, @RequestParam(value = "id") Long id){
//        HashMap<Long, GioHang> cart= (HashMap<Long, GioHang>) session.getAttribute("Cart");
//        ModelAndView modelAndView=new ModelAndView("web/gioHang");
//
//        if(cart==null){
//            cart=new HashMap<>();
//        }
//        gioHangService.addCart(id,cart);
//        session.setAttribute("Cart",cart);
//        return modelAndView;
//    }
//

    @RequestMapping(value ="/gio-hang",method = RequestMethod.GET)
    private ModelAndView listCart(){
        ModelAndView modelAndView=new ModelAndView("web/orders/gioHang");
        return modelAndView;
    }
    @RequestMapping(value = "/gio-hang/xac-nhan-thanh-toan",method = RequestMethod.GET)
     private ModelAndView thanhToan(HttpSession session){
        ModelAndView mv=null;
        UserResponse userResponse;
        HashMap<Long, GioHang> cart = (HashMap<Long, GioHang>) session.getAttribute("Cart");
        if(SecurityUtils.roles().equals("not")){
            mv=new ModelAndView("login");
        }
        else if(cart==null||cart.size()==0){
            mv = new ModelAndView("web/orders/gioHang");
        }
        else{

            mv = new ModelAndView("web/orders/thanhtoan");
            userResponse=userService.findById(SecurityUtils.getPrincipal().getId());
            mv.addObject("province",provinceService.findAll());
            mv.addObject("district",districtService.findById(userResponse.getProvince_id()));
            mv.addObject("ward",wardService.findAllByDistrict_id(userResponse.getDistrict_id()));
            mv.addObject("model",userResponse);
            Ward ward=wardService.findByTransport_fee(userResponse.getWard_id());
            if(ward!=null){
                session.setAttribute("transport_fee",ward.getTransport_fee());
            }

        }
        return mv;
    }
    @RequestMapping(value ="/gio-hang/xac-nhan-thanh-toan/xac-nhan-don-hang",method = RequestMethod.GET)
    private ModelAndView xacNhan(){
        ModelAndView modelAndView=new ModelAndView("web/orders/xacnhandonhang");
        UserRequest userRequest=new UserRequest();
        modelAndView.addObject("model",userRequest);
        return modelAndView;
    }
}

