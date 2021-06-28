package com.bookshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="roles")
@Getter
@Setter
public class Role extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false,unique = true)
    private String name;
    @Column
    private String description;

    @ManyToMany(mappedBy = "roles_user")
    private Set<User> userSet=new HashSet<>();
}
