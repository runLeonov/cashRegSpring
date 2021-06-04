package com.example.demo.service;


import com.example.demo.entity.CheckWithProducts;
import com.example.demo.entity.ProductInStore;
import com.example.demo.repos.CheckIdRepo;
import com.example.demo.repos.CheckWithProductsRepo;
import com.example.demo.repos.ProductInStoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CheckWithProductsService {
    @Autowired
    ProductInStoreRepo repoStore;
    @Autowired
    CheckWithProductsRepo repoCheck;
    @Autowired
    CheckIdRepo checkIdRepo;

    @Transactional
    public void createCheck(Iterable<ProductInStore> products) {
        long id = checkIdRepo.getFirstByOrderByIdDesc().getId();
        LocalDateTime time = LocalDateTime.now();
        products.forEach(p -> repoCheck.save(
                new CheckWithProducts(
                        id,
                        p.getNameOfProduct(),
                        p.getWeight(),
                        p.getPriceForOne(),
                        p.getId(),
                        time)
                )
        );
    }


}
