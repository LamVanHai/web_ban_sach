package com.bookshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_groups")
@Getter
@Setter
public class User_group extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    private String name;

    @Column
    private Float percent;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "user_group")
    private List<User> users=new ArrayList<>();
}
