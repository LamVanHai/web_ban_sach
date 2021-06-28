package com.bookshop.controller.web;

import com.bookshop.dto.request.AddCart;
import com.bookshop.dto.response.BookResponse;
import com.bookshop.service.BookService;
import com.bookshop.util.Viewed_productsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Book_detailController {

    @Autowired
    private BookService bookService;

    @Autowired
    private Viewed_productsUtil viewed_productsUtil;

    @RequestMapping(value = "/trang-chu/chi-tiet-san-pham", method = RequestMethod.GET)
    public ModelAndView bookDetail(@RequestParam(value = "id") long id, HttpSession session) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ModelAndView modelAndView = new ModelAndView("web/chitietsanpham");
        BookResponse bookResponse = bookService.findById(id);
        if(bookResponse.getSale_price()==null){
            bookResponse.setSale_price((long) 0);
        }
        bookResponse.setPrice1(bookResponse.getPrice()/100*(100-bookResponse.getSale_price()));
        List<BookResponse> books = (List<BookResponse>) session.getAttribute("viewed_detail");
        books = viewed_productsUtil.addList(books, id);
        List<BookResponse> bookResponses = new ArrayList<>();
        if (books.size() > 6) {
            for (int i = 0; i < 6; i++) {
                bookResponses.add(books.get(i));
            }
            session.setAttribute("viewed_detail", bookResponses);
        } else {
            session.setAttribute("viewed_detail", books);
        }

        modelAndView.addObject("model1", bookResponse);
        return modelAndView;
    }

    @RequestMapping(value = "/trang-chu/chi-tiet-san-pham1", method = RequestMethod.GET)
    public ModelAndView bookDetail1(@RequestBody AddCart addCart) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ModelAndView modelAndView = new ModelAndView("web/chitietsanpham");
        BookResponse bookResponse = bookService.findById((long) addCart.getId());
        modelAndView.addObject("model1", bookResponse);
        return modelAndView;
    }
}
