package com.example.demo.repos;

import com.example.demo.entity.ProductInStore;
import org.springframework.data.repository.CrudRepository;

public interface ProductInStoreRepo extends CrudRepository<ProductInStore, Long> {
    ProductInStore findById(long id);

    ProductInStore findByNameOfProduct(String name);
}
