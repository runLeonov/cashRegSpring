package com.example.demo.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CheckWithProducts {
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
    @Column(
            nullable = false,
            name = "prod_id"
    )
    private Long prodId;
    @Column(
            name = "date_time",
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime dateTime;

    public CheckWithProducts() {
    }

    public CheckWithProducts(Long checkId,
                             String nameOfProd,
                             Double weight,
                             Double price,
                             Long prodId,
                             LocalDateTime dateTime) {
        this.checkId = checkId;
        this.nameOfProd = nameOfProd;
        this.weight = weight;
        this.price = price;
        this.prodId = prodId;
        this.dateTime = dateTime;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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
        return "CheckWithProducts{" +
                "id=" + id +
                ", checkId=" + checkId +
                ", nameOfProd='" + nameOfProd + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", prodId=" + prodId +
                ", dateTime=" + dateTime +
                '}';
    }
}
