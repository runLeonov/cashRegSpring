package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class CheckId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public CheckId() {
    }

    @OneToMany
    @JoinColumn(name = "check_id")
    private List<CheckWithProducts> products;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CheckId{" +
                "id=" + id +
                ", products=" + products +
                '}';
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

    public CheckId(Long id) {
        this.id = id;
    }
}
