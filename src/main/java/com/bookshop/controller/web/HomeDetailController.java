package com.bookshop.controller.web;

import com.bookshop.dto.request.AddCart;
import com.bookshop.dto.response.BookResponse;
import com.bookshop.dto.response.HomeResponse;
import com.bookshop.dto.response.PageRespone;
import com.bookshop.entity.Category;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import com.bookshop.service.PublisherService;
import com.bookshop.service.WriterService;
import com.bookshop.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeDetailController {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private AddCart addCart;

    @Autowired
    private WriterService writerService;

    @RequestMapping(value = "/trang-chu/chi-tiet", method = RequestMethod.GET)
    public ModelAndView homePage(@RequestParam(value = "category", required = false) String category,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "writer", required = false) String writer,
                                 @RequestParam(value = "publisher", required = false) String publisher,
                                 @RequestParam(value = "viewed_product", required = false) String viewed_product,
                                 @RequestParam(value = "page", required = false) int page,
                                 HttpSession session
    ) {
        ModelAndView mav = new ModelAndView("web/homedetails");
        PageRespone pageRespone = new PageRespone();
        List<Category> categories = categoryService.findAllBy();
        pageRespone.setPage(page);
        pageRespone.setLimit(12);
        Pageable pageable = new PageRequest(page - 1, 12);
        HomeResponse homeResponse = new HomeResponse();
        homeResponse.setFindByTopDiscount(bookService.findByTopDiscount());
        if (category != null) {
            pageRespone.setTotalItem(bookService.countCaterory(category));
            pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));
            homeResponse.setCategories(bookService.findByCategory(category, pageable));
            mav.addObject("queryString", "category");
            mav.addObject("queryStringValue", category);

        } else if (search != null) {
            if (search.equals("all")) {
                pageRespone.setTotalItem(bookService.getTotalItem());
                pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));
                homeResponse.setCategories(bookService.findAll(pageable));
                mav.addObject("queryString", "search");
                mav.addObject("queryStringValue", "all");
            } else {
                pageRespone.setTotalItem(bookService.countByLikeNameBook(search));
                pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));
                homeResponse.setCategories(bookService.findByName(search, pageable));
                mav.addObject("queryString", "search");
                mav.addObject("search",search);
                mav.addObject("queryStringValue", search);

            }
        } else if (publisher != null) {
            pageRespone.setTotalItem(bookService.countPublisher(publisher));
            pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));
            homeResponse.setCategories(bookService.findByPublisher(publisher, pageable));
            mav.addObject("queryString", "publisher");
            mav.addObject("queryStringValue", publisher);
        }
        else if (writer != null) {
            pageRespone.setTotalItem(bookService.countPublisher(publisher));
            pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));
            homeResponse.setCategories(bookService.findByPublisher(publisher, pageable));
            mav.addObject("queryString", "publisher");
            mav.addObject("queryStringValue", publisher);
        }
        else if (viewed_product.equals("all")) {
            pageRespone.setTotalItem(bookService.countPublisher(publisher));
            List<BookResponse> books = (List<BookResponse>) session.getAttribute("viewed_detail");
            pageRespone.setTotalPage((int) Math.ceil((double) books.size() / pageRespone.getLimit()));
            homeResponse.setCategories(books);
            mav.addObject("queryString", "viewed_product");
            mav.addObject("queryStringValue", viewed_product);
        }

        mav.addObject("listCategory", categories);
        mav.addObject("category", category);
        mav.addObject("homeResponse", homeResponse);
        mav.addObject("page", pageRespone);
        mav.addObject("publisher", publisherService.findAllList());
        mav.addObject("writer",writerService.findAll());

        return mav;
    }
}
