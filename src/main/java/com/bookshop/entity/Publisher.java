package com.bookshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publishers")
@Getter
@Setter
public class Publisher extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column
    private String address;

    @Column
    private String keyWord;

    @Column
    private String phone_number;

    @OneToMany(mappedBy = "publisher_book")
    private List<Book> books=new ArrayList<>();
}
