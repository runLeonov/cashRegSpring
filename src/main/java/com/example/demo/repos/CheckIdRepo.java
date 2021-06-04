package com.example.demo.repos;

import com.example.demo.entity.CheckId;
import com.example.demo.entity.CheckWithProducts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CheckIdRepo extends CrudRepository<CheckId, Long> {
    CheckId findById(long id);


    CheckId getFirstByOrderByIdDesc();

    boolean existsById(long id);


}
