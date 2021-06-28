package com.bookshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column
    private String keyWord;

    @Column
    private String image;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    private Long price;

    @Column
    private Long input_price;

    @Column
    private Long sale_price;

    @Column
    private Integer amount;

    @Column
    private Integer input_amount;


    @ManyToMany
    @JoinTable(name = "books_writers"
            ,joinColumns = @JoinColumn(name = "book_id",nullable = false)
            ,inverseJoinColumns = @JoinColumn(name = "writer_id",nullable = false)
    )
    private Set<Writer> writers=new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category=new Category();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher_book=new Publisher();

    @OneToMany(mappedBy = "book_comment")
    private List<Comment> comments=new ArrayList<>();


    @OneToMany(mappedBy = "book3")
    private List<Order_detail> order_details=new ArrayList<>();
}
