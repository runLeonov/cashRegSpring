package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class CheckID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public CheckID() {
    }

    @OneToMany
    @JoinColumn(name = "check_id")
    private List<CheckWithProducts> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CheckWithProducts> getProducts() {
        return products;
    }

    public void setProducts(List<CheckWithProducts> products) {
        this.products = products;
    }

    public CheckID(Long id) {
        this.id = id;
    }
}
