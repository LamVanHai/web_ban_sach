package com.bookshop.controller.web;

import com.bookshop.dto.GioHang;
import com.bookshop.dto.request.AddCart;
import com.bookshop.dto.response.BookResponse;
import com.bookshop.dto.response.HomeResponse;
import com.bookshop.dto.response.PageRespone;
import com.bookshop.entity.Category;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import com.bookshop.service.PublisherService;
import com.bookshop.service.UserService;
import com.bookshop.util.MessageUtil;
import com.bookshop.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService ;

    @Autowired
    private UserService userService;

    @Autowired
    private AddCart addCart;

    @RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage(HttpSession session,@RequestParam(value = "category", required = false) String category,
                                 @RequestParam(value = "search", required = false) String search
    ) {
        ModelAndView mav=null;
        List<BookResponse> bookResponses;
        HashMap<Long, GioHang> cart = (HashMap<Long, GioHang>) session.getAttribute("Cart");
        int item=0;
        if(cart!=null){
            item=cart.size();
        }
        HomeResponse homeResponse=new HomeResponse();
        homeResponse.setKy_nang_song(bookService.findAllKy_Nang_Song());
        homeResponse.setNewBook(bookService.findNewBook());
        homeResponse.setTop8(bookService.findAllTop8Next());
        homeResponse.setSachBanChay(bookService.findAllTop8Next());
        homeResponse.setSachGiamGia(bookService.findAllTop8Next());
        homeResponse.setFindByTopDiscount(bookService.findByTopDiscount());
        PageRespone pageRespone = new PageRespone();
        List<Category> categories = categoryService.findAllBy();
        int page=1;
        int limit=16;
        pageRespone.setPage(page);
        pageRespone.setLimit(limit);
        Pageable pageable = new PageRequest(page - 1, limit);
        if (category != null) {
            pageRespone.setTotalItem(bookService.countCaterory(category));
            pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));
            homeResponse.setCategories(bookService.findByCategory(category,pageable));
            mav=new ModelAndView("web/homedetails");
            mav.addObject("listCategory", categories);
            mav.addObject("category", category);
            mav.addObject("bookresponse",homeResponse);
            mav.addObject("page", pageRespone);
            mav.addObject("publisher",publisherService.findAllList());
            return mav;

        } else if (search != null) {
            pageRespone.setTotalItem(bookService.countByLikeNameBook(search));
            pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));
            homeResponse.setCategories(bookService.findByName(category,pageable));
            mav=new ModelAndView("web/homedetails");
            mav.addObject("listCategory", categories);
            mav.addObject("category", category);
            mav.addObject("bookresponse",homeResponse);
            mav.addObject("page", pageRespone);
            mav.addObject("publisher",publisherService.findAllList());
            return mav;
        }
        mav= new ModelAndView("web/home");
        mav.addObject("listCategory", categories);
        mav.addObject("bookResponse",homeResponse);
        mav.addObject("cartItem",item);
        return mav;
    }

    @RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
    public ModelAndView singinPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/registers/register");
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        return mav;
    }

    @RequestMapping(value = "/thoat", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUtils.role="not";
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/trang-chu");
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        return new ModelAndView("redirect:/dang-nhap?accessDenied");
    }

    @GetMapping(value = "/create_customer")
    public ModelAndView addCustomer(){
        return new ModelAndView("web/registers/success");
    }

    @GetMapping(value = "/api/userverify")
    public void enableUser(@RequestParam("code") String code,HttpServletResponse response) throws IOException {
        userService.updateEnable(code);
        response.sendRedirect("/dang-nhap");
    }

    @GetMapping(value = "/gioi-thieu")
    public ModelAndView gioiThieu(){
        return new ModelAndView("web/gioithieu");
    }

}
