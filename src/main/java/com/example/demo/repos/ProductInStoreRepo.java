package com.example.demo.repos;

import com.example.demo.entity.ProductInStore;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductInStoreRepo extends CrudRepository<ProductInStore, Long> {
    ProductInStore findById(long id);
    @Modifying
    @Query("UPDATE ProductInStore p SET p.priceForOne = :price, p.weight = :weight WHERE p.id = :id")
    void updateProductInStore(
            @Param(value = "price")Double price,
            @Param(value = "weight")Double weight,
            @Param(value = "id") Long id);

    void deleteProductInStoreById(@Param(value = "id") Long id);


}
