package com.bookshop.service.impl;

import com.bookshop.constant.MyConstants;
import com.bookshop.dto.GioHang;
import com.bookshop.dto.request.AddCart;
import com.bookshop.dto.request.OrderRequest;
import com.bookshop.dto.request.Order_detail_Request;
import com.bookshop.dto.request.UserRequest;
import com.bookshop.dto.response.CartResponse;
import com.bookshop.entity.*;
import com.bookshop.repository.*;
import com.bookshop.service.*;
import com.bookshop.util.CheckUtil;
import com.bookshop.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserService userService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private Order_detailService order_detailService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CheckUtil checkUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private DistrictRepository districtRepository;

    public CartResponse saveCart(HttpSession session, AddCart addCart) {
        HashMap<Long, GioHang> cart = (HashMap<Long, GioHang>) session.getAttribute("Cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        int soluongconlai = bookService.countByAmount(addCart.getId());
        int soluongtronggio = 0;
        if (cart != null) {
            for (Map.Entry<Long, GioHang> giohang : cart.entrySet()) {
                if (giohang.getKey() == addCart.getId()) {
                    soluongtronggio = cart.get(addCart.getId()).getQuanty();
                }
            }
        }
        if (addCart.getQuanty() == 0) {
            addCart.setQuanty(0);
        }
        if (addCart.getQuanty() == 1) {
            if (soluongconlai - soluongtronggio - addCart.getQuanty() <= 0) {
                return new CartResponse("Sách đã hết hàng\n vui lòng chọn sách khác", cart.size());
            }
        }
        if (soluongconlai - soluongtronggio - addCart.getQuanty() <= 0) {
            return new CartResponse("Không đủ số lượng trong kho\n SỐ lượng còn lại là: " + soluongconlai, cart.size());
        }
        gioHangService.addCart(addCart.getId(), addCart.getQuanty(), cart);
        session.setAttribute("Cart", cart);
        session.setAttribute("CartTotalItem", gioHangService.totalQuanty(cart));
        session.setAttribute("CartTotalPrice", gioHangService.totalPrice(cart));

        return new CartResponse("Sách đã được thêm vào giỏ hàng ", cart.size());
    }

    @Override
    public Long editCart(HttpSession session, AddCart addCart) {
        HashMap<Long, GioHang> cart = (HashMap<Long, GioHang>) session.getAttribute("Cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        int soluongconlai = bookService.countByAmount(addCart.getId());
        if (cart.get(addCart.getId()).getQuanty() + addCart.getQuanty() > soluongconlai) {
            addCart.setQuanty(soluongconlai);
        }
        gioHangService.editCart(addCart.getId(), addCart.getQuanty(), cart);
        session.setAttribute("Cart", cart);
        session.setAttribute("CartTotalItem", gioHangService.totalQuanty(cart));
        session.setAttribute("CartTotalPrice", gioHangService.totalPrice(cart));
        return addCart.getId();
    }

    @Override
    public Long deleteCart(HttpSession session, AddCart addCart) {
        HashMap<Long, GioHang> cart = (HashMap<Long, GioHang>) session.getAttribute("Cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        gioHangService.deleteCart(addCart.getId(), cart);
        session.setAttribute("Cart", cart);
        session.setAttribute("CartTotalItem", gioHangService.totalQuanty(cart));
        session.setAttribute("CartTotalPrice", gioHangService.totalPrice(cart));
        return addCart.getId();
    }

    @Override
    @Transactional
    public void saveOrder(UserRequest userRequest, HttpServletResponse response, HttpSession session) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        HashMap<Long, GioHang> cart = (HashMap<Long, GioHang>) session.getAttribute("Cart");
        userRequest.setId(SecurityUtils.getPrincipal().getId());
        User user = userRepository.findOne(userRequest.getId());
        OrderRequest order = new OrderRequest();
        order.setPayment_info("Thanh toán khi nhận hàng");
        order.setStatus(0);
        order.setUser_order(user);
        Long transport_fee = (Long) session.getAttribute("transport_fee");
        if (userRequest.getNote()==null){
            order.setNote("không");
        }else {
            order.setNote(userRequest.getNote());
        }
        order.setPrice_total((long) gioHangService.totalPrice(cart) + transport_fee);
        Order order1 = orderService.save(order);
        int i = 0;
        int k = cart.size();
        try {
            for (Map.Entry<Long, GioHang> map : cart.entrySet()) {
                Order_detail_Request order_detail = new Order_detail_Request();
                Book book = bookRepository.findOne(map.getValue().getBook().getId());
                if (book.getAmount() >= map.getValue().getQuanty()) {
                    i++;
                }
            }
            if (i < k) {
                response.sendRedirect("/gio-hang/xac-nhan-thanh-toan/xac-nhan-don-hang?book");
            } else {
                for (Map.Entry<Long, GioHang> map : cart.entrySet()) {
                    Order_detail_Request order_detail = new Order_detail_Request();
                    Book book = bookRepository.findOne(map.getValue().getBook().getId());
                    order_detail.setAmount(map.getValue().getQuanty());
                    order_detail.setBook3(book);
                    order_detail.setOrder3(order1);
                    order_detail.setUnit_price(map.getValue().getTotal_price());
                    Order_detail order_detail1 = order_detailService.save(order_detail);
                    if (order_detail1 != null) {
                        book.setAmount(book.getAmount() - order_detail.getAmount());
                        bookRepository.save(book);
                    }
                }
                cart.clear();
                session.setAttribute("CartTotalItem", 0);
                session.setAttribute("CartTotalPrice", 0);
                response.sendRedirect("/gio-hang/xac-nhan-thanh-toan/xac-nhan-don-hang?success");
            }
        } catch (Exception e) {
        }
    }

    @Override
    @Transactional
    public void saveUser(UserRequest userRequest, HttpServletResponse response, String path) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        userRequest.setId(SecurityUtils.getPrincipal().getId());
        if (userRequest.getName() == "" || userRequest.getPhone_number() == "" || userRequest.getAddress() == "" || userRequest.getDistrict_id() == "" || userRequest.getProvince_id() == "" || userRequest.getWard_id() == "") {
            if (userRequest.getName() == "" || userRequest.getPhone_number() == "" || userRequest.getAddress() == "") {
                response.sendRedirect(path+"?nullDetails");
            } else if (userRequest.getDistrict_id() == "" || userRequest.getProvince_id() == "" || userRequest.getWard_id() == "") {
                response.sendRedirect(path+"?nullArea");
            }

        } else {
            Pattern pattern=Pattern.compile(MyConstants.PHONE_PATTERN);
            if(!pattern.matcher(userRequest.getPhone_number()).find()){
                response.sendRedirect(path+"?phonevalidate");
            }
            else if (checkUtil.checkPhone(userRequest.getPhone_number()) && !checkUtil.checkPhonedetail(userRequest.getPhone_number(), userRequest.getId())) {
                response.sendRedirect(path+"?phone");
            } else {
                District district=districtRepository.findOne(userRequest.getDistrict_id());
                Ward ward=wardRepository.findOne(userRequest.getWard_id());
                Province province=provinceRepository.findOne(userRequest.getProvince_id());
                if(district!=null&&ward!=null&&province!=null) {
                    String address = userRequest.getAddress() + ", " + ward.getName() + " - " + district.getName() + " - " + province.getName();
                    userRequest.setAddress(address);
                }
                userService.saveUser(userRequest, response,path);
//                response.sendRedirect("/gio-hang/xac-nhan-thanh-toan/xac-nhan-don-hang");
            }
        }

    }
}
