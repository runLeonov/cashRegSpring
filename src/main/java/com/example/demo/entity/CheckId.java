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

    @OneToMany
    @JoinColumn(name = "check_id")
    private List<DeletedCheckWithProducts> deletedCheckWithProducts;



    public CheckId(Long id) {
        this.id = id;
    }

    public CheckId(List<CheckWithProducts> products) {
        this.products = products;
    }

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

    public List<DeletedCheckWithProducts> getDeletedCheckWithProducts() {
        return deletedCheckWithProducts;
    }

    public void setDeletedCheckWithProducts(List<DeletedCheckWithProducts> deletedCheckWithProducts) {
        this.deletedCheckWithProducts = deletedCheckWithProducts;
    }

    @Override
    public String toString() {
        return "CheckId{" +
                "id=" + id +
                ", products=" + products.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckId checkId = (CheckId) o;

        return id != null ? id.equals(checkId.id) : checkId.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
