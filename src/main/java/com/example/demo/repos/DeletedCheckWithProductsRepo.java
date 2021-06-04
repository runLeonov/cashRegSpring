package com.example.demo.repos;

import com.example.demo.entity.DeletedCheckWithProducts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedCheckWithProductsRepo extends CrudRepository<DeletedCheckWithProducts, Long> {

}
