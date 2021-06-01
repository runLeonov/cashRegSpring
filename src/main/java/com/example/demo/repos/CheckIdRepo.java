package com.example.demo.repos;

import com.example.demo.entity.CheckId;
import org.springframework.data.repository.CrudRepository;

public interface CheckIdRepo extends CrudRepository<CheckId, Long> {
    CheckId findById(long id);
    CheckId getFirstByOrderByIdDesc();
    boolean existsById(long id);



}
