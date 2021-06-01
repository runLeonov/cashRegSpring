package com.example.demo.service;

import com.example.demo.entity.CheckId;
import com.example.demo.repos.CheckIdRepo;
import com.example.demo.repos.CheckWithProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CheckIdService {
    @Autowired
    CheckIdRepo checkIdRepo;
    @Autowired
    CheckWithProductsRepo checkWithProductsRepo;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void incrementCheckId() {
        entityManager.createNativeQuery("INSERT INTO check_id VALUES ()").executeUpdate();
    }

    public Long getFirstByOrderByIdDesc() {
        return checkIdRepo.getFirstByOrderByIdDesc().getId();
    }

    public CheckId findById(long id) {
        return checkIdRepo.findById(id);
    }

    public CheckId findLastCheck() {
        return checkIdRepo.getFirstByOrderByIdDesc();
    }

    public void deleteCheck(CheckId checkId) {
        if (checkId != null) {
            checkWithProductsRepo.deleteAll(checkId.getProducts());
            checkIdRepo.delete(checkId);
        }

    }

    public void saveCheck(CheckId check) {
        checkIdRepo.save(check);
    }

    public boolean existsCheck(long id){
        return checkIdRepo.existsById(id);
    }


    public String getTimeOfCreate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(dateTime);
    }
}
