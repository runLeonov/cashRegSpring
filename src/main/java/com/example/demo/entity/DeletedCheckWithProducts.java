package com.example.demo.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DeletedCheckWithProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            name = "check_id"
    )
    private Long checkId;
    @Column(
            nullable = false,
            name = "name_of_product",
            columnDefinition = "TEXT"
    )
    private String nameOfProd;
    @Column(
            nullable = false,
            name = "weight"
    )
    private Double weight;
    @Column(
            nullable = false,
            name = "price"
    )
    private Double price;
    @Column
    private LocalDateTime timeOfCreate;
    @Column
    private LocalDateTime timeOfDeleted;

    public LocalDateTime getTimeOfDeleted() {
        return timeOfDeleted;
    }

    public void setTimeOfDeleted(LocalDateTime timeOfDeleted) {
        this.timeOfDeleted = timeOfDeleted;
    }

    public LocalDateTime getTimeOfCreate() {
        return timeOfCreate;
    }

    public void setTimeOfCreate(LocalDateTime timeOfCreate) {
        this.timeOfCreate = timeOfCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public String getNameOfProd() {
        return nameOfProd;
    }

    public void setNameOfProd(String nameOfProd) {
        this.nameOfProd = nameOfProd;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DeletedCheckWithProducts{" +
                "id=" + id +
                ", checkId=" + checkId +
                ", nameOfProd='" + nameOfProd + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }

    public DeletedCheckWithProducts() {
    }

    public DeletedCheckWithProducts(Long checkId,
                                    String nameOfProd,
                                    Double weight,
                                    Double price,
                                    LocalDateTime timeOfCreate,
                                    LocalDateTime timeOfDeleted
    ) {
        this.checkId = checkId;
        this.nameOfProd = nameOfProd;
        this.weight = weight;
        this.price = price;
        this.timeOfCreate = timeOfCreate;
        this.timeOfDeleted = timeOfDeleted;
    }
}
