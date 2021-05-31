package com.example.demo.service;

import com.example.demo.entity.ProductInStore;
import com.example.demo.repos.ProductInStoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductsInStoreService {
    @Autowired
    ProductInStoreRepo repo;

    public void addProductToStore(String name, Double price, Double weight) {
        repo.save(new ProductInStore(name, price, weight));
    }

    public void updateProductById(long id, Double price, Double weight) {
        repo.updateProductInStore(price, weight, id);
    }

    public void  deleteProductInStoreById(long id) {
        repo.deleteProductInStoreById(id);
    }
}
