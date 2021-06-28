package com.bookshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column
    private String keyWord;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Book> books=new ArrayList<>();
}
