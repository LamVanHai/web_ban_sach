package com.bookshop.service.impl;

import com.bookshop.dto.request.OrderRequest;
import com.bookshop.dto.response.OrderResponse;
import com.bookshop.entity.Book;
import com.bookshop.entity.Order;
import com.bookshop.entity.Order_detail;
import com.bookshop.mapper.OrderMapper;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.OrderRepository;
import com.bookshop.repository.Order_detailRepository;
import com.bookshop.service.BookService;
import com.bookshop.service.OrderService;
import com.bookshop.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Order_detailRepository order_detailRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public Order save(OrderRequest orderRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Order order=new Order();
        if (orderRequest.getId()!=null){
            order=orderRepository.findOne(orderRequest.getId());
        }
        ReflectionUtil.mapper(orderRequest,order);
        return orderRepository.save(order);
    }

    @Override
    public OrderRequest save1(OrderRequest orderRequest) {
        Order order=new Order();
        if(orderRequest.getId()!=null){
            order = orderRepository.findOne(orderRequest.getId());
            if(orderRequest.getStatus()==0){
                order.setStatus(3);
                List<Order_detail> order_details=order_detailRepository.findAllByOrderId(orderRequest.getId());
                Book book;
                for (Order_detail detail: order_details
                ) {
                    book=bookRepository.findOne(detail.getBook3().getId());
                    book.setAmount(book.getAmount()+detail.getAmount());
                    bookRepository.save(book);
                }
            }
        }
        orderRepository.save(order);
        return orderRequest;
    }


    @Override
    public List<OrderResponse> findAllByStatus(int status, Pageable pageable) {
        List<Order> orders=orderRepository.findAllByStatus(status,pageable);
        List<OrderResponse> orderResponses=orders.stream().map(OrderMapper::mapToResponse).collect(Collectors.toList());
        return orderResponses;
    }

    @Override
    public List<OrderResponse> findAllByUserId(long id, Pageable pageable) {
        List<Order> order=orderRepository.findAllByUserId(id,pageable);
        List<OrderResponse> orderResponses=order.stream().map(OrderMapper::mapToResponse).collect(Collectors.toList());
        return orderResponses;
    }

    @Override
    public int countByStatus(int status) {
        return orderRepository.countByStatus(status);
    }

    @Override
    public int countByUserId(long id) {
        List<Order> orders=orderRepository.countByUserId(id);
        return orders.size();
    }

    @Override
    public OrderRequest findOneById(Long id) {
        Order order=orderRepository.findOne(id);
        OrderRequest orderRequest=new OrderRequest();
        try {
            ReflectionUtil.mapper(order,orderRequest);
        } catch (NoSuchMethodException |IllegalAccessException |InvocationTargetException e) {
            e.printStackTrace();
        }
        return orderRequest;
    }

    @Override
    @Transactional
    public void delete(Long id,int status) {
        Order order=orderRepository.findOne(id);
        if (status==0){
            order.setStatus(3);
            List<Order_detail> order_details=order_detailRepository.findAllByOrderId(id);
            Book book;
            for (Order_detail detail: order_details
                 ) {
                book=bookRepository.findOne(detail.getBook3().getId());
                book.setAmount(book.getAmount()+detail.getAmount());
                bookRepository.save(book);
            }
        }
        if (status==1){
            order.setStatus(0);
        }

        orderRepository.save(order);
    }

    @Override
    public OrderRequest confirmOrder(OrderRequest request) {
        int status=request.getStatus();
        OrderRequest orderRequest=orderService.findOneById(request.getId());
        if(status==0){
            orderRequest.setStatus(1);
        }else if(status==1) {
            orderRequest.setStatus(2);
        }
        try {
            orderService.save(orderRequest);
        } catch (NoSuchMethodException |IllegalAccessException |InvocationTargetException e) {
            e.printStackTrace();
        }
        return orderRequest;
    }

    @Override
    public List<OrderResponse> findAllByKeyWord(int status, String keyWord, Pageable pageable) {
        List<Order> orders=orderRepository.findByKeyWord(keyWord,status,pageable);
        List<OrderResponse> orderResponses=orders.stream().map(OrderMapper::mapToResponse).collect(Collectors.toList());
        return orderResponses;
    }

    @Override
    public int getTotalKeyWord(int status, String keyWord) {
        List<Order> orders=orderRepository.countByKeyWord(keyWord,status);
        return orders.size();
    }
}
