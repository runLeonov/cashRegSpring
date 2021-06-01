package com.example.demo.service;


import com.example.demo.entity.CheckWithProducts;
import com.example.demo.entity.ProductInStore;
import com.example.demo.repos.CheckIdRepo;
import com.example.demo.repos.CheckWithProductsRepo;
import com.example.demo.repos.ProductInStoreRepo;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CheckWithProductsService {
    @Autowired
    ProductInStoreRepo repoStore;
    @Autowired
    CheckWithProductsRepo repoCheck;
    @Autowired
    CheckIdRepo checkIdRepo;
    @PersistenceContext
    private EntityManager entityManager;

    public ProductInStore addProdToCheck(long id, String name, Double weight) {
        if (!name.equals("")) {
            ProductInStore product = repoStore.findByNameOfProduct(name);
            product.setWeight(weight);
            product.setPriceForOne(weight * product.getPriceForOne());
            return product;
        } else {
            ProductInStore product = repoStore.findById(id);
            product.setWeight(weight);
            product.setPriceForOne(weight * product.getPriceForOne());
            return product;
        }
    }

    public ProductInStore findById(long id) {
        return repoStore.findById(id);
    }

    public ProductInStore findByName(String name) {
        return repoStore.findByNameOfProduct(name);
    }

    @Transactional
    public void createCheck(@NotNull List<ProductInStore> products) {
        long id = checkIdRepo.getFirstByOrderByIdDesc().getId();
        for (ProductInStore p : products) {
            repoCheck.save(
                    new CheckWithProducts(
                            id,
                            p.getNameOfProduct(),
                            p.getWeight(),
                            p.getPriceForOne(),
                            p.getId(),
                            LocalDateTime.now())
            );
        }
    }

    private void createCheck(Long id, ProductInStore product) {
        entityManager.createNativeQuery(
                "INSERT INTO check_with_products (check_id, prod_id, name_of_product, price, weight) VALUES (?,?,?,?,?)")
                .setParameter(1, id)
                .setParameter(2, product.getId())
                .setParameter(3, product.getNameOfProduct())
                .setParameter(4, product.getPriceForOne())
                .setParameter(5, product.getWeight())
                .executeUpdate();
    }
}
