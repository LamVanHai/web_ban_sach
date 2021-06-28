package com.bookshop.controller.web;

import com.bookshop.dto.request.UserRequest;
import com.bookshop.dto.response.OrderResponse;
import com.bookshop.dto.response.PageRespone;
import com.bookshop.dto.response.UserResponse;
import com.bookshop.entity.Order_detail;
import com.bookshop.service.*;
import com.bookshop.service.other.Message;
import com.bookshop.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class User_detailController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Message message;

    @Autowired
    private Order_detailService order_detailService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    @RequestMapping(value = "/nguoi-dung/thong-tin-chi-tiet",method = RequestMethod.GET)
    public ModelAndView chitietnguoidung(@ModelAttribute UserRequest userRequest, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("web/user_details/detail");
        userRequest.setId(SecurityUtils.getPrincipal().getId());
        UserResponse user=userService.findById(userRequest.getId());
        modelAndView.addObject("model",user);
        modelAndView.addObject("province",provinceService.findAll());
        modelAndView.addObject("district",districtService.findById(user.getProvince_id()));
        modelAndView.addObject("ward",wardService.findAllByDistrict_id(user.getDistrict_id()));
        message.listMessage(request,modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/nguoi-dung/doi-mat-khau",method = RequestMethod.GET)
    public ModelAndView doimatkhau(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("web/user_details/doimatkhau");
        message.listMessage(request,modelAndView);
        return modelAndView;
    }
    @RequestMapping(value = "/nguoi-dung/thong-tin-giao-dich",method = RequestMethod.GET)
    public ModelAndView findByAllStatus( @RequestParam("page") int page){
        ModelAndView modelAndView=new ModelAndView("web/user_details/thongtingiaodich");
        PageRespone pageRespone=new PageRespone();
        pageRespone.setLimit(5);
        pageRespone.setPage(page);
        pageRespone.setTotalItem(orderService.countByUserId(SecurityUtils.getPrincipal().getId()));
        pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem()/5));
        Pageable pageable=new PageRequest(page-1,5);
        List<OrderResponse> orderResponses=orderService.findAllByUserId(SecurityUtils.getPrincipal().getId(),pageable);
        modelAndView.addObject("page", pageRespone);
        modelAndView.addObject("model", orderResponses);
        return  modelAndView;
    }
    @RequestMapping(value = "/nguoi-dung/thong-tin-giao-dich/chi-tiet-don-hang",method = RequestMethod.GET)
    public ModelAndView chiTietDonHang(@RequestParam(value = "id")  Long id,
                                       @RequestParam("page") int page,
                                       @RequestParam("limit") int limit){
        ModelAndView modelAndView=new ModelAndView("web/user_details/thongtingiaodichchitiet");
        PageRespone pageRespone=new PageRespone();
        pageRespone.setLimit(limit);
        pageRespone.setPage(page);
        pageRespone.setTotalItem(order_detailService.countByOrder_id(id));
        pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem()/limit));
        Pageable pageable=new PageRequest(page-1,limit);
        List<Order_detail> order_details=order_detailService.findAllByOrder_id(id,pageable);

        modelAndView.addObject("page", pageRespone);
        modelAndView.addObject("model", order_details);
        return modelAndView;
    }

}
