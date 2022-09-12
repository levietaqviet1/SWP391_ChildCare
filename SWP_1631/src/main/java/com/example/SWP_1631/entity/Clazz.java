package com.example.SWP_1631.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "clazz")
public class Clazz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int clazzId;

    @Column(name = "class_name")
    private String className;

    @Column(name = "grade")
    private int grade;

    @Column(name = "class_description")
    private String classDescription;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "clazz_account",
            joinColumns = @JoinColumn(name = "clazz_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<Account> account;

}
