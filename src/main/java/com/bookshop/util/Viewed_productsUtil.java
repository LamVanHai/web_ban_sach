package com.bookshop.util;

import com.bookshop.dto.response.BookResponse;
import com.bookshop.entity.Book;
import com.bookshop.mapper.BookMapper;
import com.bookshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Viewed_productsUtil {

    @Autowired
    private BookRepository bookRepository;

    public List<BookResponse> addList(List<BookResponse> bookList, Long id){
        Book book=bookRepository.findOne(id);
        if (bookList==null){
            bookList=new ArrayList<>();
        }
        BookResponse bookResponse= BookMapper.mapToResponse(book);
        if(bookList!=null) {
            for (BookResponse bookResponse1 : bookList) {
                if (bookResponse1.getId() == bookResponse.getId()) {
                    return bookList;
                }
            }
            bookList.add(bookResponse);
        }
        return bookList;
    }
}
