package com.bookshop.dto;

import com.bookshop.entity.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GioHang {

    public GioHang() {
    }

    private int quanty;

    private Long total_price;

    private Book book;

    public GioHang(int quanty, Long total_price, Book book) {
        this.quanty = quanty;
        this.total_price = total_price;
        this.book = book;
    }


}
