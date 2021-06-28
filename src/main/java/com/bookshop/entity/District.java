package com.bookshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "districts")
@Getter
@Setter
public class District extends BaseEntity{
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String type;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province=new Province();

    @OneToMany(mappedBy = "district",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Ward> wards=new ArrayList<>();

//    @OneToMany(mappedBy = "district1",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<User> users=new ArrayList<>();

}
