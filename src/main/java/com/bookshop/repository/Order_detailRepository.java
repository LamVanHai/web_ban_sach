package com.bookshop.repository;

import com.bookshop.entity.Order_detail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Order_detailRepository extends JpaRepository<Order_detail,Long> {

    @Query("select e FROM Order_detail e where e.book3.id=:id")
    List<Order_detail> findAllByBook_id(@Param("id") Long id);

    @Query("select e FROM Order_detail e where e.order3.id=:id")
    List<Order_detail> findAllByOrderId(@Param("id") Long id, Pageable pageable);

    @Query("select e FROM Order_detail e where e.order3.id=:id")
    List<Order_detail> findAllByOrderId(@Param("id") Long id);

    @Query("select e FROM Order_detail e where e.order3.id=:id")
    List<Order_detail> findAllByOrderIdNoPage(@Param("id") Long id);

    @Query("select  e from Order_detail e where e.order3.id=:order_id AND e.book3.id=:book_id")
    Order_detail findByOrderidAndBookid(@Param("order_id") Long order_id, @Param("book_id") Long book_id);
}
