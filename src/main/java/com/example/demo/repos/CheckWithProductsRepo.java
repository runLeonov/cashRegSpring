package com.example.demo.repos;

import com.example.demo.entity.CheckWithProducts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckWithProductsRepo extends CrudRepository<CheckWithProducts, Long> {
    List<CheckWithProducts> findCheckWithProductsByCheckId(Long id);
}
