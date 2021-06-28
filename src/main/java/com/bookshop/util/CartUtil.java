package com.bookshop.util;

import com.bookshop.dto.GioHang;
import com.bookshop.entity.Book;
import com.bookshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CartUtil {

    @Autowired
    private BookRepository bookRepository;

    public HashMap<Long,GioHang> addCart(Long id,int quanty, HashMap<Long,GioHang> cart){
        GioHang gioHang=new GioHang();
        Book book=bookRepository.findOneById(id);
        if(book.getSale_price()==null){
            book.setSale_price((long) 0);
        }
        if(book!=null&&cart.containsKey(id)){
            gioHang=cart.get(id);
            gioHang.setQuanty(gioHang.getQuanty()+quanty);

            gioHang.setTotal_price(book.getPrice()/100*(100-book.getSale_price())*gioHang.getQuanty());
        }else {
            gioHang.setBook(book);
            gioHang.setQuanty(quanty);
            gioHang.setTotal_price(book.getPrice()/100*(100-book.getSale_price())*gioHang.getQuanty());
        }
        cart.put(id,gioHang);
        return cart;
    }

    public HashMap<Long,GioHang> editCart(Long id,int quanty, HashMap<Long,GioHang> cart){
        if(cart==null){
            return cart;
        }
        GioHang gioHang=new GioHang();
        if(cart.containsKey(id)){
            gioHang=cart.get(id);
            gioHang.setQuanty(quanty);
            if(gioHang.getBook().getSale_price()==null){
                gioHang.getBook().setSale_price((long) 0);
            }
            gioHang.setTotal_price(quanty*gioHang.getBook().getPrice()/100*(100-gioHang.getBook().getSale_price()));
        }
        cart.put(id,gioHang);
        return cart;
    }

    public HashMap<Long,GioHang> deleteCart(Long id,HashMap<Long,GioHang> cart){
        if(cart==null){
            return cart;
        }
        if(cart.containsKey(id)){
            cart.remove(id);
        }
        return cart;
    }

    public int totalQuanty(HashMap<Long,GioHang> cart){
        int totalQuanty=0;
        for(Map.Entry<Long,GioHang> giohang:cart.entrySet()){
            totalQuanty+= giohang.getValue().getQuanty();
        }
        return totalQuanty;
    }

    public int totalPrice(HashMap<Long,GioHang> cart){
        int totalPrice=0;
        for(Map.Entry<Long,GioHang> giohang:cart.entrySet()){
            totalPrice+= giohang.getValue().getTotal_price();
        }
        return totalPrice;
    }
}
