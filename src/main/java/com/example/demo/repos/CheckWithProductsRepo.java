package com.example.demo.repos;

import com.example.demo.entity.CheckWithProducts;
import com.example.demo.entity.ProductInStore;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CheckWithProductsRepo extends CrudRepository<CheckWithProducts, Long> {
    List<CheckWithProducts> findCheckWithProductsByCheckId(Long id);



}
