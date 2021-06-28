package com.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provinces")
@Getter
@Setter
public class Province extends BaseEntity {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String type;

    @OneToMany(mappedBy = "province")
    @JsonIgnore
    private List<District> districts=new ArrayList<>();

//    @OneToMany(mappedBy = "province1",cascade=CascadeType.ALL,orphanRemoval = true)
//    @JsonIgnore
//    private List<User> users=new ArrayList<>();

}
