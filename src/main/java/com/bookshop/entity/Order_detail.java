package com.bookshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
public class Order_detail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    private Integer amount;

    @Column
    private Long unit_price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order3=new Order();

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book3=new Book();
}
