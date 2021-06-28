package com.bookshop.service;

import com.bookshop.dto.request.BookRequest;
import com.bookshop.dto.response.BookResponse;
import com.bookshop.dto.response.ThongKeResponse;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface BookService {

    List<BookResponse> findAll(Pageable pageable);

    List<BookResponse> findByKeyWord(String keyWord, Pageable pageable);

    int getTotalItem();

    BookRequest save(BookRequest bookRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    BookRequest findOneById(Long id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    void delete(long[] ids);

    List<BookResponse> findAllKy_Nang_Song();

    List<BookResponse> findAllTop8Next();

    List<BookResponse> findByCategory(String name, Pageable pageable);

    List<BookResponse> findByPublisher(String name, Pageable pageable);

    List<BookResponse> findNewBook();

    List<BookResponse> findByWriter(String name, Pageable pageable);

    int countPublisher(String name);

    int countByLikeNameBook(String name);

    int countCaterory(String name);

    BookResponse findById(Long id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    List<BookResponse> findByName(String name, Pageable pageable);

    int countByAmount(Long id);

    List<BookResponse> findByDiscount(Pageable pageable);

    List<BookResponse> findByTopDiscount();

    int countByDiscount();

    List<ThongKeResponse> list(Pageable pageable);

    List<ThongKeResponse> list();

}
