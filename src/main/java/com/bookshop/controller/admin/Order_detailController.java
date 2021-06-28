package com.bookshop.controller.admin;

import com.bookshop.entity.Order_detail;
import com.bookshop.service.Order_detailService;
import com.bookshop.service.other.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Order_detailController {

    @Autowired
    private Order_detailService order_detailService;

    @Autowired
    private Message message;

    @RequestMapping(value = "/quan-tri/don-hang/chi-tiet-don-hang/chinh-sua",method = RequestMethod.GET)
    public ModelAndView edit_Order_detail(@RequestParam(value = "id") Long id, HttpServletRequest request){
        ModelAndView mav=new ModelAndView("admin/donhang/edit");
        Order_detail order_detail=new Order_detail();
        if (id != null) {
            order_detail = order_detailService.findById(id);
        }
        message.listMessage(request,mav);
        mav.addObject("model", order_detail);
        return mav;
    }
}
