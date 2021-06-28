package com.bookshop.controller.admin;

import com.bookshop.dto.ThongKe;
import com.bookshop.dto.request.BookRequest;
import com.bookshop.dto.response.BookResponse;
import com.bookshop.dto.response.PageRespone;
import com.bookshop.dto.response.ThongKeResponse;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import com.bookshop.service.PublisherService;
import com.bookshop.service.WriterService;
import com.bookshop.service.other.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller(value = "newControllerOfAdmin")
public class BookController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Message message;

    @Autowired
    private BookService bookService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private WriterService writerService;

    @RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
    public ModelAndView showList(@RequestParam("page") int page,
                                 HttpServletRequest request,@RequestParam(value = "search",required = false) String search) {
        PageRespone pageRespone = new PageRespone();
        pageRespone.setLimit(5);
        pageRespone.setPage(page);
        ModelAndView mav = new ModelAndView("admin/sanpham/list");
        Pageable pageable = new PageRequest(page - 1, 5);
        List<BookResponse> bookResponses;
        if(!search.equals("all")){
            bookResponses = bookService.findByName(search,pageable);
            pageRespone.setTotalItem(bookService.countByLikeNameBook(search));
            pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));

        }
        else {
            bookResponses = bookService.findAll(pageable);
            pageRespone.setTotalItem(bookService.getTotalItem());
            pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));
        }

        message.listMessage(request,mav);
        mav.addObject("page", pageRespone);
        mav.addObject("model", bookResponses);
        mav.addObject("search",search);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
    public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ModelAndView mav = new ModelAndView("admin/sanpham/edit");
        BookRequest bookRequest = new BookRequest();
        if (id != null) {
            bookRequest = bookService.findOneById(id);
        }
        message.listMessage(request,mav);
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("publisher",publisherService.findAll());
        mav.addObject("writer",writerService.findAllByName());
        mav.addObject("model", bookRequest);
        return mav;

    }

    @RequestMapping(value = "quan-tri/thong-ke",method = RequestMethod.GET)
    public ModelAndView thongKe(@RequestParam int page, HttpSession session){
        ModelAndView modelAndView=new ModelAndView("admin/thongke/thongke");
        PageRespone pageRespone = new PageRespone();
        pageRespone.setLimit(10);
        pageRespone.setPage(page);
        Pageable pageable = new PageRequest(page - 1, 10);
        pageRespone.setTotalItem(bookService.getTotalItem());
        pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));
        List<ThongKeResponse> thongKeResponses=bookService.list();
        ThongKe thongKe;
        int total_input_amount=0;
        int total_output_amount=0;
        long total_input_price=0;
        long total_output_price=0;
        long revenue=0;
        for (ThongKeResponse response: thongKeResponses
             ) {
            total_input_amount+=response.getInput_amount();
            total_output_amount+=response.getOutput_amount();
            total_input_price+=response.getTotal_input_price();
            total_output_price+=response.getTotal_output_price();
            revenue+=response.getRevenue();
        }
        thongKe=new ThongKe(total_input_amount,total_output_amount,total_input_price,total_output_price,revenue);
        modelAndView.addObject("item",thongKe);
        modelAndView.addObject("model",bookService.list(pageable));
        modelAndView.addObject("page", pageRespone);
        return modelAndView;
    }
}
