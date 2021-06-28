package com.bookshop.controller.web;

import com.bookshop.dto.response.HomeResponse;
import com.bookshop.dto.response.PageRespone;
import com.bookshop.entity.Category;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import com.bookshop.service.PublisherService;
import com.bookshop.service.WriterService;
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
public class BookDiscountController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WriterService writerService;

    @Autowired
    private PublisherService publisherService;
    @RequestMapping(value = "/san-pham-giam-gia", method = RequestMethod.GET)
    public ModelAndView giamGia(@RequestParam int page){
        ModelAndView modelAndView=new ModelAndView("web/giamgia");
        List<Category> categories = categoryService.findAllBy();
        PageRespone pageRespone = new PageRespone();
        pageRespone.setPage(page);
        pageRespone.setLimit(12);
        Pageable pageable = new PageRequest(page - 1, 12);
        HomeResponse homeResponse = new HomeResponse();
        homeResponse.setCategories(bookService.findByDiscount(pageable));
        pageRespone.setTotalItem(bookService.countByDiscount());
        homeResponse.setFindByTopDiscount(bookService.findByTopDiscount());
        pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));
        modelAndView.addObject("model",homeResponse);
        modelAndView.addObject("page", pageRespone);
        modelAndView.addObject("publisher", publisherService.findAllList());
        modelAndView.addObject("listCategory", categories);
        modelAndView.addObject("writer",writerService.findAll());
        return modelAndView;
    }
}
