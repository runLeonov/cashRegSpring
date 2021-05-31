package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductInStore implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            nullable = false,
            name = "name_of_product",
            columnDefinition = "TEXT"
    )
    private String nameOfProduct;
    @Column(
            nullable = false,
            name = "price_for_one"
    )
    private Double priceForOne;
    @Column(
            nullable = false,
            name = "weight"
    )
    private Double weight;


    public ProductInStore(Long id, String nameOfProduct, Double priceForOne, Double weight) {
        this.id = id;
        this.nameOfProduct = nameOfProduct;
        this.priceForOne = priceForOne;
        this.weight = weight;
    }

    public ProductInStore(String nameOfProduct, Double priceForOne, Double weight) {
        this.nameOfProduct = nameOfProduct;
        this.priceForOne = priceForOne;
        this.weight = weight;
    }

    public ProductInStore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public Double getPriceForOne() {
        return priceForOne;
    }

    public void setPriceForOne(Double priceForOne) {
        this.priceForOne = priceForOne;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
