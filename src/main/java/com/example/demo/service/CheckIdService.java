package com.example.demo.service;

import com.example.demo.entity.CheckId;
import com.example.demo.entity.CheckWithProducts;
import com.example.demo.entity.DeletedCheckWithProducts;
import com.example.demo.repos.CheckIdRepo;
import com.example.demo.repos.CheckWithProductsRepo;
import com.example.demo.repos.DeletedCheckWithProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CheckIdService {
    @Autowired
    CheckIdRepo checkIdRepo;
    @Autowired
    CheckWithProductsRepo checkWithProductsRepo;
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    DeletedCheckWithProductsRepo deletedCheckWithProductsRepo;

    @Transactional
    public void incrementCheckId() {
        entityManager.createNativeQuery("INSERT INTO check_id VALUES ()").executeUpdate();
    }

    public CheckId findById(long id) {
        return checkIdRepo.findById(id);
    }

    public CheckId findLastCheck() {
        return checkIdRepo.getFirstByOrderByIdDesc();
    }

    public CheckWithProducts findLastCheckWithProds() {
        return checkWithProductsRepo.getFirstByOrderByCheckIdDesc();
    }

    public void deleteCheck(CheckId checkId) {
        if (Objects.nonNull(checkId)) {
            checkWithProductsRepo.deleteAll(checkId.getProducts());
            deletedCheckWithProductsRepo.saveAll(createDeletedCheck(checkId));
        }

    }

    public String getTimeOfCreate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(dateTime);
    }

    @Transactional
    List<DeletedCheckWithProducts> createDeletedCheck(CheckId checkId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<DeletedCheckWithProducts> deletedCheckWithProducts = new ArrayList<>();
        List<CheckWithProducts> productInStoreList = checkId.getProducts();
        productInStoreList.forEach(
                x -> deletedCheckWithProducts.add(
                        new DeletedCheckWithProducts(
                                x.getCheckId(),
                                x.getNameOfProd(),
                                x.getWeight(),
                                x.getPrice(),
                                x.getDateTime(),
                                localDateTime
                        )
                )
        );
        return deletedCheckWithProducts;
    }
}
