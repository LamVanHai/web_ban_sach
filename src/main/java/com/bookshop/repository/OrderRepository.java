package com.bookshop.repository;

import com.bookshop.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o from Order o where o.status=:status")
    List<Order> findAllByStatus(@Param("status") int status, Pageable pageable);

    @Query("select count(o.id) from Order o where o.status=:status")
    int countByStatus(@Param("status") int status);

    @Query("select e from Order  e where e.status=?2 and concat(e.user_order.name,e.user_order.phone_number,e.payment_info) like %?1%")
    List<Order> findByKeyWord(String keyWord, int status, Pageable pageable);

    @Query("select e from Order  e where e.status=?2 and concat(e.user_order.name,e.user_order.phone_number,e.payment_info) like %?1%")
    List<Order> countByKeyWord(String keyWord, int status);

    @Query("select o from Order o where o.user_order.id=:id")
    List<Order> findAllByUserId(@Param("id") long Id, Pageable pageable);

    @Query("select o from Order o where o.user_order.id=:id")
    List<Order> countByUserId(@Param("id") long Id);

}
