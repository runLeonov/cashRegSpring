package com.example.demo.entity;


import javax.persistence.*;

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
}
