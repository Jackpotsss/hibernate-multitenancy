package com.example.hibernate.entity;

import javax.persistence.*;

/**
 * 定义一个租户实体类，用于记录租户信息
 */
@Entity
@Table(name = "customer")
public class Customer {

    public Customer(String name) {
        this.name = name;
    }

    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
