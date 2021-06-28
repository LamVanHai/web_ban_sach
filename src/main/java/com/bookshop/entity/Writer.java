package com.bookshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "writers")
@Getter
@Setter
public class Writer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String keyWord;

    @Column
    private String address;

    @Column(columnDefinition = "longtext")
    private String biography;

    @Column
    private String phone;

    @ManyToMany(mappedBy = "writers")
    private List<Book> books=new ArrayList<>();
}
