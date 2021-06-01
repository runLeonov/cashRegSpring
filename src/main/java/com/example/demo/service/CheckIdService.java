package com.example.demo.service;

import com.example.demo.repos.CheckIdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CheckIdService {
    @Autowired
    CheckIdRepo checkIdRepo;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void incrementCheckId() {
        entityManager.createNativeQuery("INSERT INTO check_id VALUES ()").executeUpdate();
    }

    public Long getFirstByOrderByIdDesc() {
        return checkIdRepo.getFirstByOrderByIdDesc().getId();
    }


}
