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

    public boolean addProductCheck(ProductInStore product, Double weight) {
        return weight < product.getWeight();
    }

    public ProductInStore findById(long id) {
        return repo.findById(id);
    }

    public void updateProductById(long id, Double price, Double weight) {
        ProductInStore product = repo.findById(id);
        product.setWeight(weight);
        product.setPriceForOne(price);
        repo.save(product);
    }

    public void deleteProductInStoreById(long id) {
        ProductInStore product = repo.findById(id);
        repo.delete(product);
    }

    public void updateWeightInStore(long id, Double weight) {
        ProductInStore p = repo.findById(id);
        p.setWeight(p.getWeight() - weight);
        repo.save(p);
    }
}
