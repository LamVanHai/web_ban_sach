package com.bookshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "wards")
@Getter
@Setter
public class Ward extends BaseEntity{
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Long transport_fee;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district=new District();

//    @OneToMany(mappedBy = "ward")
//    private List<User> users=new ArrayList<>();

}
