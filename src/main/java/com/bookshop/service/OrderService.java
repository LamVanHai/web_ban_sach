package com.bookshop.service;

import com.bookshop.dto.request.OrderRequest;
import com.bookshop.dto.response.OrderResponse;
import com.bookshop.entity.Order;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface OrderService {

    Order save(OrderRequest orderRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    OrderRequest save1(OrderRequest orderRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    List<OrderResponse> findAllByStatus(int status, Pageable pageable);

    List<OrderResponse> findAllByUserId(long id, Pageable pageable);

    int countByStatus(int status);

    int countByUserId(long id);

    OrderRequest findOneById(Long id);

    void delete(Long id, int status);

    OrderRequest confirmOrder(OrderRequest orderRequest);

    List<OrderResponse> findAllByKeyWord(int status, String keyWord, Pageable pageable);

    int getTotalKeyWord(int status, String keyWord);
}
