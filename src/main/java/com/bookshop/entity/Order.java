package com.bookshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    private String note;

    @Column
    private Integer status;

    @Column
    private String payment_info;

    @Column
    private Long price_total;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_order = new User();

    @OneToMany(mappedBy = "order3")
    private List<Order_detail> order_details = new ArrayList<>();
}
