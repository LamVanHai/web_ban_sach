package com.bookshop.service;

import com.bookshop.dto.request.Order_detail_Request;
import com.bookshop.entity.Order_detail;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Order_detailService {

    List<Order_detail> findAllByOrder_id(Long id, Pageable pageable);

    int countByOrder_id(Long id);

    void delete(Long[] ids);

    Order_detail findById(long id);

    Order_detail findByOrderidAndBookid(Long order_id, Long book_id);

    List<Order_detail> findByBookId(Long id);

    List<Order_detail> findByOrder_id(Long id);

    Order_detail save(Order_detail_Request order_detail_request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
