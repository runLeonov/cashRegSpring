package com.example.demo.service;


import com.example.demo.repos.CheckWithProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckWithProductsService {
    @Autowired
    CheckWithProductsRepo repo;

}
