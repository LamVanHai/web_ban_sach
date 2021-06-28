package com.bookshop.controller.admin;

import com.bookshop.dto.response.OrderResponse;
import com.bookshop.dto.response.PageRespone;
import com.bookshop.entity.Order_detail;
import com.bookshop.service.OrderService;
import com.bookshop.service.Order_detailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Order_detailService order_detailService;

    @RequestMapping(value = "/quan-tri/don-hang",method = RequestMethod.GET)
    public ModelAndView findByAllStatus(@RequestParam(value = "status")  int status, @RequestParam("page") int page,
                                        @RequestParam(value = "search",required = false)String search){
        ModelAndView modelAndView=new ModelAndView("admin/donhang/list");
        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setLimit(5);
        orderResponse.setPage(page);
        Pageable pageable=new PageRequest(page-1,5);
        if(search.equals("all")){
            orderResponse.setTotalItem(orderService.countByStatus(status));
            orderResponse.setTotalPage((int) Math.ceil((double) orderResponse.getTotalItem()/5));
            orderResponse.setData(orderService.findAllByStatus(status,pageable));
        }else {
            orderResponse.setTotalItem(orderService.getTotalKeyWord(status,search));
            orderResponse.setTotalPage((int) Math.ceil((double) orderResponse.getTotalItem()/5));
            orderResponse.setData(orderService.findAllByKeyWord(status,search,pageable));
        }
        modelAndView.addObject("model", orderResponse);
        modelAndView.addObject("search", search);
        modelAndView.addObject("status1",status);
        return  modelAndView;
    }

    @RequestMapping(value = "/quan-tri/don-hang/chi-tiet-don-hang",method = RequestMethod.GET)
    public ModelAndView chiTietDonHang(@RequestParam(value = "id")  Long id,
                                       @RequestParam("page") int page
                                       ){
        ModelAndView modelAndView=new ModelAndView("admin/donhang/details");
        PageRespone pageRespone=new PageRespone();
        pageRespone.setLimit(5);
        pageRespone.setPage(page);
        pageRespone.setTotalItem(order_detailService.countByOrder_id(id));
        pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem()/5));
        Pageable pageable=new PageRequest(page-1,5);
        List<Order_detail> order_details=order_detailService.findAllByOrder_id(id,pageable);

        modelAndView.addObject("page", pageRespone);
        modelAndView.addObject("model", order_details);
        return modelAndView;
    }

}
