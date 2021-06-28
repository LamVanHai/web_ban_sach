package com.bookshop.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeResponse {
    private List<BookResponse> newBook;
    private List<BookResponse> ky_nang_song;
    private List<BookResponse> top8;
    private List<BookResponse> sachBanChay;
    private List<BookResponse> sachGiamGia;
    private List<BookResponse> writers;
    private List<BookResponse> publishers;
    private List<BookResponse> categories;
    private List<BookResponse> search;
    private List<BookResponse> findByTopDiscount;



}
