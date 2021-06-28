package com.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    @Column
    private String address;

    @Column(unique = true)
    private String phone_number;

    @Column
    private String sex;

    @Column
    private LocalDate birthday;

    @Column
    private Integer enable;

    @Column
    private String verification_Code;

    @Column
    private String province_id;

    @Column
    private String district_id;

    @Column
    private String ward_id;

    @OneToMany(mappedBy = "user_comment")
    @JsonIgnore
    private List<Comment> comments=new ArrayList<>();

    @OneToMany(mappedBy = "user_order")
    @JsonIgnore
    private List<Order> orders=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "group_id")
    private User_group user_group=new User_group();

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "permission"
            ,joinColumns = @JoinColumn(name = "user_id", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "role_id",nullable = false))
    private List<Role> roles_user=new ArrayList<>();

//    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
//    @JoinColumn(name = "district_id")
//    private District district1=new District();

//    @ManyToOne
//    @JoinColumn(name = "province_id")
//    @Fetch(FetchMode.SELECT)
//    private Province province1=new Province();

//    @ManyToOne
//    @JoinColumn(name = "ward_id")
//    @Fetch(FetchMode.SELECT)
//    private Ward ward=new Ward();


}
