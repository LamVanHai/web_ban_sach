package com.bookshop.service.impl;

import com.bookshop.dto.request.Order_detail_Request;
import com.bookshop.entity.Order_detail;
import com.bookshop.repository.Order_detailRepository;
import com.bookshop.service.Order_detailService;
import com.bookshop.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class Order_detailServiceImpl implements Order_detailService {

    @Autowired
    private Order_detailRepository order_detailRepository;

    @Override
    public List<Order_detail> findAllByOrder_id(Long id, Pageable pageable) {
        List<Order_detail> order_details=order_detailRepository.findAllByOrderId(id,pageable);
        return order_details;
    }

    @Override
    public int countByOrder_id(Long id) {
        List<Order_detail> order_details=order_detailRepository.findAllByOrderIdNoPage(id);
        return order_details.size();
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for(long i:ids){
            order_detailRepository.delete(i);
        }
    }

    @Override
    public Order_detail findById(long id) {
        Order_detail order_detail=order_detailRepository.findOne(id);
        return order_detail;
    }

    @Override
    public Order_detail findByOrderidAndBookid(Long order_id, Long book_id) {
        Order_detail order_detail=order_detailRepository.findByOrderidAndBookid(order_id,book_id);
        return order_detail;
    }

    @Override
    public List<Order_detail> findByBookId(Long id) {
        return order_detailRepository.findAllByBook_id(id);
    }

    @Override
    public List<Order_detail> findByOrder_id(Long id) {
        return order_detailRepository.findAllByOrderId(id);
    }

    @Override
    @Transactional
    public Order_detail save(Order_detail_Request order_detail_request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Order_detail order_detail=new Order_detail();
        if(order_detail_request.getId()!=null){
            Order_detail order_detail1Old=order_detailRepository.findOne(order_detail_request.getId());
            ReflectionUtil.mapper(order_detail_request,order_detail1Old);
            order_detail=order_detail1Old;
        }
        else {
            ReflectionUtil.mapper(order_detail_request,order_detail);
        }
        return order_detailRepository.save(order_detail);
    }
}
